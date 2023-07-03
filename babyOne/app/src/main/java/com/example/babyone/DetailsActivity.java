package com.example.babyone;

import static com.example.babyone.FirestoreHelper.readFromCollection;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
//THIS IS USED FOR BABY - VACCINE CONNECTING
public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";
    private FirebaseFirestore db;
    private LinearLayout linearLayoutData;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the instance of FirebaseFirestore
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        linearLayoutData = findViewById(R.id.linear_layout_data);

        // Read data from "users" collection
        //readUsersCollection();

        // Read data from "vaccinations" collection
        readVaccinationsCollection();

        String collectionName = "guardians";
        String uid = firebaseUser.getUid();
        //method for reading is used
        // Call the modified method to retrieve the data and obtain the HashMap
//        HashMap<String, Map<String, Object>> dataMap = readFromCollection(db, collectionName,uid);
//        System.out.println("Name: ");
//        for (Map.Entry<String, Map<String, Object>> entry : dataMap.entrySet()) {
//            String documentId = entry.getKey();
//            Map<String, Object> data = entry.getValue();
//
//            // Print the document ID
//            System.out.println("Document ID: " + documentId);
//
//            // Print the values of the data map
//            for (Map.Entry<String, Object> fieldEntry : data.entrySet()) {
//                String fieldName = fieldEntry.getKey();
//                Object fieldValue = fieldEntry.getValue();
//                System.out.println(fieldName + ": " + fieldValue);
//            }
//
//            System.out.println();
//        }
    }
    /*
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
    */


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

                            // Calculate the next vaccination date
                            String birthDate = "2023-06-25"; // Replace with the actual birth date
                            String nextVaccineDate = calculateNextVaccineDate(birthDate, weeksFromBirth);

                            // Create a TextView to display vaccination data
                            TextView textViewVaccination = new TextView(DetailsActivity.this);
                            textViewVaccination.setText("Vaccination Name: " + name + "\nWeeks from Birth: " + weeksFromBirth);

                            // Add the TextView to the LinearLayout
                            linearLayoutData.addView(textViewVaccination);

                            // Store the details in the Firestore "vaccinations" collection
                            Map<String, Object> updatedVaccination = new HashMap<>();
                            updatedVaccination.put("name", name);
                            updatedVaccination.put("weeksFromBirth", weeksFromBirth);
                            updatedVaccination.put("nextVaccineDate", nextVaccineDate);

                            String documentId = documentSnapshot.getId();
                            DocumentReference vaccinationRef = vaccinationsCollectionRef.document(documentId);
                            vaccinationRef.update(updatedVaccination)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "Vaccination details updated for document ID: " + documentId);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error updating vaccination details for document ID: " + documentId, e);
                                        }
                                    });
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

    private String calculateNextVaccineDate(String birthDate, long weeksFromBirth) {
        // Perform the calculation to get the next vaccination date
        // You need to provide the appropriate implementation based on your specific requirements and date format

        // For example, assuming birthDate is in "yyyy-MM-dd" format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(birthDate));
            calendar.add(Calendar.DAY_OF_YEAR, (int) (weeksFromBirth * 7));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Format the calculated date as desired
        String nextVaccineDate = dateFormat.format(calendar.getTime());

        return nextVaccineDate;
    }

}
