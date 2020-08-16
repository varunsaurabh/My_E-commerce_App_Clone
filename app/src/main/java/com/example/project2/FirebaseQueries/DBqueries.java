package com.example.project2.FirebaseQueries;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.project2.Adapter.CategoryAdapter;
import com.example.project2.Adapter.HomepageAdapter;
import com.example.project2.Fragments.HomeFragment;
import com.example.project2.Model.Category;
import com.example.project2.Model.HomepageModel;
import com.example.project2.Model.HorizontalProductModel;
import com.example.project2.Model.ShowAllProductRecyclerViewModel;
import com.example.project2.Model.Slider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DBqueries {

    public static FirebaseFirestore  firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<Category> categoryList = new ArrayList<Category>();
    //public static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //public static FirebaseUser currentUser = firebaseAuth.getCurrentUser();

    public static List<List<HomepageModel>> lists = new ArrayList<>();
    public static List<String> loadedCategoriesNames = new ArrayList<>();
    public static List<String> wishList = new ArrayList<>();

    public static void loadCategories(final RecyclerView categoryRecyclerview, final Context context){
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot QDS : task.getResult()) {
                        categoryList.add(new Category(QDS.get("icon").toString(), QDS.get("categoryName").toString()));
                    }
                    CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);
                    categoryRecyclerview.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void loadFragmentData(final RecyclerView homepageRecyclerview, final Context context, final int index, String categoryName){
        firebaseFirestore.collection("CATEGORIES").document(categoryName.toUpperCase()).collection("TOP_DEALS").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot QDS : task.getResult()) {

                                if ((long) QDS.get("view_type") == 0) {
                                    List<Slider> sliderList = new ArrayList<>();
                                    long no_of_banners = (long) QDS.get("no_of_banners");
                                    for (long x = 1; x < no_of_banners + 1; x++) {
                                        sliderList.add(new Slider(QDS.get("banner_" + x).toString()));
                                    }
                                    lists.get(index).add(new HomepageModel(0, sliderList));
                                } else if ((long) QDS.get("view_type") == 1) {
                                    lists.get(index).add(new HomepageModel(1, QDS.get("strip_ad_banner").toString()
                                            , QDS.get("background_color").toString()));

                                } else if ((long) QDS.get("view_type") == 2) {

                                    List<ShowAllProductRecyclerViewModel> showAllProductRecyclerViewModelList = new ArrayList<>();
                                    List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
                                    long no_of_banners = (long) QDS.get("no_of_products");
                                    for (long x = 1; x < no_of_banners + 1; x++) {
                                        horizontalProductModelList.add(new HorizontalProductModel(QDS.get("product_ID_1").toString()
                                                , QDS.get("product_image_" + x).toString()
                                                , QDS.get("product_title").toString()
                                                , QDS.get("product_subtitle").toString()
                                                , QDS.get("product_price").toString()));

                                        showAllProductRecyclerViewModelList.add(new ShowAllProductRecyclerViewModel(QDS.get("product_image_"+x).toString()
                                                ,QDS.get("product_full_title").toString()
                                                ,QDS.get("product_price").toString()
                                                ,QDS.get("product_cut_price").toString()
                                                ,QDS.get("product_COD").toString()));
                                    }
                                    lists.get(index).add(new HomepageModel(2, QDS.get("layout_title").toString(), horizontalProductModelList,showAllProductRecyclerViewModelList));

                                } else if ((long) QDS.get("view_type") == 3) {
                                    List<HorizontalProductModel> gridProductModelList = new ArrayList<>();
                                    long no_of_banners = (long) QDS.get("no_of_products");
                                    for (long x = 1; x < no_of_banners + 1; x++) {
                                        gridProductModelList.add(new HorizontalProductModel(QDS.get("product_ID_1").toString()
                                                , QDS.get("product_image_" + x).toString()
                                                , QDS.get("product_title_1").toString()
                                                , QDS.get("product_subtitle_1").toString()
                                                , QDS.get("product_price_1").toString()));
                                    }
                                    lists.get(index).add(new HomepageModel(3, QDS.get("layout_title").toString(), gridProductModelList));
                                }
                            }
                            HomepageAdapter homepageAdapter = new HomepageAdapter(lists.get(index));
                            homepageRecyclerview.setAdapter(homepageAdapter);
                            homepageAdapter.notifyDataSetChanged();
                            HomeFragment.swipeRefreshLayout.setRefreshing(false);
                        } else {
                            Toast.makeText(context, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

   /* public static void loadWishList(final Context context){
        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA")
                .document("MY_WISHLIST").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    for (long x = 0; x < (long)task.getResult().get("list_size"); x++){
                        wishList.add(task.getResult().get("product_ID_"+x).toString());
                    }
                }else{
                    Toast.makeText(context, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

}
