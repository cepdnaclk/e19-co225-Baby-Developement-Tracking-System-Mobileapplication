package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class VaccineActivity extends AppCompatActivity {


    private RecyclerView recviewVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        recviewVaccine = findViewById(R.id.recviewVaccine);
    }
}