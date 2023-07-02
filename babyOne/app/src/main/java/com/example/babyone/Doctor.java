package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends AppCompatActivity {

    private SearchView searchView;
    /*private MaterialCardView card1;*/
    private FirebaseFirestore db;

    private RecyclerView recyclerView;

    private BabyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        searchView = findViewById(R.id.searchView);
        /*card1 = findViewById(R.id.card1);*/
        recyclerView = findViewById(R.id.babyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db = FirebaseFirestore.getInstance();

        // Specify the collection name
        String collectionName = "guardians";

        adapter = new BabyAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Doctor.this, "searching " + query, Toast.LENGTH_SHORT).show();

                // Retrieve data from the database and update the adapter with relevant data
                db.collection("guardians")
                        .whereGreaterThanOrEqualTo("babyname", query)
                        .whereLessThan("babyname", query + "z")
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                List<Guardian> guardians = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String babyName = document.getString("babyname");
                                    String parentName = document.getString("parentname");
                                    String email = document.getString("email");

                                    Guardian guardian = new Guardian(babyName, parentName, email);
                                    guardians.add(guardian);
                                }

                                adapter.updateData(guardians);
                            }
                        });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                Toast.makeText(Doctor.this, "searching " + query, Toast.LENGTH_SHORT).show();

                // Retrieve data from the database and update the adapter with relevant data
                db.collection("guardians")
                        .whereGreaterThanOrEqualTo("babyname", query)
                        .whereLessThan("babyname", query + "z")
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                List<Guardian> guardians = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String babyName = document.getString("babyname");
                                    String parentName = document.getString("parentname");
                                    String email = document.getString("email");

                                    Guardian guardian = new Guardian(babyName, parentName, email);
                                    guardians.add(guardian);
                                }

                                adapter.updateData(guardians);
                            }
                        });

                return false;
            }

        });

        /*card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MainLandin activity
                // Assuming you have a button or click event to navigate to MainLanding
                Intent intent = new Intent(Doctor.this, MainLanding.class);
                intent.putExtra("sourceFragment", "doctor");
                startActivity(intent);
            }
        });*/

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