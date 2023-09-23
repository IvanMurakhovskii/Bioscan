package com.murik.lite.presentation.presenter.summary;


import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.dto.SummaryFullParcelable;
import com.murik.lite.model.summary.Summary_60;
import com.murik.lite.presentation.view.summary.SummaryView;

import lombok.Setter;
import lombok.val;

@InjectViewState
@Setter
public class SummaryPresenter extends MvpPresenter<SummaryView> {

    private SummaryFullParcelable summaryParcelable;

    public void initSummaryResult(Context context) {

        val timeRegistrationMaxSignal = summaryParcelable.getTimeRegistrationMaxSignal();
        val summaryLeft = summaryParcelable.getSummaryLeft();

        val inputData = new DataByMaxParcelable();
        inputData.setGender(summaryParcelable.getGender());

        Summary_60 summaryResultLeft = new Summary_60(summaryLeft, inputData,  context);

        String resultCommentLeft = summaryResultLeft.getResultComment();
        int viewColorLeft = summaryResultLeft.getViewColor();
        int imageResIdLeft = summaryResultLeft.getImageResId();

        getViewState().setProgressLeft(summaryLeft, viewColorLeft);
        getViewState().setDescriptionLeft(resultCommentLeft, viewColorLeft);
        getViewState().setResultImageLeft(imageResIdLeft);

        val summaryRight = summaryParcelable.getSummaryRight();

        Summary_60 summaryResultRight = new Summary_60(summaryRight, inputData,  context);

        String resultCommentRight = summaryResultRight.getResultComment();
        int viewColorLeftRight = summaryResultRight.getViewColor();
        int imageResIdRight = summaryResultRight.getImageResId();

        getViewState().setProgressRight(summaryRight, viewColorLeftRight);
        getViewState().setDescriptionRight(resultCommentRight, viewColorLeftRight);
        getViewState().setResultImageRight(imageResIdRight);
    }
}
