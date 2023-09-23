package com.murik.lite.ui.fragment.parserXml;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.lite.Const;
import com.murik.lite.R;
import com.murik.lite.presentation.presenter.parserXml.ParserXmlPresenter;
import com.murik.lite.presentation.view.parserXML.ParserXmlView;

import java.util.Objects;

public class ParserXmlFragment extends MvpAppCompatFragment implements ParserXmlView {

  public static final String TAG = "ParserXmlFragment";
  @InjectPresenter
  ParserXmlPresenter mParserXmlPresenter;

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
  public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    etDescriptions = view.findViewById(R.id.etDescription_xml);
    tvLeftHandFileName = view.findViewById(R.id.tv_file_name_left_hand);
    tvRightHandFileName = view.findViewById(R.id.tv_file_name_right_hand);
    btnLeftFile = view.findViewById(R.id.btn_add_left_hand_file);
    btnRightFile = view.findViewById(R.id.btn_add_right_hand_file);
    grGender = view.findViewById(R.id.rg_gender_xml);
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

    btnSave.setOnClickListener(event -> mParserXmlPresenter.onSaveButtonClick(etDescriptions.getText().toString(), gender, scPractice.isChecked(), getContext()));

  }

  private String getRealPathFromURI(Context context, Uri contentUri) {

    Cursor returnCursor =
        context.getContentResolver().query(contentUri, null, null, null, null);
    int nameIndex = Objects.requireNonNull(returnCursor).getColumnIndex(OpenableColumns.DISPLAY_NAME);
    returnCursor.moveToFirst();

    return returnCursor.getString(nameIndex);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(data != null){
      Uri uri = data.getData();
      data.getAction();

      if(requestCode == Const.SELECT_LEFT_FILE){
        mParserXmlPresenter.setFileLeftHand(uri);
        tvLeftHandFileName.setText(getRealPathFromURI(Objects.requireNonNull(getContext()),uri));
        tvLeftHandFileName.setTextColor(Color.RED);

      } else if(requestCode == Const.SELECT_RIGHT_FILE) {
        mParserXmlPresenter.setFileRightHand(uri);
        tvRightHandFileName.setText(getRealPathFromURI(Objects.requireNonNull(getContext()),uri));
        tvRightHandFileName.setTextColor(Color.RED);
      }
    }
  }


  public void setVisibilitySaveButton(int visibility){
    btnSave.setVisibility(visibility);
  }

  private String getRealPathFromURI_1(Context context, Uri contentUri) {
    Cursor cursor = null;
    try {
      String[] proj = { };
      cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
      int column_index = Objects.requireNonNull(cursor).getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      cursor.moveToFirst();
      return cursor.getString(column_index);
    } catch (Exception e) {
      Log.e(TAG, "getRealPathFromURI Exception : " + e.toString());
      return "";
    } finally {
      if (cursor != null) {
        cursor.close();
      }
    }
  }

}

