package com.murik.enose.ui.fragment.LiveBluetoothChart;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.murik.enose.App;
import com.murik.enose.R;
import com.murik.enose.Screens;
import com.murik.enose.presentation.presenter.liveBluetoothChart.LiveBluetoothChartPresenter;
import com.murik.enose.presentation.view.liveBluetoothChart.LiveBluetoothChartView;
import com.murik.enose.service.BluetoothService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

public class LiveBluetoothChartFragment extends MvpAppCompatFragment implements LiveBluetoothChartView {

  public static final String TAG = "LiveBluetoothChartFragment";
  @InjectPresenter
  LiveBluetoothChartPresenter mLiveBluetoothChartPresenter;


  private LineChart lineChart1;
  private LineChart lineChart2;
  private LineChart lineChart3;
  private LineChart lineChart4;
  private LineChart lineChart5;
  private LineChart lineChart6;
  private LineChart lineChart7;
  private LineChart lineChart8;
  private FloatingActionButton fabStartDimension;

  ArrayList<Entry> lineChartData = new ArrayList<>();

  BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();


      if (BluetoothService.ACTION_CHARACTERISTIC_CHANGE.equals(action)) {
        String str = intent.getStringExtra(BluetoothService.EXTRA_DATA);
        mLiveBluetoothChartPresenter.addDataFromDevice(str);
        Observable.create(emitter -> {

        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe();

       for(int i = 0;i <  str.length(); i=i+8){
          Log.d("MyLog","sens_count =  " + Integer.decode(str.substring(i, i+1)) + " value =  "
              + Integer.parseInt(str.substring(i+1, i+8),16));
        }

      } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
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
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    lineChart1 = view.findViewById(R.id.line_graph1);
    lineChart2 = view.findViewById(R.id.line_graph2);
    lineChart3 = view.findViewById(R.id.line_graph3);
    lineChart4 = view.findViewById(R.id.line_graph4);
    lineChart5 = view.findViewById(R.id.line_graph5);
    lineChart6 = view.findViewById(R.id.line_graph6);
    lineChart7 = view.findViewById(R.id.line_graph7);
    lineChart8 = view.findViewById(R.id.line_graph8);

    fabStartDimension = view.findViewById(R.id.btnStartDimension);
    fabStartDimension.setOnClickListener(event -> {
      App.INSTANCE.getRouter().replaceScreen(Screens.BLUETOOTH_DIMENSION_FRAGMENT);
    });

    mLiveBluetoothChartPresenter.initLineChart();
  }

  @Override
  public void onResume() {
    super.onResume();
    //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    IntentFilter filter = new IntentFilter();
    filter.addAction(BluetoothService.ACTION_CHARACTERISTIC_CHANGE);
    getActivity().registerReceiver(broadcastReceiver, filter);

  }

  @Override
  public void onStop() {
    super.onStop();
    getActivity().unregisterReceiver(broadcastReceiver);
    //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
  }



  public void setLineChart(List<ILineDataSet> dataSets){
    //LineDataSet dataSet = new LineDataSet(lineChartData,  "sensor 8");
    //LineDataSet dataSet12 = new LineDataSet(lineChartData,  "sensor 7");
    for(ILineDataSet d : dataSets){
      d.setDrawValues(false);
    }

  //chart sensor_1
    YAxis y_right = lineChart1.getAxisRight();
    YAxis y_left = lineChart1.getAxisLeft();
    y_right.setDrawLabels(false);
    y_left.setValueFormatter(new LargeValueFormatter());
    LineData data = new LineData(dataSets.get(0));
    lineChart1.setData(data);

    //chart sensor_2
    y_right = lineChart2.getAxisRight();
    y_left = lineChart2.getAxisLeft();
    y_right.setDrawLabels(false);
    y_left.setValueFormatter(new LargeValueFormatter());
    LineData data1 = new LineData(dataSets.get(1));
    lineChart2.setData(data1);

    //chart sensor_3
    y_right = lineChart3.getAxisRight();
    y_left = lineChart3.getAxisLeft();
    y_right.setDrawLabels(false);
    y_left.setValueFormatter(new LargeValueFormatter());
    LineData data2 = new LineData(dataSets.get(2));
    lineChart3.setData(data2);
    //chart sensor_4
    y_right = lineChart4.getAxisRight();
    y_left = lineChart4.getAxisLeft();
    y_right.setDrawLabels(false);
    y_left.setValueFormatter(new LargeValueFormatter());
    LineData data3 = new LineData(dataSets.get(3));
    lineChart4.setData(data3);

    //chart sensor_5
    y_right = lineChart5.getAxisRight();
    y_left = lineChart5.getAxisLeft();
    y_right.setDrawLabels(false);
    y_left.setValueFormatter(new LargeValueFormatter());
    LineData data4 = new LineData(dataSets.get(4));
    lineChart5.setData(data4);

    //chart sensor_6
    y_right = lineChart6.getAxisRight();
    y_left = lineChart6.getAxisLeft();
    y_right.setDrawLabels(false);
    y_left.setValueFormatter(new LargeValueFormatter());
    LineData data5 = new LineData(dataSets.get(5));
    lineChart6.setData(data5);

    //chart sensor_7
    y_right = lineChart7.getAxisRight();
    y_left = lineChart7.getAxisLeft();
    y_right.setDrawLabels(false);
    y_left.setValueFormatter(new LargeValueFormatter());
    LineData data6 = new LineData(dataSets.get(6));
    lineChart7.setData(data6);

    //chart sensor_8
    y_right = lineChart8.getAxisRight();
    y_left = lineChart8.getAxisLeft();
    y_right.setDrawLabels(false);
    y_left.setValueFormatter(new LargeValueFormatter());
    LineData data7 = new LineData(dataSets.get(7));
    lineChart8.setData(data7);
  }

  public void notifyLineChart1(){
    lineChart1.notifyDataSetChanged();
    lineChart1.invalidate();
  }

  @Override
  public void notifyLineChart2() {
    lineChart2.notifyDataSetChanged();
    lineChart2.invalidate();
  }

  @Override
  public void notifyLineChart3() {
    lineChart3.notifyDataSetChanged();
    lineChart3.invalidate();
  }

  @Override
  public void notifyLineChart4() {
    lineChart4.notifyDataSetChanged();
    lineChart4.invalidate();
  }

  @Override
  public void notifyLineChart5() {
    lineChart5.notifyDataSetChanged();
    lineChart5.invalidate();
  }

  @Override
  public void notifyLineChart6() {
    lineChart6.notifyDataSetChanged();
    lineChart6.invalidate();
  }

  @Override
  public void notifyLineChart7() {
    lineChart7.notifyDataSetChanged();
    lineChart7.invalidate();
  }

  @Override
  public void notifyLineChart8() {
    lineChart8.notifyDataSetChanged();
    lineChart8.invalidate();
  }

}
