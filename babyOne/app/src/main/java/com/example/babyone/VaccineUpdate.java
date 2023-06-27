package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VaccineUpdate extends AppCompatActivity {

    Button btnback_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_update);

        btnback_2 = findViewById(R.id.btnBack_vaccine);

        /*ACTION WHICH OCCURS WHEN THE BACK BUTTON IS CLICKED*/
        btnback_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}