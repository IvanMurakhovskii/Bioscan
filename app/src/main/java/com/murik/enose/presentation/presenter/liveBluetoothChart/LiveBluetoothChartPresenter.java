package com.murik.enose.presentation.presenter.liveBluetoothChart;


import android.graphics.Color;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.murik.enose.presentation.view.liveBluetoothChart.LiveBluetoothChartView;
import java.util.ArrayList;
import java.util.List;


@InjectViewState
public class LiveBluetoothChartPresenter extends MvpPresenter<LiveBluetoothChartView> {

    private List<ILineDataSet> dataSets = new ArrayList<>();
    private ArrayList<Entry> entrySens1 = new ArrayList<>();
    private ArrayList<Entry> entrySens2 = new ArrayList<>();
    private ArrayList<Entry> entrySens3 = new ArrayList<>();
    private ArrayList<Entry> entrySens4 = new ArrayList<>();
    private ArrayList<Entry> entrySens5 = new ArrayList<>();
    private ArrayList<Entry> entrySens6 = new ArrayList<>();
    private ArrayList<Entry> entrySens7 = new ArrayList<>();
    private ArrayList<Entry> entrySens8 = new ArrayList<>();

    LineDataSet dataSet1 = new LineDataSet(entrySens1,  "sensor 1");
    LineDataSet dataSet2 = new LineDataSet(entrySens2,  "sensor 2");
    LineDataSet dataSet3 = new LineDataSet(entrySens3,  "sensor 3");
    LineDataSet dataSet4 = new LineDataSet(entrySens4,  "sensor 4");
    LineDataSet dataSet5 = new LineDataSet(entrySens5,  "sensor 5");
    LineDataSet dataSet6 = new LineDataSet(entrySens6,  "sensor 6");
    LineDataSet dataSet7 = new LineDataSet(entrySens7,  "sensor 7");
    LineDataSet dataSet8 = new LineDataSet(entrySens8,  "sensor 8");

  public void addDataFromDevice(String str){
      int  sensNumber = 0;
      int sensValue = 0;
      for(int i = 0;i <  str.length(); i=i+8) {
        sensNumber = Integer.decode(str.substring(i, i + 1));
        //todo (sensValue - initial)
        sensValue = Integer.parseInt(str.substring(i + 1, i + 8), 16);

          if(sensNumber == 1){
            if(entrySens1.size() > 50){
              entrySens1.clear();
            }
            entrySens1.add(new Entry(entrySens1.size(), sensValue));
            dataSet1.notifyDataSetChanged();
            getViewState().notifyLineChart1();

          } else if(sensNumber == 2){
            if(entrySens2.size() > 50){
              entrySens2.clear();
            }
            entrySens2.add(new Entry(entrySens2.size(), sensValue));
            dataSet2.notifyDataSetChanged();
            getViewState().notifyLineChart2();

          } else if(sensNumber == 3) {
            if(entrySens3.size() > 50){
              entrySens3.clear();
            }
            entrySens3.add(new Entry(entrySens3.size(), sensValue));
            dataSet3.notifyDataSetChanged();
            getViewState().notifyLineChart3();

          } else if(sensNumber == 4) {
            if(entrySens4.size() > 50){
              entrySens4.clear();
            }
            entrySens4.add(new Entry(entrySens4.size(), sensValue));
            dataSet4.notifyDataSetChanged();
            getViewState().notifyLineChart4();

          } else if(sensNumber == 5) {
            if(entrySens5.size() > 50){
              entrySens5.clear();
            }
            entrySens5.add(new Entry(entrySens5.size(), sensValue));
            dataSet5.notifyDataSetChanged();
            getViewState().notifyLineChart5();

          } else if(sensNumber == 6) {
            if(entrySens6.size() > 50){
              entrySens6.clear();
            }
            entrySens6.add(new Entry(entrySens6.size(), sensValue));
            dataSet6.notifyDataSetChanged();
            getViewState().notifyLineChart6();
          } else if(sensNumber == 7) {
            if(entrySens7.size() > 50){
              entrySens7.clear();
            }
            entrySens7.add(new Entry(entrySens7.size(), sensValue));
            dataSet7.notifyDataSetChanged();
            getViewState().notifyLineChart7();
          } else if(sensNumber == 8) {
            if(entrySens8.size() > 50){
              entrySens8.clear();
            }
            entrySens8.add(new Entry(entrySens8.size(), sensValue));
            dataSet8.notifyDataSetChanged();
            getViewState().notifyLineChart8();
          }
        }

    getViewState().setLineChart(dataSets);
  }

  public void initLineChart(){
    entrySens1.add(new Entry(0,0));
    dataSets.add(dataSet1);
    dataSet1.setColor(Color.BLACK);
    entrySens2.add(new Entry(0,0));
    dataSets.add(dataSet2);
    dataSet2.setColor(Color.BLACK);
    entrySens3.add(new Entry(0,0));
    dataSets.add(dataSet3);
    dataSet3.setColor(Color.BLACK);
    entrySens4.add(new Entry(0,0));
    dataSets.add(dataSet4);
    dataSet4.setColor(Color.BLACK);
    entrySens5.add(new Entry(0,0));
    dataSets.add(dataSet5);
    dataSet5.setColor(Color.BLACK);
    entrySens6.add(new Entry(0,0));
    dataSets.add(dataSet6);
    dataSet6.setColor(Color.BLACK);
    entrySens7.add(new Entry(0,0));
    dataSets.add(dataSet7);
    dataSet7.setColor(Color.BLACK);
    entrySens8.add(new Entry(0,0));
    dataSets.add(dataSet8);
    dataSet8.setColor(Color.BLACK);

    getViewState().setLineChart(dataSets);
  }
}

