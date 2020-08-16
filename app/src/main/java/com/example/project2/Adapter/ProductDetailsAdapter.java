package com.example.project2.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.project2.Fragments.PDCenterFragment;
import com.example.project2.Fragments.PDLeftFragment;
import com.example.project2.Model.ProductCenterDetailModel;

import java.util.List;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int totalTabs;
    private String productLeftDetails;
    private String productRightDetails;
    private List<ProductCenterDetailModel> productCenterDetailModelList;

    public ProductDetailsAdapter(@NonNull FragmentManager fm, int totalTabs, String productLeftDetails, String productRightDetails, List<ProductCenterDetailModel> productCenterDetailModelList) {
        super(fm);
        this.totalTabs = totalTabs;
        this.productLeftDetails = productLeftDetails;
        this.productRightDetails = productRightDetails;
        this.productCenterDetailModelList = productCenterDetailModelList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PDLeftFragment pdLeftFragment1 = new PDLeftFragment();
                pdLeftFragment1.body = productLeftDetails;
                return pdLeftFragment1;
            case 1:
                PDCenterFragment pdCenterFragment = new PDCenterFragment();
                pdCenterFragment.productCenterDetailModelList = productCenterDetailModelList;
                return pdCenterFragment;
            case 2:
                PDLeftFragment pdLeftFragment2 = new PDLeftFragment();
                pdLeftFragment2.body = productRightDetails;
                return pdLeftFragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
