package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BmiGraph extends AppCompatActivity {

    Button btnBack_1;
    LineChart mpLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_graph);

        btnBack_1 = findViewById(R.id.btnBack_medicine);

        /*ACTION WHICH OCCURS WHEN THE BACK BUTTON IS CLICKED*/
        btnBack_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // The BMI Chart
        mpLineChart = (LineChart) findViewById(R.id.chart);

        mpLineChart.getDescription().setEnabled(false);

        ChartEntries entries = new ChartEntries();

        LineDataSet lineDataSet1 = new LineDataSet(entries.getEntriesBabyBMI(), "Your Baby");
        lineDataSet1.setColor(Color.parseColor("#9966cb"));
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setDrawFilled(true); // Enable filling the area under the line
        lineDataSet1.setFillAlpha(100); // Set the transparency level of the fill
        lineDataSet1.setFillColor(Color.parseColor("#9966cb")); // Set the solid color fill
        GradientDrawable gradientDrawable1 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{Color.parseColor("#9966cb"), Color.TRANSPARENT} // Define the gradient colors
        );
        lineDataSet1.setFillDrawable(gradientDrawable1);

        LineDataSet lineDataSet2 = new LineDataSet(entries.getEntriesStandardBMI().subList(0, entries.getEntriesBabyBMI().size()), "Standard");
        lineDataSet2.setColor(Color.parseColor("#808080"));
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setDrawFilled(true); // Enable filling the area under the line
        lineDataSet2.setFillAlpha(100); // Set the transparency level of the fill
        lineDataSet2.setFillColor(Color.parseColor("#808080")); // Set the solid color fill
        GradientDrawable gradientDrawable2 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{Color.parseColor("#808080"), Color.TRANSPARENT} // Define the gradient colors
        );
        lineDataSet2.setFillDrawable(gradientDrawable2);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet1);

        String[] months = entries.getMonths();

        XAxis xAxis = mpLineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(80);
        xAxis.setLabelCount(months.length);
        xAxis.setAxisMinimum(0.5f);
        xAxis.setSpaceMin(2f);
        xAxis.setSpaceMax(2f);

        mpLineChart.setDragEnabled(true);
        mpLineChart.setVisibleXRangeMaximum(12);
        mpLineChart.moveViewToX(dataSets.get(0).getEntryCount() - 12);

        YAxis rightAxis = mpLineChart.getAxisRight();
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawLabels(false);
        YAxis leftAxis = mpLineChart.getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawLabels(false);

        mpLineChart.getXAxis().setSpaceMin(0.5f); // Set the minimum space between points
        mpLineChart.getXAxis().setSpaceMax(0.5f); // Set the maximum space between points

        ObjectAnimator animator = ObjectAnimator.ofFloat(mpLineChart, "translationX", 0f, 0f);
        animator.setDuration(1000);
        mpLineChart.animateX(1000);

        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();

        ArrayList<Entry> entries1 = entries.getEntriesBabyBMI();
        ArrayList<Entry> entries2 = entries.getEntriesStandardBMI();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // Set the last entry of barEntries1 to the TextView
        if (entries1.size() > 0) {
            Entry lastEntry = entries1.get(entries1.size() - 1);
            TextView card_text = findViewById(R.id.card1text);
            card_text.setText(String.valueOf(decimalFormat.format(lastEntry.getY())));
        }

        // Set the deviation
        if ((entries1.size() > 0) && (entries2.size() > 0)) {
            Entry lastEntry1 = entries1.get(entries1.size() - 1);
            Entry lastEntry2 = entries2.get(entries1.size() - 1);
            double deviation = lastEntry1.getY() - lastEntry2.getY();
            TextView card_text = findViewById(R.id.card2text);
            card_text.setText(String.valueOf(decimalFormat.format(deviation)));
        }

        // Set the status
        if ((entries1.size() > 1) && (entries2.size() > 1)) {
            Entry lastEntry1Baby = entries1.get(entries1.size() - 1);
            Entry lastEntry2Baby = entries1.get(entries1.size() - 2);
            float rateBaby = lastEntry1Baby.getY() - lastEntry2Baby.getY();
            Entry lastEntry1Standard = entries2.get(entries1.size() - 1);
            Entry lastEntry2Standard = entries2.get(entries1.size() - 2);
            float rateStandard = lastEntry1Standard.getY() - lastEntry2Standard.getY();
            TextView card_text = findViewById(R.id.card3text);
            if (((rateBaby+3.0) < rateStandard) || ((rateBaby-3.0) > rateStandard)) {
                card_text.setText("DANGER");
                card_text.setTextColor(Color.RED);
            } else {
                card_text.setText("GOOD");
            }
        }

        int good=0;
        int dangedr=0;
        for (int i=1; i<entries1.size(); i++) {
            if((((entries1.get(i).getY()-entries2.get(i).getY()) - (entries1.get(i-1).getY()-entries2.get(i-1).getY())) > 3.0) || (((entries1.get(i).getY()-entries2.get(i).getY()) - (entries1.get(i-1).getY()-entries2.get(i-1).getY())) < -3.0)) {
                dangedr++;
            }
            else {
                good++;
            }
        }

        float good_percentage = ((float) good/(float) (good+dangedr))*100.0f;

        PieChart pieChart = findViewById(R.id.card4chart);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setTransparentCircleRadius(50f);

        ArrayList<PieEntry> past = new ArrayList<>();
        past.add(new PieEntry(good, ""));
        past.add(new PieEntry(dangedr, ""));

        PieDataSet pieDataSet = new PieDataSet(past, "past");
        pieDataSet.setColors(new int[]{Color.WHITE, Color.parseColor("#ADCBD8")});
        pieDataSet.setValueTextColor(Color.TRANSPARENT);
        pieDataSet.setDrawValues(false);
        pieDataSet.setDrawIcons(false);
        pieDataSet.setSliceSpace(0.1f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText(String.valueOf(Math.round(good_percentage)) + "%");
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.animate();
        pieChart.animateY(1500, Easing.EaseInOutCubic);

    }
}