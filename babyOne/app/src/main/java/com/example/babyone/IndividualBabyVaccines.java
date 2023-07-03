package com.example.babyone;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
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

        //UPDATING VACCINE STATUS
        IndividualBabyVaccines.changeVaccineStatus(email, "Corona", 0);

    }


    public static void changeVaccineStatus(String email, String vaccineName, int newStatus) {
        // Initialize Firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Specify the collection and subcollection names
        String collectionName = "guardians";
        String subcollectionName = "vaccines";

        // Query the collection based on the email field
        CollectionReference collectionRef = db.collection(collectionName);
        Query query = collectionRef.whereEqualTo("email", email);

        query.get().addOnSuccessListener(querySnapshot -> {
            for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                CollectionReference subcollectionRef = documentSnapshot.getReference().collection(subcollectionName);

                // Query the subcollection based on the vaccine name
                Query subcollectionQuery = subcollectionRef.whereEqualTo("name", vaccineName);
                subcollectionQuery.get().addOnSuccessListener(subcollectionSnapshot -> {
                    for (DocumentSnapshot subdocumentSnapshot : subcollectionSnapshot.getDocuments()) {
                        String subdocumentId = subdocumentSnapshot.getId();
                        subcollectionRef.document(subdocumentId)
                                .update("status", newStatus)
                                .addOnSuccessListener(aVoid -> {
                                    // Status updated successfully
                                    System.out.println("Status updated for subdocument ID: " + subdocumentId);
                                })
                                .addOnFailureListener(e -> {
                                    // Error updating status
                                    System.out.println("Error updating status for subdocument ID: " + subdocumentId);
                                    e.printStackTrace();
                                });
                    }
                }).addOnFailureListener(e -> {
                    // Error accessing subcollection or finding vaccine by name
                    System.out.println("Error accessing subcollection: " + subcollectionName);
                    e.printStackTrace();
                });
            }
        }).addOnFailureListener(e -> {
            // Error querying the collection
            System.out.println("Error querying collection: " + collectionName);
            e.printStackTrace();
        });
    }


}
