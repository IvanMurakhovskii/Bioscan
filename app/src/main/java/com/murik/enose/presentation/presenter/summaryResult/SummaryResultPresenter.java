package com.murik.enose.presentation.presenter.summaryResult;


import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.dto.SummaryParcelable;
import com.murik.enose.model.resultbyMaxValue.BaseResult;
import com.murik.enose.model.summary.Summary_30;
import com.murik.enose.model.summary.Summary_60;
import com.murik.enose.presentation.view.summaryResultView.SummaryResultView;

import lombok.Setter;
import lombok.val;

@InjectViewState
@Setter
public class SummaryResultPresenter extends MvpPresenter<SummaryResultView> {

    private SummaryParcelable summaryParcelable;

    public void initSummaryResult(Context context) {
        val summary = summaryParcelable.getSummary();
        val timeRegistrationMaxSignal = summaryParcelable.getTimeRegistrationMaxSignal();

        System.out.println(timeRegistrationMaxSignal);

        BaseResult summaryResult = null;

        if (timeRegistrationMaxSignal == 30) {
            summaryResult = new Summary_30(summary, null, context);
        }

        if (timeRegistrationMaxSignal == 60) {
            summaryResult = new Summary_60(summary, null, context);
        }

        if(summaryResult == null) return;

        String resultComment = summaryResult.getResultComment();
        int viewColor = summaryResult.getViewColor();

        getViewState().setProgress(summary, viewColor);
        getViewState().setDescription(resultComment, viewColor);
    }
}
