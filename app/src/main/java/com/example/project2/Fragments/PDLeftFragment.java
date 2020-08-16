package com.example.project2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PDLeftFragment extends Fragment {

    public PDLeftFragment() {
        // Required empty public constructor
    }

    private TextView detailsBody;
    public String body;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p_d_left, container, false);

        detailsBody = view.findViewById(R.id.PDL_details_textview);
        detailsBody.setText(body);
        return  view;
    }
}
