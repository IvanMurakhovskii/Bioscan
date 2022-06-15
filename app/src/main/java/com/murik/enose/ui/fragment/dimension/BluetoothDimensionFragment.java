package com.murik.enose.ui.fragment.dimension;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.Screens;
import com.murik.enose.db.DBHelper;
import com.murik.enose.presentation.presenter.dimension.BluetoothDimensionPresenter;
import com.murik.enose.presentation.view.dimension.BluetoothDimensionView;
import com.murik.enose.service.Impl.BluetoothImplService;
import com.murik.enose.ui.dialog.ContinueDimensionDialogFragment;
import com.murik.enose.ui.dialog.DialogListener;
import com.murik.enose.ui.dialog.StartDimensionDialogFragment;
import com.murik.enose.ui.dialog.TakeAwayHandDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import lombok.val;

import static com.murik.enose.Const.BIOSCANNER_DEVICE_TYPE;
import static com.murik.enose.Const.DUAL_SENSOR_DEVICE_TYPE;

public class BluetoothDimensionFragment extends MvpAppCompatFragment implements BluetoothDimensionView,
        DialogListener {

    private static final int ID_DIALOG_START = 1;
    private static final int ID_DIALOG_CONTINUE = 2;
    public static final String DIALOG_CONTINUE_TAG = "DIALOG_CONTINUE";
    public static final String DIALOG_START_TAG = "DIALOG_START";
    public static final String TAG = "BluetoothDimension";

    DBHelper dbHelper;

    private Handler progressBarbHandler = new Handler();

    @InjectPresenter
    BluetoothDimensionPresenter mBluetoothDimensionPresenter;

    private StartDimensionDialogFragment startDimensionDialogFragment;
    private ContinueDimensionDialogFragment continueDimensionDialogFragment;

    private String description;
    private boolean isPractice;
    private int gender = Const.GENDER_MALE;
    private boolean isDimensionStart = false;
    private boolean isLeftHand = false;
    public int dimensionTime = 20;
    public int substanceDimensionTime = 10;
    private int deviceType;
    private int countSensors = 1;

    private LineChart lineChart;
    private ProgressBar progressBar;
    private Button btnStart;
    private Button btnContinue;
    private Button btnSave;

    private TextView maxSignal;
    private LinearLayout llMaxSignal;
    private LinearLayout llDimensionTimer;
    private TextView dimensionTimer;
//    private TextView initial;
//    private TextView signal;

    public boolean isFirstDimension = true;
    private int count = 0;

    List<Integer> initialValue = Arrays.asList(0, 0);
    List<Boolean> isInitialValue = Arrays.asList(false, false);

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @SuppressLint("ShowToast")
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE.equals(action)) {
                String str = intent.getStringExtra(BluetoothImplService.EXTRA_DATA);

                for (int i = 0; i < str.length(); i = i + 8) {
                    int sensNumber = Integer.decode(str.substring(i, i + 1));
                    int value = Integer.parseInt(str.substring(i + 1, i + 8), 16);

                    Log.d("BDF", "sens_index =  " + Integer.decode(str.substring(i, i + 1)) + " value =  "
                            + Integer.parseInt(str.substring(i + 1, i + 8), 16));

                    if (sensNumber <= countSensors) {
                        int sensorIndex = sensNumber - 1;
                        if (!isInitialValue.get(sensorIndex)) {
                            initialValue.set(sensorIndex, value);
                            isInitialValue.set(sensorIndex, true);
                        }
                        
                        addEntry(initialValue.get(sensorIndex) - value, sensorIndex);

                        if (isDimensionStart) {
                            progressBar.getHandler().post(()
                                    -> progressBar.setProgress(count * 100 / dimensionTime));

                            mBluetoothDimensionPresenter.addSensData(isLeftHand, sensorIndex, initialValue.get(sensorIndex) - value);
                            mBluetoothDimensionPresenter.addSensFullData(isLeftHand, sensorIndex, value, initialValue.get(sensorIndex) - value);

                            count++;

                            if (count == substanceDimensionTime - 1 && deviceType == BIOSCANNER_DEVICE_TYPE) {
                                makeNotificationSound();
                                Toast.makeText(
                                        getContext(),
                                        "Уберите руку от прибора!",
                                        Toast.LENGTH_LONG
                                ).show();

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
            }
        }
    };

    private void findAndShowMaxSignal() {
        List<Integer> data1 = mBluetoothDimensionPresenter.getSensData(isLeftHand, 0);
        List<Integer> data2 = mBluetoothDimensionPresenter.getSensData(isLeftHand, 0);

        maxSignal.getHandler().post(() -> maxSignal.setText(max(data1) + " " + max(data2)));
        llMaxSignal.getHandler().post(() -> llMaxSignal.setVisibility(View.VISIBLE));
    }

    private Integer max(List<Integer> data) {
        int max = data.get(0);

        for (int el : data) {
            if (el > max) max = el;
        }

        return max;
    }

    public static BluetoothDimensionFragment newInstance(DBHelper dbHelper) {
        BluetoothDimensionFragment fragment = new BluetoothDimensionFragment();
        fragment.dbHelper = dbHelper;

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bluetooth_dimension, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lineChart = view.findViewById(R.id.line_dimension_chart);
        progressBar = view.findViewById(R.id.progress_bar);

        btnContinue = view.findViewById(R.id.btn_continue_dimension);
        btnSave = view.findViewById(R.id.btn_save_dimension);
        btnStart = view.findViewById(R.id.btn_start_dimension);
        maxSignal = view.findViewById(R.id.max_signal);
        llMaxSignal = view.findViewById(R.id.ll_max_signal);
        llDimensionTimer = view.findViewById(R.id.ll_dimensionTimer);
        dimensionTimer = view.findViewById(R.id.dimensionTimer);
//        initial = view.findViewById(R.id.initial);
//        signal = view.findViewById(R.id.signal);
//        initial.setVisibility(View.GONE);
//        signal.setVisibility(View.GONE);

        btnContinue.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);

        btnStart.setOnClickListener(v -> getStartDimensionDialog()
                .show(Objects.requireNonNull(getFragmentManager()), DIALOG_START_TAG));

        btnContinue.setOnClickListener(v -> {
            isFirstDimension = false;
            llMaxSignal.setVisibility(View.GONE);
            btnContinue.setVisibility(View.GONE);
            btnSave.setVisibility(View.GONE);
            waitBeforeDimension();
        });

        btnSave.setOnClickListener(v -> {
            mBluetoothDimensionPresenter.save();
            mBluetoothDimensionPresenter.saveToDB(dbHelper);
            App.INSTANCE.getRouter().replaceScreen(Screens.REALM_FRAGMENT);
        });

        initLineChart();

        //todo remove
//        startDimension();
    }

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

//    @Override
//    public void onStop() {
//        super.onStop();
//        Objects.requireNonNull(getActivity()).unregisterReceiver(mBroadcastReceiver);
//    }

    @Override
    public void onDialogPositiveClick(int id) {
        if (id == ID_DIALOG_START) {
            btnContinue.setVisibility(View.GONE);
            btnSave.setVisibility(View.GONE);
            btnStart.setVisibility(View.GONE);
            waitBeforeDimension();
        } else if (id == ID_DIALOG_CONTINUE) {
            isLeftHand = !isLeftHand;
            isFirstDimension = false;
            count = 0;
            isDimensionStart = true;
            lineChart.getLineData().clearValues();
            initDataForLineChart();
        }
    }

    private void startDimension() {
        isDimensionStart = true;
        isInitialValue = Arrays.asList(false, false);
        description = startDimensionDialogFragment.getDescriptions();
        isPractice = startDimensionDialogFragment.isPractice();
        gender = startDimensionDialogFragment.getGender();
        isLeftHand = startDimensionDialogFragment.isLeftHand();
        deviceType = startDimensionDialogFragment.getDeviceType();

        if(deviceType == DUAL_SENSOR_DEVICE_TYPE){
            countSensors = 2;
        }

        try {
            val dimensionEnum = startDimensionDialogFragment.getDimensionTime();
            dimensionTime = dimensionEnum.getDimensionTime();
            substanceDimensionTime = dimensionEnum.getMaxSignalTime();
        } catch (NumberFormatException e) {
            Toast.makeText(
                    getContext(),
                    "Не удалось считать значения времени измерения" + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();

            App.INSTANCE.getRouter().replaceScreen(Screens.REALM_FRAGMENT);
        }

        mBluetoothDimensionPresenter.setDimensionParametrs(description, gender, isPractice, isLeftHand, substanceDimensionTime);

        lineChart.getLineData().clearValues();
        makeNotificationSound();
    }

    CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            dimensionTimer.setText((millisUntilFinished / 1000) + " c");
        }

        @Override
        public void onFinish() {
            llDimensionTimer.setVisibility(View.GONE);
            startOrResumeMeasure();
        }
    };

    private void startOrResumeMeasure(){
        if (isFirstDimension) {
            startDimension();
        } else {
            continueDimension();
        }
    }

    private void waitBeforeDimension() {
        if(startDimensionDialogFragment.getDeviceType() == BIOSCANNER_DEVICE_TYPE) {
            llDimensionTimer.setVisibility(View.VISIBLE);
            countDownTimer.start();
        } else {
            startOrResumeMeasure();
        }
    }

    private void continueDimension() {
        isLeftHand = !isLeftHand;
        isInitialValue = Arrays.asList(false, false);
        count = 0;
        isDimensionStart = true;
        lineChart.getLineData().clearValues();
        mBluetoothDimensionPresenter.refreshInitialTime();
    }

    TimerTask task = new TimerTask() {
        public void run() {
            int value = (int)(Math.round(Math.random() * 99));
            Log.i(TAG, "value " + value);

            addEntry(value, 0);
            addEntry(value, 1);

//            initial.getHandler().post(() -> initial.setText(String.valueOf(value)));
//            signal.getHandler().post(() -> signal.setText(String.valueOf(value)));

            if (isDimensionStart) {

                progressBar.getHandler().post(() -> progressBar.setProgress(count * 100 / dimensionTime));

                mBluetoothDimensionPresenter.addSensData(isLeftHand, 0, value);
                mBluetoothDimensionPresenter.addSensData(isLeftHand, 1, value);
                count++;

                if (count == substanceDimensionTime - 1) {
                    makeNotificationSound();
                    takeAwayHandDialog();
                }

                if (isDimensionTimeOver(count)) stopDimension();
            }
        }
    };

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
    }

    private void initDataForLineChart(){
        List<Entry> valsComp1 = new ArrayList<>();
        List<Entry> valsComp2 = new ArrayList<>();
        Entry c1e1 = new Entry(0, 0);
        valsComp1.add(c1e1);

        Entry c2e1 = new Entry(0, 0);
        valsComp2.add(c2e1);

        LineDataSet setComp1 = createSet(valsComp1, "Сенсор 1", Color.GREEN);
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet setComp2 = createSet(valsComp2, "Сенсор 2", Color.MAGENTA);
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);
        lineChart.setData(new LineData(dataSets));
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

        initDataForLineChart();

        // get the legend (only possible after setting data)
        Legend l = lineChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
