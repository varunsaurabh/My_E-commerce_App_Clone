package com.example.project2.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.project2.Adapter.CartAdapter;
import com.example.project2.DeliveryActivity;
import com.example.project2.Model.CartItemModel;
import com.example.project2.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {

    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemRecyclerview;
    private Button continueBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        continueBtn = view.findViewById(R.id.cart_continue_btn);
        cartItemRecyclerview = view.findViewById(R.id.cart_items_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemRecyclerview.setLayoutManager(linearLayoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.redmi5a, 1, "REDMI 5A", "Rs. 5,999/-", "Rs. 6,999/-"));
        cartItemModelList.add(new CartItemModel(0, R.drawable.redmi5a, 1, "REDMI 5A", "Rs. 5,999/-", "Rs. 6,999/-"));
        cartItemModelList.add(new CartItemModel(0, R.drawable.redmi5a, 1, "REDMI 5A", "Rs. 5,999/-", "Rs. 6,999/-"));
        cartItemModelList.add(new CartItemModel(0, R.drawable.redmi5a, 1, "REDMI 5A", "Rs. 5,999/-", "Rs. 6,999/-"));
        cartItemModelList.add(new CartItemModel(1, "Total item (2)", "FREE", "Rs. 24,999/-", "5,999/-", "24.999/-"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemRecyclerview.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DeliveryActivity.class));
            }
        });

        return view;
    }
}
