package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class Doctor extends AppCompatActivity {

    private SearchView searchView;
    private MaterialCardView card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        searchView = findViewById(R.id.searchView);
        card1 = findViewById(R.id.card1);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                card1.setVisibility(View.VISIBLE);
                Toast.makeText(Doctor.this, "searching", Toast.LENGTH_SHORT).show();
                //retrieve data from the database and display relevent data in the card view
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MainLandin activity
                Intent intent = new Intent(Doctor.this, MainLanding.class);
                startActivity(intent);
            }
        });


    }
}