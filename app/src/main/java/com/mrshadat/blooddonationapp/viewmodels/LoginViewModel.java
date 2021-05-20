package com.mrshadat.blooddonationapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrshadat.blooddonationapp.repos.FirebaseLoginRepository;

public class LoginViewModel extends ViewModel {

    public enum AuthenticationState {
        AUTHENTICATED,
        UNAUTHENTICATED
    }

    private FirebaseLoginRepository repository;
    public MutableLiveData<AuthenticationState> stateLiveData;

    public LoginViewModel() {
        stateLiveData = new MutableLiveData<>();
        repository = new FirebaseLoginRepository(stateLiveData);
        if (repository.getFirebaseUser() != null) {
            stateLiveData.postValue(AuthenticationState.AUTHENTICATED);
        } else {
            stateLiveData.postValue(AuthenticationState.UNAUTHENTICATED);
        }
    }

    public void login(String email, String password) {
        stateLiveData = repository.loginFirebaseUser(email, password);
    }

    public void register(String email, String password) {
        stateLiveData = repository.registerFirebaseUser(email, password);
    }

}
