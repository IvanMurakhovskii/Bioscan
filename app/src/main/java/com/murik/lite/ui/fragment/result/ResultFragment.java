package com.murik.lite.ui.fragment.result;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.murik.lite.R;
import com.murik.lite.configuration.AuthService;
import com.murik.lite.model.ResultBySens;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.presentation.presenter.result.ResultPresenter;
import com.murik.lite.presentation.view.result.ResultView;
import com.murik.lite.ui.fragment.result.recycler.ResultAdapter;
import java.util.ArrayList;
import java.util.Objects;

public class ResultFragment extends MvpAppCompatFragment implements ResultView {
  public static final String TAG = "ResultFragment";
  public static final String CALCULATE_A_KEY = "RESULT_A";
  public static final String ARG_PAGE = "ARG_PAGE";

  private int mPage;

  @InjectPresenter
  ResultPresenter mResultPresenter;

  DataByMaxParcelable inputDataParcelable;

  private RecyclerView mResultRecycler;
  private PieChart pieChart;
  private FloatingActionButton fab_summary;

  public static Fragment newInstance(DataByMaxParcelable resultBySens, int mPage) {
    ResultFragment fragment = new ResultFragment();

    Bundle args = new Bundle();
    args.putParcelable(CALCULATE_A_KEY, resultBySens);
    args.putInt(ARG_PAGE, mPage);
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }
  @NonNull
  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                           final Bundle savedInstanceState) {
    Bundle bundle = getArguments();
    if(bundle != null){
      inputDataParcelable =  bundle.getParcelable(CALCULATE_A_KEY);
      mPage = bundle.getInt(ARG_PAGE);
    }
    setHasOptionsMenu(true);
    return inflater.inflate(R.layout.fragment_result, container, false);

  }

  @Override
  public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mResultRecycler = view.findViewById(R.id.result_recycler_view);
    pieChart = view.findViewById(R.id.result_pie_chart);
    fab_summary = view.findViewById(R.id.fab_summary);
    mResultPresenter.setContext(getContext());

    fab_summary.setOnClickListener((event) -> mResultPresenter.onSummaryClick());

  }


  @Override
  public void onStop() {
    super.onStop();
  }

  public void initPieChart(ArrayList<ResultBySens> sensResult) {
    ArrayList<PieEntry> entries = new ArrayList<>();
    ArrayList<Integer> colors = new ArrayList<>();

    for (int i = 0; i < sensResult.size(); i++) {
      String legend = AuthService.getInstance().isAdmin() ? sensResult.get(i).getLegend() : String.valueOf(i + 1);
      entries.add(new PieEntry(10, legend));
      colors.add(sensResult.get(i).getViewColor());
    }

    PieDataSet dataSet = new PieDataSet(entries, " ");

    dataSet.setAutomaticallyDisableSliceSpacing(true);
    PieData pieData = new PieData(dataSet);
    pieData.setValueTextSize(0f);

    Description des = pieChart.getDescription();
    des.setEnabled(false);

    dataSet.setColors(colors);

    dataSet.setSliceSpace(1);

    pieChart.setDrawHoleEnabled(false);
    pieChart.setDrawMarkers(true);
    pieChart.getLegend().setEnabled(false);
    pieChart.setDrawEntryLabels(true);
    pieChart.setEntryLabelColor(Color.BLACK);
    pieChart.setEntryLabelTextSize(16f);
    pieChart.notifyDataSetChanged();
    pieChart.setTouchEnabled(false);
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

    android.support.v7.app.AlertDialog.Builder builder = new Builder(Objects.requireNonNull(getContext()));
    builder.setMessage("Сохранить?");
    builder.setPositiveButton("Save", (dialog, id) -> mResultPresenter.onSave(inputDataParcelable)
    )
            .setNegativeButton("Cancel", (dialog, id) ->
                    dialog.cancel())
            .create();
    builder.show();
  }

  @Override
  public void calculateResult() {
    mResultPresenter.calculateResult(inputDataParcelable, mPage);
  }

  @Override
  public void showSummaryButton() {
    fab_summary.show();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_result_fragment, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()){
      case R.id.app_bar_save: {
        mResultPresenter.onSaveButtonClick();
        return true;
      }
      default:
        return super.onOptionsItemSelected(item);
    }
  }

}
