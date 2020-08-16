package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.project2.Adapter.MyAddressesAdapter;
import com.example.project2.Fragments.MyCartFragment;
import com.example.project2.Model.MyAddressesModel;

import java.util.ArrayList;
import java.util.List;

public class MyAddressesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayout assNewAddressBtn;
    private Button deliverToThisBtn;
    private static MyAddressesAdapter myAddressesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        deliverToThisBtn = findViewById(R.id.deliver_to_this_address_btn);
        deliverToThisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        assNewAddressBtn = findViewById(R.id.add_new_address_btn);
        recyclerView = findViewById(R.id.addressess_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<MyAddressesModel> myAddressesModelList = new ArrayList<>();
        myAddressesModelList.add(new MyAddressesModel("Saurabh kumar varun","Kiri mohalla, old city, Dholpur, Rajasthan - 328001","9057677087",true));
        myAddressesModelList.add(new MyAddressesModel("Saurabh kumar varun","Kiri mohalla, old city, Dholpur, Rajasthan - 328001","9057677087",false));
        myAddressesModelList.add(new MyAddressesModel("Saurabh kumar varun","Kiri mohalla, old city, Dholpur, Rajasthan - 328001","9057677087",false));
        myAddressesModelList.add(new MyAddressesModel("Saurabh kumar varun","Kiri mohalla, old city, Dholpur, Rajasthan - 328001","9057677087",false));
        myAddressesModelList.add(new MyAddressesModel("Saurabh kumar varun","Kiri mohalla, old city, Dholpur, Rajasthan - 328001","9057677087",false));
        myAddressesModelList.add(new MyAddressesModel("Saurabh kumar varun","Kiri mohalla, old city, Dholpur, Rajasthan - 328001","9057677087",false));

        myAddressesAdapter = new MyAddressesAdapter(myAddressesModelList);
        recyclerView.setAdapter(myAddressesAdapter);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        myAddressesAdapter.notifyDataSetChanged();

        assNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAddressesActivity.this,AddNewAddressActivity.class));
            }
        });
    }

    public static void refreshItem(int deselect, int select){
        myAddressesAdapter.notifyItemChanged(deselect);
        myAddressesAdapter.notifyItemChanged(select);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
