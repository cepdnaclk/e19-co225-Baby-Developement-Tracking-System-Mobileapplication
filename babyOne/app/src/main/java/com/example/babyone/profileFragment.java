package com.example.babyone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        MaterialCardView cardView_1 = view.findViewById(R.id.materialCardViewp1);
        MaterialCardView cardView_2 = view.findViewById(R.id.materialCardViewp2);
        MaterialCardView cardView_3 = view.findViewById(R.id.materialCardViewp3);

        LineChart chartBMI,chartWeight,chartHeight;

        chartBMI = view.findViewById(R.id.chartBMI);
        chartWeight = view.findViewById(R.id.chartWeight);
        chartHeight = view.findViewById(R.id.chartHeight);

        //TODO: Test values replace with real ones
        HashMap<Float, Float> data1 = new HashMap<>();
        data1.put(0f, 2f);
        data1.put(1f, 4f);
        data1.put(2f, 1f);
        data1.put(3f, 6f);
        data1.put(4f, 3f);
        data1.put(5f, 5f);
        HashMap<Float, Float> data2 = new HashMap<>();
        data2.put(0f, 1f);
        data2.put(1f, 2f);
        data2.put(2f, 3f);
        data2.put(3f, 2f);
        data2.put(4f, 5f);
        data2.put(5f, 7f);
        HashMap<Float, Float> data3 = new HashMap<>();
        data3.put(0f, 6f);
        data3.put(1f, 5f);
        data3.put(2f, 3f);
        data3.put(3f, 3f);
        data3.put(4f, 2f);
        data3.put(5f, 5f);

        drawGraph(getContext(),chartBMI,data1);
        drawGraph(getContext(),chartHeight,data2);
        drawGraph(getContext(),chartWeight,data3);

        cardView_1.setOnClickListener(view1 -> {
            /*Toast.makeText(requireContext(), "Medicine and Vitamins clicked", Toast.LENGTH_SHORT).show();*/
            startActivity(new Intent(getActivity(), BmiGraph.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        });

        cardView_2.setOnClickListener(view12 -> {
            /*Toast.makeText(requireContext(), "BMI Clicked", Toast.LENGTH_SHORT).show();*/
            startActivity(new Intent(getActivity(), HeightGraph.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        });

        cardView_3.setOnClickListener(view13 -> {
            /*Toast.makeText(requireContext(), "Went to Vaccine updated", Toast.LENGTH_SHORT).show();*/
            startActivity(new Intent(getActivity(), WeightGraph.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        });

        // Inflate the layout for this fragment
        return view;


    }

    @SuppressWarnings("ConstantConditions")
    public void drawGraph(Context context, LineChart lineChart, HashMap<Float, Float> data) {
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
        GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{context.getColor(R.color.Amethyst), Color.TRANSPARENT});
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
        dataSet2.setCircleColor(context.getColor(R.color.TruePurple));
        //dataSet2.setHighLightColor(Color.rgb(244, 117, 117));
        dataSet2.setColor(context.getColor(R.color.TruePurple));
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