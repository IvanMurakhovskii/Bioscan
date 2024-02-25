package com.murik.lite.ui.fragment.substances;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.murik.lite.R;
import com.murik.lite.dto.MeasureDataParcelable;
import com.murik.lite.model.substances.Substance;
import com.murik.lite.presentation.presenter.substances.SubstancesPresenter;
import com.murik.lite.presentation.view.substances.SubstancesView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.val;


public class SubstancesFragment extends MvpAppCompatFragment implements SubstancesView {

    public static final String TAG = "SubstancesFragment";
    public static final String RESULT_KEY = "RESULT";

    @InjectPresenter
    SubstancesPresenter presenter;
    private HorizontalBarChart horizontalBarChart;

    public static Fragment newInstance(MeasureDataParcelable resultBySens) {
        SubstancesFragment fragment = new SubstancesFragment();

        Bundle args = new Bundle();
        args.putParcelable(RESULT_KEY, resultBySens);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.substances_fragment, container, false);
        setHasOptionsMenu(true);

        System.out.println("substance");

        Bundle bundle = getArguments();
        if (bundle != null) {
            presenter.setData(bundle.getParcelable(RESULT_KEY));
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        horizontalBarChart = view.findViewById(R.id.substances_chart);

        XAxis xAxis = horizontalBarChart.getXAxis();

        horizontalBarChart.setDrawBarShadow(false);
        horizontalBarChart.getLegend().setEnabled(false);
        horizontalBarChart.setPinchZoom(false);
        horizontalBarChart.setDrawValueAboveBar(false);


        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(false);

        val yLeft = horizontalBarChart.getAxisLeft();
        yLeft.setAxisMaximum(100f);
        yLeft.setAxisMinimum(0f);
        yLeft.setEnabled(false);

        val yRight = horizontalBarChart.getAxisRight();
        yRight.setDrawAxisLine(true);
        yRight.setDrawGridLines(false);
        yRight.setEnabled(false);
        yRight.setValueFormatter((value, axis) -> Math.floor(value) + "%");


        horizontalBarChart.setDrawBarShadow(false);
        Description description = new Description();
        description.setText(" ");
        horizontalBarChart.setDescription(description);

        horizontalBarChart.getLegend().setEnabled(false);
        horizontalBarChart.setPinchZoom(false);
        horizontalBarChart.setDrawValueAboveBar(false);


        horizontalBarChart.setDrawBarShadow(true);
    }

    @Override
    public void initRadarChart(List<Substance> substances) {
        Collections.sort(substances, (o1, o2) -> o1.getPercentProbability().compareTo(o2.getPercentProbability()));

        List<BarEntry> entries = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (int i = 0; i < substances.size(); i++) {
            val substance = substances.get(i);
            entries.add(new BarEntry(i + 1, substance.getPercentProbability()));
            colors.add(ContextCompat.getColor(horizontalBarChart.getContext(), substance.getColor()));
            labels.add(substance.getName());
        }

        horizontalBarChart.getXAxis().setValueFormatter((value, axis) -> {
            if (value >= 0) {
                return labels.get((int) value - 1);
            }
            return "";
        });
        horizontalBarChart.getXAxis().setLabelCount(colors.size());

        val dataSet = new BarDataSet(entries, "Substances");
        dataSet.setColors(colors);

        dataSet.setBarShadowColor(Color.argb(40, 150, 150, 150));

        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);

        horizontalBarChart.setData(data);
        horizontalBarChart.animateXY(2000, 2000);
        horizontalBarChart.invalidate();
    }
}
