package com.example.babyone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DoctorView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorview);

        recyclerView = findViewById(R.id.babyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();

        // Specify the collection name
        String collectionName = "guardians";



        // Query the guardians collection to retrieve all documents
        db.collection(collectionName)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Guardian> guardians = new ArrayList<>();

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Extract the guardian data from the document
                            String babyName = document.getString("babyname");
                            String parentName = document.getString("parentname");
                            String email = document.getString("email");

                            // Create a Guardian object
                            Guardian guardian = new Guardian(babyName, parentName, email);
                            guardians.add(guardian);
                        }

                        // Set up the RecyclerView adapter
                        BabyAdapter adapter = new BabyAdapter(guardians, this);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // Handle error
                    }
                });
    }
}
