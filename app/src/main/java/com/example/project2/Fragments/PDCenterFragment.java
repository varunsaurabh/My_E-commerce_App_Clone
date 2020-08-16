package com.example.project2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2.Adapter.ProductCenterDetailAdapter;
import com.example.project2.Model.ProductCenterDetailModel;
import com.example.project2.ProductDetailsActivity;
import com.example.project2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PDCenterFragment extends Fragment {

    public PDCenterFragment() {
        // Required empty public constructor
    }

    private RecyclerView PDC_details_recyclerview;
    public List<ProductCenterDetailModel> productCenterDetailModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p_d_center, container, false);

        PDC_details_recyclerview = view.findViewById(R.id.PDC_details_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        PDC_details_recyclerview.setLayoutManager(linearLayoutManager);

        ProductCenterDetailAdapter productCenterDetailAdapter = new ProductCenterDetailAdapter(productCenterDetailModelList);
        PDC_details_recyclerview.setAdapter(productCenterDetailAdapter);
        productCenterDetailAdapter.notifyDataSetChanged();

        return view;
    }
}
