package com.example.project2.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.project2.CategoryActivity;
import com.example.project2.Model.Category;
import com.example.project2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryModelList;
    private int lastPosition = -1;

    public CategoryAdapter(List<Category> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int viewType) {

        View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.category_item,viewgroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        String Icon = categoryModelList.get(position).getCategoryIconLink();
        String Name = categoryModelList.get(position).getCategoryName();
        holder.setCategory(Name,position);
        holder.setCategoryIcon(Icon);

        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);
        }

        private void setCategoryIcon(String iconURL){
            if (!iconURL.equals("null")){
                Glide.with(itemView.getContext()).load(iconURL).apply(new RequestOptions().placeholder(R.drawable.imageplaceholder)).into(categoryIcon);
                //Picasso.get().load(iconURL).placeholder(R.drawable.imageplaceholder).into(categoryIcon);
            }else{
                categoryIcon.setImageResource(R.drawable.home);
            }
        }
        private void setCategory(final String name,final int position){
            categoryName.setText(name);
            if (!name.equals("")) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position != 0) {
                            Intent categoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                            categoryIntent.putExtra("CategoryName", name);
                            itemView.getContext().startActivity(categoryIntent);
                        }
                    }
                });
            }
        }
    }
}
