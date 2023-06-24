package com.example.babyone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class StoreActivity extends AppCompatActivity {

    private static final String TAG = "StoreActivity";
    private FirebaseFirestore db;
    private EditText editTextName;
    private EditText editTextBirthDate;
    private Button btnGetStarted;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // Get the instance of FirebaseFirestore
        db = FirebaseFirestore.getInstance();

        // Find views from XML layout
        editTextName = findViewById(R.id.edit_text_name);
        editTextBirthDate = findViewById(R.id.edit_text_birth_date);
        btnGetStarted = findViewById(R.id.btn_get_started);

        // Set button click listener
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String birthDate = editTextBirthDate.getText().toString();

                // Create a new user with a name and birth date
                Map<String, Object> user = new HashMap<>();
                user.put("Baby Name", name);
                user.put("Birth Date", birthDate);

                // Add a new document with a generated ID
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });
    }
}
