package com.example.project2.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.project2.Model.HorizontalProductModel;
import com.example.project2.ProductDetailsActivity;
import com.example.project2.R;
import com.example.project2.ShowProductActivity;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<HorizontalProductModel> horizontalProductModelList;

    public GridProductLayoutAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    @Override
    public int getCount() {
        return horizontalProductModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final View view;
        if (convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));
            ImageView productImage = view.findViewById(R.id.hsp_img);
            TextView productTitle = view.findViewById(R.id.hsp_title);
            TextView productDes = view.findViewById(R.id.hsp_des);
            TextView productPrice = view.findViewById(R.id.hsp_price);

            Glide.with(parent.getContext()).load(horizontalProductModelList.get(position).getProductImg()).apply(new RequestOptions().placeholder(R.drawable.imageplaceholder)).into(productImage);
            productTitle.setText(horizontalProductModelList.get(position).getProductTitle());
            productDes.setText(horizontalProductModelList.get(position).getProductDes());
            productPrice.setText("\u20B9 " + horizontalProductModelList.get(position).getProductPrice() + "/-");
        }else{
            view = convertView;
        }

        return view;
    }
}
