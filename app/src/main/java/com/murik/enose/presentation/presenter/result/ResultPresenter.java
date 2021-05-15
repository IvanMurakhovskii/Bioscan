package com.murik.enose.presentation.presenter.result;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.Screens;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.RealmController;
import com.murik.enose.model.ResultAFactory;
import com.murik.enose.model.ResultBySens;
import com.murik.enose.model.resultbyMaxValue.ResultAFactoryOneSensor;
import com.murik.enose.model.resultbyMaxValue.ResultAFactoryStandard;
import com.murik.enose.presentation.view.result.ResultView;
import com.murik.enose.ui.fragment.result.recycler.HeaderViewHolder;
import com.murik.enose.ui.fragment.result.recycler.ResultViewHolder;
import com.murik.enose.ui.fragment.result.recycler.TotalIndicatorsViewHolder;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import lombok.val;
import lombok.var;

@InjectViewState
public class ResultPresenter extends MvpPresenter<ResultView> {

    private ResultAFactory resultAFactory;
    private ArrayList<ResultBySens> res = new ArrayList<>();
    private List<String> totalIndicators = new ArrayList<>();
    private Context context;
    private DataByMaxParcelable data;

    public ResultPresenter() {
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onFirstViewAttach() {
        try {
            getViewState().calculateResult();
        } catch (Exception e) {
            Log.e("ResultPresenter", e.getMessage());
        }
    }

    public void calculateResult(DataByMaxParcelable data, int hand) {

        this.data = data;

        if (data.getMeasureType().equals(Const.STANDARD_MEASURE)) {
            resultAFactory = new ResultAFactoryStandard(data, hand, context);
        }

        if (data.getMeasureType().equals(Const.ONE_SENSOR_MEASURE)) {
            resultAFactory = new ResultAFactoryOneSensor(data, hand, context);
        }

        if (resultAFactory.calculateResultA()) {
            ArrayList<ResultBySens> resultBySens = resultAFactory.getA();
            if (!res.isEmpty()) {
                res.clear();
            }
            for (int i = 0; i < resultBySens.size(); i++) {
                if (resultBySens.get(i).getResultComment() != null)
                    res.add(resultBySens.get(i));
            }

            if(resultAFactory instanceof ResultAFactoryOneSensor) {
                totalIndicators = ((ResultAFactoryOneSensor)resultAFactory).getTotalIndicators();
            }

            getViewState().initPieChart(resultAFactory.getA());
            getViewState().initRecyclerView();
        }
    }

    public void onBindHeader(int position, HeaderViewHolder holder) {
        if (position == 0) {
            holder.setTvDescriptions(data.getDescriptions());
        } else {
            holder.setTvDescriptions("Интегральные показатели");
        }
    }

    public void onBindTotalIndicators(int pos, TotalIndicatorsViewHolder holder) {
        holder.setTvDescriptions(totalIndicators.get(pos - (res.size() + 1) - 1));
    }

    public void onBindPlacesViewPosition(int pos, ResultViewHolder holder) {
        int position = pos - 1;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        holder.setDivider(res.get(position).getViewColor());
        holder.setTvComment(res.get(position).getLegend() + " =" + df.format(res.get(position).getA()) + "\n" + res.get(position).getResultComment());
    }

    public int getTotalIndicatorsPositionStart() {
        return res.size() + 1;
    }

    public int getResultRowsCount() {
        var size = res.size() + 1;
        if (!totalIndicators.isEmpty()) {
          size += totalIndicators.size() + 1;
        }
        return size;
    }

    public void onSaveButtonClick() {
        getViewState().showDialog();
    }

    public void onSave(DataByMaxParcelable data) {

        RealmController realmController = new RealmController();
        realmController.addInfoMax(data);
        App.INSTANCE.getRouter().newScreenChain(Screens.REALM_FRAGMENT);
    }

    public void showDiagram() {
        App.INSTANCE.getRouter().navigateTo(Screens.RESULT_BAR_CHART_FRAGMENT, data);
    }
}
