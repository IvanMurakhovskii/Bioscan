package com.murik.lite.ui.fragment.resultRadarChart;

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
import com.murik.lite.App;
import com.murik.lite.Const;
import com.murik.lite.R;
import com.murik.lite.Screens;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.dto.SensorDataFullParcelable;
import com.murik.lite.service.Impl.MeasureServiceImpl;
import com.murik.lite.service.MeasureService;
import com.murik.lite.ui.fragment.resultRadarChart.tab.ResultRadarChartTabAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

public  class RadarTabContentFragment extends MvpAppCompatFragment {

  private SensorDataFullParcelable inputDataParcelable;
  private TabLayout tabLayout;
  private ViewPager viewPager;
  private Button btnResult;
  private TextView tv_k_3_left;
  private TextView tv_k_4_left;
  private TextView tv_k_5_left;
  private TextView tv_k_7_left;
  private TextView tv_difference;
  private LinearLayout ll_k_3_left;
  private LinearLayout ll_k_4_left;
  private LinearLayout ll_k_5_left;
  private LinearLayout ll_k_7_left;

  private TextView tv_k_3_right;
  private TextView tv_k_4_right;
  private TextView tv_k_5_right;
  private TextView tv_k_7_right;
  private LinearLayout ll_k_3_right;
  private LinearLayout ll_k_4_right;
  private LinearLayout ll_k_5_right;
  private LinearLayout ll_k_7_right;

  private float left_k3 = 0;
  private float left_k4 = 0;
  private float left_k5 = 0;
  private float left_k7 = 0;
  private float right_k3 = 0;
  private float right_k4 = 0;
  private float right_k5 = 0;
  private float right_k7 = 0;

  private MeasureService measureService;


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
    tv_k_3_left = view.findViewById(R.id.tv_k3_left);
    tv_k_4_left = view.findViewById(R.id.tv_k4_left);
    tv_k_5_left = view.findViewById(R.id.tv_k5_left);
    tv_k_7_left = view.findViewById(R.id.tv_k7_left);
    tv_k_3_right = view.findViewById(R.id.tv_k3_right);
    tv_k_4_right = view.findViewById(R.id.tv_k4_right);
    tv_k_5_right = view.findViewById(R.id.tv_k5_right);
    tv_k_7_right = view.findViewById(R.id.tv_k7_right);

    ll_k_3_left = view.findViewById(R.id.ll_k_3);
    ll_k_4_left = view.findViewById(R.id.ll_k_4);
    ll_k_5_left = view.findViewById(R.id.ll_k_5);
    ll_k_7_left = view.findViewById(R.id.ll_k_7);
    ll_k_3_right = view.findViewById(R.id.ll_k_3_right);
    ll_k_4_right = view.findViewById(R.id.ll_k_4_right);
    ll_k_5_right= view.findViewById(R.id.ll_k_5_right);
    ll_k_7_right = view.findViewById(R.id.ll_k_7_right);

    calculate_k();

    btnResult = view.findViewById(R.id.btnResult_by_max);

    btnResult.setOnClickListener(event -> App.INSTANCE.getRouter().navigateTo(Screens.RESULT_FRAGMENT, createDate()));

    ResultRadarChartTabAdapter adapter = new ResultRadarChartTabAdapter(getChildFragmentManager(), inputDataParcelable);
    viewPager.setAdapter(adapter);

