package com.example.project2.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.project2.Model.HomepageModel;
import com.example.project2.Model.HorizontalProductModel;
import com.example.project2.Model.ShowAllProductRecyclerViewModel;
import com.example.project2.Model.Slider;
import com.example.project2.ProductDetailsActivity;
import com.example.project2.R;
import com.example.project2.ShowProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomepageAdapter extends RecyclerView.Adapter {
    private List<HomepageModel> homepageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private int lastPosition = -1;

    public HomepageAdapter(List<HomepageModel> homepageModelList) {
        this.homepageModelList = homepageModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public int getItemViewType(int position) {
        switch (homepageModelList.get(position).getType()) {
            case 0:
                return HomepageModel.BANNER_SLIDER;
            case 1:
                return HomepageModel.STRIP_AD_BANNER;
            case 2:
                return HomepageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomepageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {

            case HomepageModel.BANNER_SLIDER:
                View bannerSliderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_add_banner, parent, false);
                return new BannerSliderViewHolder(bannerSliderView);
            case HomepageModel.STRIP_AD_BANNER:
                View stripAdView = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_add_layout, parent, false);
                return new StripAdBannerViewHolder(stripAdView);
            case HomepageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new HorizontalProductViewHolder(horizontalProductView);
            case HomepageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout, parent, false);
                return new GridProductViewHolder(gridProductView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (homepageModelList.get(position).getType()) {
            case HomepageModel.BANNER_SLIDER:
                List<Slider> sliderModelList = homepageModelList.get(position).getSliderModelList();
                ((BannerSliderViewHolder) holder).setBannerSliderViewPager(sliderModelList);
                break;

            case HomepageModel.STRIP_AD_BANNER:
                String resource = homepageModelList.get(position).getResource();
                String color = homepageModelList.get(position).getBackgroundColor();
                ((StripAdBannerViewHolder) holder).setStripAd(resource, color);
                break;

            case HomepageModel.HORIZONTAL_PRODUCT_VIEW:
                String hTitle = homepageModelList.get(position).getTitle();
                List<HorizontalProductModel> horizontalProductModelList = homepageModelList.get(position).getHorizontalProductModelList();
                List<ShowAllProductRecyclerViewModel> showAllProductRecyclerViewModelList = homepageModelList.get(position).getShowAllProductRecyclerViewModelList();
                ((HorizontalProductViewHolder) holder).setHorizontalProductLayout(horizontalProductModelList, hTitle, showAllProductRecyclerViewModelList);
                break;

            case HomepageModel.GRID_PRODUCT_VIEW:
                String gTitle = homepageModelList.get(position).getTitle();
                List<HorizontalProductModel> gridProductModelList = homepageModelList.get(position).getHorizontalProductModelList();
                ((GridProductViewHolder) holder).setGridProductLayout(gridProductModelList, gTitle);
                break;

            default:
                return;
        }
        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return homepageModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {

        private ViewPager banner_slider_viewpager;
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<Slider> arrangedList;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            banner_slider_viewpager = itemView.findViewById(R.id.bannerSliderViewPager);
        }

        private void setBannerSliderViewPager(final List<Slider> sliderModelList) {
            currentPage = 2;
            if (timer != null) {
                timer.cancel();
            }
            arrangedList = new ArrayList<>();
            for (int x = 0; x < sliderModelList.size(); x++) {
                arrangedList.add(x, sliderModelList.get(x));
            }
            arrangedList.add(0, sliderModelList.get(sliderModelList.size() - 2));
            arrangedList.add(1, sliderModelList.get(sliderModelList.size() - 1));
            arrangedList.add(sliderModelList.get(0));
            arrangedList.add(sliderModelList.get(1));


            SliderAdapter sliderAdapter = new SliderAdapter(arrangedList);
            banner_slider_viewpager.setAdapter(sliderAdapter);
            banner_slider_viewpager.setClipToPadding(false);
            banner_slider_viewpager.setPageMargin(20);

            banner_slider_viewpager.setCurrentItem(currentPage);

            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }

                @Override
                public void onPageScrollStateChanged(int i) {
                    if (i == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangedList);
                    }
                }
            };
            banner_slider_viewpager.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(arrangedList);

            banner_slider_viewpager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(arrangedList);
                    stopBannerSlideShow();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(arrangedList);
                    }
                    return false;
                }
            });

        }

        private void pageLooper(List<Slider> sliderModelList) {
            if (currentPage == sliderModelList.size() - 2) {
                currentPage = 2;
                banner_slider_viewpager.setCurrentItem(currentPage, false);
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size() - 3;
                banner_slider_viewpager.setCurrentItem(currentPage, false);
            }
        }

        private void startBannerSlideShow(final List<Slider> sliderModelList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage >= sliderModelList.size()) {
                        currentPage = 1;
                    }
                    banner_slider_viewpager.setCurrentItem(currentPage++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopBannerSlideShow() {
            timer.cancel();
        }
    }

    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView stripAdImage;
        private ConstraintLayout strip_ad_container;

        public StripAdBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            stripAdImage = itemView.findViewById(R.id.strip_add_image);
            strip_ad_container = itemView.findViewById(R.id.strip_add_container);
        }

        private void setStripAd(String resource, String color) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.imageplaceholder)).into(stripAdImage);
            //Picasso.get().load(resource).placeholder(R.drawable.corner_round).into(stripAdImage);
            stripAdImage.setBackgroundColor(Color.parseColor(color));
        }
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {

        private TextView horizontalScrollLayoutTitle;
        private Button horizontalScrollViewAll;
        private RecyclerView horizontalRecyclerview;

        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontalScrollLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizontalScrollViewAll = itemView.findViewById(R.id.horizontal_scoll_layout_viewall_button);
            horizontalRecyclerview = itemView.findViewById(R.id.horizontal_scroll_layouyt_recyclerview);
            horizontalRecyclerview.setRecycledViewPool(recycledViewPool);
        }

        private void setHorizontalProductLayout(List<HorizontalProductModel> horizontalProductModelList, final String title, final List<ShowAllProductRecyclerViewModel> showAllProductRecyclerViewModelList) {

            horizontalScrollLayoutTitle.setText(title);
            if (horizontalProductModelList.size() > 8) {
                horizontalScrollViewAll.setVisibility(View.VISIBLE);
                horizontalScrollViewAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowProductActivity.showAllProductRecyclerViewModelList = showAllProductRecyclerViewModelList;
                        itemView.getContext().startActivity(new Intent(itemView.getContext(), ShowProductActivity.class).putExtra("layout_code", 0).putExtra("title", title));
                    }
                });
            } else {
                horizontalScrollViewAll.setVisibility(View.INVISIBLE);
            }
            HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductModelList);
            LinearLayoutManager horizontalLinearLayoutManager = new LinearLayoutManager(itemView.getContext());
            horizontalLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            horizontalRecyclerview.setLayoutManager(horizontalLinearLayoutManager);
            horizontalRecyclerview.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();
        }

    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder {
        private TextView gridLayoutTitle;
        private Button gridLayoutViewAll;
        private GridLayout gridView;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
            gridLayoutTitle = itemView.findViewById(R.id.gpl_title);
            gridLayoutViewAll = itemView.findViewById(R.id.gpl_viewall);
            gridView = itemView.findViewById(R.id.gpl_gridview);
        }

        private void setGridProductLayout(final List<HorizontalProductModel> horizontalProductModelList, final String title) {
            gridLayoutTitle.setText(title);

            for (int x = 0; x < 4; x++) {
                ImageView productImage = gridView.getChildAt(x).findViewById(R.id.hsp_img);
                final TextView productTitle = gridView.getChildAt(x).findViewById(R.id.hsp_title);
                TextView productDes = gridView.getChildAt(x).findViewById(R.id.hsp_des);
                TextView productPrice = gridView.getChildAt(x).findViewById(R.id.hsp_price);

                Glide.with(itemView.getContext()).load(horizontalProductModelList.get(x).getProductImg()).apply(new RequestOptions().placeholder(R.drawable.imageplaceholder)).into(productImage);
                productTitle.setText(horizontalProductModelList.get(x).getProductTitle());
                productDes.setText(horizontalProductModelList.get(x).getProductDes());
                productPrice.setText("\u20B9 " + horizontalProductModelList.get(x).getProductPrice() + "/-");

                gridView.getChildAt(x).setBackgroundColor(Color.parseColor("#ffffff"));
                if (!title.equals("")) {
                    gridView.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            itemView.getContext().startActivity(new Intent(itemView.getContext(), ProductDetailsActivity.class).putExtra("title", productTitle.getText().toString()));
                       /* ShowProductActivity.horizontalProductModelList = horizontalProductModelList;
                        itemView.getContext().startActivity(new Intent(itemView.getContext(),ShowProductActivity.class).putExtra("layout_code",1)
                                .putExtra("title",title));*/
                        }
                    });
                }
            }
            if (!title.equals("")) {
                gridLayoutViewAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowProductActivity.horizontalProductModelList = horizontalProductModelList;
                        itemView.getContext().startActivity(new Intent(itemView.getContext(), ShowProductActivity.class).putExtra("layout_code", 1)
                                .putExtra("title", title));
                    }
                });
            }
        }
    }
}
