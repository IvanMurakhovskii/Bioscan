package com.murik.enose.ui.fragment.input;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.R;
import com.murik.enose.model.InputData;
import com.murik.enose.presentation.input.InputPresenter;
import com.murik.enose.presentation.input.InputView;
import java.util.ArrayList;


public class InputFragment extends MvpAppCompatFragment implements InputView {

  public static final String TAG = "InputFragment";

  @InjectPresenter
  InputPresenter mInputPresenter;

  ArrayList<EditText> sensorData = new ArrayList<>();
  private InputData inputData = new InputData();
  private Button submitButton;
  private CheckBox cbPractice;

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

    return view;
  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if(!sensorData.isEmpty()){
      sensorData.clear();
    }
    sensorData.add(view.findViewById(R.id.sens_1));
    sensorData.add(view.findViewById(R.id.sens_2));
    sensorData.add(view.findViewById(R.id.sens_3));
    sensorData.add(view.findViewById(R.id.sens_4));
    sensorData.add(view.findViewById(R.id.sens_5));
    sensorData.add(view.findViewById(R.id.sens_6));
    sensorData.add(view.findViewById(R.id.sens_7));
    sensorData.add(view.findViewById(R.id.sens_8));

    cbPractice = view.findViewById(R.id.cbPractice);
    submitButton = view.findViewById(R.id.btSubmit);

    submitButton.setOnClickListener(event -> {
      inputData.setDatasens(getEditTextData());
      inputData.setPractice(cbPractice.isChecked());
      mInputPresenter.onSubmitButtonClick(inputData);
    });

  }

  public ArrayList<Integer> getEditTextData () {


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
}
