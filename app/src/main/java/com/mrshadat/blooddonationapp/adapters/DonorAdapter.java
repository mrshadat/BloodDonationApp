package com.mrshadat.blooddonationapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mrshadat.blooddonationapp.R;
import com.mrshadat.blooddonationapp.databinding.DonorRowBinding;
import com.mrshadat.blooddonationapp.pojos.Donor;

import java.util.List;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DonorViewHolder> {

    private Context context;
    private List<Donor> donorList;
    private DonorRowBinding binding;


    public DonorAdapter(Context context, List<Donor> donorList) {
        this.context = context;
        this.donorList = donorList;
    }

    @NonNull
    @Override
    public DonorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.donor_row, parent, false);
        return new DonorViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull DonorViewHolder holder, int position) {
        String lastDonation = "last donation " + donorList.get(position).getLastDonation() + " days ago";
        binding.rowDonorName.setText(donorList.get(position).getDonorName());
        binding.rowDonorArea.setText(donorList.get(position).getDonorArea());
        binding.rowLastDonation.setText(lastDonation);
        binding.dialImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri phoneUri = Uri.parse("tel:"+ donorList.get(position).getDonorPhone());
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, phoneUri);
                if (dialIntent.resolveActivity(context.getPackageManager()) != null){
                    context.startActivity(dialIntent);
                }else{
                    Toast.makeText(context, "no component found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return donorList.size();
    }

    class DonorViewHolder extends RecyclerView.ViewHolder {
        public DonorViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
