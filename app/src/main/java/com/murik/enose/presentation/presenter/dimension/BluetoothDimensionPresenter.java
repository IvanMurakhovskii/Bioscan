package com.murik.enose.presentation.presenter.dimension;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.Screens;
import com.murik.enose.model.RealmController;
import com.murik.enose.dto.SensorDataFullParcelable;
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

  private Map<Integer, Integer> initial = new HashMap<>();
  private Map<String, ArrayList<Integer>> data = new HashMap<>();
  private ArrayList<Integer> arrDataSens1 = new ArrayList<>();

  private List<Integer> sens1 = new ArrayList<>();
  private List<Integer> sens2 = new ArrayList<>();
  private List<Integer> sens3 = new ArrayList<>();
  private List<Integer> sens4 = new ArrayList<>();
  private List<Integer> sens5 = new ArrayList<>();
  private List<Integer> sens6 = new ArrayList<>();
  private List<Integer> sens7 = new ArrayList<>();
  private List<Integer> sens8 = new ArrayList<>();

  Map<String, ArrayList<Integer>> dataLeftHand = new HashMap<>();
  Map<String, ArrayList<Integer>> dataRightHand = new HashMap<>();

  private RealmController realmController = new RealmController();

  public boolean isFirstDimension = true;
  private Map<Integer, Number> value = new HashMap<>();

  private int count = 0;
  @Override
  protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }



  public void getDimensionData(String str) {
    int  sensNumber = 0;
    int sensValue = 0;
    /*if(sens1.size() == 10){
      if(isFirstDimension){
        isFirstDimension = false;
        getViewState().stopChartRender();
        getViewState().showContinueDialog();
      } else {

        if(dataLeftHand == null){
          saveData(true);
        } else {
          saveData(false);
        }

        //todo save()
      }

    }*/
    for(int i = 0;i <  str.length(); i=i+8) {
      sensNumber = Integer.decode(str.substring(i, i + 1));
      sensValue = Integer.parseInt(str.substring(i + 1, i + 8), 16);
      if (initial.size() < 8) {
          initial.put((sensNumber), sensValue);
        } else {
        if(sensNumber == 1){
          sens1.add(initial.get(sensNumber) -  sensValue);

        }if(sensNumber == 2){
          sens2.add(initial.get(sensNumber) -  sensValue);

        }if(sensNumber == 3){
          sens3.add(initial.get(sensNumber) -  sensValue);

        }if(sensNumber == 4){
          sens4.add(initial.get(sensNumber) -  sensValue);

        }if(sensNumber == 5){
          sens5.add(initial.get(sensNumber) -  sensValue);

        }if(sensNumber == 6){
          sens6.add(initial.get(sensNumber) -  sensValue);

        }if(sensNumber == 7){
          sens7.add(initial.get(sensNumber) -  sensValue);

        }if(sensNumber == 8){
          sens8.add(initial.get(sensNumber) -  sensValue);
        }
      }
    }
  }

  public void save(String description, boolean isPractice, int gender){
    SensorDataFullParcelable sensorDataFullParcelable = new SensorDataFullParcelable();
    sensorDataFullParcelable.setDescriptions(description);
    sensorDataFullParcelable.setFullData(true);
    sensorDataFullParcelable.setGender(gender);
    sensorDataFullParcelable.setPractice(isPractice);
    sensorDataFullParcelable.setDataSensorMapLeftHand(dataLeftHand);
    sensorDataFullParcelable.setDataSensorMapRightHand(dataRightHand);

    realmController.addInfoFull(sensorDataFullParcelable);
    App.INSTANCE.getRouter().navigateTo(Screens.REALM_FRAGMENT);
  }

  public void saveData(boolean isLeftHand){
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
  }

  public void clearData(){
    sens1.clear();
    sens2.clear();
    sens3.clear();
    sens4.clear();
    sens5.clear();
    sens6.clear();
    sens7.clear();
    sens8.clear();
    initial.clear();
  }

  public int getLastDataSens1() {
    if(sens1.size() > 1){
      return sens1.get(sens1.size() - 1);
    }
    return 0;
  }

  public int getLastDataSens2() {
    if(sens2.size() > 1){
      return sens2.get(sens2.size() - 1);
    }
    return 0;
  }

  public int getLastDataSens3() {
    if(sens3.size() > 1){
      return sens3.get(sens3.size() - 1);
    }
    return 0;
  }

  public int getLastDataSens4() {
    if(sens4.size() > 1){
      return sens4.get(sens4.size() - 1);
    }
    return 0;
  }

  public int getLastDataSens5() {
    if(sens5.size() > 1){
      return sens5.get(sens5.size() - 1);
    }
    return 0;
  }

  public int getLastDataSens6() {
    if(sens6.size() > 1){
      return sens6.get(sens6.size() - 1);
    }
    return 0;
  }

  public int getLastDataSens7() {
    if(sens7.size() > 1){
      return sens7.get(sens7.size() - 1);
    }
    return 0;
  }

  public int getLastDataSens8() {
    if(sens8.size() > 1){
      return sens8.get(sens8.size() - 1);
    }
    return 0;
  }

}
