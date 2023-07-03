package com.example.babyone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



//    public MedicineFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_medicine, container, false);
//    }
public class MedicineFragment extends Fragment {

    private EditText editTextVaccineName;
    private EditText editTextGivenDate;
    private EditText editTextPlaceOfAdministration;
    private Button btnSubmit;

    private FirebaseFirestore db;
    private String email;

    public MedicineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);
        System.out.println("ONCREATE");
        // Initialize views
        editTextVaccineName = view.findViewById(R.id.editTextVaccineName);
        editTextGivenDate = view.findViewById(R.id.editTextGivenDate);
        editTextPlaceOfAdministration = view.findViewById(R.id.editTextPlaceOfAdministration);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        // Get the Firebase Firestore instance
        db = FirebaseFirestore.getInstance();
        email = FragmentHelper.getEmail();

        System.out.println(email);


        // Set click listener for the Submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values
                String vaccineName = editTextVaccineName.getText().toString().trim();
                String givenDate = editTextGivenDate.getText().toString().trim();
                String placeOfAdministration = editTextPlaceOfAdministration.getText().toString().trim();

                // Create a data object
                Map<String, Object> data = new HashMap<>();
                data.put("vaccineName", vaccineName);
                data.put("dose", givenDate);
                data.put("givenDate", placeOfAdministration);

                // Check if the email is not null
                if (email != null) {
                    // Query the database to retrieve the document ID of the baby based on the email
                    db.collection("guardians")
                            .whereEqualTo("email", email)
                            .get()
                            .addOnSuccessListener(querySnapshot -> {
                                if (!querySnapshot.isEmpty()) {
                                    // Iterate over the documents in the QuerySnapshot
                                    for (DocumentSnapshot documentSnapshot : querySnapshot) {
                                        // Get the document ID of the baby
                                        String babyId = documentSnapshot.getId();

                                        // Add the data to the subcollection of the baby document
                                        db.collection("guardians")
                                                .document(babyId)
                                                .collection("medicines")
                                                .add(data)
                                                .addOnSuccessListener(documentReference -> {
                                                    // Data added successfully
                                                    Toast.makeText(getActivity(), "Data added successfully", Toast.LENGTH_SHORT).show();
                                                    // Clear the input fields
                                                    editTextVaccineName.setText("");
                                                    editTextGivenDate.setText("");
                                                    editTextPlaceOfAdministration.setText("");
                                                })
                                                .addOnFailureListener(e -> {
                                                    // Failed to add data
                                                    Toast.makeText(getActivity(), "Failed to add data", Toast.LENGTH_SHORT).show();
                                                });
                                    }
                                }
                            })
                            .addOnFailureListener(e -> {
                                // Failed to retrieve the document ID
                                Toast.makeText(getActivity(), "Failed to retrieve the document ID", Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });

        return view;
    }
}


