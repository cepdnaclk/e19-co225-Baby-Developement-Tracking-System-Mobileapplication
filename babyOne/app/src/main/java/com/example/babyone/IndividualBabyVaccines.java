package com.example.babyone;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class IndividualBabyVaccines extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_individual_baby_vaccines);

        // Initialize Firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Specify the collection and subcollection names
        String collectionName = "guardians";
        String subcollectionName = "vaccines";

        // Specify the email to query the subcollection
        String email = "harithabeysinghe@gmail.com";

        // Reference to the specified collection
        CollectionReference collectionRef = db.collection(collectionName);

        // Query the collection based on email
        collectionRef.whereEqualTo("email", email).get()
                .addOnSuccessListener(querySnapshot -> {
                    // Iterate over the documents in the QuerySnapshot
                    for (DocumentSnapshot documentSnapshot : querySnapshot) {
                        // Retrieve the subcollection reference using the subcollection name
                        CollectionReference subcollectionRef = documentSnapshot.getReference().collection(subcollectionName);

                        // Query the subcollection documents
                        subcollectionRef.get()
                                .addOnSuccessListener(subcollectionSnapshot -> {
                                    // Process the subcollection documents
                                    for (DocumentSnapshot subdocumentSnapshot : subcollectionSnapshot) {
                                        // Retrieve the data as a map of field names and values
                                        Map<String, Object> data = subdocumentSnapshot.getData();
                                        if (data != null) {
                                            // Get the subdocument ID
                                            String subdocumentId = subdocumentSnapshot.getId();

                                            // Access the fields in the data map
                                            String vaccinationName = (String) data.get("name");
                                            String formattedDate = (String) data.get("date");
                                            int status = ((Long) data.get("status")).intValue();
                                            String icon = (String) data.get("icon");

                                            // Print the retrieved data
                                            System.out.println( "Subdocument ID: " +  subdocumentId);
                                            System.out.println("Vaccination Name: " + vaccinationName);
                                            System.out.println("Formatted Date: " + formattedDate);
                                            System.out.println("Status: " + status);
                                            System.out.println("Icon: " + icon);
                                            System.out.println("");
                                        }
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    Log.e("IndividualBabyVaccines", "Error getting documents from subcollection: " + subcollectionName, e);
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("IndividualBabyVaccines", "Error getting documents from collection: " + collectionName, e);
                });
    }
}
