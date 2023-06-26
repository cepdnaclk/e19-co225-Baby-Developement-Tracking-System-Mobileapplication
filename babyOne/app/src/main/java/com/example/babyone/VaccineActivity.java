package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class VaccineActivity extends AppCompatActivity {


    private RecyclerView recviewVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        recviewVaccine = findViewById(R.id.recviewVaccine);

        ArrayList<VaccineModel> vaccineList = new ArrayList<>();
        vaccineList.add(new VaccineModel("Ebola","21/02/2015","given"));
        vaccineList.add(new VaccineModel("Corona","21/02/2011","given"));
        vaccineList.add(new VaccineModel("Dengue","21/02/2012","notgiven"));
        vaccineList.add(new VaccineModel("Polio","21/02/2011","given"));
        vaccineList.add(new VaccineModel("Sars","21/02/2010","given"));
        vaccineList.add(new VaccineModel("I","21/02/2010","given"));
        vaccineList.add(new VaccineModel("Don\'t","21/02/2010","given"));
        vaccineList.add(new VaccineModel("Care","21/02/2010","given"));
        vaccineList.add(new VaccineModel("Anymore","21/02/2010","given"));


        VaccineRecViewAdapter adapter = new VaccineRecViewAdapter();
        adapter.setVaccines(vaccineList);

        recviewVaccine.setAdapter(adapter);
        recviewVaccine.setLayoutManager(new LinearLayoutManager(this));
    }
}