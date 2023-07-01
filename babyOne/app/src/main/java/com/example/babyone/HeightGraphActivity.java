//package com.example.babyone;
//
//import android.graphics.Color;
//import android.graphics.LinearGradient;
//import android.graphics.Shader;
//import android.graphics.drawable.GradientDrawable;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.components.Description;
//import com.github.mikephil.charting.components.XAxis;
//import com.github.mikephil.charting.components.YAxis;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
//import com.github.mikephil.charting.utils.ColorTemplate;
//
//import java.util.ArrayList;
//
//public class HeightGraphActivity extends AppCompatActivity {
//
//    private LineChart chartHeight;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_height_graph);
//
//        chartHeight = findViewById(R.id.chartHeight);
//
//        // Create sample data for the graph (height values)
//        ArrayList<Entry> heightValues = new ArrayList<>();
//        heightValues.add(new Entry(0, 50)); // Month 0 (January) with height 50
//        heightValues.add(new Entry(1, 55)); // Month 1 (February) with height 55
//        heightValues.add(new Entry(2, 60)); // Month 2 (March) with height 60
//        // Add more entries for each month...
//
//        // Create a line dataset using the height values
//        LineDataSet dataSet = new LineDataSet(heightValues, "Height");
//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        dataSet.setLineWidth(2f);
//        dataSet.setCircleRadius(4f);
//        dataSet.setDrawValues(true);
//
//        // Set gradient fill for the line dataset
//        setGradientFill(dataSet);
//
//
//        // Create a LineData object from the datasets
//        LineData lineData = new LineData(dataSet);
//
//        // Customize the chart appearance and behavior
//        chartHeight.setData(lineData);
//        chartHeight.getDescription().setEnabled(false);
//        chartHeight.setTouchEnabled(true);
//        chartHeight.setDragEnabled(true);
//        chartHeight.setScaleEnabled(true);
//        chartHeight.getXAxis().setDrawGridLines(false);
//        chartHeight.getAxisLeft().setDrawGridLines(false);
//        chartHeight.getAxisRight().setDrawGridLines(false);
//
//        XAxis xAxis = chartHeight.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//
//        YAxis leftAxis = chartHeight.getAxisLeft();
//        leftAxis.setAxisMinimum(0f);
//
//        YAxis rightAxis = chartHeight.getAxisRight();
//        rightAxis.setEnabled(false);
//
//        // Set a description for the chart
//        Description description = new Description();
//        description.setText("Height Graph");
//        chartHeight.setDescription(description);
//
//        // Invalidate the chart to refresh and display the data
//        chartHeight.invalidate();
//    }
//    private void setGradientFill(LineDataSet dataSet) {
//        int[] colors = new int[]{
//                Color.parseColor("#FF4081"), // Start color
//                Color.parseColor("#3F51B5") // End color
//        };
//
//        // Create a gradient drawable
//        GradientDrawable gradientDrawable = new GradientDrawable(
//                GradientDrawable.Orientation.TOP_BOTTOM,
//                colors);
//
//        // Set the shape as a rectangle
//        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
//
//        // Set the gradient drawable as the fill drawable
//        dataSet.setFillDrawable(gradientDrawable);
//    }
//}
