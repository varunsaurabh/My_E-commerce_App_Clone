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
import android.widget.Button;

import com.example.project2.Adapter.CartAdapter;
import com.example.project2.Fragments.MyCartFragment;
import com.example.project2.Model.CartItemModel;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    private RecyclerView deliveryRecyclerview;
    private Button addressCngBtn,viewAllAddress;
    public static final int SELECT_ADDRESS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delivery");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addressCngBtn = findViewById(R.id.address_change);
        viewAllAddress = findViewById(R.id.view_all_address);
        viewAllAddress.setVisibility(View.GONE);

        deliveryRecyclerview = findViewById(R.id.delivery_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deliveryRecyclerview.setLayoutManager(linearLayoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.redmi5a, 1, "REDMI 5A", "Rs. 5,999/-", "Rs. 6,999/-"));
        cartItemModelList.add(new CartItemModel(0, R.drawable.redmi5a, 1, "REDMI 5A", "Rs. 5,999/-", "Rs. 6,999/-"));
        cartItemModelList.add(new CartItemModel(0, R.drawable.redmi5a, 1, "REDMI 5A", "Rs. 5,999/-", "Rs. 6,999/-"));
        cartItemModelList.add(new CartItemModel(0, R.drawable.redmi5a, 1, "REDMI 5A", "Rs. 5,999/-", "Rs. 6,999/-"));
        cartItemModelList.add(new CartItemModel(1, "Total item (2)", "FREE", "Rs. 24,999/-", "5,999/-", "24.999/-"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deliveryRecyclerview.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        addressCngBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeliveryActivity.this,MyAddressesActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
