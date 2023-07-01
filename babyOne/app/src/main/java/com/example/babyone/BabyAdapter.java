package com.example.babyone;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.ViewHolder> {

    private List<Guardian> guardians;

    public BabyAdapter(List<Guardian> guardians) {
        this.guardians = guardians;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_baby, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Guardian guardian = guardians.get(position);
        holder.btnBaby.setText(guardian.getBabyName());

        holder.btnBaby.setOnClickListener(v -> {
            // Handle button click event
            // Get the selected guardian data
            String babyName = guardian.getBabyName();
            String parentName = guardian.getParentName();
            String email = guardian.getEmail();

            // Start a new activity or fragment and pass the data
            Intent intent = new Intent(v.getContext(), BabyDetailsActivity.class);
            intent.putExtra("babyName", babyName);
            intent.putExtra("parentName", parentName);
            intent.putExtra("email", email);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return guardians.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button btnBaby;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnBaby = itemView.findViewById(R.id.btnBaby);
        }
    }
}