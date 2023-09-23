package com.murik.lite.ui.fragment.additionalResult;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.lite.R;
import com.murik.lite.dto.AdditionalParcelable;
import com.murik.lite.model.resultbyMaxValue.BaseResult;
import com.murik.lite.presentation.presenter.additionalResult.AdditionalResultPresenter;
import com.murik.lite.presentation.view.additionalResult.AdditionalResultView;

public class AdditionalResultFragment extends MvpAppCompatFragment implements
        AdditionalResultView {

    public static final String ADDITIONAL_RESULT_TAG = "ADDITIONAL_RESULT";

    @InjectPresenter
    AdditionalResultPresenter additionalResultPresenter;

    private AdditionalParcelable additionalData;

    private TextView ps;
    private TextView pgct;
    private TextView sp;
    private TextView y;
    private LinearLayout psColor;
    private LinearLayout pgctColor;
    private LinearLayout spColor;
    private LinearLayout yColor;

    public static Fragment newInstance(AdditionalParcelable result) {
        AdditionalResultFragment fragment = new AdditionalResultFragment();

        Bundle args = new Bundle();
        args.putParcelable(ADDITIONAL_RESULT_TAG, result);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.additional_result, container, false);
        Bundle bundle = getArguments();
        if(bundle != null){
            additionalData =  bundle.getParcelable(ADDITIONAL_RESULT_TAG);
        }

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ps = view.findViewById(R.id.tv_ps);
        pgct = view.findViewById(R.id.tv_pgct);
        sp = view.findViewById(R.id.tv_sp);
        y = view.findViewById(R.id.tv_y);
        psColor = view.findViewById(R.id.ps_color);
        pgctColor = view.findViewById(R.id.pgct_color);
        spColor = view.findViewById(R.id.sp_color);
        yColor = view.findViewById(R.id.y_color);

        additionalResultPresenter.calculateAdditional(additionalData, getContext());
    }

    @Override
    public void setPs(BaseResult result) {
        ps.setText(getResultText(result));
        psColor.setBackgroundTintList(ColorStateList.valueOf(result.getViewColor()));
    }

    @Override
    public void setPgct(BaseResult result) {
        pgct.setText(getResultText(result));
        pgctColor.setBackgroundTintList(ColorStateList.valueOf(result.getViewColor()));
    }


    @Override
    public void setSp(BaseResult result) {
        sp.setText(getResultText(result));
        spColor.setBackgroundTintList(ColorStateList.valueOf(result.getViewColor()));
    }

    @Override
    public void setY(BaseResult result) {
        y.setText(getResultText(result));
        yColor.setBackgroundTintList(ColorStateList.valueOf(result.getViewColor()));
    }

    @SuppressLint("SetTextI18n")
    private String getResultText(BaseResult br) {
        return "Значение = " + br.getA() + "\n" + br.getResultComment();
    }
}
