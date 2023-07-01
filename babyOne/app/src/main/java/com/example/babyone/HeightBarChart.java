package com.example.babyone;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.protobuf.EmptyOrBuilder;

import java.util.ArrayList;
import java.util.List;

public class HeightBarChart extends AppCompatActivity {

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_bar_chart);

        // The Bar Chart
        barChart = findViewById(R.id.chart);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);

        String chartTitle = "Height Comparison";
        barChart.getDescription().setText(chartTitle);
        barChart.getDescription().setTextSize(14f);
        barChart.getDescription().setTextColor(Color.BLACK);

        ChartEntries barEntries = new ChartEntries();
        BarDataSet barDataSet1 = new BarDataSet(barEntries.getBarEntriesBabyHeight(), "Your baby");
        BarDataSet barDataSet2 = new BarDataSet(barEntries.getBarEntriesStandardHeight().subList(0, barEntries.getBarEntriesBabyHeight().size()), "Standard");
        barDataSet1.setColor(Color.parseColor("#075B81"));
        barDataSet2.setColor(Color.parseColor("#E70955"));

        BarData data = new BarData(barDataSet1, barDataSet2);
        barChart.setData(data);

        String[] months = barEntries.getMonths();

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(80);
        xAxis.setLabelCount(months.length);

        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(12);
        barChart.moveViewToX(data.getEntryCount() - 12);

        float barSpace = 0.00f;
        float groupSpace = 0.05f;
        data.setBarWidth(0.03f);

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace)*barEntries.getBarEntriesBabyHeight().size());

        ObjectAnimator animator = ObjectAnimator.ofFloat(barChart, "translationY", 0f, 0f);
        animator.setDuration(1000);
        barChart.animateY(2000);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false); // Add this line to disable the right y-axis

        barChart.groupBars(0, groupSpace, barSpace);

        barChart.invalidate();

        ArrayList<BarEntry> barEntries1 = barEntries.getBarEntriesBabyHeight();
        ArrayList<BarEntry> barEntries2 = barEntries.getBarEntriesStandardHeight();

        // Set the last entry of barEntries1 to the TextView
        if (barEntries1.size() > 0) {
            BarEntry lastEntry = barEntries1.get(barEntries1.size() - 1);
            TextView card_text = findViewById(R.id.card1text);
            card_text.setText(String.valueOf(lastEntry.getY()) + "cm");
        }

        // Set the deviation
        if ((barEntries1.size() > 0) && (barEntries2.size() > 0)) {
            BarEntry lastEntry1 = barEntries1.get(barEntries1.size() - 1);
            BarEntry lastEntry2 = barEntries2.get(barEntries1.size() - 1);
            double deviation = lastEntry1.getY() - lastEntry2.getY();
            TextView card_text = findViewById(R.id.card2text);
            card_text.setText(String.valueOf(deviation) + "cm");
        }

        // Set the status
        if ((barEntries1.size() > 0) && (barEntries2.size() > 0)) {
            BarEntry lastEntry1 = barEntries1.get(barEntries1.size() - 1);
            BarEntry lastEntry2 = barEntries2.get(barEntries1.size() - 1);
            TextView card_text = findViewById(R.id.card3text);
            if (((lastEntry1.getY()+5.0) < lastEntry2.getY()) || ((lastEntry1.getY()-5.0) > lastEntry2.getY())) {
                card_text.setText("DANGER");
                card_text.setTextColor(Color.RED);
            } else {
                card_text.setText("GOOD");
            }
        }

        int good=0;
        int dangedr=0;
        for (int i=0; i<barEntries1.size(); i++) {
            if(((barEntries1.get(i).getY()-barEntries2.get(i).getY()) > 5.0) || ((barEntries1.get(i).getY()-barEntries2.get(i).getY()) < -5.0)) {
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
        pieChart.animateY(2000, Easing.EaseInOutCubic);

    }

}

