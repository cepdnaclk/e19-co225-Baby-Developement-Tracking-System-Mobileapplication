package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FieldValue;

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
                String height = "80"; // Replace with the actual value of the height entered
                String weight = "44"; // Replace with the actual value of the weight entered

                // Create an array to store height and weight
                String[] babyData = {height, weight};

                // Get Firestore instance
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                // Get the reference to the "guardians" collection
                CollectionReference guardiansRef = db.collection("guardians");

                // Assuming you have the email as the document ID for the guardian's data
                String guardianEmail = "dasun.theekshana.git@gmail.com"; // Replace with the actual email

                // Update the array field in the document
                guardiansRef.document(guardianEmail)
                        .update("babyData", FieldValue.arrayUnion(babyData))
                        .addOnSuccessListener(aVoid -> Toast.makeText(MidWife.this, "Data stored successfully", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(MidWife.this, "Failed to store data", Toast.LENGTH_SHORT).show());

                card2.setVisibility(View.VISIBLE);
                Toast.makeText(MidWife.this, "searching", Toast.LENGTH_SHORT).show();
                // Retrieve data from the database and display relevant data in the card view
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
                /*Intent intent = new Intent(MidWife.this, MainLanding.class);
                intent.putExtra("sourceFragment", "midwife");
                startActivity(intent);*/
            }
        });
    }
}
