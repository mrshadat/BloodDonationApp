package com.mrshadat.blooddonationapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrshadat.blooddonationapp.adapters.DonorAdapter;
import com.mrshadat.blooddonationapp.databinding.FragmentDonorListBinding;
import com.mrshadat.blooddonationapp.pojos.Donor;
import com.mrshadat.blooddonationapp.viewmodels.DonorViewModel;

import java.util.List;


public class DonorListFragment extends Fragment {

    private DonorViewModel donorViewModel;
    private DonorAdapter adapter;
    private FragmentDonorListBinding binding;

    public DonorListFragment() {
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
        donorViewModel = new ViewModelProvider(requireActivity()).get(DonorViewModel.class);
        binding = FragmentDonorListBinding.inflate(LayoutInflater.from(getActivity()));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        donorViewModel.donorListLD.observe(getViewLifecycleOwner(), new Observer<List<Donor>>() {
            @Override
            public void onChanged(List<Donor> donorList) {
                adapter = new DonorAdapter(getActivity(), donorList);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                binding.donorRecyclerView.setLayoutManager(llm);
                binding.donorRecyclerView.setAdapter(adapter);
            }
        });
    }
}