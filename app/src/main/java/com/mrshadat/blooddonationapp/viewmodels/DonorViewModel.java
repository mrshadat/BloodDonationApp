package com.mrshadat.blooddonationapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrshadat.blooddonationapp.pojos.Donor;
import com.mrshadat.blooddonationapp.repos.DonorDBRepository;

import java.util.List;

public class DonorViewModel  extends ViewModel {

    private DonorDBRepository dbRepository;
    public MutableLiveData<List<Donor>> donorListLD = new MutableLiveData<>();

    public DonorViewModel(){
        dbRepository = new DonorDBRepository();
        donorListLD = dbRepository.donorListLD;
    }

    public void saveDonor(Donor donor){
        dbRepository.addNewDonorToDB(donor);
    }
}
