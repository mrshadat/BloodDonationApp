package com.mrshadat.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.mrshadat.blooddonationapp.databinding.ActivityMainBinding;
import com.mrshadat.blooddonationapp.viewmodels.LoginViewModel;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private LoginViewModel loginViewModel;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        pd = new ProgressDialog(this);

        mainBinding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mainBinding.emailEtSignIn.getEditText().getText().toString();
                String password = mainBinding.passwordEtSignIn.getEditText().getText().toString();
                loginViewModel.login(email, password);

                pd.setTitle("Processing...");
                pd.setMessage("Please wait.");
                pd.setCancelable(false);
                pd.setIndeterminate(true);
                pd.show();
            }
        });

        mainBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });


        loginViewModel.stateLiveData.observe(this, new Observer<LoginViewModel.AuthenticationState>() {
            @Override
            public void onChanged(LoginViewModel.AuthenticationState authenticationState) {
                switch (authenticationState) {
                    case AUTHENTICATED:
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        pd.dismiss();
                        break;

                    case UNAUTHENTICATED:
                        pd.dismiss();
                        break;
                }
            }
        });
    }

}