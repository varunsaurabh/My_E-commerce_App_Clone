package com.example.project2.Adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.Model.MyWishlistModel;
import com.example.project2.ProductDetailsActivity;
import com.example.project2.R;

import java.util.List;

public class MyWishlistAdapter extends RecyclerView.Adapter<MyWishlistAdapter.ViewHolder>  {

    List<MyWishlistModel> myWishlistModelList;

    public MyWishlistAdapter(List<MyWishlistModel> myWishlistModelList) {
        this.myWishlistModelList = myWishlistModelList;
    }

    @NonNull
    @Override
    public MyWishlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyWishlistAdapter.ViewHolder holder, int position) {
        int img = myWishlistModelList.get(position).getItemImage();
        String title = myWishlistModelList.get(position).getItemTitle();
        String price = myWishlistModelList.get(position).getItemPrice();
        String cutPrice = myWishlistModelList.get(position).getItemCutPrice();
        String COD = myWishlistModelList.get(position).getItemCOD();
        holder.setWishListData(img,title,price,cutPrice,COD);
    }

    @Override
    public int getItemCount() {
        return myWishlistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemTitle,itemPrice,itemCutPrice,itemCOD;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.wish_item_image);
            itemTitle = itemView.findViewById(R.id.wish_item_title);
            itemPrice = itemView.findViewById(R.id.wish_item_price);
            itemCutPrice = itemView.findViewById(R.id.wish_item_cut_price);
            itemCOD = itemView.findViewById(R.id.wish_item_delivery);
        }
        private void setWishListData(int img, final String title, String price, String cutPrice, String COD){
            itemImage.setImageResource(img);
            itemTitle.setText(title);
            itemPrice.setText(price);
            itemCutPrice.setText(cutPrice);
            if (COD.equals("COD Not Available")){
                itemCOD.setTextColor(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.Red)));
            }
            itemCOD.setText(COD);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), ProductDetailsActivity.class).putExtra("title",title));
                }
            });
        }
    }
}
