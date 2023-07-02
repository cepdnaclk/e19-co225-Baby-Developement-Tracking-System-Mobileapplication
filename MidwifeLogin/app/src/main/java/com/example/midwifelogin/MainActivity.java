package com.example.midwifelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ktx.FirebaseFirestoreKtxRegistrar;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText Midwifename;
    EditText MidwifeReg;
    Button enter;

   FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        textView = (TextView) findViewById(R.id.textView);
        Midwifename = (EditText) findViewById(R.id.editTextText);
        MidwifeReg =   (EditText) findViewById(R.id.editTextText2);
        enter = (Button) findViewById(R.id.button);
    }

}