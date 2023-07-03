package com.example.babyone;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;

import java.util.*;

public class FirestoreHelper {

    //Implement onDataLoaded method to slove Firebase Asyncrones nature.
    public interface FirestoreDataCallback {
        void onDataLoaded(HashMap<String, Map<String, Object>> dataMap);
    }

    public static void addToFirestore(String collectionName, HashMap<String, Object> data, Context context, Activity activity) {
        // Get the Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String email = firebaseUser.getEmail();

        // Specify the collection name in Firestore where you want to store the data
        CollectionReference collection = db.collection(collectionName);
        data.put("email", email);
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
        String guardiansCollection = "guardians";
        String vaccinationsCollection = "standardvaccinations";

        BabyVaccination.calculateAndStoreVaccineData(db, guardiansCollection, vaccinationsCollection,email);
    }

    public static void readFromCollection(FirebaseFirestore db, String collectionName, String email, FirestoreDataCallback callback) {
        HashMap<String, Map<String, Object>> dataMap = new HashMap<>();

        // Reference to the specified collection
        CollectionReference collectionRef = db.collection(collectionName);

        Query query = collectionRef.whereEqualTo("email", email);

        // Read the documents matching the query
        query.get()
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

                    // Invoke the callback with the retrieved data
                    callback.onDataLoaded(dataMap);
                })
                .addOnFailureListener(e -> {
                    Log.w("FirestoreHelper", "Error getting documents from collection: " + collectionName, e);
                });
    }

    public static void readFromSubcollection(FirebaseFirestore db, String collectionName, String email, String subcollectionName, FirestoreDataCallback callback) {
        HashMap<String, Map<String, Object>> dataMap = new HashMap<>();

        // Reference to the specified collection
        CollectionReference collectionRef = db.collection(collectionName);

        // Query the collection to filter by the guardian's email
        Query query = collectionRef.whereEqualTo("email", email);

        // Read the documents in the filtered collection
        query.get()
                .addOnSuccessListener(querySnapshot -> {
                    // Iterate over the documents in the QuerySnapshot
                    for (DocumentSnapshot documentSnapshot : querySnapshot) {
                        // Retrieve the subcollection reference using the subcollection name
                        CollectionReference subcollectionRef = documentSnapshot.getReference().collection(subcollectionName);

                        // Read the documents in the subcollection
                        subcollectionRef.get()
                                .addOnSuccessListener(subcollectionSnapshot -> {
                                    // Iterate over the documents in the subcollection Snapshot
                                    for (DocumentSnapshot subdocumentSnapshot : subcollectionSnapshot) {
                                        // Retrieve the data as a map of field names and values
                                        Map<String, Object> data = subdocumentSnapshot.getData();
                                        if (data != null) {
                                            // Get the subdocument ID as the key in the HashMap
                                            String subdocumentId = subdocumentSnapshot.getId();
                                            // Add the data map to the HashMap with the subdocument ID as the key
                                            dataMap.put(subdocumentId, data);
                                        }
                                    }
                                    // Invoke the callback with the retrieved data
                                    callback.onDataLoaded(dataMap);
                                })
                                .addOnFailureListener(e -> {
                                    Log.e("FirestoreHelper", "Error getting documents from subcollection: " + subcollectionName, e);
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("FirestoreHelper", "Error getting documents from collection: " + collectionName, e);
                });
    }

}
