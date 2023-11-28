package com.murik.lite.ui.fragment.dimension;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.murik.lite.App;
import com.murik.lite.Const;
import com.murik.lite.R;
import com.murik.lite.Screens;
import com.murik.lite.enums.NoseType;
import com.murik.lite.presentation.presenter.dimension.BluetoothDimensionPresenter;
import com.murik.lite.presentation.presenter.dimension.Dimension;
import com.murik.lite.presentation.view.dimension.BluetoothDimensionView;
import com.murik.lite.service.Impl.BluetoothImplService;
import com.murik.lite.ui.dialog.ContinueDimensionDialogFragment;
import com.murik.lite.ui.dialog.DialogListener;
import com.murik.lite.ui.dialog.ErrorDialogFragment;
import com.murik.lite.ui.dialog.InitDimensionDialogFragment;
import com.murik.lite.ui.dialog.StartDialogListener;
import com.murik.lite.ui.dialog.StartDimensionDialogFragment;
import com.murik.lite.ui.dialog.TakeAwayHandDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import lombok.val;
import pl.droidsonroids.gif.GifImageView;

public class BluetoothDimensionFragment extends MvpAppCompatFragment implements BluetoothDimensionView,
        DialogListener, StartDialogListener {


    public static final String DIALOG_CONTINUE_TAG = "DIALOG_CONTINUE";
    public static final String DIALOG_INIT_TAG = "DIALOG_INIT";
    public static final String DIALOG_START_TAG = "DIALOG_START";
    public static final String TAG = "BluetoothDimension";
    private static final int ID_DIALOG_START = 1;
    private static final int ID_DIALOG_CONTINUE = 2;
    public int dimensionTime = 20;
    public int substanceDimensionTime = 10;
    public boolean isFirstDimension = true;
    @InjectPresenter
    BluetoothDimensionPresenter mBluetoothDimensionPresenter;
    Map<Integer, Integer> initialValues = new HashMap<>();
    private Handler progressBarbHandler = new Handler();
    private InitDimensionDialogFragment initDimensionDialogFragment;
    private ContinueDimensionDialogFragment continueDimensionDialogFragment;
    private StartDimensionDialogFragment startDimensionDialogFragment;
    private String description;
    private boolean isPractice;
    private int gender = Const.GENDER_MALE;
    private NoseType noseType = NoseType.BIOSCANER;
    private boolean isDimensionStart = false;
    private boolean isLeftHand = false;
    private LineChart lineChart;
    private ProgressBar progressBar;
    private Button btnStart;
    private Button btnContinue;
    private Button btnSave;
    private TextView maxSignal;
    private LinearLayout llMaxSignal;
    private LinearLayout llDimensionTimer;
    private TextView dimensionTimer;
//  private TextView initial;
//  private TextView signal;
    private GifImageView gifImageView;
    private int count = 0;

    TimerTask task = new TimerTask() {
        public void run() {
            val value = (Math.round(Math.random() * 99));
            Log.i(TAG, "value " + value);

            addEntry((int) value, "Sensor 1", Color.MAGENTA, 0, noseType);


            if (isDimensionStart) {
                progressBar.getHandler().post(() -> progressBar.setProgress(count * 100 / dimensionTime));
                if (isLeftHand) {
                    mBluetoothDimensionPresenter.addSens1DataLeftHand((int) value);
                } else {
                    mBluetoothDimensionPresenter.addSens1DataRightHand((int) value);
                }
                count++;

                if (count == substanceDimensionTime - 1) {
                    makeNotificationSound();
                    takeAwayHandDialog();

                }

                if (count == substanceDimensionTime + 2) {
                    findAndShowMaxSignal();
                }

                if (isDimensionTimeOver(count)) stopDimension();
            }
        }
    };

    private boolean isInitial1 = false;
    CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            dimensionTimer.setText((millisUntilFinished / 1000) + " c");
        }

        @Override
        public void onFinish() {
            llDimensionTimer.setVisibility(View.GONE);
            if (isFirstDimension) {
                startDimension();
            } else {
                continueDimension();
            }
        }
    };

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @SuppressLint("ShowToast")
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE.equals(action)) {
                String str = intent.getStringExtra(BluetoothImplService.EXTRA_DATA);

                for (int i = 0; i < str.length(); i = i + 8) {
                    val sensNumber = Integer.decode(str.substring(i, i + 1));
                    val value = Integer.parseInt(str.substring(i + 1, i + 8), 16);

                    Log.i(TAG, " sensor " + sensNumber + " value = " + value);

                    if (initialValues.get(sensNumber) == null) {
                        initialValues.put(sensNumber, value);
                    }

//                    if (!isInitial1) {
//                        initial1 = value;
//                        isInitial1 = true;
//                    }

                    if (value != 0) {
                        Integer result = initialValues.get(sensNumber) - value;

                        val lineColor = isDimensionStart ? Const.CHART_COLOR.get(sensNumber - 1) : Color.GRAY;
                        addEntry(result, "Sensor " + sensNumber, lineColor, sensNumber - 1, noseType);

//                        Log.i(TAG, "addEntry = " + result + " sensor " + sensNumber);

                        if (isDimensionStart) {
                            progressBar.getHandler().post(()
                                    -> progressBar.setProgress(count * 100 / dimensionTime));

                            count = mBluetoothDimensionPresenter.addSensorData(isLeftHand, sensNumber, result);
                        }
                    }
                }

                if (isDimensionStart) {
                    if (count == substanceDimensionTime - 1) {
                        makeNotificationSound();
                        takeAwayHandDialog();
                    }

                    if (count == substanceDimensionTime + 6) {
                        findAndShowMaxSignal();
                    }

                    if (isDimensionTimeOver(count)) {


                        stopDimension();
                        makeNotificationSound();
                        Toast.makeText(
                                getContext(),
                                "Измерение завершено!",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
            }
        }
    };

    public static BluetoothDimensionFragment newInstance() {
        BluetoothDimensionFragment fragment = new BluetoothDimensionFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @SuppressLint("SetTextI18n")
    private void findAndShowMaxSignal() {
        List<Integer> data;
        if (isLeftHand) {
            data = mBluetoothDimensionPresenter.getSens1DataLeftHand();
        } else {
            data = mBluetoothDimensionPresenter.getSens1DataRightHand();
        }

        if (data.size() > substanceDimensionTime) {
            final Integer max = data.get(substanceDimensionTime);

            maxSignal.getHandler().post(() -> maxSignal.setText(max.toString()));
            llMaxSignal.getHandler().post(() -> llMaxSignal.setVisibility(View.VISIBLE));

            if (max < 50) {
                measureError();
            }
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bluetooth_dimension, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Objects.requireNonNull(getActivity()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        lineChart = view.findViewById(R.id.line_dimension_chart);
        progressBar = view.findViewById(R.id.progress_bar);

        btnContinue = view.findViewById(R.id.btn_continue_dimension);
        btnSave = view.findViewById(R.id.btn_save_dimension);
        btnStart = view.findViewById(R.id.btn_start_dimension);
        maxSignal = view.findViewById(R.id.max_signal);
        llMaxSignal = view.findViewById(R.id.ll_max_signal);
        llDimensionTimer = view.findViewById(R.id.ll_dimensionTimer);
        dimensionTimer = view.findViewById(R.id.dimensionTimer);
        gifImageView = view.findViewById(R.id.dimension_gif);

        btnContinue.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);

        btnStart.setOnClickListener(v -> getInitDimensionDialog()
                .show(Objects.requireNonNull(getFragmentManager()), DIALOG_INIT_TAG));

        btnContinue.setOnClickListener(v -> {
            isFirstDimension = false;
            llMaxSignal.setVisibility(View.GONE);
            btnContinue.setVisibility(View.GONE);
            btnSave.setVisibility(View.GONE);
            gifImageView.setVisibility(View.VISIBLE);

            getStartDimensionDialog()
                    .show(Objects.requireNonNull(getFragmentManager()), DIALOG_START_TAG);
        });

        btnSave.setOnClickListener(v -> {
            mBluetoothDimensionPresenter.save();
            App.INSTANCE.getRouter().replaceScreen(Screens.REALM_FRAGMENT);
        });

        initLineChart();

        //todo remove
//        startDimensionWithMock();
    }

    @Override
    public void setProgressBarColor(int color) {
        progressBar.setProgressTintList(ColorStateList.valueOf(color));
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        Objects.requireNonNull(getActivity()).unregisterReceiver(mBroadcastReceiver);
//    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE);
        Objects.requireNonNull(getActivity()).registerReceiver(mBroadcastReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        Objects.requireNonNull(getActivity()).unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onDialogPositiveClick(int id) {
        if (id == ID_DIALOG_START) {
            btnContinue.setVisibility(View.GONE);
            btnSave.setVisibility(View.GONE);
            btnStart.setVisibility(View.GONE);
            gifImageView.setVisibility(View.VISIBLE);

            getStartDimensionDialog()
                    .show(Objects.requireNonNull(getFragmentManager()), DIALOG_START_TAG);
//            waitBeforeDimension();
        } else if (id == ID_DIALOG_CONTINUE) {
            isLeftHand = !isLeftHand;
            isFirstDimension = false;
            count = 0;
            isDimensionStart = true;
            lineChart.getLineData().clearValues();

            getStartDimensionDialog()
                    .show(Objects.requireNonNull(getFragmentManager()), DIALOG_START_TAG);
        }
    }

    @Override
    public void onStartDimensionClick() {
        waitBeforeDimension();
    }

    private void startDimension() {
        isDimensionStart = true;
        isInitial1 = false;
        initialValues = new HashMap<>();
        description = initDimensionDialogFragment.getDescriptions();
//        isPractice = initDimensionDialogFragment.isPractice();
        gender = initDimensionDialogFragment.getGender();
        noseType = initDimensionDialogFragment.getNoseType();
        isLeftHand = initDimensionDialogFragment.isLeftHand();

        try {
            val algorithm = initDimensionDialogFragment.getAlgorithm();
            val measurePoint = initDimensionDialogFragment.getMeasurePoint();
            dimensionTime = algorithm.getDimensionTime();
            substanceDimensionTime = algorithm.getMaxSignalTime();

            val dimension = Dimension.builder().description(description)
                    .gender(gender).isPractice(isPractice).algorithmId(algorithm.getAlgorithmId())
                    .measurePointId(measurePoint.getId())
                    .build();

            mBluetoothDimensionPresenter.setDimensionParameters(dimension);

            resetChart();
            makeNotificationSound();
        } catch (NumberFormatException e) {
            Toast.makeText(
                    getContext(),
                    "Не удалось считать значения времени измерения" + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();

            App.INSTANCE.getRouter().replaceScreen(Screens.REALM_FRAGMENT);
        }
    }

    private void waitBeforeDimension() {
        llDimensionTimer.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    private void continueDimension() {
        isLeftHand = !isLeftHand;
        isInitial1 = false;
        initialValues = new HashMap<>();
        count = 0;
        isDimensionStart = true;
        resetChart();
        makeNotificationSound();
    }

    private void makeNotificationSound() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startDimensionWithMock() {
        Timer timer = new Timer(true);
        timer.schedule(task, 1, 1000);
    }


    private void takeAwayHandDialog() {
        val dialog = new TakeAwayHandDialogFragment();
        dialog.show(getFragmentManager(), "TAKE_AWAY_HAND");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 5000);
    }

    private void measureError() {
        val dialog = new ErrorDialogFragment();
        dialog.show(getFragmentManager(), "ERROR_DIALOG");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 10000);
    }

    private InitDimensionDialogFragment getInitDimensionDialog() {
        initDimensionDialogFragment = new InitDimensionDialogFragment();
        initDimensionDialogFragment.setDialogListener(this);

        return initDimensionDialogFragment;
    }

    private StartDimensionDialogFragment getStartDimensionDialog() {
        startDimensionDialogFragment = new StartDimensionDialogFragment();
        startDimensionDialogFragment.setDialogListener(this);

        return startDimensionDialogFragment;
    }


    private boolean isDimensionTimeOver(int count) {
        return (count >= dimensionTime);
    }

    private void stopDimension() {
        isDimensionStart = false;

        if (isFirstDimension) {
            btnContinue.getHandler().post(() -> btnContinue.setVisibility(View.VISIBLE));
        } else {
            btnContinue.getHandler().post(() -> btnContinue.setVisibility(View.GONE));
        }

        btnSave.getHandler().post(() -> btnSave.setVisibility(View.VISIBLE));
        gifImageView.getHandler().post(() -> gifImageView.setVisibility(View.GONE));
    }

    private void initLineChart() {

        lineChart.getDescription().setText("");

        // enable touch gestures
        lineChart.setTouchEnabled(true);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);

        lineChart.setPinchZoom(true);

//        LineData data = new LineData();

        // add empty data
//        lineChart.setData(data);

        initDataSets();

        Legend l = lineChart.getLegend();

        XAxis xl = lineChart.getXAxis();
        xl.setDrawGridLines(true);
        xl.setEnabled(true);
        xl.setTextSize(12);

        YAxis y1 = lineChart.getAxisLeft();
        y1.setDrawGridLines(false);
        y1.setDrawGridLines(true);
        y1.setTextSize(12);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        lineChart.getAxisLeft().setDrawGridLines(true);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.setDrawBorders(false);
    }

    private void resetChart() {
        lineChart.getLineData().clearValues();
        initDataSets();
    }

    public static final int MAX_SENS_NUMBER = 8;

    private void initDataSets() {
        List<ILineDataSet> dataSets = new ArrayList<>();

        for (int i = 0; i < MAX_SENS_NUMBER; i++) {
            List<Entry> entries = new ArrayList<>();
            Entry entry = new Entry(0, 0);

            entries.add(entry);
            LineDataSet sets = createSet(entries, String.format("S_%s", i + 1), Const.CHART_COLOR.get(i));
            sets.setAxisDependency(YAxis.AxisDependency.LEFT);

            dataSets.add(sets);
        }

        lineChart.setData(new LineData(dataSets));

        if (noseType == NoseType.BIOSCANER) {
            lineChart.getLegend().setEnabled(false);
        } else {
            lineChart.getLegend().setEnabled(true);
        }
    }

    private void addEntry(Integer value, final String label, final int color, int index, NoseType noseType) {

        LineData data = lineChart.getData();

        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(index);


//            if (set == null) {
//                if (noseType == NoseType.DIAGNOST) {
//                    initDataForDiagnostLineChart();
//                    data = lineChart.getLineData();
//                    set = data.getDataSetByIndex(index);
//                } else {
//                    set = createSet(null, label, color);
//                    data.addDataSet(set);
//                }
//            }

            data.addEntry(new Entry(set.getEntryCount(), value), index);

            data.notifyDataChanged();
            lineChart.notifyDataSetChanged();

            lineChart.setVisibleXRangeMaximum(150);
            lineChart.moveViewToX(data.getEntryCount());
        }
    }

    private LineDataSet createSet(final List<Entry> entries, final String label, final int color) {

        LineDataSet set = new LineDataSet(entries, label);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set.setLineWidth(3f);
        set.setColor(isDimensionStart ? color: Color.GRAY);
        set.setHighlightEnabled(false);
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setCubicIntensity(0.2f);

        return set;
    }

    @Override
    public void onDialogNegativeClick(int id) {
        if (id == ID_DIALOG_CONTINUE) {

        } else if (id == ID_DIALOG_START) {
            App.INSTANCE.getRouter().replaceScreen(Screens.REALM_FRAGMENT);
        }
    }

    @Override
    public void stopChartRender() {
    }

    @Override
    public void showContinueDialog() {
        if (continueDimensionDialogFragment == null) {
            continueDimensionDialogFragment = new ContinueDimensionDialogFragment();
            continueDimensionDialogFragment.setDialogListener(this);
            continueDimensionDialogFragment.show(getFragmentManager(), DIALOG_CONTINUE_TAG);
        }
    }
}


