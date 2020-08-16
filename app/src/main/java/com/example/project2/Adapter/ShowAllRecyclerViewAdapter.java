package com.example.project2.Adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.project2.Model.ShowAllProductRecyclerViewModel;
import com.example.project2.ProductDetailsActivity;
import com.example.project2.R;

import java.util.List;

public class ShowAllRecyclerViewAdapter extends RecyclerView.Adapter<ShowAllRecyclerViewAdapter.ViewHolder> {

    List<ShowAllProductRecyclerViewModel> showAllProductRecyclerViewModelList;

    public ShowAllRecyclerViewAdapter(List<ShowAllProductRecyclerViewModel> showAllProductRecyclerViewModelList) {
        this.showAllProductRecyclerViewModelList = showAllProductRecyclerViewModelList;
    }

    @NonNull
    @Override
    public ShowAllRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_product_recyclerview_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllRecyclerViewAdapter.ViewHolder holder, int position) {
        String img = showAllProductRecyclerViewModelList.get(position).getProductImage();
        String title = showAllProductRecyclerViewModelList.get(position).getProductTitle();
        String price = showAllProductRecyclerViewModelList.get(position).getProductPrice();
        String cutPrice = showAllProductRecyclerViewModelList.get(position).getProductCutPrice();
        String CODStatus = showAllProductRecyclerViewModelList.get(position).getProductCODStatus();
        holder.setData(img,title,price,cutPrice,CODStatus);
    }

    @Override
    public int getItemCount() {
        return showAllProductRecyclerViewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle,productPrice,productCutPrice,productCODStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.show_all_item_image);
            productTitle = itemView.findViewById(R.id.show_all_item_title);
            productPrice = itemView.findViewById(R.id.show_all_item_price);
            productCutPrice = itemView.findViewById(R.id.show_all_item_cut_price);
            productCODStatus = itemView.findViewById(R.id.show_all_item_delivery_mode);
        }

        private void setData(String img, final String title, String price, String cutPrice, String CODStatus){
            Glide.with(itemView.getContext()).load(img).apply(new RequestOptions().placeholder(R.drawable.slider_backgroud)).into(productImage);
            productTitle.setText(title);
            productPrice.setText("\u20B9 " + price + "/-");
            productCutPrice.setText("\u20B9 " + cutPrice + "/-");
            if (CODStatus.equals("COD Not Available")){
                productCODStatus.setTextColor(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.Red)));
            }
            productCODStatus.setText(CODStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), ProductDetailsActivity.class).putExtra("title",title));
                }
            });
        }
    }
}
