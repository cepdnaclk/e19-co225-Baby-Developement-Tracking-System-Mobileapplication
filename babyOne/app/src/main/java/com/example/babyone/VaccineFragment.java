package com.example.babyone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class VaccineFragment extends Fragment {
    EditText edtHeight;
    EditText edtWeight;
    EditText edtDate;
    EditText edtvName;
    EditText edtvdate;
    EditText edtvPlace;
    Button btnSubmit;
    HashMap<String, Object> data = new HashMap<>();
    Map<String, Object> datav = new HashMap<>();

    public VaccineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vaccine, container, false);

        // Get views
        edtHeight = view.findViewById(R.id.edtHeight);
        edtWeight = view.findViewById(R.id.edtWeight);
        edtDate = view.findViewById(R.id.edtDate);
        edtvName = view.findViewById(R.id.edtvName);
        edtvdate = view.findViewById(R.id.edtvdate);
        edtvPlace = view.findViewById(R.id.edtvPlace);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("guardians");

        Query query = collectionRef.whereEqualTo("email", "kgdasuntheekshana@gmail.com");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String height = edtHeight.getText().toString();
                String weight = edtWeight.getText().toString();
                String date = edtDate.getText().toString();
                String vname= edtvName.getText().toString();
                String vdate = edtvdate.getText().toString();
                String vplace = edtvPlace.getText().toString();

                if (height != null && weight != null && date != null) {
                    long h = Long.parseLong(height);
                    long w = Long.parseLong(weight);

                    data.put("baby_height", FieldValue.arrayUnion(h));
                    data.put("baby_weight", FieldValue.arrayUnion(w));

                    query.get()
                            .addOnSuccessListener(queryDocumentSnapshots -> {
                                // Iterate over the documents in the QuerySnapshot
                                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    String docId = documentSnapshot.getId();
                                    collectionRef.document(docId).update(data)
                                            .addOnSuccessListener(documentReference -> {
                                                // Data added successfully
                                                edtWeight.setText("");
                                                edtHeight.setText("");
                                                edtDate.setText("");
                                                Toast.makeText(getActivity(), "Weight and Height added successfully", Toast.LENGTH_SHORT).show();
                                                // Clear the input fields
                                            })
                                            .addOnFailureListener(e -> {
                                                // Failed to add data
                                                Toast.makeText(getActivity(), "Failed to add Weight and Height", Toast.LENGTH_SHORT).show();
                                            });;
                                }
                                //Toast.makeText(getContext(), "Weight and Height added to Firestore!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                // Handle the failure case
                            });
                }

                if (vname != null & vdate != null & vplace != null){
                    datav.put("vaccineName", vname);
                    datav.put("givenDate", vdate);
                    datav.put("place", vplace);
                    query.get()
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
                                                .add(datav)
                                                .addOnSuccessListener(documentReference -> {
                                                    // Data added successfully
                                                    edtvdate.setText("");
                                                    edtvPlace.setText("");
                                                    edtvName.setText("");
                                                    Toast.makeText(getActivity(), "Vaccinationadded successfully", Toast.LENGTH_SHORT).show();
                                                    // Clear the input fields
                                                })
                                                .addOnFailureListener(e -> {
                                                    // Failed to add data
                                                    Toast.makeText(getActivity(), "Failed to add Vaccination", Toast.LENGTH_SHORT).show();
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
