package com.murik.enose.presentation.presenter.dimension;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.Const;
import com.murik.enose.db.DBHelper;
import com.murik.enose.db.model.Measure;
import com.murik.enose.dto.SensorDataFullParcelable;
import com.murik.enose.model.RealmController;
import com.murik.enose.presentation.view.dimension.BluetoothDimensionView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

//  private Map<Integer, Integer> initial = new HashMap<>();
//  private Map<String, ArrayList<Integer>> data = new HashMap<>();
//  private ArrayList<Integer> arrDataSens1 = new ArrayList<>();

  private String description;
  private boolean isPractice;
  private int gender = Const.GENDER_MALE;
  private int measureTime;
  private Date initialTime;
//  private boolean isLeftHand = false;

  private List<List<Integer>> sensLeftHand = Arrays.asList(new ArrayList<>(), new ArrayList<>());
  private List<List<Integer>> sensRightHand = Arrays.asList(new ArrayList<>(), new ArrayList<>());
  List<Measure> measures = new ArrayList<>();

  Map<String, ArrayList<Integer>> dataLeftHand = new HashMap<>();
  Map<String, ArrayList<Integer>> dataRightHand = new HashMap<>();


  @Override
  protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }


  public void setDimensionParametrs(final String description,
                                    final int gender,
                                    final boolean isPractice,
                                    final boolean isLeftHand,
                                    final int measureTime) {
    this.description = description;
    this.gender = gender;
    this.isPractice = isPractice;
    this.measureTime = measureTime;
    initialTime = new Date();
//    this.isLeftHand = isLeftHand;
  }

  public void refreshInitialTime(){
    initialTime = new Date();
  }

  public void save(){
    SensorDataFullParcelable sensorDataFullParcelable = new SensorDataFullParcelable();
    sensorDataFullParcelable.setDescriptions(description);
    sensorDataFullParcelable.setFullData(true);
    sensorDataFullParcelable.setGender(gender);
    sensorDataFullParcelable.setPractice(isPractice);

    dataLeftHand.put(Const.SENSOR_1, new ArrayList<>(sensLeftHand.get(0)));
    dataRightHand.put(Const.SENSOR_1, new ArrayList<>(sensRightHand.get(0)));
    dataLeftHand.put(Const.SENSOR_2, new ArrayList<>(sensLeftHand.get(1)));
    dataRightHand.put(Const.SENSOR_2, new ArrayList<>(sensRightHand.get(1)));

    sensorDataFullParcelable.setDataSensorMapLeftHand(dataLeftHand);
    sensorDataFullParcelable.setDataSensorMapRightHand(dataRightHand);

    RealmController controller = new RealmController();
    controller.addInfoFull(sensorDataFullParcelable);
  }

  public void saveToDB(DBHelper dbHelper){

    int sessionId = dbHelper.createNewMeasureSession(gender, isPractice ? 1 : 0, measureTime,
            description, 0);

    for(Measure measure : measures){
      measure.setSessionId(sessionId);
    }

    dbHelper.saveMeasures(measures);
  }

  public void addSensData(boolean isLeftHand, int sensorIndex, int value) {
    if(isLeftHand){
      sensLeftHand.get(sensorIndex).add(value);
    } else {
      sensRightHand.get(sensorIndex).add(value);
    }
  }

  public void addSensFullData(boolean isLeftHand, int sensorIndex, int value, int diffValue) {

    if(initialTime == null){
      initialTime = new Date();
    }

    measures.add(new Measure(new Date(), 0, sensorIndex, isLeftHand ? 1 : 0, value,
            diffValue, new Date().getTime() - initialTime.getTime()));
  }

  public List<Integer> getSensData(boolean isLeftHand, int sensorIndex) {
    if(isLeftHand){
      return sensLeftHand.get(sensorIndex);
    } else {
      return sensRightHand.get(sensorIndex);
    }
  }

  /*public void saveData(boolean isLeftHand){
    Map<String, ArrayList<Integer>> data = new HashMap<>();
    data.put(Const.SENSOR_1, new ArrayList<>(sens1));
    data.put(Const.SENSOR_2, new ArrayList<>(sens2));
    data.put(Const.SENSOR_3, new ArrayList<>(sens3));
    data.put(Const.SENSOR_4, new ArrayList<>(sens4));
    data.put(Const.SENSOR_5, new ArrayList<>(sens5));
    data.put(Const.SENSOR_6, new ArrayList<>(sens6));
    data.put(Const.SENSOR_7, new ArrayList<>(sens7));
    data.put(Const.SENSOR_8, new ArrayList<>(sens8));

    if(isLeftHand){
      dataLeftHand = data;
    } else {
      dataRightHand = data;
    }
  }*/

 /* public int getLastDataSens1() {
    if(sens1.size() > 1){
      return sens1.get(sens1.size() - 1);
    }
    return 0;
  }*/

}