//      l.setTextColor(Color.WHITE);

        XAxis xl = lineChart.getXAxis();
        xl.setDrawGridLines(true);
//        xl.setAvoidFirstLastClipping(false);
        xl.setEnabled(true);
        xl.setTextSize(12);

        YAxis y1 = lineChart.getAxisLeft();
        y1.setDrawGridLines(false);
//      leftAxis.setAxisMaximum(10f);
//      leftAxis.setAxisMinimum(0f);
        y1.setDrawGridLines(true);
        y1.setTextSize(12);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        lineChart.getAxisLeft().setDrawGridLines(true);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.setDrawBorders(false);
    }

    private void addEntry(Integer value, int index) {

        LineData data = lineChart.getData();

        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(index);

            if(set == null){
                //todo need to find place which clears data after starting of measure
                initDataForLineChart();
                data = lineChart.getLineData();
                set = data.getDataSetByIndex(index);
            }

            data.addEntry(new Entry(set.getEntryCount(), value), index);

            data.notifyDataChanged();

            lineChart.notifyDataSetChanged();

            lineChart.setVisibleXRangeMaximum(150);
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            lineChart.moveViewToX(data.getEntryCount());

        }
    }

    private LineDataSet createSet(final List<Entry> entries, final String label, final int color) {

        LineDataSet set = new LineDataSet(entries, label);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set.setLineWidth(3f);
        set.setColor(color);
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


