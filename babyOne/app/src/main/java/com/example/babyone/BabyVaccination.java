package com.example.babyone;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BabyVaccination {
//    public static void calculateAndStoreVaccineData(FirebaseFirestore db, String guardiansCollection, String vaccinationsCollection) {
//        CollectionReference guardiansCollectionRef = db.collection(guardiansCollection);
//
//        guardiansCollectionRef.get()
//                .continueWithTask(task -> {
//                    List<Task<Void>> tasks = new ArrayList<>();
//
//                    for (DocumentSnapshot guardianDocumentSnapshot : task.getResult()) {
//                        String birthdate = guardianDocumentSnapshot.getString("birthdate");
//                        CollectionReference vaccineSubcollectionRef = guardianDocumentSnapshot.getReference().collection("vaccines");
//
//                        CollectionReference vaccinationsCollectionRef = db.collection(vaccinationsCollection);
//                        Task<QuerySnapshot> vaccinationsQueryTask = vaccinationsCollectionRef.get();
//
//                        Task<Void> vaccineInfoTask = vaccinationsQueryTask.continueWithTask(vaccinationsQueryTaskResult -> {
//                            List<Task<DocumentReference>> vaccineInfoTasks = new ArrayList<>();
//
//                            for (DocumentSnapshot vaccinationDocumentSnapshot : vaccinationsQueryTaskResult.getResult()) {
//                                String vaccinationName = vaccinationDocumentSnapshot.getString("name");
//                                int vaccinationTime = vaccinationDocumentSnapshot.getLong("time").intValue();
//
//                                Date vaccinationDate = calculateVaccinationDate(birthdate, vaccinationTime);
//
//                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                                String formattedDate = dateFormat.format(vaccinationDate);
//
//                                Map<String, Object> vaccineInfo = new HashMap<>();
//                                vaccineInfo.put("name", vaccinationName);
//                                vaccineInfo.put("date", formattedDate);
//                                vaccineInfo.put("status", 0);
//                                vaccineInfo.put("icon", "");
//
//                                Task<DocumentReference> storeVaccineInfoTask = vaccineSubcollectionRef.add(vaccineInfo)
//                                        .addOnSuccessListener(documentReference -> {
//                                            Log.d("BabyVaccination", "Vaccine information stored for guardian: " + guardianDocumentSnapshot.getId());
//                                        })
//                                        .addOnFailureListener(e -> {
//                                            Log.e("BabyVaccination", "Error storing vaccine information", e);
//                                        });
//
//                                vaccineInfoTasks.add(storeVaccineInfoTask);
//                            }
//
//                            // Return a combined task for all the vaccine information tasks
//                            return Tasks.whenAll(vaccineInfoTasks);
//                        });
//
//                        tasks.add(vaccineInfoTask);
//                    }
//
//                    // Return a combined task for all the tasks related to guardians
//                    return Tasks.whenAll(tasks);
//                })
//                .addOnSuccessListener(aVoid -> {
//                    Log.d("BabyVaccination", "All vaccine information stored successfully for all guardians");
//                    // Perform any actions needed after successfully storing all vaccine information.
//                    // For example, you can update the UI or display a success message.
//                })
//                .addOnFailureListener(e -> {
//                    Log.e("BabyVaccination", "Error calculating and storing vaccine information", e);
//                    // Handle the error gracefully. You can display an error message or retry the operation.
//                });
//    }
public static void calculateAndStoreVaccineData(FirebaseFirestore db, String guardiansCollection, String vaccinationsCollection, String email) {
    CollectionReference guardiansCollectionRef = db.collection(guardiansCollection);

    Query query = guardiansCollectionRef.whereEqualTo("email", email);

    query.get()
            .addOnSuccessListener(queryDocumentSnapshots -> {
                if (!queryDocumentSnapshots.isEmpty()) {
                    DocumentSnapshot guardianDocumentSnapshot = queryDocumentSnapshots.getDocuments().get(0);

                    String birthdate = guardianDocumentSnapshot.getString("baby_bday");
                    CollectionReference vaccineSubcollectionRef = guardianDocumentSnapshot.getReference().collection("vaccines");

                    CollectionReference vaccinationsCollectionRef = db.collection(vaccinationsCollection);
                    Task<QuerySnapshot> vaccinationsQueryTask = vaccinationsCollectionRef.get();

                    Task<Void> vaccineInfoTask = vaccinationsQueryTask.continueWithTask(vaccinationsQueryTaskResult -> {
                        List<Task<DocumentReference>> vaccineInfoTasks = new ArrayList<>();

                        for (DocumentSnapshot vaccinationDocumentSnapshot : vaccinationsQueryTaskResult.getResult()) {
                            String vaccinationName = vaccinationDocumentSnapshot.getString("name");
                            int vaccinationTime = vaccinationDocumentSnapshot.getLong("time").intValue();

                            Date vaccinationDate = calculateVaccinationDate(birthdate, vaccinationTime);

                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            String formattedDate = dateFormat.format(vaccinationDate);

                            Map<String, Object> vaccineInfo = new HashMap<>();
                            vaccineInfo.put("name", vaccinationName);
                            vaccineInfo.put("date", formattedDate);
                            vaccineInfo.put("status", 0);
                            vaccineInfo.put("icon", "");

                            Task<DocumentReference> storeVaccineInfoTask = vaccineSubcollectionRef.add(vaccineInfo)
                                    .addOnSuccessListener(documentReference -> {
                                        Log.d("BabyVaccination", "Vaccine information stored for guardian: " + guardianDocumentSnapshot.getId());
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e("BabyVaccination", "Error storing vaccine information", e);
                                    });

                            vaccineInfoTasks.add(storeVaccineInfoTask);
                        }

                        // Return a combined task for all the vaccine information tasks
                        return Tasks.whenAll(vaccineInfoTasks);
                    });

                    vaccineInfoTask.addOnSuccessListener(aVoid -> {
                        Log.d("BabyVaccination", "Vaccine information stored successfully for guardian: " + guardianDocumentSnapshot.getId());
                        // Perform any actions needed after successfully storing the vaccine information.
                        // For example, you can update the UI or display a success message.
                    }).addOnFailureListener(e -> {
                        Log.e("BabyVaccination", "Error storing vaccine information for guardian: " + guardianDocumentSnapshot.getId(), e);
                        // Handle the error gracefully. You can display an error message or retry the operation.
                    });

                } else {
                    Log.d("BabyVaccination", "No guardian found with email: " + email);
                    // Handle the case when no guardian is found with the provided email.
                }
            })
            .addOnFailureListener(e -> {
                Log.e("BabyVaccination", "Error retrieving guardian information", e);
                // Handle the error gracefully. You can display an error message or retry the operation.
            });
}


    private static Date calculateVaccinationDate(String birthdate, int time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate;
        try {
            System.out.println(birthdate);
            birthDate = dateFormat.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.DAY_OF_YEAR, time);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
