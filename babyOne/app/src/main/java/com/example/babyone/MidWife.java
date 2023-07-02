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

import java.util.Map;

public class MidWife extends AppCompatActivity {

    private SearchView searchView;
    private MaterialCardView card2;

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
                // Start the MainLanding activity
                // Assuming you have a button or click event to navigate to MainLanding
                Intent intent = new Intent(MidWife.this, MainLanding.class);
                intent.putExtra("sourceFragment", "midwife");
                startActivity(intent);

            }
        });
                Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String guardianEmail = extras.getString("email");
        }
    }

    private void updateBabyData(String height, String weight) {
        // Get Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get the reference to the "guardians" collection
        CollectionReference guardiansRef = db.collection("guardians");

        // Update the baby data in the guardian's document
        String guardianEmail = "harithabeysinghe@gmail.com";
        DocumentReference guardianDocRef = guardiansRef.document(guardianEmail);
        guardianDocRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                // Retrieve the existing baby data from the document
                Map<String, Object> data = documentSnapshot.getData();
                if (data != null) {
                    // Update the height and weight fields
                    data.put("66", height);
                    data.put("88", weight);

                    // Update the document with the new baby data
                    guardianDocRef.set(data, SetOptions.merge())
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(MidWife.this, "Baby data updated successfully", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(MidWife.this, "Failed to update baby data", Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });
    }
}


