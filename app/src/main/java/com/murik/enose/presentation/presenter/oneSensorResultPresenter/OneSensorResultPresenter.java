package com.murik.enose.presentation.presenter.oneSensorResultPresenter;


import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.Screens;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.RealmController;
import com.murik.enose.model.ResultAFactory;
import com.murik.enose.model.ResultBySens;
import com.murik.enose.model.resultbyMaxValue.ResultAFactoryLongMask;
import com.murik.enose.model.resultbyMaxValue.ResultAFactoryShortMask;
import com.murik.enose.model.resultbyMaxValue.ResultAFactoryStandard;
import com.murik.enose.presentation.view.result.ResultView;
import com.murik.enose.ui.fragment.result.recycler.HeaderViewHolder;
import com.murik.enose.ui.fragment.result.recycler.ResultViewHolder;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class OneSensorResultPresenter extends MvpPresenter<ResultView> {
  private ArrayList<ResultBySens> resultBySens = new ArrayList<>();
  private ResultAFactory resultAFactory;
  private ArrayList<ResultBySens> res = new ArrayList<>();
  private Context context;
  private DataByMaxParcelable data;
  private int hand = Const.LEFT_HAND;

  public OneSensorResultPresenter() {
  }

  public void setContext(Context context) {
    this.context = context;
  }

  @Override
  protected void onFirstViewAttach() {
    try{
      getViewState().calculateResult();
    } catch (Exception e){
        return;
    }
  }

  public void calculateResult(DataByMaxParcelable data, int hand){

    this.data = data;
    if(data.getMeasureType().equals(Const.STANDARD_MEASURE)){
      resultAFactory = new ResultAFactoryStandard(data, hand, context);
    }
    if(data.getMeasureType().equals(Const.FIRST_MEASURE)){
    resultAFactory = new ResultAFactoryShortMask(data, hand, context);
  }
    if(data.getMeasureType().equals(Const.SECOND_MEASURE)){
        resultAFactory = new ResultAFactoryLongMask(data, hand, context);
    }

    if(resultAFactory.calculateResultA()){
      resultBySens = resultAFactory.getA();
      if(!res.isEmpty()){
        res.clear();
      }
      for(int i = 0; i < resultBySens.size(); i++){
        if(resultBySens.get(i).getResultComment() != null)
          res.add(resultBySens.get(i));
      }

      getViewState().initPieChart(resultAFactory.getA());
      getViewState().initRecyclerView();
    }
  }

  private ArrayList<Integer> createDataByMask(final List<Integer> data, int[] mask){
    ArrayList<Integer> dataByMask = new ArrayList<>();
    if (data != null && data.size() > mask[mask.length - 1]) {
      for (int i = 0; i < mask.length; i++) {
        dataByMask.add(data.get(mask[i]));
      }
    }
    return dataByMask;
  }

  public void onBindHeader(HeaderViewHolder holder){
      holder.setTvDescriptions(data.getDescriptions());
  }
  public void onBindPlacesViewPosition(int pos,ResultViewHolder holder){
    int position = pos - 1;
    DecimalFormat df = new DecimalFormat("#.##");
    df.setRoundingMode(RoundingMode.HALF_UP);

    holder.setDivider(res.get(position).getViewColor());
    holder.setTvComment(res.get(position).getLegend() +" =" + df.format(res.get(position).getA()) +"\n" + res.get(position).getResultComment());

  }

  public int getResultRowsCount() {
    return res.size() + 1;
  }

  public void onSaveButtonClick(){
    getViewState().showDialog();
  }
  public void onSave(DataByMaxParcelable data){

    RealmController realmController = new RealmController();
    realmController.addInfoMax(data);
    App.INSTANCE.getRouter().newScreenChain(Screens.REALM_FRAGMENT);
  }
}
