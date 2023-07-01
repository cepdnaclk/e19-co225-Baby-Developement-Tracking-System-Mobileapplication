package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class LangChangeActivity extends AppCompatActivity {

    Button btnSinhalese, btnEnglish, btnWTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_change);

        btnEnglish = findViewById(R.id.btnSetLangEN);
        btnSinhalese = findViewById(R.id.btnSetLangSI);
        btnWTF = findViewById(R.id.buttonWTF);

        GlobalFunctions locale = new GlobalFunctions(getApplicationContext(),this);

        btnEnglish.setOnClickListener(v -> {
            locale.setLocale("en-EN");
        });

        btnSinhalese.setOnClickListener(v -> {
            locale.setLocale("si-LK");
        });

        btnWTF.setOnClickListener(v -> {
            locale.getLang();
        });
    }
}