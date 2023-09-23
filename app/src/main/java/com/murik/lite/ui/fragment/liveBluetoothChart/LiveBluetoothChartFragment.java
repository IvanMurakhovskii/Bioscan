package com.murik.lite.ui.fragment.liveBluetoothChart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.murik.lite.App;
import com.murik.lite.R;
import com.murik.lite.Screens;
import com.murik.lite.presentation.presenter.liveBluetoothChart.LiveBluetoothChartPresenter;
import com.murik.lite.presentation.view.liveBluetoothChart.LiveBluetoothChartView;
import com.murik.lite.service.Impl.BluetoothImplService;

import java.util.List;
import java.util.Objects;

public class LiveBluetoothChartFragment extends MvpAppCompatFragment implements LiveBluetoothChartView {

    public static final String TAG = "LiveBluetoothChartFragment";
    @InjectPresenter
    LiveBluetoothChartPresenter mLiveBluetoothChartPresenter;


    private LineChart lineChart1;
    private FloatingActionButton fabStartDimension;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE.equals(action)) {
                String str = intent.getStringExtra(BluetoothImplService.EXTRA_DATA);
                mLiveBluetoothChartPresenter.addDataFromDevice(str);

                for (int i = 0; i < str.length(); i = i + 8) {
                    Log.d("MyLog", "sens_count =  " + Integer.decode(str.substring(i, i + 1)) + " value =  "
                            + Integer.parseInt(str.substring(i + 1, i + 8), 16));
                }

            }
        }
    };

    public static LiveBluetoothChartFragment newInstance() {
        LiveBluetoothChartFragment fragment = new LiveBluetoothChartFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_live_bluetooth_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lineChart1 = view.findViewById(R.id.line_graph1);

        fabStartDimension = view.findViewById(R.id.btnStartDimension);
        fabStartDimension.setOnClickListener(event -> App.INSTANCE.getRouter().replaceScreen(Screens.BLUETOOTH_DIMENSION_FRAGMENT));

        mLiveBluetoothChartPresenter.initLineChart();
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE);
        Objects.requireNonNull(getActivity()).registerReceiver(broadcastReceiver, filter);

    }

    @Override
    public void onStop() {
        super.onStop();
        Objects.requireNonNull(getActivity()).unregisterReceiver(broadcastReceiver);
    }


    public void setLineChart(List<ILineDataSet> dataSets) {
        for (ILineDataSet d : dataSets) {
            d.setDrawValues(false);
        }

        //chart sensor_1
        YAxis y_right = lineChart1.getAxisRight();
        YAxis y_left = lineChart1.getAxisLeft();
        lineChart1.getDescription().setText("");
        y_right.setDrawLabels(false);
        y_left.setValueFormatter(new LargeValueFormatter());
        LineData data = new LineData(dataSets.get(0));
        lineChart1.setData(data);
    }

    public void notifyLineChart1() {
        lineChart1.notifyDataSetChanged();
        lineChart1.invalidate();
    }

}
