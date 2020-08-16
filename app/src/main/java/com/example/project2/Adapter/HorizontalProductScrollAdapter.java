package com.example.project2.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.project2.Model.HorizontalProductModel;
import com.example.project2.ProductDetailsActivity;
import com.example.project2.R;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    List<HorizontalProductModel> horizontalProductModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder holder, int position) {

        String resource = horizontalProductModelList.get(position).getProductImg();
        String title = horizontalProductModelList.get(position).getProductTitle();
        String des = horizontalProductModelList.get(position).getProductDes();
        String price = horizontalProductModelList.get(position).getProductPrice();

        holder.setProductData(resource,title,des,price);
    }

    @Override
    public int getItemCount() {
        if (horizontalProductModelList.size() > 8){
            return  8;
        }else{
            return horizontalProductModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle,productDes,productPrice;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.hsp_img);
            productTitle = itemView.findViewById(R.id.hsp_title);
            productDes = itemView.findViewById(R.id.hsp_des);
            productPrice = itemView.findViewById(R.id.hsp_price);
        }

        private void setProductData(String resource,String title, String des, String price){
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.imageplaceholder)).into(productImage);
            productTitle.setText(title);
            productDes.setText(des);
            productPrice.setText("\u20B9 " + price + "/-");

            if (!title.equals("")){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });
            }
        }
    }
}
