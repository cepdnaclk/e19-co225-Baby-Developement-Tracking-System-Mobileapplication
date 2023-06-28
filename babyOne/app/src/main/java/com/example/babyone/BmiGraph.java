package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BmiGraph extends AppCompatActivity {

    Button btnBack_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_graph);

        btnBack_1 = findViewById(R.id.btnBack_medicine);

        /*ACTION WHICH OCCURS WHEN THE BACK BUTTON IS CLICKED*/
        btnBack_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}