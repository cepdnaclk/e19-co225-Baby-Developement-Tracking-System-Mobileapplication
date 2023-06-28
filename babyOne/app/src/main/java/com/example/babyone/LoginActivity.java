package com.example.babyone;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    // Initialize variables
    SignInButton btSignIn;
    ImageView imageViewLAB;
    int currentImageIndex =0 ,oldImageIndex = 0;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth firebaseAuth;

    int[] imgArr = new int[]{
            R.drawable.login_bg1,
            R.drawable.login_bg2,
            R.drawable.login_bg3,
            R.drawable.login_bg4
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // transparent status bar
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setStatusBarColor(Color.TRANSPARENT);

        // Assign variable
        btSignIn = findViewById(R.id.check_id_btn);
        imageViewLAB = findViewById(R.id.imageViewLAB);

        // Start the image loop
        startImageLoop();

        // Initialize sign in options the client-id is copied form google-services.json file
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("596633506864-vq352e92ukbn9htj8rq53bu07tc9b7ur.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(LoginActivity.this, googleSignInOptions);

        btSignIn.setOnClickListener((View.OnClickListener) view -> {
            // Initialize sign in intent
            Intent intent = googleSignInClient.getSignInIntent();
            // Start activity for result
            startActivityForResult(intent, 100);
        });

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        // Initialize firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        // Check condition
        if (firebaseUser != null) {
            // When user already sign in redirect to profile activity
            startActivity(new Intent(LoginActivity.this, MainLanding.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }

    }

    private void startImageLoop() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create a TransitionDrawable to fade between the current and next image
                TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[] {
                        ContextCompat.getDrawable(LoginActivity.this, imgArr[oldImageIndex]),
                        ContextCompat.getDrawable(LoginActivity.this, imgArr[currentImageIndex])
                });
                imageViewLAB.setImageDrawable(transitionDrawable);
                transitionDrawable.startTransition(500);

                // Increment the image index and wrap around if necessary
                oldImageIndex = currentImageIndex;
                currentImageIndex = (currentImageIndex + 1) % imgArr.length;

                // Schedule the next image loop iteration
                handler.postDelayed(this, 5000);
            }
        }, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check condition
        if (requestCode == 100) {
            // When request code is equal to 100 initialize task
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            // check condition
            if (signInAccountTask.isSuccessful()) {
                // When google sign in successful initialize string
                String s = "Google sign in successful";
                // Display Toast
                displayToast(s);
                // Initialize sign in account
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null initialize auth credential
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        // Check credential
                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Check  condition
                                if (task.isSuccessful()) {
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    boolean isNewUser = Objects.requireNonNull(task.getResult().getAdditionalUserInfo()).isNewUser();

                                    if (isNewUser) {
                                        // User signed in for the first time
                                        // Perform any necessary operations
                                        startActivity(new Intent(LoginActivity.this, FirstTimeGuardian.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                    } else {
                                        // User has previously signed in
                                        // Perform any necessary operations
                                        // Redirect to profile activity and display Toast
                                        startActivity(new Intent(LoginActivity.this, MainLanding.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                        displayToast("Firebase authentication successful");
                                    }

                                    // Notification Handle
//                                    FirebaseMessaging.getInstance().getToken()
//                                            .addOnCompleteListener(new OnCompleteListener<String>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<String> task) {
//                                                    if (!task.isSuccessful()) {
//                                                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
//                                                        return;
//                                                    }
//
//                                                    // Get new FCM registration token
//                                                    String token = task.getResult();
//
//                                                    // Log and toast
//                                                    Log.d(TAG, "Token: "+token);
//                                                    Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
//                                                }
//                                            });

                                    /*// When task is successful redirect to profile activity display Toast
                                    startActivity(new Intent(MainActivity.this, ProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                                    displayToast("Firebase authentication successful");*/
                                } else {
                                    // When task is unsuccessful display Toast
                                    displayToast("Authentication Failed :" + task.getException().getMessage());
                                }
                            }
                        });
                    }
                } catch (ApiException e) {
                    Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }
            }
        }
    }

    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
