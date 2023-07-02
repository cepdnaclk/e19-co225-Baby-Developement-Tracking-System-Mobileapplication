package com.example.babyone;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.firestore.FirebaseFirestore;


public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        // Get the instance of FirebaseFirestore
        db = FirebaseFirestore.getInstance();
    /*
        // Create a new vaccination with name and weeks from birth
        Map<String, Object> vaccination = new HashMap<>();
        vaccination.put("name", "Vaccination C");
        vaccination.put("weeksFromBirth", 5);

        // Add the vaccination document to the "vaccinations" collection
        db.collection("vaccinations")
                .add(vaccination)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Vaccination details added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding vaccination details", e);
                    }
                });

     */

        String guardiansCollection = "guardians";
        String vaccinationsCollection = "standardvaccinations";

        BabyVaccination.calculateAndStoreVaccineData(db, guardiansCollection, vaccinationsCollection,"e19443@eng.pdn.ac.lk");

    }
}