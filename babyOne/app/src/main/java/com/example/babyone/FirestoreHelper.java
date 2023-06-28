package com.example.babyone;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.*;

import com.google.firebase.firestore.*;

import java.util.*;

public class FirestoreHelper {

    public static void addToFirestore(String collectionName, HashMap<String, String> data, Context context, Activity activity) {
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

    public static HashMap<String, Map<String, Object>> readFromCollection(FirebaseFirestore db, String collectionName) {
        HashMap<String, Map<String, Object>> dataMap = new HashMap<>();

        // Reference to the specified collection
        CollectionReference collectionRef = db.collection(collectionName);

        // Read the documents in the collection
        collectionRef.get()
                .addOnSuccessListener(querySnapshot -> {
                    // Iterate over the documents in the QuerySnapshot
                    for (DocumentSnapshot documentSnapshot : querySnapshot) {
                        // Retrieve the data as a map of field names and values
                        Map<String, Object> data = documentSnapshot.getData();
                        if (data != null) {
                            // Get the document ID as the key in the HashMap
                            String documentId = documentSnapshot.getId();
                            // Add the data map to the HashMap with the document ID as the key
                            dataMap.put(documentId, data);
                        }
                    }

                    // Print the results
                    for (Map.Entry<String, Map<String, Object>> entry : dataMap.entrySet()) {
                        String documentId = entry.getKey();
                        Map<String, Object> data = entry.getValue();
                        System.out.println("Document ID: " + documentId);
                        for (Map.Entry<String, Object> fieldEntry : data.entrySet()) {
                            String fieldName = fieldEntry.getKey();
                            Object fieldValue = fieldEntry.getValue();
                            System.out.println(fieldName + ": " + fieldValue);
                        }
                        System.out.println();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("FirestoreHelper", "Error getting documents from collection: " + collectionName, e);
                });

        return dataMap;
    }


    /*
    public static void readFromCollection(FirebaseFirestore db, String collectionName, ViewGroup viewGroup) {
        // Reference to the specified collection
        CollectionReference collectionRef = db.collection(collectionName);

        // Read the documents in the collection
        collectionRef.get()
                .addOnSuccessListener(querySnapshot -> {
                    // Iterate over the documents in the QuerySnapshot
                    for (DocumentSnapshot documentSnapshot : querySnapshot) {
                        // Retrieve the data as a map of field names and values
                        Map<String, Object> data = documentSnapshot.getData();
                        if (data != null) {
                            // Create a StringBuilder to store the user data
                            StringBuilder userData = new StringBuilder();

                            // Iterate over the fields and values
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                String fieldName = entry.getKey();
                                Object fieldValue = entry.getValue();

                                // Append the field name and value to the StringBuilder
                                userData.append(fieldName).append(": ").append(fieldValue).append("\n");
                            }

                            // Create a TextView to display user data
                            TextView textViewUser = new TextView(viewGroup.getContext());
                            textViewUser.setText(userData.toString());

                            // Add the TextView to the ViewGroup
                            viewGroup.addView(textViewUser);
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("FirestoreHelper", "Error getting documents from collection: " + collectionName, e);
                });
    }

     */

}
