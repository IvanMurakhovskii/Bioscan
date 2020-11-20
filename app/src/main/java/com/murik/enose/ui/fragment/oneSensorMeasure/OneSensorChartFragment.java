package com.murik.enose.ui.fragment.oneSensorMeasure;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.presentation.presenter.oneSensorMeasure.OneSensorMeasurePresenter;
import com.murik.enose.presentation.view.oneSensorMeasure.OneSensorMeasureView;
import com.murik.enose.service.Impl.BaseMeasureService;
import com.murik.enose.ui.fragment.resultRadarChart.ResultRadarChartFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OneSensorChartFragment extends MvpAppCompatFragment implements OneSensorMeasureView {

    @InjectPresenter
    public OneSensorMeasurePresenter oneSensorMeasurePresenter;

    private RadarChart radarChart;
    private TextView tvLeftArea;
    private TextView tvRightArea;
    private TextView tvRightDelta;
    private TextView tvLeftDelta;
    private TextView tvLeftPs_3425;
    private TextView tvLeftPs_2435;
    private TextView tvRightPs_3425;
    private TextView tvRightPs_2435;
    private TextView tvDifferenceComment;
    private TextView tvAreaDifference;
    private TextView tvAreaDangerOnLungsLeft;
    private TextView tvAreaDangerOnLungsRight;
    private LinearLayout llLeftDelta;
    private LinearLayout llRightDelta;
    private CardView llAreaDifference;
    private LinearLayout llRightPs_3425;
    private LinearLayout llRightPs_2435;
    private LinearLayout llLeftPs_3425;
    private LinearLayout llLeftPs_2435;
    private LinearLayout llDangerOnLungsAreaLeft;
    private LinearLayout llDangerOnLungsAreaRight;
    private Button btnResult;

    private LinearLayout ll_left;

    private int mPage = 2;
    final private float testChartSize = 18;

    private DataByMaxParcelable dataByMaxParcelable;

    public static Fragment newInstance(int mPage, DataByMaxParcelable dataByMaxParcelable) {
        OneSensorChartFragment fragment = new OneSensorChartFragment();

        Bundle args = new Bundle();
        args.putInt(ResultRadarChartFragment.PAGE, mPage);
        args.putParcelable(ResultRadarChartFragment.DATA, dataByMaxParcelable);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPage = bundle.getInt(ResultRadarChartFragment.PAGE);
            dataByMaxParcelable = bundle.getParcelable(ResultRadarChartFragment.DATA);
        }
        return inflater.inflate(R.layout.fragment_one_sensor_measure, container, false);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radarChart = view.findViewById(R.id.oneSens_radar_chart);

        tvLeftArea = view.findViewById(R.id.tv_area_left_one_sens);
        tvRightArea = view.findViewById(R.id.tv_area_right_one_sens);
        btnResult = view.findViewById(R.id.btn_one_sens_result);
        ll_left = view.findViewById(R.id.ll_left_one_sens);

        tvLeftDelta = view.findViewById(R.id.tv_delta_left_one_sens);
        llLeftDelta = view.findViewById(R.id.ll_delta_left);

        tvRightDelta = view.findViewById(R.id.tv_delta_right_one_sens);
        llRightDelta = view.findViewById(R.id.ll_delta_right);

        tvLeftPs_3425 = view.findViewById(R.id.tv_ps_left_one_sens_3425);
        tvLeftPs_2435 = view.findViewById(R.id.tv_ps_left_one_sens_2435);
        tvRightPs_3425 = view.findViewById(R.id.tv_ps_right_one_sens_3425);
        tvRightPs_2435 = view.findViewById(R.id.tv_ps_right_one_sens_2435);
        tvAreaDangerOnLungsLeft = view.findViewById(R.id.tv_one_sensor_danger_on_lungs_left);
        tvAreaDangerOnLungsRight = view.findViewById(R.id.tv_one_sensor_danger_on_lungs_right);
        llLeftPs_3425 = view.findViewById(R.id.ll_ps_left_3425);
        llLeftPs_2435 = view.findViewById(R.id.ll_ps_left_2435);
        llRightPs_3425 = view.findViewById(R.id.ll_ps_right_3425);
        llRightPs_2435 = view.findViewById(R.id.ll_ps_right_2435);
        llDangerOnLungsAreaRight = view.findViewById(R.id.ll_one_sensor_danger_on_lungs_right);
        llDangerOnLungsAreaLeft = view.findViewById(R.id.ll_one_sensor_danger_on_lungs_left);

        tvAreaDifference = view.findViewById(R.id.tv_one_sensor_difference);
        llAreaDifference = view.findViewById(R.id.ll_one_sensor_difference);

        tvDifferenceComment = view.findViewById(R.id.tv_difference_comment);

        btnResult.setOnClickListener(event -> oneSensorMeasurePresenter.btnResultClickListener(dataByMaxParcelable));

        oneSensorMeasurePresenter.createRadarChart(mPage, dataByMaxParcelable, new BaseMeasureService());
    }

    public void initRadarChart(ArrayList<RadarEntry> entryLeftHand, ArrayList<RadarEntry> entryRightHand,
                               String description, int colorLeft, int colorRight) {
        List<IRadarDataSet> DATA_SET = new ArrayList<>();

        if (!entryLeftHand.isEmpty()) {
            RadarDataSet dataSet_leftHand = new RadarDataSet(entryLeftHand, "Левая");
            dataSet_leftHand.setDrawFilled(true);
            dataSet_leftHand.setFillColor(colorLeft);
            dataSet_leftHand.setColor(colorLeft);
            dataSet_leftHand.setHighlightCircleFillColor(Color.YELLOW);
            dataSet_leftHand.setValueTextSize(40);
            DATA_SET.add(dataSet_leftHand);
        }
        if (!entryRightHand.isEmpty()) {
            RadarDataSet dataSet_rightHand = new RadarDataSet(entryRightHand, "Правая");
            dataSet_rightHand.setDrawFilled(true);
            dataSet_rightHand.setFillColor(colorRight);
            dataSet_rightHand.setColor(colorRight);
            dataSet_rightHand.setValueTextSize(40);
            DATA_SET.add(dataSet_rightHand);
        }

        Description des = radarChart.getDescription();
        des.setText("");
        RadarData radarData;
        if (!DATA_SET.isEmpty()) {
            radarData = new RadarData(DATA_SET);
            radarChart.setData(radarData);
            radarData.setDrawValues(false);
        }

        radarChart.setRotationX(0);
        XAxis x = radarChart.getXAxis();
        x.setTextSize(testChartSize);
        YAxis y = radarChart.getYAxis();
        y.setTextSize(testChartSize);

        int count = 0;
        if (mPage == Const.PAGE_SHORT) {
            count = (int) x.getAxisMaximum() / Const.SHORT.length - 1;
        } else if (mPage == Const.PAGE_LONG) {
            count = (int) x.getAxisMaximum() / Const.LONG.length - 1;
        }

        x.setDrawLabels(true);
        x.setEnabled(true);
        y.setGranularity(count);
        y.setAxisMinimum(0f);
        y.setDrawTopYLabelEntry(false);

        radarChart.setTouchEnabled(false);
        radarChart.setSkipWebLineCount(count);
        radarChart.invalidate();
    }

    @Override
    public void setLeftArea(final float area) {
        tvLeftArea.setText(String.valueOf(area));
    }

    @Override
    public void setRightArea(final float area) {
        tvRightArea.setText(String.valueOf(area));
    }

    @Override
    public void setLeftDelta(final float delta) {
        llLeftDelta.setVisibility(View.VISIBLE);
        tvLeftDelta.setText(String.format("%.2f", delta) + "%");
    }

    @Override
    public void setRightDelta(final float delta) {
        llRightDelta.setVisibility(View.VISIBLE);
        tvRightDelta.setText(String.format("%.2f", delta) + "%");
    }

    @Override
    public void setAreaDifference(final float difference) {
        llAreaDifference.setVisibility(View.VISIBLE);
        tvAreaDifference.setText(String.format("%.2f", difference) + "%");

        int color = ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorResultGreen);

        if(difference <= 5.5) {
           color = ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorResultGreen);
        }

        if(difference > 5.5 && difference <= 16.5) {
            color = ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorResultYellow);
        }

        if(difference > 16.5 && difference <= 50) {
            color = ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorResultRed);
            tvDifferenceComment.setVisibility(View.VISIBLE);
            tvDifferenceComment.setText("ОБРАТИТЕ ВНИМАНИЕ!");
        }

        if(difference > 50) {
            color = ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorResultBurgundy);
            tvDifferenceComment.setVisibility(View.VISIBLE);
            tvDifferenceComment.setText("ОПАСНОЕ РАЗЛИЧИЕ!");
        }


        llAreaDifference.setBackgroundColor(color);
    }

    @Override
    public void setLeftPs_3425(float ps) {
        llLeftPs_3425.setVisibility(View.VISIBLE);
        tvLeftPs_3425.setText(String.format("%.2f", ps));
    }

    @Override
    public void setLeftPs_2435(float ps) {
        llLeftPs_2435.setVisibility(View.VISIBLE);
        tvLeftPs_2435.setText(String.format("%.2f", ps));
    }

    @Override
    public void setRightPs_3425(float ps) {
        llRightPs_3425.setVisibility(View.VISIBLE);
        tvRightPs_3425.setText(String.format("%.2f", ps));
    }

    @Override
    public void setRightPs_2435(float ps) {
        llRightPs_2435.setVisibility(View.VISIBLE);
        tvRightPs_2435.setText(String.format("%.2f", ps));
    }

    @Override
    public void setDeltaDangerOnLungsLeft(final float delta) {
        llDangerOnLungsAreaLeft.setVisibility(View.VISIBLE);
        tvAreaDangerOnLungsLeft.setText(String.format("%.2f", delta));
    }

    @Override
    public void setDeltaDangerOnLungsRight(final float delta) {
        llDangerOnLungsAreaRight.setVisibility(View.VISIBLE);
        tvAreaDangerOnLungsRight.setText(String.format("%.2f", delta));
    }
}
