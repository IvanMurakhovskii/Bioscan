package com.murik.lite.presentation.presenter.measurementLineChart;

import android.graphics.Color;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.murik.lite.dto.SensorDataFullParcelable;
import com.murik.lite.enums.HandSideEnum;
import com.murik.lite.presentation.view.measurementLineChart.MeasurementLineChartView;

import java.util.ArrayList;
import java.util.Map;

import lombok.val;
import lombok.var;

@InjectViewState
public class MeasurementLineChartPresenter extends MvpPresenter<MeasurementLineChartView> {

    private SensorDataFullParcelable measurementSensorData;

    public void setMeasurementSensorData(SensorDataFullParcelable measurementSensorData) {
        this.measurementSensorData = measurementSensorData;
    }

    int[] colors = {
            Color.parseColor("#d32f2f"),
            Color.parseColor("#9c27b0"),
            Color.parseColor("#673ab7"),
            Color.parseColor("#3f51b5"),
            Color.parseColor("#1e88e5"),
            Color.parseColor("#43a047"),
            Color.parseColor("#f4511e"),
            Color.parseColor("#757575")
    };

    public void onHandSideItemSelected(HandSideEnum handSideEnum, LineChart lineChart) {
        switch (handSideEnum) {
            case LEFT: {
                addEntries( measurementSensorData.getDataSensorMapLeftHand(), lineChart);
                break;
            }
            case RIGHT: {
                addEntries( measurementSensorData.getDataSensorMapRightHand(), lineChart);
                break;
            }
            default: addEntries( measurementSensorData.getDataSensorMapLeftHand(), lineChart);
        }
    }

    public void initLineChart(LineChart lineChart) {
        lineChart.getDescription().setText("");

        // enable touch gestures
        lineChart.setTouchEnabled(true);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);

        lineChart.setPinchZoom(true);

        LineData data = new LineData();

        // add empty data
        lineChart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = lineChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.CIRCLE);

        XAxis xl = lineChart.getXAxis();
        xl.setDrawGridLines(true);
        xl.setEnabled(true);
        xl.setTextSize(12);

        YAxis y1 = lineChart.getAxisLeft();
        y1.setDrawGridLines(true);
        y1.setDrawGridLines(true);
        y1.setTextSize(12);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        lineChart.getAxisLeft().setDrawGridLines(true);
        lineChart.getXAxis().setDrawGridLines(true);
        lineChart.setDrawBorders(false);
    }

    public void addEntries(final Map<String, ArrayList<Integer>> data, final LineChart lineChart) {

        if (data == null) return;

        val lineData = lineChart.getLineData();

        lineData.clearValues();

        var index = 0;

        for (val entries : data.entrySet()) {
            val label = entries.getKey();

            val sensors = entries.getValue();

            ILineDataSet set = lineData.getDataSetByIndex(index);

            if (set == null) {
                set = createSet(label, colors[index]);
                lineData.addDataSet(set);
            }

            for (val value : sensors) {
                lineData.addEntry(new Entry(set.getEntryCount(), value), index);
                lineData.notifyDataChanged();
            }
            index++;
        }
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    private LineDataSet createSet(final String label, final int color) {
        LineDataSet set = new LineDataSet(null, label);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(3f);
        set.setColor(color);
        set.setHighlightEnabled(true);
        set.setDrawValues(true);
        set.setDrawCircles(false);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setCubicIntensity(0.2f);
        return set;
    }
}