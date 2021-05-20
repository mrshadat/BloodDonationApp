package com.mrshadat.blooddonationapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.mrshadat.blooddonationapp.databinding.FragmentMainBinding;
import com.mrshadat.blooddonationapp.databinding.FragmentRegisterDonorBinding;
import com.mrshadat.blooddonationapp.pojos.Donor;
import com.mrshadat.blooddonationapp.viewmodels.DonorViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterDonorFragment extends Fragment {

    private FragmentRegisterDonorBinding binding;
    private String lastDonation = null;
    private long days;
    private DonorViewModel donorViewModel;
    private ProgressDialog pd;


    public RegisterDonorFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterDonorBinding.inflate(LayoutInflater.from(getActivity()));
        donorViewModel = new ViewModelProvider(requireActivity()).get(DonorViewModel.class);
        pd = new ProgressDialog(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnRegisterDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String donorName = binding.donorFullName.getEditText().getText().toString();
                String donorPhone = binding.donorPhoneNumber.getEditText().getText().toString();
                String donorArea = binding.donorArea.getEditText().getText().toString();
                String donorBloodGroup = binding.donorBloodGroup.getEditText().getText().toString();
                String donorAge = binding.donorAge.getEditText().getText().toString();

                if (donorName.isEmpty()) {
                    binding.donorFullName.getEditText().setError("Enter full name");
                } else if (donorPhone.isEmpty()) {
                    binding.donorPhoneNumber.getEditText().setError("Phone number is mandatory");
                } else if (donorArea.isEmpty()) {
                    binding.donorArea.getEditText().setError("Please provide area");
                } else if (donorAge.isEmpty()) {
                    binding.donorAge.getEditText().setError("Please provide your age");
                } else if (donorBloodGroup.isEmpty()) {
                    binding.donorArea.getEditText().setError("Blood group is mandatory");
                } else {

                    Donor donor = new Donor(donorName, donorPhone, donorArea, donorAge, donorBloodGroup, String.valueOf(days));
                    donorViewModel.saveDonor(donor);
                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getActivity(), R.id.btn_register_donor).navigate(R.id.mainFragment);
                }
            }
        });

        binding.btnDonorLastDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(
                getActivity(),
                dateSetListener,
                year, month, day
        );
        dpd.show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(i, i1, i2);
            lastDonation = new SimpleDateFormat("dd MMM, YYYY")
                    .format(calendar.getTime());
            Date date = calendar.getTime();
            days = (new Date().getTime() - date.getTime()) / 86400000;
            binding.btnDonorLastDonation.setText(lastDonation);
        }
    };

}