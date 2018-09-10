package com.murik.enose.ui.fragment.result;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.murik.enose.R;
import com.murik.enose.model.InputData;
import com.murik.enose.model.ResultBySens;
import com.murik.enose.presentation.result.ResultPresenter;
import com.murik.enose.presentation.result.ResultView;
import com.murik.enose.ui.fragment.result.recycler.ResultAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class ResultFragment extends MvpAppCompatFragment implements ResultView {

  public static final String TAG = "ResultFragment";
  public static final String CALCULATE_A_KEY = "RESULT_A";


  @InjectPresenter
  ResultPresenter mResultPresenter;

  InputData inputData;


  private RecyclerView mResultRecycler;
  private PieChart pieChart;
  private Button btnSave;
  private Button btnScreenshot;

  public static Fragment newInstance(InputData resultBySens) {
    ResultFragment fragment = new ResultFragment();

    Bundle args = new Bundle();
    args.putParcelable(CALCULATE_A_KEY, resultBySens);
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //mResultPresenter = createResultPresenter();

  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {
    Bundle bundle = getArguments();
    if(bundle != null){
     inputData =  bundle.getParcelable(CALCULATE_A_KEY);
    }
    return inflater.inflate(R.layout.fragment_result, container, false);

  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mResultRecycler = view.findViewById(R.id.result_recycler_view);
    pieChart = view.findViewById(R.id.result_pie_chart);
    btnScreenshot = view.findViewById(R.id.btnScreenshot);
    btnScreenshot.setOnClickListener(event -> {
      onScreenshotButtonClick();
    });
    btnSave = view.findViewById(R.id.btnSave);
    btnSave.setOnClickListener(event -> mResultPresenter.onSaveButtonClick());
    mResultPresenter.setContext(getContext());
  }


  @Override
  public void onStop() {
    super.onStop();
    //mResultPresenter.detachView(this);
    //getArguments().clear();
  }

  public void initPieChart(ArrayList<ResultBySens> sensResult) {
    ArrayList<PieEntry> entries = new ArrayList<>();
    ArrayList<String> lables = new ArrayList<>();
    ArrayList<Integer> colors = new ArrayList<>();

    for (int i = 0; i < sensResult.size(); i++) {
      entries.add(new PieEntry(10, sensResult.get(i).getLegend()));
      lables.add(sensResult.get(i).getLegend());
      colors.add(sensResult.get(i).getViewColor());
    }

    //pieChart.setHoleColorTransparent(true);


    PieDataSet dataSet = new PieDataSet(entries, " ");

    //dataSet.setSliceSpace(4);
    dataSet.setAutomaticallyDisableSliceSpacing(true);
    PieData pieData = new PieData(dataSet);
    pieData.setValueTextSize(0f);

    Description des = pieChart.getDescription();
    des.setEnabled(false);

    dataSet.setColors(colors);

    pieChart.setDrawHoleEnabled(false);
    pieChart.setDrawMarkers(false);
    pieChart.getLegend().setEnabled(false);
    pieChart.setDrawEntryLabels(true);
    pieChart.setEntryLabelColor(Color.BLACK);
    pieChart.setEntryLabelTextSize(7f);
    pieChart.setData(pieData);
  }

  @Override
  public void initRecyclerView(){
    mResultRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    ResultAdapter adapter = new ResultAdapter(mResultPresenter);
    mResultRecycler.setAdapter(adapter);
  }

  @Override
  public void showDialog() {

    LayoutInflater inflater = LayoutInflater.from(getContext());
    final View saveDialog = inflater.inflate(R.layout.dialog_save, null);
    final EditText userinput = (EditText) saveDialog.findViewById(R.id.etDiscription);
    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder.setView(saveDialog)
        .setTitle("Описание");
    builder.setPositiveButton("Save", (dialog, id) -> {

          mResultPresenter.onSave(userinput.getText().toString(), inputData);
        }
    )
        .setNegativeButton("Cancel", (dialog, id) ->
            dialog.cancel())
        .create();
    builder.show();
  }
  @Override
  public void calculateResult() {
    mResultPresenter.calculateResult(inputData);
  }

  public void onScreenshotButtonClick(){
    Date now = new Date();
    android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
    int check = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
    if (check == PackageManager.PERMISSION_GRANTED) {
      try {
        // image naming and path  to include sd card  appending name you choose for file
        File directory = new File(Environment.getExternalStorageDirectory().toString() +File.separator + "Enose");
        directory.mkdirs();
        String mPath = Environment.getExternalStorageDirectory().toString()  + "/Enose/" + now + ".jpg";

        // create bitmap screen capture
        View v1 = getActivity().getWindow().getDecorView().getRootView();
        v1.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
        v1.setDrawingCacheEnabled(false);

        File imageFile = new File(mPath);

        FileOutputStream outputStream = new FileOutputStream(imageFile);
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        if(bitmap != null) {
          Toast.makeText(getContext(), "Screenshot saved..!", Toast.LENGTH_LONG).show();
        }
        outputStream.flush();
        outputStream.close();

        // openScreenshot(imageFile);
      } catch (Throwable e) {
        // Several error may come o ut with file handling or DOM
        Log.d("ScreenShotActivity", "Failed to capture screenshot because:" + e.getMessage());
        e.printStackTrace();
      }
    } else {
      requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1024);
    }

  }
}
