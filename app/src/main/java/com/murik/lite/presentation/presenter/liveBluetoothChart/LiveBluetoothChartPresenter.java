package com.murik.lite.presentation.presenter.liveBluetoothChart;


import android.graphics.Color;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.murik.lite.presentation.view.liveBluetoothChart.LiveBluetoothChartView;

import java.util.ArrayList;
import java.util.List;


@InjectViewState
public class LiveBluetoothChartPresenter extends MvpPresenter<LiveBluetoothChartView> {

    private List<ILineDataSet> dataSets = new ArrayList<>();
    private ArrayList<Entry> entrySens1 = new ArrayList<>();

    LineDataSet dataSet1 = new LineDataSet(entrySens1, "sensor 1");

    public void addDataFromDevice(String str) {
        int sensNumber;
        int sensValue;
        for (int i = 0; i < str.length(); i = i + 8) {
            sensNumber = Integer.decode(str.substring(i, i + 1));
            sensValue = Integer.parseInt(str.substring(i + 1, i + 8), 16);

            if (sensNumber == 1) {
                if (entrySens1.size() > 50) {
                    entrySens1.clear();
                }
                entrySens1.add(new Entry(entrySens1.size(), sensValue));
                dataSet1.notifyDataSetChanged();
                getViewState().notifyLineChart1();
            }
        }

        getViewState().setLineChart(dataSets);
    }

    public void initLineChart() {
        entrySens1.add(new Entry(0, 0));
        dataSets.add(dataSet1);
        dataSet1.setColor(Color.RED);
        getViewState().setLineChart(dataSets);
    }
}

