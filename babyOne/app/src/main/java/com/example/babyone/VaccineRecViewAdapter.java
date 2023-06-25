package com.example.babyone;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VaccineRecViewAdapter extends RecyclerView.Adapter<VaccineRecViewAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtVaccineType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVaccineType = itemView.findViewById(R.id.txtvVaccineType);
        }
    }
}
