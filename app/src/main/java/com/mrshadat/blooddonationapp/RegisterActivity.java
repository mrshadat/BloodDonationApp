package com.mrshadat.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mrshadat.blooddonationapp.databinding.ActivityRegisterBinding;
import com.mrshadat.blooddonationapp.viewmodels.LoginViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding registerBinding;
    private LoginViewModel loginViewModel;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        pd = new ProgressDialog(this);

        registerBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = registerBinding.emailEtSignUp.getEditText().getText().toString();
                String password = registerBinding.passwordEtSignUp.getEditText().getText().toString();
                loginViewModel.register(email, password);

                pd.setTitle("Processing...");
                pd.setMessage("Please wait.");
                pd.setCancelable(false);
                pd.setIndeterminate(true);
                pd.show();
            }
        });

        loginViewModel.stateLiveData.observe(this, new Observer<LoginViewModel.AuthenticationState>() {
            @Override
            public void onChanged(LoginViewModel.AuthenticationState authenticatinState) {
                switch (authenticatinState) {
                    case AUTHENTICATED:
                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        pd.dismiss();
                        break;

                    case UNAUTHENTICATED:

                        break;
                }
            }
        });
    }
}