package com.example.project2.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project2.Adapter.CategoryAdapter;
import com.example.project2.Adapter.HomepageAdapter;
import com.example.project2.HomeActivity;
import com.example.project2.Model.Category;
import com.example.project2.Model.HomepageModel;
import com.example.project2.Model.HorizontalProductModel;
import com.example.project2.Model.ShowAllProductRecyclerViewModel;
import com.example.project2.Model.Slider;
import com.example.project2.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.project2.FirebaseQueries.DBqueries.categoryList;
import static com.example.project2.FirebaseQueries.DBqueries.lists;
import static com.example.project2.FirebaseQueries.DBqueries.loadCategories;
import static com.example.project2.FirebaseQueries.DBqueries.loadFragmentData;
import static com.example.project2.FirebaseQueries.DBqueries.loadedCategoriesNames;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView homepageRecyclerview;
    private HomepageAdapter adapter;
    private ImageView noInternetConnection;
    private List<Category> categoryModelFakeList = new ArrayList<>();
    private List<HomepageModel> homepageModelFakeList = new ArrayList<>();
    private Button retryBtn;
    private TextView no_internet_txt;

    public static SwipeRefreshLayout swipeRefreshLayout;


    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        noInternetConnection = view.findViewById(R.id.no_internet_connection);
        retryBtn = view.findViewById(R.id.retry_btn);
        no_internet_txt = view.findViewById(R.id.no_internet_text);

        /////////// Category RecyclerView
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);

        ///////////////////////////////////////////// Final RecyclerView
        homepageRecyclerview = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(RecyclerView.VERTICAL);
        homepageRecyclerview.setLayoutManager(testingLayoutManager);
        //////////////////////////////////////////// Final RecyclerView

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.colorPrimary), getContext().getResources().getColor(R.color.successGreen), getContext().getResources().getColor(R.color.Red));

        /////////// Category fake list
        categoryModelFakeList.add(new Category("null", ""));
        categoryModelFakeList.add(new Category("", ""));
        categoryModelFakeList.add(new Category("", ""));
        categoryModelFakeList.add(new Category("", ""));
        categoryModelFakeList.add(new Category("", ""));
        categoryModelFakeList.add(new Category("", ""));
        categoryModelFakeList.add(new Category("", ""));
        categoryModelFakeList.add(new Category("", ""));
        categoryModelFakeList.add(new Category("", ""));
        categoryModelFakeList.add(new Category("", ""));
        /////////// Category fake list

        /////////// Homepage fake list
        List<Slider> sliderFakeList = new ArrayList<>();
        sliderFakeList.add(new Slider("null"));
        sliderFakeList.add(new Slider("null"));
        sliderFakeList.add(new Slider("null"));
        sliderFakeList.add(new Slider("null"));
        sliderFakeList.add(new Slider("null"));
        sliderFakeList.add(new Slider("null"));

        List<HorizontalProductModel> horizontalProductModelFakeList = new ArrayList<>();
        horizontalProductModelFakeList.add(new HorizontalProductModel("", "", "", "", ""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("", "", "", "", ""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("", "", "", "", ""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("", "", "", "", ""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("", "", "", "", ""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("", "", "", "", ""));
        horizontalProductModelFakeList.add(new HorizontalProductModel("", "", "", "", ""));

        homepageModelFakeList.add(new HomepageModel(0, sliderFakeList));
        homepageModelFakeList.add(new HomepageModel(1, "", "#ffffff"));
        homepageModelFakeList.add(new HomepageModel(2, "", horizontalProductModelFakeList, new ArrayList<ShowAllProductRecyclerViewModel>()));
        homepageModelFakeList.add(new HomepageModel(3, "", horizontalProductModelFakeList));
        /////////// Homepage fake list

        categoryAdapter = new CategoryAdapter(categoryModelFakeList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        adapter = new HomepageAdapter(homepageModelFakeList);

        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homepageRecyclerview.setVisibility(View.VISIBLE);
            noInternetConnection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            no_internet_txt.setVisibility(View.GONE);

            if (categoryList.size() == 0) {
                loadCategories(categoryRecyclerView, view.getContext());
            } else {
                categoryAdapter = new CategoryAdapter(categoryList);
                categoryAdapter.notifyDataSetChanged();
            }
            if (lists.size() == 0) {
                loadedCategoriesNames.add("HOME");
                lists.add(new ArrayList<HomepageModel>());
                loadFragmentData(homepageRecyclerview, getContext(), 0, "home");
            } else {
                adapter = new HomepageAdapter(lists.get(0));
                adapter.notifyDataSetChanged();
            }
            homepageRecyclerview.setAdapter(adapter);

        } else {
            //Glide.with(this).load(R.drawable.ic_signal_wifi_off_black_24dp).into(noInternetConnection);
            no_internet_txt.setVisibility(View.VISIBLE);
            noInternetConnection.setVisibility(View.VISIBLE);
            categoryRecyclerView.setVisibility(View.GONE);
            homepageRecyclerview.setVisibility(View.GONE);
            retryBtn.setVisibility(View.VISIBLE);
        }

        /////// refresh layout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                reloadPage();
            }
        });
        /////// refresh layout

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadPage();
            }
        });

        return view;
    }

    private void reloadPage() {
        networkInfo = connectivityManager.getActiveNetworkInfo();

        categoryList.clear();
        lists.clear();
        loadedCategoriesNames.clear();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homepageRecyclerview.setVisibility(View.VISIBLE);
            noInternetConnection.setVisibility(View.GONE);
            no_internet_txt.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            categoryAdapter = new CategoryAdapter(categoryModelFakeList);
            adapter = new HomepageAdapter(homepageModelFakeList);
            categoryRecyclerView.setAdapter(categoryAdapter);
            homepageRecyclerview.setAdapter(adapter);
            loadCategories(categoryRecyclerView, getContext());

            loadedCategoriesNames.add("HOME");
            lists.add(new ArrayList<HomepageModel>());
            loadFragmentData(homepageRecyclerview, getContext(), 0, "home");

        } else {
            //Glide.with(getContext()).load(R.drawable.ic_signal_wifi_off_black_24dp).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            categoryRecyclerView.setVisibility(View.GONE);
            homepageRecyclerview.setVisibility(View.GONE);
            retryBtn.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
            no_internet_txt.setVisibility(View.VISIBLE);
            final Animation animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake_text);
            no_internet_txt.startAnimation(animShake);
        }
    }
}
