package com.mrshadat.blooddonationapp.repos;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mrshadat.blooddonationapp.viewmodels.LoginViewModel;

public class FirebaseLoginRepository {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private MutableLiveData<LoginViewModel.AuthenticationState> stateLiveData;

    public FirebaseLoginRepository(MutableLiveData<LoginViewModel.AuthenticationState> stateLiveData) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        this.stateLiveData = stateLiveData;
    }

    public MutableLiveData<LoginViewModel.AuthenticationState> loginFirebaseUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseUser = firebaseAuth.getCurrentUser();
                    stateLiveData.postValue(LoginViewModel.AuthenticationState.AUTHENTICATED);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                stateLiveData.postValue(LoginViewModel.AuthenticationState.UNAUTHENTICATED);
            }
        });

        return stateLiveData;
    }

    public MutableLiveData<LoginViewModel.AuthenticationState> registerFirebaseUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseUser = firebaseAuth.getCurrentUser();
                    stateLiveData.postValue(LoginViewModel.AuthenticationState.AUTHENTICATED);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                stateLiveData.postValue(LoginViewModel.AuthenticationState.UNAUTHENTICATED);
            }
        });

        return stateLiveData;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }
}
