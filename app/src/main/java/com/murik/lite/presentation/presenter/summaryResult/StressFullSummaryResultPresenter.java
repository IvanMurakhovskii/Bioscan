package com.murik.lite.presentation.presenter.summaryResult;


import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.dto.StressSummaryFullParcelable;
import com.murik.lite.dto.SummaryParcelable;
import com.murik.lite.model.summary.Summary_60;
import com.murik.lite.model.summary.stress.StopStressSummary_60;
import com.murik.lite.model.summary.stress.StressSummary_60;
import com.murik.lite.presentation.view.summaryResultView.StressFullSummaryResultView;
import com.murik.lite.presentation.view.summaryResultView.SummaryResultView;

import lombok.Setter;
import lombok.val;

@InjectViewState
@Setter
public class StressFullSummaryResultPresenter extends MvpPresenter<StressFullSummaryResultView> {

    private StressSummaryFullParcelable summaryParcelable;

    public void init(Context context) {
        initStressSummaryResult(context);
        initSecondStressSummaryResult(context);
    }

    public void initStressSummaryResult(Context context) {
        val summary = summaryParcelable.getSummaryRun();

        val inputData = new DataByMaxParcelable();
        inputData.setGender(summaryParcelable.getGender());

        StressSummary_60 summaryResult = new StressSummary_60(summary, inputData, context);

        String resultComment = summaryResult.getResultComment();
        int viewColor = summaryResult.getViewColor();
        int resultImageResId = summaryResult.getImageResId();

        getViewState().setProgressRun(summary, viewColor);
        getViewState().setDescriptionRun(resultComment, viewColor);
        getViewState().setResultImageRun(resultImageResId);
    }
    public void initSecondStressSummaryResult(Context context) {
        val summary = summaryParcelable.getSummaryStop();

        val inputData = new DataByMaxParcelable();
        inputData.setGender(summaryParcelable.getGender());

        val summaryResult = new StopStressSummary_60(summary, inputData, context);

        String resultComment = summaryResult.getResultComment();
        int viewColor = summaryResult.getViewColor();
        int resultImageResId = summaryResult.getImageResId();

        getViewState().setProgressStop(summary, viewColor);
        getViewState().setDescriptionStop(resultComment, viewColor);
        getViewState().setResultImageStop(resultImageResId);
    }
}
