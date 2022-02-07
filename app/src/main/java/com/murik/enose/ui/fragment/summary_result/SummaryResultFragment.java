package com.murik.enose.ui.fragment.summary_result;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.R;
import com.murik.enose.dto.SummaryParcelable;
import com.murik.enose.presentation.presenter.summaryResult.SummaryResultPresenter;
import com.murik.enose.presentation.view.summaryResoltView.SummaryResultView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.val;


public class SummaryResultFragment extends MvpAppCompatFragment implements SummaryResultView {

    public static final String TAG = "SummaryResultFragment";
    public static final String SUMMARY_DATE = "SummaryResultFragment";

    @InjectPresenter
    SummaryResultPresenter summaryResultPresenter;

    private ProgressBar progressBar;
    private TextView progressText;
    private TextView resultTest;
    private CardView resultCard;

    public static SummaryResultFragment newInstance(SummaryParcelable summaryParcelable) {
        SummaryResultFragment fragment = new SummaryResultFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putParcelable(SUMMARY_DATE, summaryParcelable);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_fragment, container, false);
        setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            SummaryParcelable summary = bundle.getParcelable(SUMMARY_DATE);
            summaryResultPresenter.setSummaryParcelable(summary);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.summary_progress_bar);
        progressText = view.findViewById(R.id.text_view_progress);
        resultTest = view.findViewById(R.id.summary_description);
        resultCard = view.findViewById(R.id.summary_description_card);

        summaryResultPresenter.initSummaryResult(getContext());
    }

    @Override
    public void setProgress(Double progress, int color) {
        progressBar.setProgress(BigDecimal.valueOf(progress).setScale(0, RoundingMode.HALF_UP).intValue());
        progressText.setText(progress.intValue() + "%");
        val drawable = ContextCompat.getDrawable(getContext(), R.drawable.circle);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        progressText.setBackground(drawable);
    }

    @Override
    public void setDescription(String description, int color) {
        resultTest.setText(description);
        resultCard.setCardBackgroundColor(color);
    }
}
