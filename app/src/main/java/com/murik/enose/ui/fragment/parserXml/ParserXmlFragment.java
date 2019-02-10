package com.murik.enose.ui.fragment.parserXml;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.RealPathUtils;
import com.murik.enose.presentation.presenter.parserXml.ParserXmlPresenter;
import com.murik.enose.presentation.view.parserXML.ParserXmlView;
import java.io.File;

public class ParserXmlFragment extends MvpAppCompatFragment implements ParserXmlView {

  public static final String TAG = "ParserXmlFragment";
  @InjectPresenter
  ParserXmlPresenter mParserXmlPresenter;



  //private RecyclerView mRecycler;
  private TextView tvLeftHandFileName;
  private TextView tvRightHandFileName;
  private Button btnLeftFile;
  private Button btnRightFile;
  private RadioGroup grGender;
  private RadioGroup rgHand;
  private SwitchCompat scPractice;
  private Button btnSave;
  private EditText etDescriptions;

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



   // mRecycler = view.findViewById(R.id.xmlFileRecycler);
    etDescriptions = view.findViewById(R.id.etDescription_xml);
    tvLeftHandFileName = view.findViewById(R.id.tv_file_name_left_hand);
    tvRightHandFileName = view.findViewById(R.id.tv_file_name_right_hand);
    btnLeftFile = view.findViewById(R.id.btn_add_left_hand_file);
    btnRightFile = view.findViewById(R.id.btn_add_right_hand_file);
    grGender = view.findViewById(R.id.rg_gender_xml);
    /*rgHand = view.findViewById(R.id.rg_hand);*/
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
    /*rgHand.setOnCheckedChangeListener((group, checkId) -> {
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
    });*/

    btnLeftFile.setOnClickListener(event -> {
      Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
      intent.addCategory(Intent.CATEGORY_OPENABLE);
      intent.setType("*/*");
      startActivityForResult(intent, Const.SELECT_LEFT_FILE);

    });
    btnRightFile.setOnClickListener(event -> {
      Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
      intent.addCategory(Intent.CATEGORY_OPENABLE);
      intent.setType("*/*");
      startActivityForResult(intent, Const.SELECT_RIGHT_FILE);

    });

    btnSave.setOnClickListener(event -> {
      mParserXmlPresenter.onSaveButtonClick(etDescriptions.getText().toString(), gender, scPractice.isChecked());
    });

  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    Uri uri = data.getData();
   String path =  RealPathUtils.getPath(getContext(), uri);
   if(requestCode == Const.SELECT_LEFT_FILE){
      mParserXmlPresenter.setFileLeftHand(new File(path));
      tvLeftHandFileName.setText(getRealPathFromURI(getContext(), uri));
   } else if(requestCode == Const.SELECT_RIGHT_FILE) {
      mParserXmlPresenter.setFileRightHand(new File(path));
      tvRightHandFileName.setText(getRealPathFromURI(getContext(), uri));
   }
  }


  private String getRealPathFromURI(Context context, Uri contentUri) {

    Cursor returnCursor =
        context.getContentResolver().query(contentUri, null, null, null, null);
    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
    returnCursor.moveToFirst();

    return returnCursor.getString(nameIndex);
  }

  /*public void initRecyclerView(ParserXmlAdapter adapter){
    mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

    mRecycler.setAdapter(adapter);
  }*/

  public void setVisibilitySaveButton(int visibility){
    btnSave.setVisibility(visibility);
  }

}
