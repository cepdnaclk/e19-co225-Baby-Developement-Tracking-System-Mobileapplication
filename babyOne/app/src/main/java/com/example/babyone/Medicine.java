package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Medicine extends AppCompatActivity {

    Button btnBack_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        btnBack_1 = findViewById(R.id.btnBack_medicine);

        /*ACTION WHICH OCCURS WHEN THE BACK BUTTON IS CLICKED*/
        btnBack_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an instance of the profileFragment
                profileFragment fragment = new profileFragment();

                // Get the FragmentManager
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Begin the fragment transaction
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                // Replace the current fragment with the profileFragment
                transaction.replace(R.id.profileFragment, fragment);

                // Add the transaction to the back stack (optional)
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

            }
        });
    }
}