    viewPager.setOffscreenPageLimit(3);
    tabLayout.setupWithViewPager(viewPager);

  }

  public float leftHand_k3() {
    if (inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3) != null && Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3)).get(20) != 0) {
      if (Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3)).size() > 60) {
        return (float) Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3)).get(60)
            / Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3)).get(20);
      }
    }
    return 0;
  }

  public float rightHand_k3() {
    if (inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3) != null && Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3)).get(20) != 0) {
      if (Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3)).size() > 60) {
        return (float) Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3)).get(60)
            / Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3)).get(20);
      }
    }
    return 0;
  }

  public float leftHand_k4() {
    if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4) != null && Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4)).get(20) != 0){
      if (Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4)).size() > 60){
        return  (float) Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4)).get(60)
            / Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4)).get(20);
      }
    }
    return 0;
  }

  public float rightHand_k4() {
    if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4) != null && Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4)).get(20) != 0){
      if (Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4)).size() > 60){
        return  (float) Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4)).get(60)
            / Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4)).get(20);
      }
    }
    return 0;
  }

  public float leftHand_k5() {
    if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5) != null && Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5)).get(30) != 0){
      if(Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5)).size() > 60){
        return (float) Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5)).get(60)
            / Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5)).get(30);
      }
    }
    return 0;
  }

  public float rightHand_k5() {
    if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5) != null && Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5)).get(30) != 0){
      if(Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5)).size() > 60){
        return (float) Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5)).get(60)
            / Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5)).get(30);
      }
    }
    return 0;
  }

  public float leftHand_k7() {
    if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7) != null && Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7)).get(30) != 0){
      if(Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7)).size() > 60){

        return (float) Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7)).get(60)
            / Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7)).get(30);
      }
    }
    return 0;
  }

  public float rightHand_k7() {
    if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7) != null && Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7)).get(30) != 0){
      if(Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7)).size() > 60){
        return (float) Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7)).get(60)
            / Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7)).get(30);
      }
    }
    return 0;
  }

  public void calculate_k() {
    left_k3 = leftHand_k3();
    left_k4 = leftHand_k4();
    left_k5 = leftHand_k5();
    left_k7 = leftHand_k7();

    right_k3 = rightHand_k3();
    right_k4 = rightHand_k4();
    right_k5 = rightHand_k5();
    right_k7 = rightHand_k7();

    tv_k_3_left.setText(String.valueOf( new BigDecimal(left_k3).setScale(1, RoundingMode.DOWN).floatValue()));
    tv_k_4_left.setText(String.valueOf( new BigDecimal(left_k4).setScale(1, RoundingMode.DOWN).floatValue()));
    tv_k_5_left.setText(String.valueOf( new BigDecimal(left_k5).setScale(1, RoundingMode.DOWN).floatValue()));
    tv_k_7_left.setText(String.valueOf( new BigDecimal(left_k7).setScale(1, RoundingMode.DOWN).floatValue()));

    tv_k_3_right.setText(String.valueOf( new BigDecimal(right_k3).setScale(1, RoundingMode.DOWN).floatValue()));
    tv_k_4_right.setText(String.valueOf( new BigDecimal(right_k4).setScale(1, RoundingMode.DOWN).floatValue()));
    tv_k_5_right.setText(String.valueOf( new BigDecimal(right_k5).setScale(1, RoundingMode.DOWN).floatValue()));
    tv_k_7_right.setText(String.valueOf( new BigDecimal(right_k7).setScale(1, RoundingMode.DOWN).floatValue()));

    if(left_k4 < 2.0){
      ll_k_4_left.setBackgroundColor(Color.RED);
    }
    if(right_k4 < 2.0){
      ll_k_4_right.setBackgroundColor(Color.RED);
    }

    if(left_k5 >= 2.5){
      ll_k_5_left.setBackgroundColor(Color.RED);
    }
    if(right_k5 >= 2.5){
      ll_k_5_right.setBackgroundColor(Color.RED);
    }
  }


  public DataByMaxParcelable createDate(){
    measureService = new MeasureServiceImpl(inputDataParcelable);
    DataByMaxParcelable dataByMaxParcelable = new DataByMaxParcelable();
    dataByMaxParcelable.setDescriptions(inputDataParcelable.getDescriptions());
    dataByMaxParcelable.setGender(inputDataParcelable.getGender());
    dataByMaxParcelable.setPractice(inputDataParcelable.isPractice());
    dataByMaxParcelable.setDifferenceArea(measureService.getDifferenceArea());

    ArrayList<Integer> leftHand = new ArrayList<>();
    if(!inputDataParcelable.getDataSensorMapLeftHand().isEmpty()){
      if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_1) != null){
        leftHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_1))));
      } else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_2) != null){
        leftHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_2))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3) != null){
        leftHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_3))));
      }
      if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4) != null){
        leftHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_4))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5) != null){
        leftHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_5))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_6) != null){
        leftHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_6))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7) != null){
        leftHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_7))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_8) != null){
        leftHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapLeftHand().get(Const.SENSOR_8))));
      }else {
        leftHand.add(0);
      }
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
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_1) != null){
        rightHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_1))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_2) != null){
        rightHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_2))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3) != null){
        rightHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_3))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4) != null){
        rightHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_4))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5) != null){
        rightHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_5))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_6) != null){
        rightHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_6))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7) != null){
        rightHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_7))));
      }else {
        leftHand.add(0);
      }
      if(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_8) != null){
        rightHand.add(max(Objects.requireNonNull(inputDataParcelable.getDataSensorMapRightHand().get(Const.SENSOR_8))));
      }else {
        leftHand.add(0);
      }
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
