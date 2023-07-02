package com.example.babyone;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class VaccinesNewActivity extends AppCompatActivity {

    private ListView vaccineListView;
    private FirebaseFirestore db;
    private String guardianDocumentId; // Guardian document ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines_new);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();

        vaccineListView = findViewById(R.id.vaccineListView);

        // Get the guardian document ID passed from the previous activity
        guardianDocumentId = getIntent().getStringExtra("guardianDocumentId");

        // Read vaccines from the subcollection and display them in the ListView
        readVaccines();
    }

    private void readVaccines() {
        CollectionReference vaccinesRef = db.collection("guardians").document(guardianDocumentId).collection("vaccines");

        vaccinesRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<String> vaccines = new ArrayList<>();

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Retrieve the vaccine name from the document
                            String vaccineName = document.getString("name");
                            vaccines.add(vaccineName);
                        }

                        // Display the vaccines in the ListView
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(VaccinesNewActivity.this,
                                android.R.layout.simple_list_item_1, vaccines);
                        vaccineListView.setAdapter(adapter);
                    } else {
                        Log.e("VaccinesActivity", "Error getting vaccines", task.getException());
                    }
                });
    }
}
