package com.murik.lite.presentation.presenter.result_simple;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.App;
import com.murik.lite.Const;
import com.murik.lite.Screens;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.dto.SummaryFullParcelable;
import com.murik.lite.model.RealmController;
import com.murik.lite.model.ResultAFactory;
import com.murik.lite.model.ResultBySens;
import com.murik.lite.model.resultbyMaxValue.ResultAFactoryOneSensor;
import com.murik.lite.model.resultbyMaxValue.ResultAFactoryStandard;
import com.murik.lite.model.total.TotalResult;
import com.murik.lite.presentation.view.result_simple.ResultSimpleView;
import com.murik.lite.ui.fragment.result.recycler.HeaderViewHolder;
import com.murik.lite.ui.fragment.result.recycler.ResultViewHolder;
import com.murik.lite.ui.fragment.result.recycler.TotalIndicatorsViewHolder;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import lombok.val;
import lombok.var;

@InjectViewState
public class ResultSimplePresenter extends MvpPresenter<ResultSimpleView> {


    private ResultAFactory resultAFactoryLeft;
    private ResultAFactory resultAFactoryRight;
    private ArrayList<ResultBySens> res = new ArrayList<>();
    private List<TotalResult> totalIndicators = new ArrayList<>();
    private Context context;
    private DataByMaxParcelable data;

    public ResultSimplePresenter() {
    }

    public void onSummaryClick() {
        val summaryLeft = resultAFactoryLeft.getSummaryResult();
        val summaryRight = resultAFactoryRight.getSummaryResult();

        val summary = new SummaryFullParcelable(summaryLeft, summaryRight, data.getAlgorithmId(), data.getGender());

        App.INSTANCE.getRouter().navigateTo(Screens.SUMMARY_RESULT, summary);
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
            resultAFactoryLeft = new ResultAFactoryStandard(data, Const.LEFT_HAND, context);
            resultAFactoryRight = new ResultAFactoryStandard(data, Const.RIGHT_HAND, context);
        }

        if (data.getMeasureType().equals(Const.ONE_SENSOR_MEASURE)) {
            resultAFactoryLeft = new ResultAFactoryOneSensor(data, Const.LEFT_HAND, context);
            resultAFactoryRight = new ResultAFactoryOneSensor(data, Const.RIGHT_HAND, context);
        }

        if (resultAFactoryLeft.calculateResultA()) {
            ArrayList<ResultBySens> resultBySens = resultAFactoryLeft.getA();
            if (!res.isEmpty()) {
                res.clear();
            }
            for (int i = 0; i < resultBySens.size(); i++) {
                if (resultBySens.get(i).getResultComment() != null)
                    res.add(resultBySens.get(i));
            }

            if (resultAFactoryLeft instanceof ResultAFactoryOneSensor) {
                totalIndicators = ((ResultAFactoryOneSensor) resultAFactoryLeft).getTotalIndicators();
            }

            getViewState().initPieChartLeft(resultAFactoryLeft.getA());
            getViewState().initPieChartRight(resultAFactoryRight.getA());
        }

        if (resultAFactoryRight.calculateResultA()) {
            ArrayList<ResultBySens> resultBySens = resultAFactoryRight.getA();
            if (!res.isEmpty()) {
                res.clear();
            }
            for (int i = 0; i < resultBySens.size(); i++) {
                if (resultBySens.get(i).getResultComment() != null)
                    res.add(resultBySens.get(i));
            }

            if (resultAFactoryRight instanceof ResultAFactoryOneSensor) {
                totalIndicators = ((ResultAFactoryOneSensor) resultAFactoryRight).getTotalIndicators();
            }

            getViewState().initPieChartRight(resultAFactoryRight.getA());
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
        holder.setTvDescriptions(totalIndicators.get(pos - (res.size() + 1) - 1).getDescription());
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
