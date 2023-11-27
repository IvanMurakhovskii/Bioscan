package com.murik.lite.ui.fragment.bluetooth;

import android.Manifest;
import android.Manifest.permission;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.lite.App;
import com.murik.lite.R;
import com.murik.lite.Screens;
import com.murik.lite.configuration.AuthService;
import com.murik.lite.presentation.presenter.bluetooth.BluetoothConnectionPresenter;
import com.murik.lite.presentation.view.bluetooth.BluetoothConnectionView;
import com.murik.lite.service.Impl.BluetoothImplService;
import com.murik.lite.ui.activity.ProgressDisplay;
import com.murik.lite.ui.activity.start.StartActivity;
import com.murik.lite.ui.fragment.bluetooth.recycler.BluetoothRecyclerAdapter;

import java.util.Objects;

import lombok.val;

import static java.util.Objects.*;

public class BluetoothConnectionFragment extends MvpAppCompatFragment implements
        BluetoothConnectionView {

    public static final String TAG = "BluetoothConnectionFragment";
    @InjectPresenter
    BluetoothConnectionPresenter mBluetoothConnectionPresenter;

    BluetoothAdapter bluetooth = App.INSTANCE.getmBluetoothAdapter();

    private int mConnectionState = STATE_DISCONNECTED;
    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_CONNECTED = 2;

    private int REQUEST_ENABLE_BT = 100;

    private FloatingActionButton btnSearch;
    private TextView tvInfo;
    private RecyclerView rvBluetoothDevices;
    private ProgressBar progressBar;

    public static BluetoothConnectionFragment newInstance() {
        BluetoothConnectionFragment fragment = new BluetoothConnectionFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothImplService.ACTION_GATT_CONNECTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        requireNonNull(getActivity()).registerReceiver(mReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        requireNonNull(getActivity()).unregisterReceiver(mReceiver);
        hideProgress();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_bluetooth_connection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvInfo = view.findViewById(R.id.tvInfo);
        btnSearch = view.findViewById(R.id.btnSearchDevice);
        btnSearch.setOnClickListener(event -> mBluetoothConnectionPresenter.onButtonSearchClick());
        rvBluetoothDevices = view.findViewById(R.id.rvBluetoothDevice);
        mBluetoothConnectionPresenter.initRecyclerView();

        progressBar = view.findViewById(R.id.progressBar_bluetooth);

    }

    public void initRecyclerView(BluetoothRecyclerAdapter adapter) {
        rvBluetoothDevices.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBluetoothDevices.setAdapter(adapter);
    }

    public void bluetoothConnecting() {
        if (bluetooth == null) {
            return;
        }

        if (!bluetooth.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        if (bluetooth.isEnabled()) {
            searchBluetoothDevices();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void searchBluetoothDevices() {
        setTextViewInfo("Поиск...");
        requestAppPermissions();
        showProgress();
//        bluetooth.startDiscovery();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                hideProgress();
                bluetooth.cancelDiscovery();
                setTextViewInfo("");
                Log.d("MyLog", "Finished");

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d("MyLog", "Name: " + device.getName() + "  Address: " + device.getAddress());
            } else if (Objects.equals(action, BluetoothImplService.ACTION_GATT_CONNECTED)) {
                mBluetoothConnectionPresenter.stopBluetoothDeviceDiscovering();

                StartActivity activity = (StartActivity) getActivity();
                requireNonNull(activity).setBleConnectionStateColor(Color.GREEN);

                val screen = AuthService.getInstance().isAdmin() ? Screens.BLUETOOTH_LIVE_CHART_FRAGMENT : Screens.BLUETOOTH_DIMENSION_FRAGMENT;
                App.INSTANCE.getRouter().replaceScreen(screen);
                showProgress();
            } else if (BluetoothImplService.ACTION_GATT_DISCONNECTED.equals(action)) {
                Toast.makeText(getContext(), "Disconnect from GATT", Toast.LENGTH_SHORT).show();
                hideProgress();
            }
        }
    };


    public void connect(BluetoothDevice device) {
        Intent intent = new Intent(getActivity(), BluetoothImplService.class);
        intent.setAction(BluetoothImplService.ACTION_SEND_DEVICE);
        intent.putExtra("DEVICE", device);
        requireNonNull(getActivity()).startService(intent);

    }

    @Override
    public void setInfoText(String text) {
        setTextViewInfo(text);
    }

    @Override
    public void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasCoarseLocationPermissions() && hasFineLocationPermissions() && hasBluetoothAdminPermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(requireNonNull(getActivity()),
                new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        permission.ACCESS_COARSE_LOCATION,
                        permission.BLUETOOTH,
                }, 0);
    }

    @Override
    public void enableBluetooth() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }

    private boolean hasFineLocationPermissions() {
        return (ContextCompat.checkSelfPermission(requireNonNull(getActivity()), permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasCoarseLocationPermissions() {
        return (ContextCompat.checkSelfPermission(requireNonNull(getActivity()), permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasBluetoothAdminPermissions() {
        return (ContextCompat.checkSelfPermission(requireNonNull(getActivity()), permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED);
    }

    public void showProgress() {
        if (getActivity() instanceof ProgressDisplay) {
            ((ProgressDisplay) getActivity()).showProgress();
        }
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        if (getActivity() instanceof ProgressDisplay) {
            ((ProgressDisplay) getActivity()).hideProgress();
        }
    }

    public void setTextViewInfo(String message) {
        tvInfo.setText(message);
    }

}