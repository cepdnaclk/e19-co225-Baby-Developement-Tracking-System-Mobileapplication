package com.example.babyone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Midwife_Reg extends AppCompatActivity {

    TextView textView;
    EditText Midwifename;
    EditText MidwifeReg;
    Button enter;

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midwife_reg);

        db = FirebaseFirestore.getInstance();
        textView = (TextView) findViewById(R.id.textView);
        Midwifename = (EditText) findViewById(R.id.editTextText);
        MidwifeReg =   (EditText) findViewById(R.id.editTextText2);
        enter = (Button) findViewById(R.id.button);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String MidWifename =Midwifename.getText().toString();
                String MidWifeReg =MidwifeReg.getText().toString();
                Map<String,Object> user= new HashMap<>();
                user.put("Docter Name",MidWifename);
                user.put("Docter RegNo",MidWifeReg);

                db.collection("MidWifeLog")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Midwife_Reg.this,"succesful",Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(Midwife_Reg.this,"failed",Toast.LENGTH_SHORT).show();

                            }
                        });



            }
        });
    }


    public void updateText(View view){

        System.out.println("Button clicked");

    }




}
