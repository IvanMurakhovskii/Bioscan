package com.murik.lite.presentation.presenter.result;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.App;
import com.murik.lite.Const;
import com.murik.lite.Screens;
import com.murik.lite.configuration.AuthService;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.dto.SummaryParcelable;
import com.murik.lite.model.RealmController;
import com.murik.lite.model.ResultAFactory;
import com.murik.lite.model.ResultBySens;
import com.murik.lite.model.resultbyMaxValue.ResultAFactoryOneSensor;
import com.murik.lite.model.resultbyMaxValue.ResultAFactoryStandard;
import com.murik.lite.model.total.TotalResult;
import com.murik.lite.presentation.view.result.ResultView;
import com.murik.lite.ui.fragment.result.recycler.CustomResultItem;
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
public class ResultPresenter extends MvpPresenter<ResultView> {

    private final ArrayList<CustomResultItem> resultItems = new ArrayList<>();
    private ResultAFactory resultAFactory;
    private List<TotalResult> totalIndicators = new ArrayList<>();
    private Context context;
    private DataByMaxParcelable data;

    public ResultPresenter() {
    }

    public void onSummaryClick() {
        val summary = resultAFactory.getSummaryResult();
        val timeRegistrationMaxSignal = resultAFactory.getInputData().getTimeRegistrationMaxSignal();
        App.INSTANCE.getRouter().navigateTo(Screens.SUMMARY_RESULT, new SummaryParcelable(summary, timeRegistrationMaxSignal, resultAFactory.getInputData().getGender()));
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
            if (!resultItems.isEmpty()) {
                resultItems.clear();
            }
            for (int i = 0; i < resultBySens.size(); i++) {
                val result = resultBySens.get(i);


                if (AuthService.getInstance().isAdmin()) {
                    resultItems.add(
                            new CustomResultItem(
                                    result.getViewColor(),
                                    result.getLegend(),
                                    result.getResultComment() != null ? result.getResultComment() : "",
                                    result.getA()
                            )
                    );
                } else {
                    if (result.getResultComment() != null) {
                        resultItems.add(
                                new CustomResultItem(result.getViewColor(), String.valueOf(i + 1), result.getResultComment(), result.getA())
                        );
                    }
                }
            }

            if (resultAFactory instanceof ResultAFactoryOneSensor) {
                totalIndicators = ((ResultAFactoryOneSensor) resultAFactory).getTotalIndicators();
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
        holder.setTvDescriptions(totalIndicators.get(pos - (resultItems.size() + 1) - 1).getDescription());
    }

    public void onBindPlacesViewPosition(int pos, ResultViewHolder holder) {
        int position = pos - 1;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        holder.setDivider(resultItems.get(position).getColor());

        if (AuthService.getInstance().isAdmin()) {
            holder.setTvComment(resultItems.get(position).getLegend() + ". = "
                    + df.format(resultItems.get(position).getValue()) + "\n"
                    + resultItems.get(position).getDescription());
        } else {
            holder.setTvComment(resultItems.get(position).getLegend() + ". " + resultItems.get(position).getDescription());
        }
    }

    public int getTotalIndicatorsPositionStart() {
        return resultItems.size() + 1;
    }

    public int getResultRowsCount() {
        var size = resultItems.size() + 1;
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
