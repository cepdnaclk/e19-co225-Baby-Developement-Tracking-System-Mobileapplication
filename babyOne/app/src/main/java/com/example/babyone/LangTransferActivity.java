package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class LangTransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_transfer);

        ImageButton en= findViewById(R.id.btn_usa);
        ImageButton si= findViewById(R.id.btn_srilanka);
        en.setOnClickListener(view -> {

        });
        si.setOnClickListener(view -> {

        });
    }
}