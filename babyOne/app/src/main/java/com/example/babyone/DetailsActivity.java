package com.example.babyone;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";
    private FirebaseFirestore db;
    private LinearLayout linearLayoutData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the instance of FirebaseFirestore
        db = FirebaseFirestore.getInstance();

        linearLayoutData = findViewById(R.id.linear_layout_data);

        // Read data from "users" collection
        readUsersCollection();

        // Read data from "vaccinations" collection
        readVaccinationsCollection();
    }

    private void readUsersCollection() {
        // Reference to the "users" collection
        CollectionReference usersCollectionRef = db.collection("users");

        // Read the documents in the "users" collection
        usersCollectionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        // Iterate over the documents in the QuerySnapshot
                        for (DocumentSnapshot documentSnapshot : querySnapshot) {
                            // Retrieve the data from each document
                            String babyName = documentSnapshot.getString("Baby Name");
                            String birthDate = documentSnapshot.getString("Birth Date");

                            // Create a TextView to display user data
                            TextView textViewUser = new TextView(DetailsActivity.this);
                            textViewUser.setText("Baby Name: " + babyName + "\nBirth Date: " + birthDate);

                            // Add the TextView to the LinearLayout
                            linearLayoutData.addView(textViewUser);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error getting documents from 'users' collection", e);
                    }
                });
    }

    private void readVaccinationsCollection() {
        // Reference to the "vaccinations" collection
        CollectionReference vaccinationsCollectionRef = db.collection("vaccinations");

        // Read the documents in the "vaccinations" collection
        vaccinationsCollectionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        // Iterate over the documents in the QuerySnapshot
                        for (DocumentSnapshot documentSnapshot : querySnapshot) {
                            // Retrieve the data from each document
                            String name = documentSnapshot.getString("name");
                            long weeksFromBirth = documentSnapshot.getLong("weeksFromBirth");

                            // Create a TextView to display vaccination data
                            TextView textViewVaccination = new TextView(DetailsActivity.this);
                            textViewVaccination.setText("Vaccination Name: " + name + "\nWeeks from Birth: " + weeksFromBirth);

                            // Add the TextView to the LinearLayout
                            linearLayoutData.addView(textViewVaccination);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error getting documents from 'vaccinations' collection", e);
                    }
                });
    }
}
