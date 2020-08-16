package com.example.project2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2.Adapter.CartAdapter;
import com.example.project2.Adapter.MyOrderAdapter;
import com.example.project2.Model.CartItemModel;
import com.example.project2.Model.MyOrderItemModel;
import com.example.project2.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {

    public MyOrdersFragment() {
        // Required empty public constructor
    }

    private RecyclerView myOrderRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        myOrderRecyclerView = view.findViewById(R.id.my_orders_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrderRecyclerView.setLayoutManager(linearLayoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","12 june"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","15 june"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","17 june"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","30 june"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","12 june"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","15 june"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","17 june"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","30 june"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.redmi5a,"Redmi 5A","Cancelled"));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrderRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();

        return view;
    }
}
