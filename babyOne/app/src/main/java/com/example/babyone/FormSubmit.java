package com.example.babyone;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormSubmit {

    private EditText edtEBFullName, edtFullName, edtPhoneNumber;
    private RadioGroup radioGroupEBGender, radioGroupRelation;
    private RadioButton radbtnEBMale, radbtnEBFemale, radbtnEBGn, radbtnMother, radbtnFather, radbtnOther;
    private Button submitButton;
    private FirebaseFirestore firestore;

    public FormSubmit(View view) {
        edtEBFullName = view.findViewById(R.id.edtEBFullName);
        edtFullName = view.findViewById(R.id.edtFullName);
        edtPhoneNumber = view.findViewById(R.id.edtPhoneNumber);
        radioGroupEBGender = view.findViewById(R.id.radioGroupEBGender);
        radioGroupRelation = view.findViewById(R.id.radioGroupRelation);
        radbtnEBMale = view.findViewById(R.id.radbtnEBMale);
        radbtnEBFemale = view.findViewById(R.id.radbtnEBFemale);
        radbtnEBGn = view.findViewById(R.id.radbtnEBGn);
        radbtnMother = view.findViewById(R.id.radbtnMother);
        radbtnFather = view.findViewById(R.id.radbtnFather);
        radbtnOther = view.findViewById(R.id.radbtnOther);
        submitButton = view.findViewById(R.id.btnNext);;
        firestore = FirebaseFirestore.getInstance();
        setupSubmitButton();
    }

    private void setupSubmitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> userData = extractFormData();
                addDataToFirestore(userData);
            }
        });
    }

    private Map<String, Object> extractFormData() {
        Map<String, Object> userData = new HashMap<>();

        String babyFullName = edtEBFullName.getText().toString();
        String babyGender = "";
        int selectedGenderId = radioGroupEBGender.getCheckedRadioButtonId();
        if (selectedGenderId == R.id.radbtnEBMale) {
            babyGender = "Male";
        } else if (selectedGenderId == R.id.radbtnEBFemale) {
            babyGender = "Female";
        } else if (selectedGenderId == R.id.radbtnEBGn) {
            babyGender = "Rather not say";
        }

        String guardianFullName = edtFullName.getText().toString();
        String guardianPhoneNumber = edtPhoneNumber.getText().toString();
        String guardianRelation = "";
        int selectedRelationId = radioGroupRelation.getCheckedRadioButtonId();
        if (selectedRelationId == R.id.radbtnMother) {
            guardianRelation = "Mother";
        } else if (selectedRelationId == R.id.radbtnFather) {
            guardianRelation = "Father";
        } else if (selectedRelationId == R.id.radbtnOther) {
            guardianRelation = "Other";
        }

        userData.put("babyFullName", babyFullName);
        userData.put("babyGender", babyGender);
        userData.put("guardianFullName", guardianFullName);
        userData.put("guardianPhoneNumber", guardianPhoneNumber);
        userData.put("guardianRelation", guardianRelation);

        return userData;
    }

    private void addDataToFirestore(Map<String, Object> userData) {
        firestore.collection("users").add(userData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("FormSubmit", "Data added to Firestore with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.e("FormSubmit", "Error adding data to Firestore: " + e.getMessage());
                    }
                });
    }
}
