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

public class DoctorReg extends AppCompatActivity {

    TextView textView;
    EditText Docname;
    EditText DocReg;
    Button enter;

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_reg);

        db = FirebaseFirestore.getInstance();
        textView = (TextView) findViewById(R.id.textView);
        Docname = (EditText) findViewById(R.id.editTextText);
        DocReg =   (EditText) findViewById(R.id.editTextText2);
        enter = (Button) findViewById(R.id.button);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Doctername =Docname.getText().toString();
                String Docterreg =DocReg.getText().toString();
                Map<String,Object> user= new HashMap<>();
                user.put("Docter Name",Doctername);
                user.put("Docter RegNo",Docterreg);

                db.collection("DocterLog")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(DoctorReg.this,"succesful",Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(DoctorReg.this,"failed",Toast.LENGTH_SHORT).show();

                            }
                        });



            }
        });
    }

    public void updateText(View view){

        System.out.println("Button clicked");

    }
}