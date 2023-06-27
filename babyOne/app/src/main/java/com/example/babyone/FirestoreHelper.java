package com.example.babyone;

import android.app.Activity;
import android.content.Context;
import android.widget.*;

import com.google.firebase.firestore.*;

import java.util.*;

public class FirestoreHelper {

    public static void addToFirestore(String collectionName, HashMap<String, String> data, Context context, Activity activity
    ) {
        // Get the Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Specify the collection name in Firestore where you want to store the data
        CollectionReference collection = db.collection(collectionName);

        // Add the data HashMap as a document to the collection
        collection.add(data)
                .addOnSuccessListener(documentReference -> {
                    // Document was successfully added
                    Toast.makeText(context, "Data added to Firestore!", Toast.LENGTH_SHORT).show();
                    // Handle the success or navigate to another activity
                })
                .addOnFailureListener(e -> {
                    // Error occurred while adding the document
                    Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    // Handle the failure
                });
    }
}
