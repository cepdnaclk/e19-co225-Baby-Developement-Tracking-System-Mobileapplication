package com.example.babyone;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.xy.XYPlot;
import com.google.android.material.card.MaterialCardView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {

    private MaterialCardView cardView_1;
    private MaterialCardView cardView_2;
    private MaterialCardView cardView_3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    XYPlot plot;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        cardView_1 = view.findViewById(R.id.materialCardViewp1);
        cardView_2 = view.findViewById(R.id.materialCardViewp2);
        cardView_3 = view.findViewById(R.id.materialCardViewp3);

        cardView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(requireContext(), "Medicine and Vitamins clicked", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(getActivity(), BmiGraph.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        /*plot = view.findViewById(R.id.plot);

        plot.setDomainTitle(null);
        plot.setRangeTitle(null);

        plot.getGraph().getBackgroundPaint().setColor(Color.parseColor("#9DCBE1"));

        String[] domainLabels = {"0","mon","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec",};
        Number[] series1Numbers = {0,2,3,4,2,3,5,4,6,3,4,2,1};

        XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,null);

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.rgb(255, 165, 0),null,null,null);

        series1Format.setFillPaint(new Paint(Paint.ANTI_ALIAS_FLAG));
        series1Format.getFillPaint().setStyle(Paint.Style.FILL);
        series1Format.getFillPaint().setColor(Color.rgb(255, 165, 0)); // Set fill color to orange

        plot.addSeries(series1,series1Format);

        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round( ((Number)obj).floatValue() );
                return toAppendTo.append(domainLabels[i]);
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });

        plot.getGraph().getGridBackgroundPaint().setColor(Color.TRANSPARENT);
        plot.getGraph().getDomainGridLinePaint().setColor(Color.TRANSPARENT);
        plot.getGraph().getRangeGridLinePaint().setColor(Color.TRANSPARENT);
        plot.getGraph().getDomainOriginLinePaint().setColor(Color.TRANSPARENT);
        plot.getGraph().getRangeOriginLinePaint().setColor(Color.TRANSPARENT);
*/

        cardView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(requireContext(), "BMI Clicked", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(getActivity(), HeightGraph.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        cardView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(requireContext(), "Went to Vaccine updated", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(getActivity(), WeightGraph.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        // Inflate the layout for this fragment
        return view;


    }
}