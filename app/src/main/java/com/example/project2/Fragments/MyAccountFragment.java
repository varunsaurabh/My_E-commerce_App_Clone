package com.example.project2.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.project2.MyAddressesActivity;
import com.example.project2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    public MyAccountFragment() {
        // Required empty public constructor
    }

    private Button addressCng,viewAllAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_my_account, container, false);
        addressCng = view.findViewById(R.id.address_change);
        addressCng.setVisibility(View.GONE);
        viewAllAddress = view.findViewById(R.id.view_all_address);
        viewAllAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getContext().startActivity(new Intent(view.getContext(), MyAddressesActivity.class));
            }
        });
        return view;
    }
}
