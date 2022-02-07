package com.murik.enose.presentation.presenter.summaryResult;


import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.dto.SummaryParcelable;
import com.murik.enose.model.summary.Summary_60;
import com.murik.enose.presentation.view.summaryResoltView.SummaryResultView;

import lombok.Setter;
import lombok.val;

@InjectViewState
@Setter
public class SummaryResultPresenter extends MvpPresenter<SummaryResultView> {

    private SummaryParcelable summaryParcelable;

    public void initSummaryResult(Context context) {
        val summary = summaryParcelable.getSummary();
        val summaryResult = new Summary_60(summary, null, context);

        String resultComment = summaryResult.getResultComment();
        int viewColor = summaryResult.getViewColor();

        getViewState().setProgress(summary, viewColor);
        getViewState().setDescription(resultComment, viewColor);
    }
}
