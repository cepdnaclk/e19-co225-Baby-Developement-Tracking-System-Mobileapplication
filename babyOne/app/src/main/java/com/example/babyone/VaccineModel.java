package com.example.babyone;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VaccineModel {
    private String vaccineType;
    private Date date;
    private String status;

    public VaccineModel(String vaccineType, String dateadmin, String status) {
        this.vaccineType = vaccineType;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = sdf.parse(dateadmin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.status = status;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getDate() {
        return date.toString();
    }

    @NonNull
    @Override
    public String toString() {
        return "VaccineModel{" +
                "vaccineType='" + vaccineType + '\'' +
                ", date='" + date.toString() + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void setDate(String dateadmin) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = sdf.parse(dateadmin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
