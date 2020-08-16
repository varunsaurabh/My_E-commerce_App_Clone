package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.example.project2.Adapter.GridProductLayoutAdapter;
import com.example.project2.Adapter.ShowAllRecyclerViewAdapter;
import com.example.project2.Fragments.MyCartFragment;
import com.example.project2.Model.HorizontalProductModel;
import com.example.project2.Model.ShowAllProductRecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;
    public static List<ShowAllProductRecyclerViewModel> showAllProductRecyclerViewModelList;
    public static List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // String title = getIntent().getStringExtra("");
       // getSupportActionBar().setTitle(title);

        recyclerView = findViewById(R.id.show_product_recyclerview);
        gridView = findViewById(R.id.show_product_gridview);

        int layout_code = getIntent().getIntExtra("layout_code",-1);

        if (layout_code == 0) {
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);

           // showAllProductRecyclerViewModelList = new ArrayList<>();

            ShowAllRecyclerViewAdapter showAllRecyclerViewAdapter = new ShowAllRecyclerViewAdapter(showAllProductRecyclerViewModelList);
            recyclerView.setAdapter(showAllRecyclerViewAdapter);
            showAllRecyclerViewAdapter.notifyDataSetChanged();
        }else if (layout_code == 1) {

            gridView.setVisibility(View.VISIBLE);
            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductModelList);
            gridView.setAdapter(gridProductLayoutAdapter);
            gridProductLayoutAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
