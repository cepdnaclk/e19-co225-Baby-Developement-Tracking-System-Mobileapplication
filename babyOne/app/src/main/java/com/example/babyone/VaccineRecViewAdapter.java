package com.example.babyone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class VaccineRecViewAdapter extends RecyclerView.Adapter<VaccineRecViewAdapter.ViewHolder> {

    private ArrayList<VaccineModel> vaccines = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccine_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtVaccineType.setText(vaccines.get(position).getVaccineType());
        holder.txtvVIDate.setText(vaccines.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return vaccines.size();
    }

    public void setVaccines(ArrayList<VaccineModel> vaccines) {
        this.vaccines = vaccines;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtVaccineType;
        private TextView txtvVIDate;
        private MaterialCardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVaccineType = itemView.findViewById(R.id.txtvVIVaccineType);
            txtvVIDate = itemView.findViewById(R.id.txtvVIDate);
            parent = itemView.findViewById(R.id.cardviewVI);
        }
    }
}
