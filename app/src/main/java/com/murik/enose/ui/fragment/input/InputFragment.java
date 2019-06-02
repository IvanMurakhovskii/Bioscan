package com.murik.enose.ui.fragment.input;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.presentation.presenter.input.InputPresenter;
import com.murik.enose.presentation.view.input.InputView;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;
import java.util.ArrayList;


public class InputFragment extends MvpAppCompatFragment implements InputView, VerticalStepperForm {

  public static final String TAG = "InputFragment";

  public static final int DESCRIPTION_STEP_NUM = 0;
  public static final int OTHER_INFORMATION_STEP_NUM = 1;
  public static final int INPUT_LEFT_HAND_STEP_NUM = 2;
  public static final int INPUT_RIGHT_HAND_STEP_NUM = 3;

  @InjectPresenter
  InputPresenter mInputPresenter;

  //discriptins step
  private EditText etDiscriptions;

  //other informations step
  private RadioGroup rgGender;
  private SwitchCompat swPractice;
  int gender = Const.GENDER_MALE;

  //right hand sensor step
  ArrayList<EditText> rightHandSensorData = new ArrayList<>();

  //left hand sensor step
  ArrayList<EditText> leftHandSensorData = new ArrayList<>();

  private VerticalStepperFormLayout verticalStepperForm;

  public static InputFragment newInstance() {
    InputFragment fragment = new InputFragment();

    Bundle args = new Bundle();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {
    View view =  inflater.inflate(R.layout.fragment_input, container, false);
    setHasOptionsMenu(true);
    return view;
  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    verticalStepperForm =  view.findViewById(R.id.vertical_stepper_form);

    String[] mySteps = {
        getString(R.string.descriptions)
        ,getString(R.string.about_myself)
        ,getString(R.string.left_hand)
        ,getString(R.string.right_hand)
    };

    int colorPrimary = ContextCompat.getColor(getContext(), R.color.colorPrimary);
    int colorPrimaryDark = ContextCompat.getColor(getContext(), R.color.colorPrimaryDark);

    VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, getActivity())
        .primaryColor(colorPrimary)
        .primaryDarkColor(colorPrimaryDark)
        .displayBottomNavigation(true)
        .init();
  }

  public ArrayList<Integer> getEditTextData (ArrayList<EditText> sensorData) {

    ArrayList<Integer> maxSens = new ArrayList<>();
    for (int i = 0; i < sensorData.size(); i++) {

      if (VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD) {
        if (sensorData.get(i).getText().toString().isEmpty()) {
          maxSens.add(0);
        } else {
          maxSens.add(Integer.parseInt(sensorData.get(i).getText().toString()));
        }
      }
    }
    return maxSens;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_input_fragment, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public View createStepContentView(int stepNumber) {

    View view = null;
    switch(stepNumber) {
      case DESCRIPTION_STEP_NUM: {
        view = createDescriptionsStep();
        break;
      }
      case OTHER_INFORMATION_STEP_NUM: {
        view = createOtherInformationsStep();
        break;
      }
      case INPUT_LEFT_HAND_STEP_NUM: {
        view = createLeftHandDataStep();
        break;
      }
      case INPUT_RIGHT_HAND_STEP_NUM: {
        view = createRightHandDataStep();
        break;
      }
    }
    return view;
  }

  @Override
  public void onStepOpening(int stepNumber) {
    switch(stepNumber) {
      case DESCRIPTION_STEP_NUM:{
        verticalStepperForm.setActiveStepAsCompleted();
        break;
      }
      case OTHER_INFORMATION_STEP_NUM:{
        verticalStepperForm.setActiveStepAsCompleted();
        break;
      }
      case INPUT_LEFT_HAND_STEP_NUM: {
        verticalStepperForm.setActiveStepAsCompleted();
        break;
      }
      case INPUT_RIGHT_HAND_STEP_NUM:{
        verticalStepperForm.setActiveStepAsCompleted();
        break;
      }
    }
  }





  @Override
  public void sendData() {
      DataByMaxParcelable input = new DataByMaxParcelable();
      input.setDescriptions(etDiscriptions.getText().toString());
      input.setGender(gender);
      input.setPractice(swPractice.isChecked());
      input.setRightHandDataSensor(getEditTextData(rightHandSensorData));
      input.setLeftHandDataSensor(getEditTextData(leftHandSensorData));
      input.setDifferenceArea(0.0f);
    mInputPresenter.onSubmitButtonClick(input);

  }

  private View createDescriptionsStep(){
    LayoutInflater inflater = LayoutInflater.from(getContext());
    View view = inflater.inflate(R.layout.step_descriptions, null, true);
    etDiscriptions = view.findViewById(R.id.etDescription);
    return view;
  }

  private View createOtherInformationsStep(){
    LayoutInflater inflater = LayoutInflater.from(getContext());
    View view =  inflater.inflate(R.layout.step_other_informations, null, false);
    rgGender = view.findViewById(R.id.rg_gender);
    swPractice = view.findViewById(R.id.sc_practice);

    rgGender.setOnCheckedChangeListener((group,checkedId) -> {
      switch (checkedId){
        case R.id.rb_male:
          gender = Const.GENDER_MALE;
          break;
        case R.id.rb_feminine:
          gender = Const.GENDER_FEMININE;
          break;
      }
    });

    return view;
  }

  private View createRightHandDataStep(){
    LayoutInflater inflater = LayoutInflater.from(getContext());
    View view =  inflater.inflate(R.layout.step_right_sensor_data, null, false);
    if(!rightHandSensorData.isEmpty()){
      rightHandSensorData.clear();
    }
    rightHandSensorData.add(view.findViewById(R.id.sens_1r));
    rightHandSensorData.add(view.findViewById(R.id.sens_2r));
    rightHandSensorData.add(view.findViewById(R.id.sens_3r));
    rightHandSensorData.add(view.findViewById(R.id.sens_4r));
    rightHandSensorData.add(view.findViewById(R.id.sens_5r));
    rightHandSensorData.add(view.findViewById(R.id.sens_6r));
    rightHandSensorData.add(view.findViewById(R.id.sens_7r));
    rightHandSensorData.add(view.findViewById(R.id.sens_8r));
    return view;
  }

  private View createLeftHandDataStep(){
    LayoutInflater inflater = LayoutInflater.from(getContext());
    View view =  inflater.inflate(R.layout.step_left_input_sensor_data, null, false);
    if(!leftHandSensorData.isEmpty()){
      leftHandSensorData.clear();
    }
    leftHandSensorData.add(view.findViewById(R.id.sens_1));
    leftHandSensorData.add(view.findViewById(R.id.sens_2));
    leftHandSensorData.add(view.findViewById(R.id.sens_3));
    leftHandSensorData.add(view.findViewById(R.id.sens_4));
    leftHandSensorData.add(view.findViewById(R.id.sens_5));
    leftHandSensorData.add(view.findViewById(R.id.sens_6));
    leftHandSensorData.add(view.findViewById(R.id.sens_7));
    leftHandSensorData.add(view.findViewById(R.id.sens_8));
    return view;
  }
}
