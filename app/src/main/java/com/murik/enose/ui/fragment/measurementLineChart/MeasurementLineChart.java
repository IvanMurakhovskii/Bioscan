package com.murik.enose.ui.fragment.measurementLineChart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.murik.enose.R;
import com.murik.enose.dto.SensorDataFullParcelable;
import com.murik.enose.enums.HandSideEnum;
import com.murik.enose.presentation.presenter.measurementLineChart.MeasurementLineChartPresenter;
import com.murik.enose.presentation.view.measurementLineChart.MeasurementLineChartView;

import java.util.Objects;

public class MeasurementLineChart extends MvpAppCompatFragment implements MeasurementLineChartView {

    public static final String TAG = "MeasurementLineChart";
    public static final String MEASUREMENT_DATA = "MEASUREMENT_DATA";

    @InjectPresenter
    MeasurementLineChartPresenter measurementLineChartPresenter;

    private LineChart lineChart;
    private Spinner spinner;

    public static Fragment newInstance(SensorDataFullParcelable measureResult) {
        MeasurementLineChart fragment = new MeasurementLineChart();

        Bundle args = new Bundle();
        args.putParcelable(MEASUREMENT_DATA, measureResult);
        fragment.setArguments(args);

        return fragment;
    }


    @NonNull
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            SensorDataFullParcelable measureDate = bundle.getParcelable(MEASUREMENT_DATA);
            measurementLineChartPresenter.setMeasurementSensorData(measureDate);
        }

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        return inflater.inflate(R.layout.fragment_line_chart, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lineChart = view.findViewById(R.id.line_chart);
        spinner = view.findViewById(R.id.spinner_hand_side);

        spinner.setAdapter(new ArrayAdapter<>(
                Objects.requireNonNull(this.getContext()),
                android.R.layout.simple_spinner_dropdown_item,
                HandSideEnum.values())
        );

        measurementLineChartPresenter.initLineChart(lineChart);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeHandSide((HandSideEnum) spinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void changeHandSide(HandSideEnum handSide) {
        measurementLineChartPresenter.onHandSideItemSelected(handSide, lineChart);
    }

}


