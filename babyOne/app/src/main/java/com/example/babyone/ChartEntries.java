package com.example.babyone;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class ChartEntries {
    private ArrayList<BarEntry> barEntriesBabyHeight;
    private ArrayList<BarEntry> barEntriesStandardHeight;
    private ArrayList<BarEntry> barEntriesBabyWeight;
    private  ArrayList<BarEntry> barEntriesStandardWeight;
    private ArrayList<Entry> entriesBabyBMI;
    private ArrayList<Entry> entriesStandardBMI;

    private String[] months;

    public ChartEntries() {
        barEntriesBabyHeight = new ArrayList<>();
        barEntriesStandardHeight = new ArrayList<>();
        barEntriesBabyWeight = new ArrayList<>();
        barEntriesStandardWeight = new ArrayList<>();
        entriesBabyBMI = new ArrayList<>();
        entriesStandardBMI = new ArrayList<>();
        months = new String[] {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec","jan","feb","mar"};

        // Initialize barEntriesBabyHeight
        barEntriesBabyHeight.add(new BarEntry(1f, 40.0f));
        barEntriesBabyHeight.add(new BarEntry(2f, 42.0f));
        barEntriesBabyHeight.add(new BarEntry(3f, 48.0f));
        barEntriesBabyHeight.add(new BarEntry(4f, 51.0f));
        barEntriesBabyHeight.add(new BarEntry(5f, 52.0f));
        barEntriesBabyHeight.add(new BarEntry(6f, 54.0f));
        barEntriesBabyHeight.add(new BarEntry(7f, 56.0f));
        barEntriesBabyHeight.add(new BarEntry(8f, 57.0f));
        barEntriesBabyHeight.add(new BarEntry(9f, 58.0f));
        barEntriesBabyHeight.add(new BarEntry(10f, 59.0f));
        barEntriesBabyHeight.add(new BarEntry(11f, 61.0f));
        barEntriesBabyHeight.add(new BarEntry(12f, 62.0f));
        barEntriesBabyHeight.add(new BarEntry(13f, 64.0f));
        barEntriesBabyHeight.add(new BarEntry(14f, 65.0f));
        barEntriesBabyHeight.add(new BarEntry(15f, 65.0f));

        // Initialize barEntriesStandardHeight
        barEntriesStandardHeight.add(new BarEntry(1f, 45.0f));
        barEntriesStandardHeight.add(new BarEntry(2f, 47.5f));
        barEntriesStandardHeight.add(new BarEntry(3f, 50.0f));
        barEntriesStandardHeight.add(new BarEntry(4f, 52.5f));
        barEntriesStandardHeight.add(new BarEntry(5f, 55.0f));
        barEntriesStandardHeight.add(new BarEntry(6f, 57.5f));
        barEntriesStandardHeight.add(new BarEntry(7f, 58.5f));
        barEntriesStandardHeight.add(new BarEntry(8f, 59.5f));
        barEntriesStandardHeight.add(new BarEntry(9f, 60.5f));
        barEntriesStandardHeight.add(new BarEntry(10f, 61.5f));
        barEntriesStandardHeight.add(new BarEntry(11f, 62.5f));
        barEntriesStandardHeight.add(new BarEntry(12f, 63.5f));
        barEntriesStandardHeight.add(new BarEntry(13f, 64.5f));
        barEntriesStandardHeight.add(new BarEntry(14f, 65.5f));
        barEntriesStandardHeight.add(new BarEntry(15f, 66.5f));
        barEntriesStandardHeight.add(new BarEntry(16f, 67.5f));
        barEntriesStandardHeight.add(new BarEntry(17f, 68.5f));
        //TODO : Enter data for all 60 months

        // Initialize barEntriesBabyWeight
        barEntriesBabyWeight.add(new BarEntry(1f, 4.0f));
        barEntriesBabyWeight.add(new BarEntry(2f, 4.2f));
        barEntriesBabyWeight.add(new BarEntry(3f, 5.3f));
        barEntriesBabyWeight.add(new BarEntry(4f, 5.4f));
        barEntriesBabyWeight.add(new BarEntry(5f, 5.5f));
        barEntriesBabyWeight.add(new BarEntry(6f, 5.9f));
        barEntriesBabyWeight.add(new BarEntry(7f, 6.0f));
        barEntriesBabyWeight.add(new BarEntry(8f, 7.0f));
        barEntriesBabyWeight.add(new BarEntry(9f, 7.5f));
        barEntriesBabyWeight.add(new BarEntry(10f, 8.0f));
        barEntriesBabyWeight.add(new BarEntry(11f, 8.0f));
        barEntriesBabyWeight.add(new BarEntry(12f, 8.0f));
        barEntriesBabyWeight.add(new BarEntry(13f, 8.9f));
        barEntriesBabyWeight.add(new BarEntry(14f, 10.0f));
        barEntriesBabyWeight.add(new BarEntry(15f, 11.0f));

        // Initialize barEntriesStandardWeight
        barEntriesStandardWeight.add(new BarEntry(1f, 4.0f));
        barEntriesStandardWeight.add(new BarEntry(2f, 4.5f));
        barEntriesStandardWeight.add(new BarEntry(3f, 5.5f));
        barEntriesStandardWeight.add(new BarEntry(4f, 6.5f));
        barEntriesStandardWeight.add(new BarEntry(5f, 7.0f));
        barEntriesStandardWeight.add(new BarEntry(6f, 7.5f));
        barEntriesStandardWeight.add(new BarEntry(7f, 8.0f));
        barEntriesStandardWeight.add(new BarEntry(8f, 8.5f));
        barEntriesStandardWeight.add(new BarEntry(9f, 9.0f));
        barEntriesStandardWeight.add(new BarEntry(10f, 9.5f));
        barEntriesStandardWeight.add(new BarEntry(11f, 10.0f));
        barEntriesStandardWeight.add(new BarEntry(12f, 10.5f));
        barEntriesStandardWeight.add(new BarEntry(13f, 10.7f));
        barEntriesStandardWeight.add(new BarEntry(14f, 11.0f));
        barEntriesStandardWeight.add(new BarEntry(15f, 11.2f));
        barEntriesStandardWeight.add(new BarEntry(16f, 11.5f));
        barEntriesStandardWeight.add(new BarEntry(17f, 11.7f));
        //TODO : Enter data for all 60 months

        // Initialize barEntriesBabyBMI
        for (int i=0; i<barEntriesBabyHeight.size(); i++) {
            float bmi = barEntriesBabyWeight.get(i).getY() / ((barEntriesBabyHeight.get(i).getY()*barEntriesBabyHeight.get(i).getY())/10000);
            entriesBabyBMI.add(new Entry((float) i+1, bmi));
        }

        // Initialize entriesStandardBMI
        entriesStandardBMI.add(new Entry(1f, 19.75f));
        entriesStandardBMI.add(new Entry(2f, 19.94f));
        entriesStandardBMI.add(new Entry(3f, 22.00f));
        entriesStandardBMI.add(new Entry(4f, 23.58f));
        entriesStandardBMI.add(new Entry(5f, 23.14f));
        entriesStandardBMI.add(new Entry(6f, 22.68f));
        entriesStandardBMI.add(new Entry(7f, 23.38f));
        entriesStandardBMI.add(new Entry(8f, 24.01f));
        entriesStandardBMI.add(new Entry(9f, 24.59f));
        entriesStandardBMI.add(new Entry(10f, 25.12f));
        entriesStandardBMI.add(new Entry(11f, 25.60f));
        entriesStandardBMI.add(new Entry(12f, 26.04f));
        entriesStandardBMI.add(new Entry(13f, 25.72f));
        entriesStandardBMI.add(new Entry(14f, 25.64f));
        entriesStandardBMI.add(new Entry(15f, 25.33f));
        entriesStandardBMI.add(new Entry(16f, 25.24f));
        entriesStandardBMI.add(new Entry(17f, 24.93f));
        //TODO : Enter data for all 60 months

    }

    public ArrayList<BarEntry> getBarEntriesBabyHeight() { return barEntriesBabyHeight; }

    public ArrayList<BarEntry> getBarEntriesStandardHeight() { return barEntriesStandardHeight; }

    public ArrayList<BarEntry> getBarEntriesBabyWeight() { return barEntriesBabyWeight; }

    public ArrayList<BarEntry> getBarEntriesStandardWeight() { return barEntriesStandardWeight; }

    public ArrayList<Entry> getEntriesBabyBMI() { return entriesBabyBMI; }

    public ArrayList<Entry> getEntriesStandardBMI() { return entriesStandardBMI; }

    public  String[] getMonths() { return months; }
}

