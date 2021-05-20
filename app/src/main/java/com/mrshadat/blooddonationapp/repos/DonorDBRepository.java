package com.mrshadat.blooddonationapp.repos;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mrshadat.blooddonationapp.pojos.Donor;

import java.util.ArrayList;
import java.util.List;

public class DonorDBRepository {
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference donorRef;
    private FirebaseUser firebaseUser;
    public MutableLiveData<List<Donor>> donorListLD = new MutableLiveData<>();

    public DonorDBRepository(){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child(firebaseUser.getUid());
        donorRef = rootRef.child("Donors");

        donorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Donor> donor = new ArrayList<>();
                for (DataSnapshot d : snapshot.getChildren()){
                     donor.add(d.getValue(Donor.class));
                }
                donorListLD.postValue(donor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addNewDonorToDB(Donor donor){
        String donorId = donorRef.push().getKey();
        donor.setDonorID(donorId);
        donorRef.child(donorId).setValue(donor).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
