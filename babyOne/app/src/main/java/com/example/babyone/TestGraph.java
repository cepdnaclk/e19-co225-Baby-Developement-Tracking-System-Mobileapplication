package com.example.babyone;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestGraph extends AppCompatActivity {

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_graph);

        lineChart = findViewById(R.id.chartExample);

        HashMap<Float, Float> data = new HashMap<>();
        data.put(0f, 2f);
        data.put(1f, 4f);
        data.put(2f, 1f);
        data.put(3f, 6f);
        data.put(4f, 3f);
        data.put(5f, 5f);

        drawGraph(lineChart, data);

    }

    @SuppressWarnings("ConstantConditions")
    public void drawGraph(LineChart lineChart, HashMap<Float, Float> data) {
        lineChart.setTouchEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.setPinchZoom(false);
        lineChart.getDescription().setEnabled(false);

        // Add X-axis
        XAxis xAxis = lineChart.getXAxis();
        //xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setDrawGridLines(false);
        //xAxis.setGranularity(1f);
        xAxis.setEnabled(false);

        // Add Y-axis
        YAxis leftAxis = lineChart.getAxisLeft();
        //leftAxis.setDrawGridLines(true);
        //leftAxis.setAxisMinimum(0f);
        leftAxis.setEnabled(false);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        // Add legend
        Legend legend = lineChart.getLegend();
        //legend.setForm(Legend.LegendForm.LINE);
        legend.setEnabled(false);

        // Add data
        //List<Entry> entries1 = new ArrayList<>();
        List<Entry> entries2 = new ArrayList<>();
        for (float x : data.keySet()) {
            float y = data.get(x);
            entries2.add(new Entry(x, y));
        }

        //LineDataSet dataSet1 = new LineDataSet(entries1, "Dataset 1");
        //dataSet1.setColor(Color.BLUE);
        //dataSet1.setCircleColor(Color.BLUE);
        //dataSet1.setDrawValues(false);
        //dataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);


        //Gradient Settings
        GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{getColor(R.color.RoyalPurple), Color.TRANSPARENT});
        gradient.setShape(GradientDrawable.RECTANGLE);
        gradient.setSize(0, lineChart.getHeight());

        LineDataSet dataSet2 = new LineDataSet(entries2, "Dataset 2");
        dataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet2.setCubicIntensity(0.2f);
        dataSet2.setDrawFilled(true);
        dataSet2.setDrawCircles(true);
        dataSet2.setDrawValues(false);
        dataSet2.setLineWidth(2f);
        dataSet2.setCircleRadius(4f);
        dataSet2.setCircleColor(getColor(R.color.Heliotrope));
        //dataSet2.setHighLightColor(Color.rgb(244, 117, 117));
        dataSet2.setColor(getColor(R.color.Heliotrope));
        //dataSet2.setFillColor(Color.WHITE);
        dataSet2.setFillDrawable(gradient); // Apply the gradient here
        dataSet2.setFillAlpha(100);
        dataSet2.setDrawHorizontalHighlightIndicator(false);
        dataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        List<ILineDataSet> dataSets = new ArrayList<>();
        //dataSets.add(dataSet1);
        dataSets.add(dataSet2);

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        //lineChart.invalidate();
        lineChart.animate();
        //lineChart.animateY(2000, Easing.EaseInOutCubic);
        lineChart.animateY(2000, Easing.EaseInOutCubic);
        lineChart.invalidate();
    }
}