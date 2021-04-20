package at.ahif18.tradeara.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import at.ahif18.tradeara.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DepotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DepotFragment extends Fragment {

    private static final String TAG = "DepotFragment";
    private float[] yData = {40.0f, 15.0f, 65.0f};
    private String[] xData = {"SMN", "DVD", "MXN"};
    private PieChart pieChart;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DepotFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DepotFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DepotFragment newInstance(String param1, String param2) {
        DepotFragment fragment = new DepotFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_depot, container, false);
        pieChart = view.findViewById(R.id.pieChart);

        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);

        addDataSet();

        return view;
    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList();
        ArrayList<String> xEntrys = new ArrayList();


        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        Legend l = pieChart.getLegend();
        l.setEnabled(false);

        pieChart.invalidate();

    }
}