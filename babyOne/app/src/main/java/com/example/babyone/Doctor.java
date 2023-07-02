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
    private MaterialCardView card1;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        searchView = findViewById(R.id.searchView);
        card1 = findViewById(R.id.card1);

        db = FirebaseFirestore.getInstance();

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                card1.setVisibility(View.VISIBLE);
                Toast.makeText(Doctor.this, "searching " + query, Toast.LENGTH_SHORT).show();

                //retrieve data from the database and display relevent data in the card view
                db.collection("guardians")
                        .whereGreaterThanOrEqualTo("babyname",query)
                        .whereLessThan("babyname",query+"z")
                        .get()
                        .addOnCompleteListener(task ->{
                           if (task.isSuccessful()){
                               // Baby name, parentname,email and all details are store in hear
                               List<Guardian> guardians = new ArrayList<>();
                               System.out.println("Babyies");
                               for (QueryDocumentSnapshot document : task.getResult()) {
                                   // Extract the guardian data from the document
                                   String babyName = document.getString("babyname");
                                   String parentName = document.getString("parentname");
                                   String email = document.getString("email");

                                   // Print the baby names
                                   System.out.println(babyName);

                                   // Create a Guardian object
                                   Guardian guardian = new Guardian(babyName, parentName, email);
                                   guardians.add(guardian);
                               }

                           }
                        });
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MainLandin activity
                // Assuming you have a button or click event to navigate to MainLanding
                Intent intent = new Intent(Doctor.this, MainLanding.class);
                intent.putExtra("sourceFragment", "doctor");
                startActivity(intent);
            }
        });


    }
}