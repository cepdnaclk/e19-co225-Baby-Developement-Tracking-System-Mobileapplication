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

        GlobalFunctions locale = new GlobalFunctions(getApplicationContext(),this);

        btnEnglish.setOnClickListener(v -> {
            locale.setLocale("en-EN");
            locale.getLocale();
            finish();
        });

        btnSinhalese.setOnClickListener(v -> {
            locale.setLocale("si-LK");
            locale.getLocale();
            finish();
        });

    }
}