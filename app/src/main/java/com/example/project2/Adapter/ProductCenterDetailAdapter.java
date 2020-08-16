package com.example.project2.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.Model.ProductCenterDetailModel;
import com.example.project2.R;

import java.util.List;

public class ProductCenterDetailAdapter extends RecyclerView.Adapter<ProductCenterDetailAdapter.ViewHolder> {

    private List<ProductCenterDetailModel> productCenterDetailModelList;

    public ProductCenterDetailAdapter(List<ProductCenterDetailModel> productCenterDetailModelList) {
        this.productCenterDetailModelList = productCenterDetailModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (productCenterDetailModelList.get(position).getType()) {
            case 0:
                return ProductCenterDetailModel.SPECIFICATION_TITLE;
            case 1:
                return ProductCenterDetailModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ProductCenterDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ProductCenterDetailModel.SPECIFICATION_TITLE:
                TextView title = new TextView(parent.getContext());
                title.setTypeface(null,Typeface.NORMAL);
                title.setTextColor(Color.parseColor("#000000"));
                title.setTextSize(setDp(7,parent.getContext()));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setDp(16, parent.getContext()), setDp(8, parent.getContext()), setDp(8, parent.getContext()), setDp(8, parent.getContext()));
                title.setLayoutParams(layoutParams);
                return new ViewHolder(title);

            case ProductCenterDetailModel.SPECIFICATION_BODY:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_details_center_tab_details_layout, parent, false);
                return new ViewHolder(view);

            default:
                return null;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull ProductCenterDetailAdapter.ViewHolder holder, int position) {

        switch (productCenterDetailModelList.get(position).getType()) {
            case ProductCenterDetailModel.SPECIFICATION_TITLE:
                holder.setTitle(productCenterDetailModelList.get(position).getTitle());
                break;
            case ProductCenterDetailModel.SPECIFICATION_BODY:
                String featureTitle = productCenterDetailModelList.get(position).getFeatureName();
                String featureDetails = productCenterDetailModelList.get(position).getFeatureValue();
                holder.setFeatures(featureTitle, featureDetails);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return productCenterDetailModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView featureName, featureValue, title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        private void setTitle(String titleText) {
            title = (TextView) itemView;
            title.setText(titleText);
        }

        private void setFeatures(String featureTitle, String featureDetails) {
            featureName = itemView.findViewById(R.id.Feature_name);
            featureValue = itemView.findViewById(R.id.Freature_value);
            featureName.setText(featureTitle);
            featureValue.setText(featureDetails);
        }
    }

    private int setDp(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
