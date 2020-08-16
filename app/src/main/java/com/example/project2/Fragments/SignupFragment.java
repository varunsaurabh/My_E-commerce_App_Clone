package com.example.project2.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.HomeActivity;
import com.example.project2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    public SignupFragment() {
        // Required empty public constructor
    }

    TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;
    private TextView full_name,mobile_no,email,password;
    private ProgressBar signUpProgress;
    private Button signUpBtn,close;
    private FirebaseAuth registerAuth;
    private DatabaseReference userRef;
    private FirebaseFirestore firebaseFirestore;
    public static boolean disableCloseBtn = false;

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    boolean isPhoneValid(CharSequence phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        alreadyHaveAnAccount = view.findViewById(R.id.login_id_text);
        parentFrameLayout = getActivity().findViewById(R.id.askuser_framelayout);

        full_name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        mobile_no = view.findViewById(R.id.mobileno);
        password = view.findViewById(R.id.password);
        close = view.findViewById(R.id.close_signup);
        signUpBtn = view.findViewById(R.id.signupBtn);
        signUpProgress = view.findViewById(R.id.signupProgress);

        registerAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("UserData");
        firebaseFirestore = FirebaseFirestore.getInstance();

        if (disableCloseBtn){
            close.setVisibility(View.GONE);
        }else{
            close.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SigninFragment());
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        full_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mobile_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_mobile_no = mobile_no.getText().toString();
                if (isEmailValid(txt_email)){
                    if (isPhoneValid(txt_mobile_no)){
                        RegisterUser(txt_email, txt_password);
                    }else{
                        Toast.makeText(getActivity(), "Mobile No. is not valid", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Email is not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SigninFragment());
            }
        });
    }

    private void RegisterUser(String txt_email, String txt_password) {

        signUpProgress.setVisibility(View.VISIBLE);
        signUpBtn.setEnabled(false);
        signUpBtn.setTextColor(Color.argb(50,255,255,255));

        registerAuth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    HashMap<String ,Object> userMap = new HashMap<>();
                    userMap.put("Full_Name",full_name.getText().toString());
                    userMap.put("Email",email.getText().toString());
                    userMap.put("Mobile_No",mobile_no.getText().toString());
                    userMap.put("Password",password.getText().toString());

                    firebaseFirestore.collection("USERS").document(registerAuth.getUid())
                            .set(userMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                HashMap<String ,Object> WishListMap = new HashMap<>();
                                WishListMap.put("list_size",(long)0);
                                firebaseFirestore.collection("USERS").document(registerAuth.getUid()).collection("USER_DATA").document("MY_WISHLIST")
                                        .set(WishListMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            mainIntent();
                                        }else{
                                            signUpProgress.setVisibility(View.INVISIBLE);
                                            signUpBtn.setEnabled(true);
                                            signUpBtn.setTextColor(Color.rgb(255,255,255));
                                            Toast.makeText(getActivity(), "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(getActivity(), "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    signUpProgress.setVisibility(View.INVISIBLE);
                    signUpBtn.setEnabled(true);
                    signUpBtn.setTextColor(Color.rgb(255,255,255));
                    Toast.makeText(getActivity(), "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mainIntent(){
        if (disableCloseBtn == true){
            disableCloseBtn = false;
        }else{
            Toast.makeText(getActivity(), "User Registered Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), HomeActivity.class));
        }
        getActivity().finish();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs(){
        if (!TextUtils.isEmpty(email.getText().toString())){
            if (!TextUtils.isEmpty(full_name.getText().toString())){
                if (!TextUtils.isEmpty(mobile_no.getText())){
                    if (!TextUtils.isEmpty(password.getText().toString()) && password.length() >= 6){
                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(Color.rgb(255,255,255));
                    }else{
                        //Toast.makeText(getActivity(), "Password Required", Toast.LENGTH_SHORT).show();
                        signUpBtn.setEnabled(false);
                        signUpBtn.setTextColor(Color.argb(50,255,255,255));
                    }
                }else{
                    //Toast.makeText(getActivity(), "Mobile No. Required", Toast.LENGTH_SHORT).show();
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.argb(50,255,255,255));
                }
            }else{
               // Toast.makeText(getActivity(), "Name Required", Toast.LENGTH_SHORT).show();
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.argb(50,255,255,255));
            }
        }else{
            //Toast.makeText(getActivity(), "Email Required", Toast.LENGTH_SHORT).show();
            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.argb(50,255,255,255));
        }
    }

}
