package com.example.project2.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.HomeActivity;
import com.example.project2.Prevalent.Prevalent;
import com.example.project2.R;
import com.example.project2.ResetPasswordActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.paperdb.Paper;

import static com.example.project2.AskToUserActivity.onSignUpFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {

    public SigninFragment() {
        // Required empty public constructor
    }

    private TextView dontHaveAnAccount,forget_password_text,showPassText;
    private FrameLayout parentFrameLayout;
    private EditText login_id_text,login_password;
    private Button loginBtn,closeLogin,showPassBtn;
    private ProgressBar loginProgress;
    private FirebaseAuth loginAuth;
    private CheckBox rememberMe;
    private boolean btn = true;
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
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        dontHaveAnAccount = view.findViewById(R.id.signuptext);
        parentFrameLayout = getActivity().findViewById(R.id.askuser_framelayout);

        login_id_text = view.findViewById(R.id.login_id_text);
        login_password = view.findViewById(R.id.login_password_text);
        loginBtn = view.findViewById(R.id.loginBtn);
        closeLogin = view.findViewById(R.id.close_signin);
        loginProgress = view.findViewById(R.id.loginPregress);
        forget_password_text = view.findViewById(R.id.forgetPassText);
        rememberMe = view.findViewById(R.id.rememberMe);
        showPassBtn = view.findViewById(R.id.showPassBtn);
        showPassText = view.findViewById(R.id.showPassText22);
        showPassText.setVisibility(View.INVISIBLE);

        loginAuth = FirebaseAuth.getInstance();

        showPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn == true){
                    showPassBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.hidepass));
                    showPassText.setText(login_password.getText().toString());
                    showPassText.setVisibility(View.VISIBLE);
                    login_password.setVisibility(View.INVISIBLE);
                    btn = false;
                }else{
                    showPassBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.showpass));
                    login_password.setText(showPassText.getText().toString());
                    showPassText.setVisibility(View.INVISIBLE);
                    login_password.setVisibility(View.VISIBLE);
                    btn = true;
                }

            }
        });


        Paper.init(getActivity());

        Paper.init(getActivity());
        String userphonekey = Paper.book().read(Prevalent.userPhoneKey);
        String userpasskey = Paper.book().read(Prevalent.userPassKey);
        String write = Paper.book().read(Prevalent.writeInLog);

        if (write != null && write.equals("yes")){
            if (userpasskey != null && userphonekey != null){
                login_id_text.setText(userphonekey);
                login_password.setText(userpasskey);
            }
        }

        if (disableCloseBtn){
            closeLogin.setVisibility(View.GONE);
        }else{
            closeLogin.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUpFragment = true;
                setFragment(new SignupFragment());
            }
        });

        forget_password_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ResetPasswordActivity.class));
            }
        });

        closeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_loginID = login_id_text.getText().toString();
                String txt_loginPass = login_password.getText().toString();
                if (!TextUtils.isEmpty(txt_loginID)){
                    if (isEmailValid(txt_loginID)){
                        if(!TextUtils.isEmpty(txt_loginPass)){

                            loginProgress.setVisibility(View.VISIBLE);
                            logInUser(txt_loginID,txt_loginPass);

                        }else{
                            Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Enter Valid Email / Mobile No. or Password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Enter Email / Mobile No.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void logInUser(String txt_loginID, String txt_loginPass) {

        Paper.book().write(Prevalent.userPhoneKey,txt_loginID);
        Paper.book().write(Prevalent.userPassKey,txt_loginPass);
        Paper.book().write(Prevalent.loginKey,"true");

        if(rememberMe.isChecked()){
            Paper.book().write(Prevalent.writeInLog,"yes");
        }else{
            Paper.book().write(Prevalent.writeInLog,"no");
        }
        loginAuth.signInWithEmailAndPassword(txt_loginID,txt_loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    if (disableCloseBtn == true){
                        disableCloseBtn = false;
                    }else{
                        startActivity(new Intent(getActivity(),HomeActivity.class));
                    }
                    getActivity().finish();
                }else{
                    loginProgress.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Error : " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}
