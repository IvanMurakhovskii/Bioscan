package com.murik.lite.presentation.presenter.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.App;
import com.murik.lite.presentation.view.bluetooth.BluetoothConnectionView;
import com.murik.lite.ui.fragment.bluetooth.recycler.BluetoothRecyclerAdapter;
import com.murik.lite.ui.fragment.bluetooth.recycler.BluetoothRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@InjectViewState
public class BluetoothConnectionPresenter extends MvpPresenter<BluetoothConnectionView> {

    private final String TAG = "BluetoothConnectionPresenter";

    private BluetoothAdapter bluetooth = App.INSTANCE.getmBluetoothAdapter();
    private BluetoothRecyclerAdapter adapter = new BluetoothRecyclerAdapter(this);
    private Set<BluetoothDevice> bluetoothDevices = bluetooth.getBondedDevices();

    private ArrayList<BluetoothDevice> devices = new ArrayList<>();

    @Override
    public void attachView(BluetoothConnectionView view) {
        super.attachView(view);
    }

    public BluetoothRecyclerAdapter getAdapter() {
        return adapter;
    }

    public void initRecyclerView() {
        getViewState().initRecyclerView(adapter);
    }

    public void onButtonSearchClick() {

        devices = new ArrayList<>();

        getViewState().showProgress();

        if (bluetooth == null) {
            return;
        }
        if (!bluetooth.isEnabled()) {
            getViewState().enableBluetooth();
        } else {
            searchBluetoothDevices();
        }


    }

    @SuppressLint("LongLogTag")
    public void searchBluetoothDevices() {
        getViewState().requestAppPermissions();

        getViewState().setInfoText("Поиск...");

        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeScanner scanner = adapter.getBluetoothLeScanner();

        if (scanner != null) {

            ScanSettings scanSettings = new ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_BALANCED)
                    .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
                    .setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE)
                    .setNumOfMatches(ScanSettings.MATCH_NUM_ONE_ADVERTISEMENT)
                    .setReportDelay(0L)
                    .setReportDelay(0L)
                    .build();

            scanner.startScan(scanCallback);

            Log.d(TAG, "scan started");
        }  else {
            Log.e(TAG, "could not get scanner object");

            getViewState().setInfoText("could not get scanner object");
        }
    }

    public void stopBluetoothDeviceDiscovering() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeScanner scanner = adapter.getBluetoothLeScanner();

        scanner.stopScan(scanCallback);
    }

    private final ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            Log.i("1", "onScanResult");
            BluetoothDevice discoveredDevice = result.getDevice();

            if (devices.isEmpty()) {
                devices.add(discoveredDevice);
                getAdapter().notifyItemChanged(devices.size() - 1);
                return;
            }

            boolean isDeviceDiscoveredBefore = false;

            for (BluetoothDevice dev : devices) {
                if (discoveredDevice.equals(dev)) {
                    isDeviceDiscoveredBefore = true;
                    break;
                }
            }

            if (!isDeviceDiscoveredBefore) {
                devices.add(discoveredDevice);
                getAdapter().notifyItemChanged(devices.size() - 1);
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            // Ignore for now
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.i("1", "onScanFailed");
            // Ignore for now
        }
    };

    public void deviceDiscovered(BluetoothDevice discoveredDevice) {
        if (devices.isEmpty()) {
            devices.add(discoveredDevice);
            getAdapter().notifyItemChanged(devices.size() - 1);
            return;
        }

        boolean isDeviceDiscoveredBefore = false;

        for (BluetoothDevice dev : devices) {
            if (discoveredDevice.equals(dev)) {
                isDeviceDiscoveredBefore = true;
                break;
            }
        }

        if (!isDeviceDiscoveredBefore) {
            devices.add(discoveredDevice);
            getAdapter().notifyItemChanged(devices.size() - 1);
        }
    }

    public void OnBindViewHolder(BluetoothRecyclerViewHolder viewHolder, int i) {
        viewHolder.setTvDeviceName(devices.get(i));
    }

    public int getItemCount() {
        return devices.size();
    }

    @SuppressLint("CheckResult")
    public void onItemRecyclerClickListener(BluetoothRecyclerViewHolder viewHolder) {
        bluetooth.cancelDiscovery();
        getViewState().hideProgress();
        getViewState().showProgressBar();
        getViewState().connect(viewHolder.getDevice());
    }
}
