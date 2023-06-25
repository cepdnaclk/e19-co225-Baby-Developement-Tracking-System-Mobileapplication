package com.example.babyone;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {

    private MaterialCardView cardView_1;
    private MaterialCardView cardView_2;
    private MaterialCardView cardView_3;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG = "DatabaseActivity";
    private TextView textViewUserData;
    private FirebaseFirestore db;


    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        cardView_1 = view.findViewById(R.id.materialCardViewp1);
        cardView_2 = view.findViewById(R.id.materialCardViewp2);
        cardView_3 = view.findViewById(R.id.materialCardViewp3);

        cardView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Medicine and Vitamins clicked", Toast.LENGTH_SHORT).show();
            }
        });

        cardView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "BMI Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), BmiToGraph.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        cardView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Went to Vaccine updated", Toast.LENGTH_SHORT).show();

            }
        });

        // Inflate the layout for this fragment
        return view;


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewUserData = view.findViewById(R.id.text_view_user_data);
        db = FirebaseFirestore.getInstance();

        // Read data from "users" collection
        readUsersCollection();
    }

    private void readUsersCollection() {
        CollectionReference usersCollectionRef = db.collection("users");

        usersCollectionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        StringBuilder userData = new StringBuilder();

                        for (DocumentSnapshot documentSnapshot : querySnapshot) {
                            String babyName = documentSnapshot.getString("Baby Name");
                            String birthDate = documentSnapshot.getString("Birth Date");

                            userData.append("Baby Name: ").append(babyName).append("\n")
                                    .append("Birth Date: ").append(birthDate).append("\n\n");
                        }

                        textViewUserData.setText(userData.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle error
                    }
                });
    }

}