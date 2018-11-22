package com.murik.enose.ui.fragment.parserXml;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.presentation.parserXml.ParserXmlPresenter;
import com.murik.enose.presentation.parserXml.ParserXmlView;
import com.murik.enose.ui.activity.start.ProgressDisplay;
import com.murik.enose.ui.fragment.parserXml.recycler.ParserXmlAdapter;

public class ParserXmlFragment extends MvpAppCompatFragment implements ParserXmlView {

  public static final String TAG = "ParserXmlFragment";
  @InjectPresenter
  ParserXmlPresenter mParserXmlPresenter;



  private RecyclerView mRecycler;
  private RadioGroup grGender;
  private RadioGroup rgHand;
  private SwitchCompat scPractice;
  private Button btnSave;

  private int gender = Const.GENDER_MALE;
  private boolean isLeftHand = true;

  public static ParserXmlFragment newInstance() {
    ParserXmlFragment fragment = new ParserXmlFragment();

    Bundle args = new Bundle();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_parser_xml, container, false);

  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mRecycler = view.findViewById(R.id.xmlFileRecycler);
    grGender = view.findViewById(R.id.rg_gender_xml);
    rgHand = view.findViewById(R.id.rg_hand);
    btnSave =  view.findViewById(R.id.btnSaveFromXml);
    scPractice = view.findViewById(R.id.sc_practice_xml);
    btnSave.setVisibility(View.INVISIBLE);
    grGender.setOnCheckedChangeListener((group,checkedId) -> {
      switch (checkedId){
        case R.id.rb_male_xml:

          gender = Const.GENDER_MALE;
          break;
        case R.id.rb_feminine_xml:
          gender = Const.GENDER_FEMININE;
          break;
      }
    });
    rgHand.setOnCheckedChangeListener((group, checkId) -> {
      switch (checkId){
        case R.id.rb_lefr_hand_xml:
          isLeftHand = true;
          break;
        case R.id.rb_right_hand_xml:
          isLeftHand = false;
          break;
          default:
            isLeftHand = true;
            break;
      }
    });
    btnSave.setOnClickListener(event -> {
      mParserXmlPresenter.onSaveButtonClick(gender, isLeftHand, scPractice.isChecked());
    });

  }

  public void initRecyclerView(ParserXmlAdapter adapter){
    mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

    mRecycler.setAdapter(adapter);
  }

  public void setVisibilitySaveButton(int visibility){
    btnSave.setVisibility(visibility);
  }

  @Override
  public void showProgress() {
    if (getActivity() instanceof ProgressDisplay) {
      ((ProgressDisplay) getActivity()).showProgress();
    }
  }

  @Override
  public void hideProgress() {
    if (getActivity() instanceof ProgressDisplay) {
      ((ProgressDisplay) getActivity()).hideProgress();
    }
  }
}
