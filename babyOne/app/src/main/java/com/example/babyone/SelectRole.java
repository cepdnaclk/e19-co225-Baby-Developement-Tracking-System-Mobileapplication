package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.card.MaterialCardView;

public class SelectRole extends AppCompatActivity {

    MaterialCardView btnGuardian,btnDoctor,btnMidWife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);

        btnGuardian = findViewById(R.id.btnGuardian);
        btnDoctor = findViewById(R.id.btnDoctor);
        btnMidWife = findViewById(R.id.btnMidWife);

        btnGuardian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the DoctorRegistration activity
                Intent intent = new Intent(SelectRole.this, FirstTimeGuardian.class);
                startActivity(intent);
            }
        });

        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the DoctorRegistration activity
                Intent intent = new Intent(SelectRole.this, DoctorRegistration.class);
                startActivity(intent);
            }
        });

        btnMidWife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the DoctorRegistration activity
                Intent intent = new Intent(SelectRole.this, MidWifeRegistration.class);
                startActivity(intent);
            }
        });

    }
}