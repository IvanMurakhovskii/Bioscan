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
import com.murik.lite.dto.SummaryParcelable;
import com.murik.lite.presentation.presenter.summaryResult.SummaryResultPresenter;
import com.murik.lite.presentation.view.summaryResultView.SummaryResultView;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import lombok.val;


public class SummaryStressResultFragment extends MvpAppCompatFragment implements SummaryResultView {

    public static final String TAG = "SummaryResultFragment";
    public static final String SUMMARY_DATE = "SummaryResultFragment";

    @InjectPresenter
    SummaryResultPresenter summaryResultPresenter;

    private ProgressBar progressBar;
    private TextView progressText;
    private TextView resultTest;
    private CardView resultCard;
    private ImageView imageView;
    private TextView titleView;
    private TextView descriptionView;

    public static SummaryStressResultFragment newInstance(SummaryParcelable summaryParcelable) {
        SummaryStressResultFragment fragment = new SummaryStressResultFragment();

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
        imageView = view.findViewById(R.id.result_image);

        summaryResultPresenter.initStressSummaryResult(getContext());
        titleView = view.findViewById(R.id.textView4);
        titleView.setText("УРОВЕНЬ СТРЕССА");
        //descriptionView = view.findViewById(R.id.textView5);
        //descriptionView.setText("ОПИСАНИЕ");
    }

    @Override
    public void setProgress(Double progress, int color) {
        progressBar.setProgress(BigDecimal.valueOf(progress).setScale(0, RoundingMode.HALF_UP).intValue());
        progressText.setText(progress.intValue() + " %");
        val drawable = ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.circle);
        Objects.requireNonNull(drawable).setColorFilter(color, PorterDuff.Mode.SRC_IN);
        progressText.setBackground(drawable);
    }

    @Override
    public void setDescription(String description, int color) {
        resultTest.setText(description);
        resultCard.setCardBackgroundColor(color);
    }

    @Override
    public void setResultImage(int imageResId) {
        System.out.println("set resource " + imageResId);
        imageView.setImageResource(imageResId);
    }
}
