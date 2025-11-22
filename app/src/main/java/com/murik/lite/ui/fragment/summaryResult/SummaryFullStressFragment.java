package com.murik.lite.ui.fragment.summaryResult;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.lite.R;
import com.murik.lite.dto.StressSummaryFullParcelable;
import com.murik.lite.presentation.presenter.summaryResult.StressFullSummaryResultPresenter;
import com.murik.lite.presentation.presenter.summaryResult.SummaryResultPresenter;
import com.murik.lite.presentation.view.summaryResultView.StressFullSummaryResultView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import lombok.val;


public class SummaryFullStressFragment extends MvpAppCompatFragment implements StressFullSummaryResultView {

    public static final String TAG = "SummaryFullStressFragment";
    public static final String SUMMARY_FULL_STRESS_DATE = "SUMMARY_FULL_STRESS_DATE";

    @InjectPresenter
    StressFullSummaryResultPresenter summaryResultPresenter;

    private ProgressBar progressBarRun;
    private TextView progressTextRun;
    private TextView resultTextRun;
    private ImageView imageViewRun;

    private CardView cardRun;

    private ProgressBar progressBarStop;
    private TextView progressTextStop;
    private TextView resultTextStop;
    private ImageView imageViewStop;
    private CardView cardStop;

    public static SummaryFullStressFragment newInstance(StressSummaryFullParcelable summaryParcelable) {
        SummaryFullStressFragment fragment = new SummaryFullStressFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putParcelable(SUMMARY_FULL_STRESS_DATE, summaryParcelable);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stress_full_fragment, container, false);
        setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            StressSummaryFullParcelable summary = bundle.getParcelable(SUMMARY_FULL_STRESS_DATE);
            summaryResultPresenter.setSummaryParcelable(summary);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBarRun = view.findViewById(R.id.summary_progress_bar_run);
        progressTextRun = view.findViewById(R.id.text_view_progress_run);
        resultTextRun = view.findViewById(R.id.summary_description_run);
        cardRun = view.findViewById(R.id.summary_description_card_run);
        imageViewRun = view.findViewById(R.id.result_image_run);

        progressBarStop = view.findViewById(R.id.summary_progress_bar_stop);
        progressTextStop = view.findViewById(R.id.text_view_progress_stop);
        resultTextStop = view.findViewById(R.id.summary_description_stop);
        cardStop = view.findViewById(R.id.summary_description_card_stop);
        imageViewStop = view.findViewById(R.id.result_image_stop);

        summaryResultPresenter.init(getContext());
    }

    @Override
    public void setProgressRun(Double progress, int color) {
        progressBarRun.setProgress(BigDecimal.valueOf(progress).setScale(0, RoundingMode.HALF_UP).intValue());
        int rounded = (int) Math.round(progress);
        progressTextRun.setText(rounded + " %");
        val drawable = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.circle);
        Objects.requireNonNull(drawable).setColorFilter(color, PorterDuff.Mode.SRC_IN);
        progressTextRun.setBackground(drawable);
    }

    @Override
    public void setDescriptionRun(String description, int color) {
        resultTextRun.setText(description);
        cardRun.setCardBackgroundColor(color);
    }

    @Override
    public void setResultImageRun(int imageResId) {
        imageViewRun.setImageResource(imageResId);
    }

    @Override
    public void setProgressStop(Double progress, int color) {
        progressBarStop.setProgress(BigDecimal.valueOf(progress).setScale(0, RoundingMode.HALF_UP).intValue());
        int rounded = (int) Math.round(progress);
        progressTextStop.setText(rounded + " %");
        val drawable = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.circle);
        Objects.requireNonNull(drawable).setColorFilter(color, PorterDuff.Mode.SRC_IN);
        progressTextStop.setBackground(drawable);
    }

    @Override
    public void setDescriptionStop(String description, int color) {
        resultTextStop.setText(description);
        cardStop.setCardBackgroundColor(color);
    }

    @Override
    public void setResultImageStop(int imageResId) {
        imageViewStop.setImageResource(imageResId);
    }
}
