package com.example.babyone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BabyDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_details);

        // Set click listener for the back button
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            // Finish the current activity to go back
            finish();
        });
        // Set click listener for the vaccines button
        Button btnVaccines = findViewById(R.id.btnVaccines);
        btnVaccines.setOnClickListener(v -> {
            // Start a new activity to display the vaccines
            Intent intent = new Intent(BabyDetailsActivity.this, VaccinesNewActivity.class);
            startActivity(intent);
        });

        // Retrieve the data passed from the previous activity
        String babyName = getIntent().getStringExtra("babyName");
        String parentName = getIntent().getStringExtra("parentName");
        String email = getIntent().getStringExtra("email");

        // Update the UI with the retrieved data
        TextView tvBabyName = findViewById(R.id.txtBabyName);
        TextView tvParentName = findViewById(R.id.txtParentName);
        TextView tvEmail = findViewById(R.id.txtEmail);

        tvBabyName.setText(babyName);
        tvParentName.setText(parentName);
        tvEmail.setText(email);

    }
}
