package com.example.project2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.project2.Fragments.HomeFragment;
import com.example.project2.Fragments.MyAccountFragment;
import com.example.project2.Fragments.MyCartFragment;
import com.example.project2.Fragments.MyOrdersFragment;
import com.example.project2.Fragments.MyWishlistFragment;
import com.example.project2.Fragments.SigninFragment;
import com.example.project2.Fragments.SignupFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import static com.example.project2.AskToUserActivity.setSignUpFragment;

public class HomeActivity extends AppCompatActivity {

    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int MY_ORDERS_FRAGMENT = 2;
    private static final int WISHLIST_FRAGMENT = 3;
    private static final int MY_ACCOUNT_FRAGMENT = 4;
    public static boolean showCart = false;
    private Dialog signInDialog;
    private  NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;

    private FrameLayout frameLayout;
    private int currentFragment;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                if (currentUser != null) {
                    int id = item.getItemId();

                    if (id == R.id.main_cart_icon) {
                        gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
                    } else if (id == R.id.my_orders) {
                        gotoFragment("My Orders", new MyOrdersFragment(), MY_ORDERS_FRAGMENT);
                    } else if (id == R.id.my_wishlist) {
                        gotoFragment("My Wishlist", new MyWishlistFragment(), WISHLIST_FRAGMENT);
                    } else if (id == R.id.my_address) {
                        startActivity(new Intent(HomeActivity.this, MyAddressesActivity.class));
                    } else if (id == R.id.editProfile) {
                        gotoFragment("My Account", new MyAccountFragment(), MY_ACCOUNT_FRAGMENT);
                    } else if (id == R.id.settings) {
                        Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                    } else if (id == R.id.logout) {

                        final Dialog signOutDialog = new Dialog(HomeActivity.this);
                        signOutDialog.setContentView(R.layout.signout_dialog);
                        signOutDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        signOutDialog.setCancelable(true);

                        Button cancelSignOut, yesSignOut;
                        cancelSignOut = signOutDialog.findViewById(R.id.cancel_sign_out);
                        yesSignOut = signOutDialog.findViewById(R.id.yes_sign_out);

                        cancelSignOut.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                signOutDialog.dismiss();
                            }
                        });

                        yesSignOut.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                signOutDialog.dismiss();
                                FirebaseAuth.getInstance().signOut();
                                Toast.makeText(HomeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(HomeActivity.this, AskToUserActivity.class));
                                finish();
                            }
                        });

                        signOutDialog.show();
                    }
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }else{
                    drawer.closeDrawer(GravityCompat.START);
                    signInDialog.show();
                    return false;
                }
            }
        });

        frameLayout = findViewById(R.id.mainFrameLayout);
        if (showCart) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            gotoFragment("My Cart", new MyCartFragment(), -2);
        } else {
            //HOME FRAGMENT
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(frameLayout.getId(), new HomeFragment());
            fragmentTransaction.commit();
            //HOME FRAGMENT
        }

        //////////////////////signIn Dialog
        signInDialog = new Dialog(HomeActivity.this);
        signInDialog.setContentView(R.layout.signin_dialog);
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        signInDialog.setCancelable(true);

        Button dialogSignInBtn, dialogSignUpBtn;
        dialogSignInBtn = signInDialog.findViewById(R.id.signin_dialog_btn);
        dialogSignUpBtn = signInDialog.findViewById(R.id.signup_dialog_btn);
        final Intent askUserIntent = new Intent(HomeActivity.this, AskToUserActivity.class);

        dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SigninFragment.disableCloseBtn = false;
                SignupFragment.disableCloseBtn = false;
                signInDialog.dismiss();
                setSignUpFragment = false;
                startActivity(askUserIntent);
            }
        });

        dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SigninFragment.disableCloseBtn = false;
                SignupFragment.disableCloseBtn = false;
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
        if (currentUser == null) {
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(false);
        } else {
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment == HOME_FRAGMENT) {
                // currentFragment = -1;
                super.onBackPressed();
            } else {
                if (showCart) {
                    showCart = false;
                    finish();
                } else {
                    invalidateOptionsMenu();
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    setFragment(new HomeFragment(), HOME_FRAGMENT, R.anim.fade_in, R.anim.fade_out);
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == HOME_FRAGMENT) {
            getMenuInflater().inflate(R.menu.home, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.main_cart_icon) {
            if (currentUser != null) {
                gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
                return true;
            } else {
                signInDialog.show();
            }
        } else if (id == R.id.main_search_icon) {
            startActivity(new Intent(HomeActivity.this, SearchProductActivity.class));
            return true;
        } else if (id == android.R.id.home) {
            if (showCart) {
                showCart = false;
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoFragment(String fragmentTitle, Fragment fragment, int fragmentNO) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(fragmentTitle);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNO, R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setFragment(Fragment fragment, int fragmentNumber, int enterAnim, int exitAnim) {
        currentFragment = fragmentNumber;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
