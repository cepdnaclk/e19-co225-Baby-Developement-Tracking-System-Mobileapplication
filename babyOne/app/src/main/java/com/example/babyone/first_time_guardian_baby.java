package com.example.babyone;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class first_time_guardian_baby extends Fragment implements DatePickerDialog.OnDateSetListener {
    HashMap<String, String> editTextMap = new HashMap<>();
    EditText edtEBFullName;
    TextView edtEBDOB;
    RadioGroup radgroupEBGender;
    int year, month, day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_time_guardian_baby, container, false);

        // Find the EditText and RadioGroup views in the layout
        edtEBFullName = view.findViewById(R.id.edtEBFullName);
        radgroupEBGender = view.findViewById(R.id.radgroupEBGender);
        edtEBDOB = view.findViewById(R.id.edtEBDOB);

        edtEBDOB.setOnClickListener(v -> {
            showDatePicker();
        });

        return view;
    }

    private void showDatePicker() {
        // Get the current date
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and show it
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.show();
    }

    public HashMap<String, String> getInfoHashMap() {
        // Return the hashmap
        String fullName = edtEBFullName.getText().toString().trim();
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
        return editTextMap;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar date = Calendar.getInstance();
        date.set(year,month,dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtEBDOB.setText(dateFormat.format(date.getTime()));
        String formattedDate = dateFormat.format(date.getTime());
        editTextMap.put("baby_bday", formattedDate);
    }
}