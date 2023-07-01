package com.example.babyone;/*
package com.example.babyone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

*/
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *//*

public class homeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeFragment() {
        // Required empty public constructor
    }

    */
/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     *//*

    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}*/

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.babyone.databinding.FragmentHomeBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class homeFragment extends Fragment {
    // Initialize variables
    /*private ImageView ivImage;
    private TextView tvName;*/
    /*private Button btLogout;*/
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;
    private FragmentHomeBinding binding;

    private TextView txtParentname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Assign views from the layout
        /*ivImage = view.findViewById(R.id.iv_image);
        tvName = view.findViewById(R.id.tv_name);*/
        /*btLogout = view.findViewById(R.id.bt_logout);*/
        /*txtParentname = view.findViewById(R.id.txtParentname);*/

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            // Get parent name
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String collectionName = "guardians/";
            String uid = firebaseUser.getUid();
            //HashMap<String,Object> userdata = FirestoreHelper.readFromCollection(db,"guardians");
            //System.out.println(userdata);
//            DocumentReference parentDocRef = db.collection("guardians").document(firebaseUser.getUid());
//
//            // Retrieve the parent's name from the document
//            parentDocRef.get().addOnSuccessListener(documentSnapshot -> {
//                if (documentSnapshot.exists()) {
//                    String parentName = documentSnapshot.getString("parentname");
//                    if (parentName != null) {
//                        // Parent's name is available
//                        // You can use the retrieved parentName here
//                        txtParentname.setText("Name: " + parentName);
//                    } else {
//                        // Parent's name is not available
//                        txtParentname.setText("Name: Null");
//                    }
//                } else {
//                    // Parent document does not exist
//                    txtParentname.setText("Name: Not Found");
//                }
//            }).addOnFailureListener(e -> {
//                // Error occurred while retrieving the document
//                txtParentname.setText("Name: Error occurred");
//            });
            FirestoreHelper.readFromCollection(db,collectionName,uid);

        }
        // Initialize sign-in client
        googleSignInClient = GoogleSignIn.getClient(requireContext(), GoogleSignInOptions.DEFAULT_SIGN_IN);

        /*btLogout.setOnClickListener(v -> {
            // Sign out from Google
            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    // Check condition
                    if (task.isSuccessful()) {
                        // When task is successful, sign out from Firebase
                        firebaseAuth.signOut();
                        // Display Toast
                        Toast.makeText(requireContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                        // Finish activity
                        requireActivity().finish();
                        startActivity(new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                }
            });
        });*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

