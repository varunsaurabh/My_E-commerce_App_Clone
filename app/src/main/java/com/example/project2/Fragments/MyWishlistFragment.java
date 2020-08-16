package com.example.project2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2.Adapter.MyWishlistAdapter;
import com.example.project2.Model.MyWishlistModel;
import com.example.project2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishlistFragment extends Fragment {

    public MyWishlistFragment() {
        // Required empty public constructor
    }

    private RecyclerView myWishListRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_wishlist, container, false);

        myWishListRecyclerView = view.findViewById(R.id.my_wishlist_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        myWishListRecyclerView.setLayoutManager(linearLayoutManager);

        List<MyWishlistModel> myWishlistModelList = new ArrayList<>();
        myWishlistModelList.add(new MyWishlistModel(R.drawable.redmi5a,"Redmi 5A","5,999/-","6,999/-","COD Available"));
        myWishlistModelList.add(new MyWishlistModel(R.drawable.redmi5a,"Redmi 5A","6,999/-","7,999/-","COD Available"));
        myWishlistModelList.add(new MyWishlistModel(R.drawable.redmi5a,"Redmi 5A","7,999/-","8,999/-","COD Not Available"));
        myWishlistModelList.add(new MyWishlistModel(R.drawable.redmi5a,"Redmi 5A","8,999/-","9,999/-","COD Available"));
        myWishlistModelList.add(new MyWishlistModel(R.drawable.redmi5a,"Redmi 5A","9,999/-","10,999/-","COD Not Available"));

        MyWishlistAdapter myWishlistAdapter = new MyWishlistAdapter(myWishlistModelList);
        myWishListRecyclerView.setAdapter(myWishlistAdapter);
        myWishlistAdapter.notifyDataSetChanged();

        return view;
    }
}
