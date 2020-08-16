package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project2.Adapter.HomepageAdapter;
import com.example.project2.Model.Category;
import com.example.project2.Model.HomepageModel;
import com.example.project2.Model.HorizontalProductModel;
import com.example.project2.Model.ShowAllProductRecyclerViewModel;
import com.example.project2.Model.Slider;
import com.example.project2.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.project2.FirebaseQueries.DBqueries.lists;
import static com.example.project2.FirebaseQueries.DBqueries.loadFragmentData;
import static com.example.project2.FirebaseQueries.DBqueries.loadedCategoriesNames;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView category_recyclerview;
    private HomepageAdapter adapter;
    private List<HomepageModel> homepageModelFakeList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        /////////// Homepage fake list
        List<Slider> sliderFakeList = new ArrayList<>();
        sliderFakeList.add(new Slider("null"));
        sliderFakeList.add(new Slider("null"));
        sliderFakeList.add(new Slider("null"));
        sliderFakeList.add(new Slider("null"));
        sliderFakeList.add(new Slider("null"));
        sliderFakeList.add(new Slider("null"));

        List<HorizontalProductModel> horizontalProductModelFakeList = new ArrayList<>();
        horizontalProductModelFakeList.add(new HorizontalProductModel("","","","",""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("","","","",""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("","","","",""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("","","","",""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("","","","",""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("","","","",""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("","","","",""));

        homepageModelFakeList.add(new HomepageModel(0,sliderFakeList));
        homepageModelFakeList.add(new HomepageModel(1,"","#ffffff"));
        homepageModelFakeList.add(new HomepageModel(2,"",horizontalProductModelFakeList,new ArrayList<ShowAllProductRecyclerViewModel>()));
        homepageModelFakeList.add(new HomepageModel(3,"",horizontalProductModelFakeList));
        /////////// Homepage fake list

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        category_recyclerview = findViewById(R.id.category_recyclerview);

        ///////////////////////////////////////////// Final RecyclerView
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(RecyclerView.VERTICAL);
        category_recyclerview.setLayoutManager(testingLayoutManager);
        //////////////////////////////////////////// Final RecyclerView

        adapter = new HomepageAdapter(homepageModelFakeList);

        int listPosition = 0;
        for (int x = 0;x < loadedCategoriesNames.size();x++){
            if (loadedCategoriesNames.get(x).equals(title.toUpperCase())){
                listPosition = x;
            }
        }
        if (listPosition == 0){
            loadedCategoriesNames.add(title.toUpperCase());
            lists.add(new ArrayList<HomepageModel>());
            //adapter = new HomepageAdapter(lists.get(loadedCategoriesNames.size()-1));
            loadFragmentData(category_recyclerview,this,loadedCategoriesNames.size()-1,title);
        }else{
            adapter = new HomepageAdapter(lists.get(listPosition));
        }
        //HomepageAdapter adapter = new HomepageAdapter(homepageModelList);
        category_recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.main_search_icon){
            Toast.makeText(this, "open cart activity", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
