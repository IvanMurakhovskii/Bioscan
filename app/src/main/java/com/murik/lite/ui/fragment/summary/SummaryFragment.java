package com.murik.lite.ui.fragment.summary;

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
import com.murik.lite.dto.SummaryFullParcelable;
import com.murik.lite.presentation.presenter.summary.SummaryPresenter;
import com.murik.lite.presentation.view.summary.SummaryView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import lombok.val;


public class SummaryFragment extends MvpAppCompatFragment implements SummaryView {

    public static final String TAG = "SummaryFragment";
    public static final String SUMMARY_DATE = "SummaryResultFragment";

    @InjectPresenter
    SummaryPresenter summaryPresenter;

    private ProgressBar progressBarLeft;
    private TextView progressTextLeft;
    private TextView resultTestLeft;
    private CardView resultCardLeft;

    private ProgressBar progressBarRight;
    private TextView progressTextRight;
    private TextView resultTestRight;
    private CardView resultCardRight;
    private ImageView resultImageLeft;
    private ImageView resultImageRight;

    public static SummaryFragment newInstance(SummaryFullParcelable summaryParcelable) {
        SummaryFragment fragment = new SummaryFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putParcelable(SUMMARY_DATE, summaryParcelable);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_full_fragment, container, false);
        setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            SummaryFullParcelable summary = bundle.getParcelable(SUMMARY_DATE);
            summaryPresenter.setSummaryParcelable(summary);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBarLeft = view.findViewById(R.id.summary_progress_bar_left);
        progressTextLeft = view.findViewById(R.id.text_view_progress_left);
        resultTestLeft = view.findViewById(R.id.summary_description_left);
        resultCardLeft = view.findViewById(R.id.summary_description_card_left);

        progressBarRight = view.findViewById(R.id.summary_progress_bar_right);
        progressTextRight = view.findViewById(R.id.text_view_progress_right);
        resultTestRight = view.findViewById(R.id.summary_description_right);
        resultCardRight = view.findViewById(R.id.summary_description_card_right);
        resultImageLeft = view.findViewById(R.id.result_image_left);
        resultImageRight = view.findViewById(R.id.result_image_right);

        summaryPresenter.initSummaryResult(getContext());
    }

    @Override
    public void setProgressLeft(Double progress, int color) {
        progressBarLeft.setProgress(BigDecimal.valueOf(progress).setScale(0, RoundingMode.HALF_UP).intValue());
        progressTextLeft.setText(progress.intValue() + " %");
        val drawable = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.circle);
        Objects.requireNonNull(drawable).setColorFilter(color, PorterDuff.Mode.SRC_IN);
        progressTextLeft.setBackground(drawable);
    }

    @Override
    public void setProgressRight(Double progress, int color) {
        progressBarRight.setProgress(BigDecimal.valueOf(progress).setScale(0, RoundingMode.HALF_UP).intValue());
        progressTextRight.setText(progress.intValue() + " %");
        val drawable = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.circle);
        Objects.requireNonNull(drawable).setColorFilter(color, PorterDuff.Mode.SRC_IN);
        progressTextRight.setBackground(drawable);
    }

    @Override
    public void setDescriptionLeft(String description, int color) {
        resultTestLeft.setText(description);
        resultCardLeft.setCardBackgroundColor(color);
    }

    @Override
    public void setDescriptionRight(String description, int color) {
        resultTestRight.setText(description);
        resultCardRight.setCardBackgroundColor(color);
    }

    @Override
    public void setResultImageLeft(int imageResId) {
        resultImageLeft.setImageResource(imageResId);
    }

    @Override
    public void setResultImageRight(int imageResId) {
        resultImageRight.setImageResource(imageResId);
    }
}
