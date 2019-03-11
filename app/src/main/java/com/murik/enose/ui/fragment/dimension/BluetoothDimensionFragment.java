package com.murik.enose.ui.fragment.dimension;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.presentation.presenter.dimension.BluetoothDimensionPresenter;
import com.murik.enose.presentation.view.dimension.BluetoothDimensionView;
import com.murik.enose.service.Impl.BluetoothImplService;
import com.murik.enose.ui.dialog.StartDimensionDialogFragment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BluetoothDimensionFragment extends MvpAppCompatFragment implements BluetoothDimensionView,
    StartDimensionDialogFragment.NoticeDialogListener{

  public static final String TAG = "BluetoothDimensionFragment";

  @InjectPresenter
  BluetoothDimensionPresenter mBluetoothDimensionPresenter;

  private StartDimensionDialogFragment startDimensionDialogFragment;

  private AnyChartView lineChart;
  private String description;
  private boolean isPractice;
  private int gender = Const.GENDER_MALE;
  private boolean isDimensoinStart = false;

  private WebView webView;




  BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();

      if (BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE.equals(action)) {
        String str = intent.getStringExtra(BluetoothImplService.EXTRA_DATA);
        for (int i = 0; i < str.length(); i = i + 8) {
          Log.d("MyLog", "sens_count =  " + Integer.decode(str.substring(i, i + 1)) + " value =  "
              + Integer.parseInt(str.substring(i + 1, i + 8), 16));

        }
        if(isDimensoinStart){
          mBluetoothDimensionPresenter.startDimension(str);
        }

      }
    }
  };

    public static BluetoothDimensionFragment newInstance() {
      BluetoothDimensionFragment fragment = new BluetoothDimensionFragment();

      Bundle args = new Bundle();
      fragment.setArguments(args);

      return fragment;
    }


  @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
        final Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_bluetooth_dimension, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      /*Utils.init(getContext());
      lineChart = view.findViewById(R.id.line_dimension_chart);
      startDimensionDialogFragment = new StartDimensionDialogFragment();
      startDimensionDialogFragment.show(getFragmentManager(), "dialog");
      startDimensionDialogFragment.setDialogListener(this);*/
      webView = view.findViewById(R.id.web_view_bluetooth_dimension);
      WebSettings webSettings = webView.getSettings();
      webSettings.setAllowFileAccess(true);
      webSettings.setAllowFileAccessFromFileURLs(true);
      webView.setWebChromeClient(new WebChromeClient());
      webSettings.setJavaScriptEnabled(true);
      webView.loadUrl("file:android_asset/test.html");
      //webView.loadUrl("javascript:increment()");
    }

    /*public void initLineChart(List<ILineDataSet> dataSets){
      YAxis y_right = lineChart.getAxisRight();
      YAxis y_left = lineChart.getAxisLeft();
      Legend legend = lineChart.getLegend();
      Description des = lineChart.getDescription();
      des.setText("");
      //legend.setVerticalAlignment(LegendVerticalAlignment.BOTTOM);
      y_right.setDrawLabels(false);
      y_left.setValueFormatter(new LargeValueFormatter());
      LineData data = new LineData(dataSets);
      lineChart.setData(data);
      lineChart.invalidate();
    }

  @Override
  public void notifyLineChart() {
    lineChart.notifyDataSetChanged();
    lineChart.invalidate();
  }*/

  private String readHtml(String remoteUrl) {
    String out = "";
    BufferedReader in = null;
    try {
      URL url = new URL(remoteUrl);
      in = new BufferedReader(new InputStreamReader(url.openStream()));
      String str;
      while ((str = in.readLine()) != null) {
        out += str;
      }
    } catch (MalformedURLException e) {
    } catch (IOException e) {
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return out;
  }

  @Override
  public void onResume() {
    super.onResume();
    //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    IntentFilter filter = new IntentFilter();
    filter.addAction(BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE);
    getActivity().registerReceiver(mBroadcastReceiver, filter);

  }

  @Override
  public void onStop() {
    super.onStop();
    getActivity().unregisterReceiver(mBroadcastReceiver);
    //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
  }

  @Override
  public void onDialogPositiveClick() {
    description = startDimensionDialogFragment.getDescriptions();
    isPractice = startDimensionDialogFragment.isPractice();
    gender = startDimensionDialogFragment.getGender();
    String selectedItem = startDimensionDialogFragment.getSpinnerDimensionMode();
    isDimensoinStart = true;
  }

  @Override
  public void onDialogNegativeClick(DialogFragment dialog) {

  }

  @Override
  public void initLineChart(List<DataEntry> seriesData) {
    Cartesian cartesian = AnyChart.line();

    cartesian.animation(true);

    cartesian.padding(10d, 20d, 5d, 20d);

    cartesian.crosshair().enabled(true);
    cartesian.crosshair()
        .yLabel(true)
        // TODO ystroke
        .yStroke((Stroke) null, null, null, (String) null, (String) null);

    cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

    cartesian.title("Trend of Sales of the Most Popular Products of ACME Corp.");

    cartesian.yAxis(0).title("Number of Bottles Sold (thousands)");
    cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

    Set set = Set.instantiate();
    set.data(seriesData);
    Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
    Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
    Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");
    Mapping series4Mapping = set.mapAs("{ x: 'x', value: 'value4' }");
    Mapping series5Mapping = set.mapAs("{ x: 'x', value: 'value5' }");
    Mapping series6Mapping = set.mapAs("{ x: 'x', value: 'value6' }");
    Mapping series7Mapping = set.mapAs("{ x: 'x', value: 'value7' }");
    Mapping series8Mapping = set.mapAs("{ x: 'x', value: 'value8' }");

    Line series1 = cartesian.line(series1Mapping);
    series1.name("Sensor1");
    series1.hovered().markers().enabled(true);
    series1.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4d);
    series1.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5d)
        .offsetY(5d);

    /*Line series2 = cartesian.line(series2Mapping);
    series2.name("Sensor2");
    series2.hovered().markers().enabled(true);
    series2.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4d);
    series2.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5d)
        .offsetY(5d);

    Line series3 = cartesian.line(series3Mapping);
    series3.name("Sensor3");
    series3.hovered().markers().enabled(true);
    series3.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4d);
    series3.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5d)
        .offsetY(5d);

    Line series4 = cartesian.line(series4Mapping);
    series4.name("Sensor4");
    series4.hovered().markers().enabled(true);
    series4.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4d);
    series4.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5d)
        .offsetY(5d);

    Line series5 = cartesian.line(series5Mapping);
    series5.name("Sensor3");
    series5.hovered().markers().enabled(true);
    series5.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4d);
    series5.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5d)
        .offsetY(5d);

    Line series6 = cartesian.line(series6Mapping);
    series6.name("Sensor3");
    series6.hovered().markers().enabled(true);
    series6.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4d);
    series6.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5d)
        .offsetY(5d);

    Line series7 = cartesian.line(series7Mapping);
    series7.name("Sensor3");
    series7.hovered().markers().enabled(true);
    series7.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4d);
    series7.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5d)
        .offsetY(5d);

    Line series8 = cartesian.line(series8Mapping);
    series8.name("Sensor3");
    series8.hovered().markers().enabled(true);
    series8.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4d);
    series8.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5d)
        .offsetY(5d);*/

    cartesian.legend().enabled(true);
    cartesian.legend().fontSize(13d);
    cartesian.legend().padding(0d, 0d, 10d, 0d);
    APIlib.getInstance().setActiveAnyChartView(lineChart);

    lineChart.setChart(cartesian);
  }

  @Override
  public void notifyLineChart() {
    lineChart.notify();


  }
}
