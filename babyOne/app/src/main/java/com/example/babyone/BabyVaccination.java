package com.example.babyone;
import java.text.*;


import android.util.*;

import com.google.firebase.firestore.*;

import java.util.*;

public class BabyVaccination {
    public static void calculateAndStoreVaccineData(FirebaseFirestore db, String guardiansCollection, String vaccinationsCollection) {
        // Reference to the guardians collection
        CollectionReference guardiansCollectionRef = db.collection(guardiansCollection);

        // Retrieve data from the guardians collection
        guardiansCollectionRef.get()
                .addOnSuccessListener(guardiansQuerySnapshot -> {
                    // Iterate over the documents in the guardians collection
                    for (DocumentSnapshot guardianDocumentSnapshot : guardiansQuerySnapshot) {
                        // Retrieve the birthdate from the guardian document
                        String birthdate = guardianDocumentSnapshot.getString("birthdate");

                        // Reference to the sub-collection for storing vaccine information in the guardian document
                        CollectionReference vaccineSubcollectionRef = guardianDocumentSnapshot.getReference().collection("vaccines");

                        // Retrieve data from the standardvaccinations collection
                        CollectionReference vaccinationsCollectionRef = db.collection(vaccinationsCollection);
                        vaccinationsCollectionRef.get()
                                .addOnSuccessListener(vaccinationsQuerySnapshot -> {
                                    // Iterate over the documents in the standardvaccinations collection
                                    for (DocumentSnapshot vaccinationDocumentSnapshot : vaccinationsQuerySnapshot) {
                                        // Retrieve the vaccination details
                                        String vaccinationName = vaccinationDocumentSnapshot.getString("name");
                                        int vaccinationTime = vaccinationDocumentSnapshot.getLong("time").intValue();

                                        // Calculate the vaccination date based on the birthdate and time
                                        Date vaccinationDate = calculateVaccinationDate(birthdate, vaccinationTime);

                                        // Create a map to store the vaccine information
                                        Map<String, Object> vaccineInfo = new HashMap<>();
                                        vaccineInfo.put("name", vaccinationName);
                                        vaccineInfo.put("date", vaccinationDate);
                                        vaccineInfo.put("status", 0); // Initial status is 0
                                        vaccineInfo.put("icon", "");

                                        // Store the vaccine information in the guardian's document as a sub-collection
                                        vaccineSubcollectionRef.add(vaccineInfo)
                                                .addOnSuccessListener(documentReference -> {
                                                    // Vaccine information stored successfully
                                                    System.out.println("Vaccine information stored for guardian: " + guardianDocumentSnapshot.getId());
                                                })
                                                .addOnFailureListener(e -> {
                                                    // Error occurred while storing the vaccine information
                                                    Log.e("FirestoreHelper", "Error storing vaccine information", e);
                                                });
                                    }
                                })
                                .addOnFailureListener(e -> {
                                    // Error occurred while retrieving data from the standardvaccinations collection
                                    Log.e("FirestoreHelper", "Error retrieving data from standardvaccinations collection", e);
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    // Error occurred while retrieving data from the guardians collection
                    Log.e("FirestoreHelper", "Error retrieving data from guardians collection", e);
                });
    }

    private static Date calculateVaccinationDate(String birthdate, int time) {
        // Parse the birthdate
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate;
        try {
            birthDate = dateFormat.parse(birthdate);
        } catch (ParseException e) {
            // Error occurred while parsing the birthdate
            e.printStackTrace();
            return null;
        }

        // Calculate the vaccination date based on the birthdate and time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.DAY_OF_YEAR, time);
        return calendar.getTime();
    }


}
