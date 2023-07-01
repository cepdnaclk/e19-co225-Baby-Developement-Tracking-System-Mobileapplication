package com.example.babyone;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ChartEntries {
    private ArrayList<BarEntry> barEntriesBabyHeight;
    private ArrayList<BarEntry> barEntriesStandardHeight;

    private String[] months;

    public ChartEntries() {
        barEntriesBabyHeight = new ArrayList<>();
        barEntriesStandardHeight = new ArrayList<>();
        months = new String[] {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec","jan","feb","mar"};

        // Initialize barEntries1
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

        // Initialize barEntries2
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
    }

    public ArrayList<BarEntry> getBarEntriesBabyHeight() {
        return barEntriesBabyHeight;
    }

    public ArrayList<BarEntry> getBarEntriesStandardHeight() {
        return barEntriesStandardHeight;
    }

    public  String[] getMonths() { return months; }
}

