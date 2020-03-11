package com.murik.enose.ui.fragment.bar_chart;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.core.utils.OrdinalZoom;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.ResultAFactory;
import com.murik.enose.model.ResultBySens;
import com.murik.enose.model.resultbyMaxValue.ResultAFactoryLongMask;
import com.murik.enose.model.resultbyMaxValue.ResultAFactoryShortMask;
import com.murik.enose.model.resultbyMaxValue.ResultAFactoryStandard;

import java.util.ArrayList;
import java.util.List;

public class ResultBarChartFragment extends Fragment {

    public static final String CALCULATE_A_KEY = "RESULT_A";

    DataByMaxParcelable inputDataParcelable;
    private BarChart barChart;

    public static Fragment newInstance(DataByMaxParcelable resultBySens) {
        ResultBarChartFragment fragment = new ResultBarChartFragment();

        Bundle args = new Bundle();
        args.putParcelable(CALCULATE_A_KEY, resultBySens);
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            inputDataParcelable = bundle.getParcelable(CALCULATE_A_KEY);
        }
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_one_sensor_tab_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barChart = view.findViewById(R.id.result_bar_chart);

        initChart();
    }

    public void initChart() {

        List<IBarDataSet> dataSets = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        if (inputDataParcelable.getLeftHandDataSensor() != null) {
            BarDataSet leftDataSet = new BarDataSet(createEntryDataForChart(inputDataParcelable, Const.LEFT_HAND, labels), "Левая");
            leftDataSet.setColor(Color.BLUE, 100);
            leftDataSet.setValueTextSize(10);
            leftDataSet.setValueTypeface(Typeface.SANS_SERIF);
            dataSets.add(leftDataSet);
        }
        if (inputDataParcelable.getRightHandDataSensor() != null) {
            BarDataSet rightDataSet = new BarDataSet(createEntryDataForChart(inputDataParcelable, Const.RIGHT_HAND, labels), "Правая");
            rightDataSet.setColor(Color.RED, 100);
            rightDataSet.setValueTextSize(10);
            rightDataSet.setValueTypeface(Typeface.MONOSPACE);
            dataSets.add(rightDataSet);
        }

        Description des = barChart.getDescription();
        des.setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(labels.size(), false);
        xAxis.setValueFormatter((value, axis) -> labels.get((int) value));



        BarData barData = new BarData(dataSets);
        barData.setHighlightEnabled(true);
        barData.setBarWidth(0.9f);
        barChart.setTouchEnabled(true);
        barChart.setPinchZoom(true);
        barChart.setData(barData);
        barChart.invalidate();
    }

    public List<BarEntry> createEntryDataForChart(DataByMaxParcelable data, int hand, List<String> lables) {

        ResultAFactory resultAFactory = null;
        ArrayList<ResultBySens> resultBySens = null;
        List<BarEntry> entries = new ArrayList<>();
        if (data.getMeasureType().equals(Const.STANDARD_MEASURE)) {
            resultAFactory = new ResultAFactoryStandard(data, hand, getContext());
        }
        if (data.getMeasureType().equals(Const.FIRST_MEASURE)) {
            resultAFactory = new ResultAFactoryShortMask(data, hand, getContext());
        }
        if (data.getMeasureType().equals(Const.SECOND_MEASURE)) {
            resultAFactory = new ResultAFactoryLongMask(data, hand, getContext());
        }

        if (resultAFactory != null && resultAFactory.calculateResultA()) {
            resultBySens = resultAFactory.getA();

        }

        if (!lables.isEmpty()) {
            lables.clear();
        }

        int count = 0;
        if (resultBySens != null) {
            for (ResultBySens res : resultBySens) {
                entries.add(new BarEntry(count, (float) res.getA(), res.getLegend()));
                lables.add(res.getLegend());
                count++;
            }
        }
        return entries;
    }
}
