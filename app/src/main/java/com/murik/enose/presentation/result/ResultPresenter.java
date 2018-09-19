package com.murik.enose.presentation.result;


import android.content.Context;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.Screens;
import com.murik.enose.model.InputData;
import com.murik.enose.model.RealmController;
import com.murik.enose.model.ResultBySens;
import com.murik.enose.model.resultbyMaxValue.ResultAFactory;
import com.murik.enose.model.resultbyMaxValue.ResultByMask;
import com.murik.enose.ui.fragment.result.recycler.ResultViewHolder;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

@InjectViewState
public class ResultPresenter extends MvpPresenter<ResultView> {
  ArrayList<ResultBySens> resultBySens = new ArrayList<>();
  private ResultAFactory resultAFactory;
  ArrayList<ResultBySens> res = new ArrayList<>();
  Context context;

  public ResultPresenter() {
  }

  public void setContext(Context context) {
    this.context = context;
  }

  @Override
  protected void onFirstViewAttach() {
    getViewState().calculateResult();
  }

  public void calculateResult(InputData data){
    resultAFactory = new ResultAFactory(new ResultByMask(),data, context);
    resultAFactory.calculateResultA();
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

  public void onBindPlacesViewPosition(int position,ResultViewHolder holder){

    DecimalFormat df = new DecimalFormat("#.##");
    df.setRoundingMode(RoundingMode.HALF_UP);

    holder.setDivider(res.get(position).getViewColor());
    holder.setTvComment(res.get(position).getLegend() +" =" + df.format(res.get(position).getA()) +"\n" + res.get(position).getResultComment());

  }

  public int getResultRowsCount() {
    return res.size();
  }

  public void onSaveButtonClick(){
    getViewState().showDialog();
  }
  public void onSave(String discriptions, InputData data){

    RealmController realmController = new RealmController();
    realmController.addInfo(discriptions, data);
    App.INSTANCE.getRouter().newScreenChain(Screens.REALM_FRAGMENT);
  }
}
