package com.example.babyone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.babyone.databinding.ActivityMainBinding;
import com.example.babyone.databinding.ActivityProfileBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    // Initialize variable
    ImageView ivImage;
    TextView tvName;
    Button btLogout;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new homeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
           switch (item.getItemId()){
               case R.id.home:
                   replaceFragment(new homeFragment());
                   break;

               case R.id.profile:
                   replaceFragment(new profileFragment());
                   break;

               case R.id.settings:
                   replaceFragment(new settingsFragment());
                   break;
           }

           return  true;
        });



        // Assign variable
        ivImage = findViewById(R.id.iv_image);
        tvName = findViewById(R.id.tv_name);
        btLogout = findViewById(R.id.bt_logout);

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        // Check condition
        if (firebaseUser != null) {
            // When firebase user is not equal to null set image on image view
            Glide.with(ProfileActivity.this).load(firebaseUser.getPhotoUrl()).into(ivImage);
            // set name on text view
            tvName.setText(firebaseUser.getDisplayName());
        }

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(ProfileActivity.this, GoogleSignInOptions.DEFAULT_SIGN_IN);

        btLogout.setOnClickListener(view -> {
            // Sign out from google
            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    // Check condition
                    if (task.isSuccessful()) {
                        // When task is successful sign out from firebase
                        firebaseAuth.signOut();
                        // Display Toast
                        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                        // Finish activity
                        finish();
                    }
                }
            });
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}
