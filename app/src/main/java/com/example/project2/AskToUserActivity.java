package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.example.project2.Fragments.SigninFragment;
import com.example.project2.Fragments.SignupFragment;

public class AskToUserActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    public static boolean onSignUpFragment = false;
    public static boolean setSignUpFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_to_user);

        frameLayout = findViewById(R.id.askuser_framelayout);
        if (setSignUpFragment){
            setSignUpFragment = false;
            setDefaultFragment(new SignupFragment());
        }else{
            setDefaultFragment(new SigninFragment());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            SigninFragment.disableCloseBtn = false;
            SignupFragment.disableCloseBtn = false;
            if (onSignUpFragment){
                onSignUpFragment = false;
                setFragment(new SigninFragment());
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setDefaultFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}
