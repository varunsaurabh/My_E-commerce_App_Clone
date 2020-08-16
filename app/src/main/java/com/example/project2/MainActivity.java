package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth checkLoginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemClock.sleep(300);

        checkLoginStatus = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = checkLoginStatus.getCurrentUser();

        if (currentUser == null){
            startActivity(new Intent(MainActivity.this,AskToUserActivity.class));
            finish();
        }else{
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }
    }
}
