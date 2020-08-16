package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResetPasswordActivity extends AppCompatActivity {

    private Button reset_pass_btn;
    private EditText reset_ID;
    private TextView goBack,show_user_email,heading,Notice;
    private ProgressBar reset_progress;
    private FirebaseAuth checkAuth;
    private DatabaseReference userRef;

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    boolean isPhoneValid(CharSequence phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        reset_ID = findViewById(R.id.reset_ID);
        reset_pass_btn = findViewById(R.id.reset_btn);
        goBack = findViewById(R.id.goBackText);
        reset_progress = findViewById(R.id.resetProgress);
        show_user_email = findViewById(R.id.showUserEmail);
        heading = findViewById(R.id.heading);
        Notice = findViewById(R.id.emailNotRegistered);

        checkAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("UserData");

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reset_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_reset_id = reset_ID.getText().toString();
                if (isEmailValid(txt_reset_id)){
                    loginWithEmail(txt_reset_id);
                }else if (isPhoneValid(txt_reset_id)){
                    if (txt_reset_id.length() == 10)
                    loginWithMobile(txt_reset_id);
                    else{
                        heading.setText("FORGET PASSWORD ?");
                        show_user_email.setVisibility(View.INVISIBLE);
                        Notice.setText("Enter 10 digit mobile no.");
                        Notice.setVisibility(View.VISIBLE);
                    }
                }else{
                    heading.setText("FORGET PASSWORD ?");
                    show_user_email.setVisibility(View.INVISIBLE);
                    Notice.setText("Enter valid email / mobile no.");
                    Notice.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void loginWithMobile(String txt_reset_id) {
        reset_progress.setVisibility(View.VISIBLE);

        userRef.child(txt_reset_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    final String txt_email = String.valueOf(dataSnapshot.child("Email").getValue());

                    checkAuth.sendPasswordResetEmail(txt_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                heading.setText("Reset Link Send To");
                                show_user_email.setText(txt_email);
                                show_user_email.setVisibility(View.VISIBLE);
                                reset_progress.setVisibility(View.INVISIBLE);
                                Notice.setVisibility(View.INVISIBLE);
                            }else{
                                reset_progress.setVisibility(View.INVISIBLE);
                                Notice.setText(task.getException().getMessage());
                                Notice.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }else{
                    heading.setText("FORGET PASSWORD ?");
                    show_user_email.setVisibility(View.INVISIBLE);
                    reset_progress.setVisibility(View.INVISIBLE);
                    Notice.setText("There is no user corresponding to this mobile no.");
                    Notice.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loginWithEmail(String txt_reset_id) {
        reset_progress.setVisibility(View.VISIBLE);
        checkAuth.sendPasswordResetEmail(txt_reset_id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    show_user_email.setText("Reset link send to your Email");
                    show_user_email.setVisibility(View.VISIBLE);
                    reset_progress.setVisibility(View.INVISIBLE);
                    Notice.setVisibility(View.INVISIBLE);
                }else{
                    heading.setText("FORGET PASSWORD ?");
                    show_user_email.setVisibility(View.INVISIBLE);
                    reset_progress.setVisibility(View.INVISIBLE);
                    Notice.setText("There is no user corresponding to this email");
                    Notice.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
