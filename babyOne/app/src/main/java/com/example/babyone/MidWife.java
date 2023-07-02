package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Map;

public class MidWife extends AppCompatActivity {

    private SearchView searchView;
    private MaterialCardView card2;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_wife);

        searchView = findViewById(R.id.searchView2);
        card2 = findViewById(R.id.card2);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                card2.setVisibility(View.VISIBLE);
                Toast.makeText(MidWife.this, "searching", Toast.LENGTH_SHORT).show();
                //retrieve data from the database and display relevent data in the card view
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBabyHeightAndWeight();
                // Start the MainLanding activity
                // Assuming you have a button or click event to navigate to MainLanding
                Intent intent = new Intent(MidWife.this, MainLanding.class);
                intent.putExtra("sourceFragment", "midwife");
                startActivity(intent);

            }
        });

    }

    private void updateBabyHeightAndWeight() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String collectionName = "guardians";

        db.collection(collectionName)
                .whereEqualTo("kgdasuntheekshana@gmail.com", email)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        String babyId = documentSnapshot.getId();

                        // Assuming "babies" is the collection name where the baby documents are stored
                        db.collection("babies")
                                .document(babyId)
                                .update("baby_height", 99, "baby_weight", 90)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(MidWife.this, "Height and weight updated successfully", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(MidWife.this, "Failed to update height and weight", Toast.LENGTH_SHORT).show();
                                });
                    }
                });
    }
}


