package com.example.babyone;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.babyone.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BabyDetailsActivity extends AppCompatActivity {

    private TextView txtvBabyName;

    private TextView txtvProfileName;
    private TextView txtvProfileDesc;
    private FragmentHomeBinding binding;

    private TextView txtvHeight;
    private TextView txtvWeight;
    private TextView txtvBMI;
    private String babyDocumentId ; // Variable to store the document ID


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Transparent status bar
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_main_landing);

        txtvProfileName = findViewById(R.id.txtvProfileName);
        txtvProfileDesc = findViewById(R.id.txtvProfileDesc);

        // Retrieve the data from the Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String babyName = extras.getString("babyName");
            String parentName = extras.getString("parentName");
            String email = extras.getString("email");
            System.out.println(email);

            // Log the received data
            Log.d("BabyDetailsActivity", "babyName: " + babyName);
            Log.d("BabyDetailsActivity", "parentName: " + parentName);
            Log.d("BabyDetailsActivity", "email: " + email);

            // Display the data in the TextViews
            txtvProfileName.setText(babyName);

            // Get the document ID of the specified baby
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String collectionName = "guardians";


            // Declare a list to store the document IDs
            List<String> babyDocumentIds = new ArrayList<>();


            db.collection("guardians")
                    .whereEqualTo("babyname", babyName)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                // Get the document ID
                                String babyDocumentId = document.getId();
                                babyDocumentIds.add(babyDocumentId);
                                Log.d("BabyDetailsActivity", "babyDocumentId: " + babyDocumentId);
                                System.out.println(("BabyDetailsActivity " + babyDocumentId));
                            }

                            // Pass the list of document IDs to the desired fragment or use it as needed
                        } else {
                            Log.d("BabyDetailsActivity", "Error getting documents: ", task.getException());
                        }
                    });


        }
    }

}
