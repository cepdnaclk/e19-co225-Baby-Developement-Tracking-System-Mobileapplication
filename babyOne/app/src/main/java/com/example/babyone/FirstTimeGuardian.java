package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class FirstTimeGuardian extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment currentFragment;
    private Fragment fragPersonal;
    private Fragment fragBaby;
    private Button btnBack;
    private Button btnNext;

    HashMap<String, String> personalInfo = new HashMap<>();
    HashMap<String, String> babyInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_guardian);

        // Initialize the fragments
        fragPersonal = new first_time_guardian_personal();
        fragBaby = new first_time_guardian_baby();

        // Initialize the FragmentManager
        fragmentManager = getSupportFragmentManager();

        // Add the first fragment to the container
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragcontFTGuardian, fragPersonal);
        fragmentTransaction.commit();
        currentFragment = fragPersonal;

        // Initialize the navigation buttons
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        // Initialize data fields


        // Set up the click listener for the Prev button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentFragment == fragBaby) {
                    // Replace the public info fragment with the personal info fragment
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                    fragmentTransaction.replace(R.id.fragcontFTGuardian, fragPersonal);
                    fragmentTransaction.commit();
                    currentFragment = fragPersonal;
                    btnNext.setText(R.string.strvalBTNNext);
                    btnBack.setEnabled(false);
                }
            }
        });

        // Set up the click listener for the Next button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentFragment == fragPersonal) {
                    // Replace the personal info fragment with the public info fragment
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                    fragmentTransaction.replace(R.id.fragcontFTGuardian, fragBaby);
                    fragmentTransaction.commit();
                    currentFragment = fragBaby;
                    btnNext.setText(R.string.strvalEBBTNSubmit);
                    btnBack.setEnabled(true);
                } else {
                    // Submit the registration form
                    submitForm();
                }
            }
        });
    }

    private void submitForm() {
        // Validate and submit the registration form
        personalInfo = ((first_time_guardian_personal) fragPersonal).getInfoHashMap();
        babyInfo = ((first_time_guardian_baby) fragBaby).getInfoHashMap();

        // Combine personalInfo and babyInfo into a single hashmap
        HashMap<String, String> registrationInfo = new HashMap<>();
        registrationInfo.putAll(personalInfo);
        registrationInfo.putAll(babyInfo);
        /*
        // Get the Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Specify the collection name in Firestore where you want to store the data
        CollectionReference guardiansCollection = db.collection("guardians");
        //Toast.makeText(this, "First time registered!! \n "+personalInfo.toString(), Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(FirstTimeGuardian.this, ProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        // Add the personalInfo HashMap as a document to the collection
        guardiansCollection.add(registrationInfo)
                .addOnSuccessListener(documentReference -> {
                    // Document was successfully added
                    Toast.makeText(this, "First time registered!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FirstTimeGuardian.this, ProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                })
                .addOnFailureListener(e -> {
                    // Error occurred while adding the document
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

         */


        String collectionName = "guardians";
        FirestoreHelper.addToFirestore(collectionName, registrationInfo, this, FirstTimeGuardian.this);

    }
}