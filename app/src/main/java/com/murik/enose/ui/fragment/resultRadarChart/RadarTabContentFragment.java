package com.murik.enose.ui.fragment.resultRadarChart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.Screens;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.dto.SensorDataFullParcelable;
import com.murik.enose.ui.fragment.resultRadarChart.tab.ResultRadarChartTabAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public  class RadarTabContentFragment extends MvpAppCompatFragment {

  private SensorDataFullParcelable inputDataParcelable;
  private TabLayout tabLayout;
  private ViewPager viewPager;
  private Button btnResult;
  private TextView tv_k_3;
  private TextView tv_k_4;
  private TextView tv_k_5;
  private LinearLayout ll_k_3;
  private LinearLayout ll_k_4;
  private LinearLayout ll_k_5;

  private float k3 = 0;
  private float k4 = 0;
  private float k5 = 0;


  public static Fragment newInstance(SensorDataFullParcelable resultBySens) {
    RadarTabContentFragment fragment = new RadarTabContentFragment();

    Bundle args = new Bundle();
    args.putParcelable(ResultRadarChartFragment.DATA, resultBySens);
    fragment.setArguments(args);

    return fragment;
  }
  @NonNull
  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {
    Bundle bundle = getArguments();
    if(bundle != null){
      inputDataParcelable =  bundle.getParcelable(ResultRadarChartFragment.DATA);
    }
    setHasOptionsMenu(true);
    return inflater.inflate(R.layout.fragment_result_tab_container, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    tabLayout = view.findViewById(R.id.sliding_tabs_result);
    viewPager = view.findViewById(R.id.viewpager_result);
    tv_k_3 = view.findViewById(R.id.tv_k3);
    tv_k_4 = view.findViewById(R.id.tv_k4);
    tv_k_5 = view.findViewById(R.id.tv_k6);

    ll_k_3 = view.findViewById(R.id.ll_k_3);
    ll_k_4 = view.findViewById(R.id.ll_k_4);
    ll_k_5 = view.findViewById(R.id.ll_k_5);

    calculate_k();
    tv_k_3.setText(String.valueOf( new BigDecimal(k3).setScale(1, RoundingMode.DOWN).floatValue()));
    tv_k_4.setText(String.valueOf( new BigDecimal(k4).setScale(1, RoundingMode.DOWN).floatValue()));
    tv_k_5.setText(String.valueOf( new BigDecimal(k5).setScale(1, RoundingMode.DOWN).floatValue()));

    btnResult = view.findViewById(R.id.btnResult_by_max);

    btnResult.setOnClickListener(event -> {
      App.INSTANCE.getRouter().navigateTo(Screens.RESULT_FRAGMENT, createDate());
    });

    ResultRadarChartTabAdapter adapter = new ResultRadarChartTabAdapter(getChildFragmentManager(), inputDataParcelable);
    viewPager.setAdapter(adapter);

    viewPager.setOffscreenPageLimit(3);
    tabLayout.setupWithViewPager(viewPager);

  }

  public void calculate_k() {
    if (!inputDataParcelable.getDataSensorMapLeftHand().isEmpty()) {
      if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3).size() > 60){
        k3 = (float)inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3).get(60)
            / inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3).get(20);
      }
      if (inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4).size() > 60){
        k4 = (float)inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4).get(60)
            / inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4).get(20);
      }
      if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5).size() > 60){
        k5 = (float)inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5).get(60)
             /inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5).get(30);
      }

    } else if (!inputDataParcelable.getDataSensorMapRightHand().isEmpty()) {
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3).size() > 60){
        k3 = (float)inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3).get(60)
            / inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3).get(20);
      }
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4).size() > 60){
        k4 = (float)inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4).get(60)
            / inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4).get(20);
      }
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7).size() > 60){
        k5 = (float)inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7).get(60)
            / inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7).get(30);
      }
    }

    if(k4 < 2.0){
      ll_k_4.setBackgroundColor(Color.RED);
    }
  }


  public DataByMaxParcelable createDate(){
    DataByMaxParcelable dataByMaxParcelable = new DataByMaxParcelable();
    dataByMaxParcelable.setDescriptions(inputDataParcelable.getDescriptions());
    dataByMaxParcelable.setGender(inputDataParcelable.getGender());
    dataByMaxParcelable.setPractice(inputDataParcelable.isPractice());

    ArrayList<Integer> leftHand = new ArrayList<>();
    if(!inputDataParcelable.getDataSensorMapLeftHand().isEmpty()){
      leftHand.add(max(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_1)));
      leftHand.add(max(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_2)));
      leftHand.add(max(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3)));
      leftHand.add(max(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4)));
      leftHand.add(max(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5)));
      leftHand.add(max(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_6)));
      leftHand.add(max(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7)));
      leftHand.add(max(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_8)));
    } else {
      leftHand.add(0);
      leftHand.add(0);
      leftHand.add(0);
      leftHand.add(0);
      leftHand.add(0);
      leftHand.add(0);
      leftHand.add(0);
      leftHand.add(0);
    }


    ArrayList<Integer> rightHand = new ArrayList<>();
    if(!inputDataParcelable.getDataSensorMapRightHand().isEmpty()){
      rightHand.add(max(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_1)));
      rightHand.add(max(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_2)));
      rightHand.add(max(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3)));
      rightHand.add(max(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4)));
      rightHand.add(max(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5)));
      rightHand.add(max(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_6)));
      rightHand.add(max(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7)));
      rightHand.add(max(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_8)));
    }else {
      rightHand.add(0);
      rightHand.add(0);
      rightHand.add(0);
      rightHand.add(0);
      rightHand.add(0);
      rightHand.add(0);
      rightHand.add(0);
      rightHand.add(0);
    }


    dataByMaxParcelable.setLeftHandDataSensor(leftHand);
    dataByMaxParcelable.setRightHandDataSensor(rightHand);
    return dataByMaxParcelable;
  }


  public Integer max(ArrayList<Integer> arr){
    Integer max = 0;
    for(Integer i : arr){
      if(i > max){
        max = i;
      }
    }
    return max;
  }
}
