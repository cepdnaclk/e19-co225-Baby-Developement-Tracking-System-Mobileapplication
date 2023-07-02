package com.example.babyone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.HashMap;


public class first_time_guardian_baby extends Fragment {
    private HashMap<String, String> editTextMap = new HashMap<>();
    private EditText edtEBFullName;
    private EditText edtEBDOB;
    private EditText edtEBHeight;
    private EditText edtEBWeight;
    //private EditText edtEPPhoneNumber;

    private RadioGroup radgroupEBGender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_time_guardian_baby, container, false);

        // Find the EditText and RadioGroup views in the layout
        edtEBFullName = view.findViewById(R.id.edtEBFullName);
        edtEBDOB = view.findViewById(R.id.edtEBDOB);
        edtEBWeight = view.findViewById(R.id.edtEBWeight);
        edtEBHeight = view.findViewById(R.id.edtEBHeight);
        radgroupEBGender = view.findViewById(R.id.radgroupEBGender);

        return view;
    }

    public HashMap<String, String> getInfoHashMap() {
        // Return the hashmap
        String fullName = edtEBFullName.getText().toString().trim();
        String bday = edtEBDOB.getText().toString().trim();
        String weight = edtEBWeight.getText().toString().trim();
        String height = edtEBHeight.getText().toString().trim();
        //String phoneNumber = edtEPPhoneNumber.getText().toString().trim();

        if (!fullName.isEmpty()) {
            editTextMap.put("babyname", fullName);
        }

        /*if (!phoneNumber.isEmpty()) {
            editTextMap.put("phonenumber", phoneNumber);
        }*/
        // Retrieve the selected radio button ID
        int selectedRadioButtonId = radgroupEBGender.getCheckedRadioButtonId();
        // Determine the selected radio button text based on its ID
        String gender;
        if (selectedRadioButtonId == R.id.radbtnEBMale) {
            gender = "Male";
        } else if (selectedRadioButtonId == R.id.radbtnEBFemale) {
            gender = "Female";
        } else if (selectedRadioButtonId == R.id.radbtnEBGn) {
            gender = "Rather Not Say";
        } else {
            gender = ""; // Default value if no radio button is selected
        }

        // Add the gender to the HashMap
        if (!gender.isEmpty()) {
            editTextMap.put("baby_gender", gender);
        }
        editTextMap.put("baby_bday", bday);
        editTextMap.put("baby_height", height);
        editTextMap.put("baby_weight", weight);

        return editTextMap;
    }
}