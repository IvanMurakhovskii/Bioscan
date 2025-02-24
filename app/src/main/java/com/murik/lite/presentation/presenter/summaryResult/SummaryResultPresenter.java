package com.murik.lite.presentation.presenter.summaryResult;


import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.dto.SummaryParcelable;
import com.murik.lite.model.summary.Summary_60;
import com.murik.lite.model.summary.stress.StressSummary_60;
import com.murik.lite.presentation.view.summaryResultView.SummaryResultView;
import lombok.Setter;
import lombok.val;

@InjectViewState
@Setter
public class SummaryResultPresenter extends MvpPresenter<SummaryResultView> {

    private SummaryParcelable summaryParcelable;


    public void initSummaryResult(Context context) {
        val summary = summaryParcelable.getSummary();

        val inputData = new DataByMaxParcelable();
        inputData.setGender(summaryParcelable.getGender());

        Summary_60 summaryResult = new Summary_60(summary, inputData, context);

        String resultComment = summaryResult.getResultComment();
        int viewColor = summaryResult.getViewColor();
        int resultImageResId = summaryResult.getImageResId();

        getViewState().setProgress(summary, viewColor);
        getViewState().setDescription(resultComment, viewColor);
        getViewState().setResultImage(resultImageResId);
    }
    public void initStressSummaryResult(Context context) {
        val summary = summaryParcelable.getSummary();

        val inputData = new DataByMaxParcelable();
        inputData.setGender(summaryParcelable.getGender());

        StressSummary_60 summaryResult = new StressSummary_60(summary, inputData, context);

        String resultComment = summaryResult.getResultComment();
        int viewColor = summaryResult.getViewColor();
        int resultImageResId = summaryResult.getImageResId();

        getViewState().setProgress(summary, viewColor);
        getViewState().setDescription(resultComment, viewColor);
        getViewState().setResultImage(resultImageResId);
    }
}
