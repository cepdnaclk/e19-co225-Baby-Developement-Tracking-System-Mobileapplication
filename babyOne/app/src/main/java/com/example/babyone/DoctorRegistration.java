package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class DoctorRegistration extends AppCompatActivity {
    private Button btnSubmit;
    private EditText editTextName;
    private EditText editTextMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        btnSubmit = findViewById(R.id.btnSubmit);
        editTextName = findViewById(R.id.editTextName);
        editTextMobile = findViewById(R.id.editTextMobile);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the DoctorRegistration activity
                //Create hashmap to store data
                HashMap<String, Object> registrationInfo = new HashMap<>();
                HashMap<String, Object> roleInfo = new HashMap<>();
                //Retrive data from interface
                String name = editTextName.getText().toString();
                String mobile = editTextMobile.getText().toString();
                //Add data to hashmap
                registrationInfo.put("name",name);
                registrationInfo.put("mobile",mobile);
                roleInfo.put("role","D");

                String collectionName = "DoctorLog";
                String collectionNamerole = "users";
                //Add to firestore
                FirestoreHelper.addToFirestore(collectionName, registrationInfo, DoctorRegistration.this, DoctorRegistration.this);
                FirestoreHelper.addToFirestore(collectionNamerole, roleInfo, DoctorRegistration.this, DoctorRegistration.this);
                Intent intent = new Intent(DoctorRegistration.this, Doctor.class);
                startActivity(intent);
            }
        });
    }
}