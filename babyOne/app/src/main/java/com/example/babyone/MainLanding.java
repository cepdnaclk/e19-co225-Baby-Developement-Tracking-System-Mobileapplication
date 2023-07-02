package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.babyone.databinding.ActivityMainLandingBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;


public class MainLanding extends AppCompatActivity {

    private ActivityMainLandingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // transparent status bar
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setStatusBarColor(Color.TRANSPARENT);

        super.onCreate(savedInstanceState);
        binding = ActivityMainLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment homeView = new homeFragment();
        Fragment profileView = new profileFragment();
        Fragment settingsView = new extrasFragment();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(binding.getRoot().getContext(), GoogleSignInOptions.DEFAULT_SIGN_IN);
        FirebaseAuth firebaseAuth;

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        if (savedInstanceState == null)
            replaceFragment(homeView);

        binding.bottomNavigationViewML.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(homeView);
                    break;

                case R.id.profile:
                    replaceFragment(profileView);
                    break;

                case R.id.extras:
                    replaceFragment(settingsView);
                    break;
            }

            return true;
        });

        binding.btnLogOut.setOnClickListener((v -> {
            // Sign out from Google
            googleSignInClient.signOut().addOnCompleteListener(task -> {
                // Check condition
                if (task.isSuccessful()) {
                    // When task is successful, sign out from Firebase
                    firebaseAuth.signOut();
                    // Display Toast
                    Toast.makeText(binding.getRoot().getContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                    // Finish activity
                    finish();
                    startActivity(new Intent(binding.getRoot().getContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });
        }));
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Add a fade-in animation
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, 0, 0, 0);
        fragmentTransaction.replace(binding.frameLayoutML.getId(),fragment);
        fragmentTransaction.commit();
    }
}