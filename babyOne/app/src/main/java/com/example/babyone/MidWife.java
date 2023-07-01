package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class MidWife extends AppCompatActivity {

    private SearchView searchView;
    private MaterialCardView card2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_wife);

        searchView = findViewById(R.id.searchView2);
        card2 = findViewById(R.id.card2);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                card2.setVisibility(View.VISIBLE);
                Toast.makeText(MidWife.this, "searching", Toast.LENGTH_SHORT).show();
                //retrieve data from the database and display relevent data in the card view
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MainLandin activity
                Intent intent = new Intent(MidWife.this, MainLanding.class);
                startActivity(intent);
            }
        });
    }
}