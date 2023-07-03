package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;



public class MidWife extends AppCompatActivity {

    private SearchView searchView;
    /*private MaterialCardView card1;*/
    private FirebaseFirestore db;

    private RecyclerView recyclerView;

    private BabyAdapterMidWife adapter;
    private TextView welcomeMidwife;

    private Button btnLogout;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_wife);

        searchView = findViewById(R.id.searchView2);
        /*card1 = findViewById(R.id.card1);*/
        recyclerView = findViewById(R.id.babyRecyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db = FirebaseFirestore.getInstance();
        welcomeMidwife = findViewById(R.id.welcomeMidwife);

        btnLogout = findViewById(R.id.btnLogOut);
        googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN);

        // Get the current user from Firebase Authentication
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            System.out.println(email);

            // Query the doctors collection to retrieve the doctor document by email
            db.collection("MidWifeLog")
                    .whereEqualTo("email", email)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String doctorName = document.getString("name");
                                welcomeMidwife.setText("Welcome Back!\n Midwife " + doctorName);
                            }
                        } else {
                            // Handle error
                        }
                    });
        } else {
            // Handle the case when the current user is not available
        }

        // Specify the collection name
        String collectionName = "guardians";

        adapter = new BabyAdapterMidWife(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MidWife.this, "searching " + query, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(MidWife.this, "searching " + query, Toast.LENGTH_SHORT).show();

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
                        BabyAdapterMidWife adapter = new BabyAdapterMidWife(guardians, this);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // Handle error
                    }
                });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out from Google
                googleSignInClient.signOut()
                        .addOnCompleteListener(MidWife.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // Redirect to the login screen or perform any other desired action
                                Intent intent = new Intent(MidWife.this, LoginActivity.class);
                                intent.putExtra("sourceActivity", "Doctor");
                                startActivity(intent);
                                finish();
                            }
                        });
            }
        });


    }
}