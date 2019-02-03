package com.murik.enose.presentation.presenter.dimension;


import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.murik.enose.presentation.view.dimension.BluetoothDimensionView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * todo
 *
 * создать диалог, который будет отображаться во время подключения к прибору
 *
 * диалог с выбором режима измерения
 * инициализация начальных значений
 * начало измерения
 * предупреждения убрать руку в указанных момент времени
 * запись данных каждую секунду
 * расчет параметров и вывод ошибка, если это необходимо
 * по возможности сделать горизонтальную ориентацию экрана
 *
 * */

@InjectViewState
public class BluetoothDimensionPresenter extends MvpPresenter<BluetoothDimensionView> {



  private ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
  private ArrayList<Entry> entrySens1 = new ArrayList<>();
  private ArrayList<Entry> entrySens2 = new ArrayList<>();
  private ArrayList<Entry> entrySens3 = new ArrayList<>();
  private ArrayList<Entry> entrySens4 = new ArrayList<>();
  private ArrayList<Entry> entrySens5 = new ArrayList<>();
  private ArrayList<Entry> entrySens6 = new ArrayList<>();
  private ArrayList<Entry> entrySens7 = new ArrayList<>();
  private ArrayList<Entry> entrySens8 = new ArrayList<>();

  private LineDataSet dataSet1 = new LineDataSet(entrySens1,  "sensor 1");
 /* private LineDataSet dataSet2 = new LineDataSet(entrySens2,  "sensor 2");
  private LineDataSet dataSet3 = new LineDataSet(entrySens3,  "sensor 3");
  private LineDataSet dataSet4 = new LineDataSet(entrySens4,  "sensor 4");
  private LineDataSet dataSet5 = new LineDataSet(entrySens5,  "sensor 5");
  private LineDataSet dataSet6 = new LineDataSet(entrySens6,  "sensor 6");
  private LineDataSet dataSet7 = new LineDataSet(entrySens7,  "sensor 7");
  private LineDataSet dataSet8 = new LineDataSet(entrySens8,  "sensor 8");
*/
  private Map<Integer, Integer> initial = new HashMap<>();
  private Map<String, ArrayList<Integer>> data = new HashMap<>();
  private ArrayList<Integer> arrDataSens1 = new ArrayList<>();

  private List<DataEntry> seriesData = new ArrayList<>();
  private Map<Integer, Number> value = new HashMap<>();

  private int count = 0;
  @Override
  protected void onFirstViewAttach() {


    super.onFirstViewAttach();
    entrySens1.add(new Entry(0f,0f));
    entrySens2.add(new Entry(0f,0f));
    entrySens3.add(new Entry(0f,0f));
    entrySens4.add(new Entry(0f,0f));
    entrySens5.add(new Entry(0f,0f));
    entrySens6.add(new Entry(0f,0f));
    entrySens7.add(new Entry(0f,0f));
    entrySens8.add(new Entry(0f,0f));

    lineDataSets.add(dataSet1);
   /* lineDataSets.add(dataSet2);
    lineDataSets.add(dataSet3);
    lineDataSets.add(dataSet4);
    lineDataSets.add(dataSet5);
    lineDataSets.add(dataSet6);
    lineDataSets.add(dataSet7);
    lineDataSets.add(dataSet8);*/

   // getViewState().initLineChart(lineDataSets);
    value.put(1, 1);
    value.put(2, 2);
    value.put(3, 3);
    value.put(4, 4);
    value.put(5, 20);
    value.put(6, 10);
    value.put(7, 6);
    value.put(8, 9);
    seriesData.add(new CustomDataEntry(0, 0));
    //getViewState().initLineChart(seriesData);
  }

  public void startDimension(String str) {
    int  sensNumber = 0;
    int sensValue = 0;

    for(int i = 0;i <  str.length(); i=i+8) {
      sensNumber = Integer.decode(str.substring(i, i + 1));
      sensValue = Integer.parseInt(str.substring(i + 1, i + 8), 16);
      if (initial.size() < 8) {
          initial.put((sensNumber), sensValue);
        } else {
        if(sensNumber == 1){
          seriesData.add(new CustomDataEntry(seriesData.size(),initial.get(sensNumber) -  sensValue));
         count++;
        }
        if(count == 10){
          getViewState().initLineChart(seriesData);
          count = 0;
        }
        /* if(value.size() > 7){
           seriesData.add(new CustomDataEntry(seriesData.size(), value));
           getViewState().initLineChart(seriesData);
         }*/


        /*if(sensNumber == 1){
          arrDataSens1.add(initial.get(sensNumber) - sensValue);
          entrySens1.add(new Entry((float) entrySens1.size(),(float) (initial.get(sensNumber) - sensValue)));
          Collections.sort(entrySens1, new EntryXComparator());
          dataSet1 = new LineDataSet(entrySens1, "sensor 1");
          dataSet1.setColor(Color.BLACK);

          getViewState().notifyLineChart();
        } else if(sensNumber == 2){
          //data.put(Const.SENSOR_2, sensValue);
          entrySens2.add(new Entry(entrySens2.size(),(initial.get(sensNumber) - sensValue)));
          Collections.sort(entrySens2, new EntryXComparator());
          dataSet2.notifyDataSetChanged();
        } else if(sensNumber == 3) {
          //data.put(Const.SENSOR_3, sensValue);
          entrySens3.add(new Entry(entrySens3.size(),(initial.get(sensNumber) - sensValue)));
          Collections.sort(entrySens3, new EntryXComparator());
          dataSet3.notifyDataSetChanged();
        } else if(sensNumber == 4) {
          //data.put(Const.SENSOR_4, sensValue);
          entrySens4.add(new Entry(entrySens4.size(),(initial.get(sensNumber) - sensValue)));
          Collections.sort(entrySens4, new EntryXComparator());
          dataSet4.notifyDataSetChanged();
        } else if(sensNumber == 5) {
          //data.put(Const.SENSOR_5, sensValue);
          entrySens5.add(new Entry(entrySens5.size(),(initial.get(sensNumber) - sensValue)));
          Collections.sort(entrySens5, new EntryXComparator());
          dataSet5.notifyDataSetChanged();
        } else if(sensNumber == 6) {
          //data.put(Const.SENSOR_6, sensValue);
          entrySens6.add(new Entry(entrySens6.size(),(initial.get(sensNumber) - sensValue)));
          Collections.sort(entrySens6, new EntryXComparator());
          dataSet6.notifyDataSetChanged();
        } else if(sensNumber == 7) {
          //data.put(Const.SENSOR_7, sensValue);
          entrySens7.add(new Entry(entrySens7.size(),(initial.get(sensNumber) - sensValue)));
          Collections.sort(entrySens7, new EntryXComparator());
          dataSet7.notifyDataSetChanged();
        } else if(sensNumber == 8) {
          //data.put(Const.SENSOR_8, sensValue);
          entrySens8.add(new Entry(entrySens8.size(),(initial.get(sensNumber) - sensValue)));
          Collections.sort(entrySens8, new EntryXComparator());
          dataSet8.notifyDataSetChanged();
        }*/

        //getViewState().notifyLineChart();
      }
    }

  }
  private class CustomDataEntry extends ValueDataEntry {

    CustomDataEntry(Number x, Number value) {
      super(x, value);
      //setValue("value2", value.get(2));
     /* setValue("value3", value.get(3));
      setValue("value4", value.get(4));
      setValue("value5", value.get(5));
      setValue("value6", value.get(6));
      setValue("value7", value.get(7));
      setValue("value8", value.get(8));*/
    }
  }

}
