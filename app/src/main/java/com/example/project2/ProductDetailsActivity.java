package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.Adapter.ProductDetailsAdapter;
import com.example.project2.Adapter.ProductImagesAdapter;
import com.example.project2.FirebaseQueries.DBqueries;
import com.example.project2.Fragments.PDCenterFragment;
import com.example.project2.Fragments.PDLeftFragment;
import com.example.project2.Fragments.SigninFragment;
import com.example.project2.Fragments.SignupFragment;
import com.example.project2.Model.ProductCenterDetailModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static com.example.project2.AskToUserActivity.setSignUpFragment;
import static com.example.project2.HomeActivity.showCart;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewpager;
    private TabLayout viewpagerIndicator;

    private TextView productTitle,productPrice,productCutPrice;
    private ConstraintLayout productDetailsOnlyContainer,productTabDetailsContainer;
    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;
    private Button buyNowBtn,addToCartBtn;
    private TextView productOnlyDetailsBody;

    private List<ProductCenterDetailModel> productCenterDetailModelList = new ArrayList<>();
    private String  productLeftDetails;
    private String  productRightDetails;
    private Dialog signInDialog;
    private FirebaseUser currentUser;

    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        addToCartBtn = findViewById(R.id.add_to_cart_btn);
        buyNowBtn = findViewById(R.id.buy_now_btn);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);

        productImagesViewpager = findViewById(R.id.productImagesViewpager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        productTitle = findViewById(R.id.PDA_product_title);
        productPrice = findViewById(R.id.PDA_product_price);
        productCutPrice = findViewById(R.id.PDA_cutted_price);

        ///////////details
        productTabDetailsContainer = findViewById(R.id.product_detailas_tabs_container);
        productDetailsOnlyContainer = findViewById(R.id.product_details_only_container);

        productOnlyDetailsBody = findViewById(R.id.TV_product_details);
        ///////////details

        productDetailsViewPager = findViewById(R.id.PD_viewpager);
        productDetailsTabLayout = findViewById(R.id.PD_tablayout);

        final List<String> productImages = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("PRODUCTS").document("VyDqzdDhUMvJfcM1t5qm").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot docSnap = task.getResult();

                    for (long x=1; x< (long)docSnap.get("no_of_product_images") + 1; x++){
                        productImages.add(docSnap.get("product_image_"+x).toString());
                    }
                    ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
                    productImagesViewpager.setAdapter(productImagesAdapter);

                    productTitle.setText(docSnap.get("product_title").toString());
                    productPrice.setText("\u20B9 " + docSnap.get("product_price").toString() + "/-");
                    productCutPrice.setText("\u20B9 " + docSnap.get("product_cut_price").toString() + "/-");

                    if ((boolean)docSnap.get("use_tab_layout")){
                        productTabDetailsContainer.setVisibility(View.VISIBLE);
                        productDetailsOnlyContainer.setVisibility(View.GONE);
                        productLeftDetails = docSnap.get("product_description").toString();
                        productRightDetails = docSnap.get("peoduct_other_details").toString();

                        for (long x = 1; x < (long)docSnap.get("total_specification_title") + 1; x++){
                            productCenterDetailModelList.add(new ProductCenterDetailModel(0,docSnap.get("spec_title_"+x).toString()));
                            for (long y = 1; y <(long)docSnap.get("spec_title_"+ x +"_total_fields") + 1; y++){
                                productCenterDetailModelList
                                        .add(new ProductCenterDetailModel(1,docSnap.get("spec_title_" + x +"_field_"+ y +"_name").toString()
                                        ,docSnap.get("spec_title_" + x +"_field_"+ y +"_value").toString()));
                            }
                        }
                        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount(),productLeftDetails,productRightDetails,productCenterDetailModelList));

                    }else{
                        productTabDetailsContainer.setVisibility(View.GONE);
                        productDetailsOnlyContainer.setVisibility(View.VISIBLE);
                        productOnlyDetailsBody.setText(docSnap.get("peoduct_other_details").toString());
                    }
                    /*if (DBqueries.wishList.size() == 0){
                        DBqueries.loadWishList(ProductDetailsActivity.this);
                    }*/
                   // if (DBqueries.wishList.contains())
                }else{
                    Toast.makeText(ProductDetailsActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewpagerIndicator.setupWithViewPager(productImagesViewpager,true);
        //productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount(),productLeftDetails,productRightDetails,productCenterDetailModelList));
        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null) {
                    startActivity(new Intent(ProductDetailsActivity.this, DeliveryActivity.class));
                }else{
                    signInDialog.show();
                }
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null){
                    Toast.makeText(ProductDetailsActivity.this, "This product added to your cart", Toast.LENGTH_SHORT).show();
                }else{
                    signInDialog.show();
                }
            }
        });

        //////////////////////signIn Dialog
        signInDialog = new Dialog(ProductDetailsActivity.this);
        signInDialog.setContentView(R.layout.signin_dialog);
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        signInDialog.setCancelable(true);

        Button dialogSignInBtn, dialogSignUpBtn;
        dialogSignInBtn = signInDialog.findViewById(R.id.signin_dialog_btn);
        dialogSignUpBtn = signInDialog.findViewById(R.id.signup_dialog_btn);
        final Intent askUserIntent = new Intent(ProductDetailsActivity.this, AskToUserActivity.class);

        dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SigninFragment.disableCloseBtn = true;
                SignupFragment.disableCloseBtn = true;
                signInDialog.dismiss();
                setSignUpFragment = false;
                startActivity(askUserIntent);
            }
        });

        dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SigninFragment.disableCloseBtn = true;
                SignupFragment.disableCloseBtn = true;
                signInDialog.dismiss();
                setSignUpFragment = true;
                startActivity(askUserIntent);
            }
        });
        //////////////////////signIn Dialog
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.main_cart_icon){
            if (currentUser != null) {
                Intent cartIntent = new Intent(ProductDetailsActivity.this, HomeActivity.class);
                showCart = true;
                startActivity(cartIntent);
                return true;
            }else{
                signInDialog.show();
                return true;
            }
        }else if (id == R.id.main_search_icon){
            startActivity(new Intent(ProductDetailsActivity.this,SearchProductActivity.class));
        }else if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
