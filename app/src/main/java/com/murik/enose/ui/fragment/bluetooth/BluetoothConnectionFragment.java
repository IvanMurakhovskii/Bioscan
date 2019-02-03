package com.murik.enose.ui.fragment.bluetooth;

import android.Manifest;
import android.Manifest.permission;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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
import com.murik.enose.App;
import com.murik.enose.R;
import com.murik.enose.Screens;
import com.murik.enose.presentation.presenter.bluetooth.BluetoothConnectionPresenter;
import com.murik.enose.presentation.view.bluetooth.BluetoothConnectionView;
import com.murik.enose.service.BluetoothService;
import com.murik.enose.ui.activity.start.ProgressDisplay;
import com.murik.enose.ui.fragment.bluetooth.recycler.BluetoothRecyclerAdapter;
import java.util.Objects;

public class BluetoothConnectionFragment extends MvpAppCompatFragment implements
    BluetoothConnectionView {

  public static final String TAG = "BluetoothConnectionFragment";
  @InjectPresenter
  BluetoothConnectionPresenter mBluetoothConnectionPresenter;

  private final String LOG_TAG = "MyLog";
  BluetoothAdapter bluetooth = App.INSTANCE.getmBluetoothAdapter();

  private int mConnectionState = STATE_DISCONNECTED;
  private static final int STATE_DISCONNECTED = 0;
  private static final int STATE_CONNECTING = 1;
  private static final int STATE_CONNECTED = 2;

  private int REQUEST_ENABLE_BT = 0;

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
    filter.addAction(BluetoothService.ACTION_GATT_CONNECTED);
    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
    getActivity().registerReceiver(mReceiver, filter);
  }

  @Override
  public void onPause() {
    super.onPause();
    getActivity().unregisterReceiver(mReceiver);
    hideProgress();
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_bluetooth_connection, container, false);
  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    tvInfo = view.findViewById(R.id.tvInfo);
    btnSearch = view.findViewById(R.id.btnSearchDevice);
    btnSearch.setOnClickListener(event ->{

      mBluetoothConnectionPresenter.onButtonSearchClick();
    });
    rvBluetoothDevices = view.findViewById(R.id.rvBluetoothDevice);
    mBluetoothConnectionPresenter.initRecyclerView();

    progressBar = view.findViewById(R.id.progressBar_bluetooth);

//    progressBar = new ProgressBar(getContext(),null,android.R.attr.progressBarStyleLarge);
//    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
//    params.addRule(RelativeLayout.CENTER_IN_PARENT);
//   // layout.addView(progressBar,params);
//    progressBar.setVisibility(View.VISIBLE);  //To show ProgressBar
//    //progressBar.setVisibility(View.GONE);

  }

  public void initRecyclerView(BluetoothRecyclerAdapter adapter){
    rvBluetoothDevices.setLayoutManager(new LinearLayoutManager(getContext()));
    rvBluetoothDevices.setAdapter(adapter);
  }

  public void bluetoothConnecting() {

    if (bluetooth == null ) {
      return;
    }
    if (!bluetooth.isEnabled()) {
      Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }
    if (bluetooth.isEnabled()){
      searchBluetoothDevices();
    }

  }

  public void searchBluetoothDevices(){
    setTextViewInfo("Поиск...");
    requestAppPermissions();
    showProgress();
    bluetooth.startDiscovery();

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
        BluetoothDevice device =  intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        mBluetoothConnectionPresenter.findingDevice(device);
        Log.d("MyLog", "Name: " + device.getName() + "  Address: " + device.getAddress());
      } else if(action.equals(BluetoothService.ACTION_GATT_CONNECTED)){
          App.INSTANCE.getRouter().replaceScreen(Screens.BLUETOOTH_LIVE_CHART_FRAGMENT);
          showProgress();
      } else if(BluetoothService.ACTION_GATT_DISCONNECTED.equals(action)){
        Toast.makeText(getContext(), "Disconnct from GATT", Toast.LENGTH_SHORT).show();
        hideProgress();
      }
    }
  };


  public void connect(BluetoothDevice device){
    Intent intent = new Intent(getActivity(), BluetoothService.class);
    intent.setAction(BluetoothService.ACTION_SEND_DEVICE);
    intent.putExtra("DEVICE", device);
    Objects.requireNonNull(getActivity()).startService(intent);

  }

  protected void requestAppPermissions() {
    if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      return;
    }

    if (hasCorseLocationPermissions() && hasFineLocationPermissions() && hasBluetoothAdminPermissions()) {
      return;
    }

    ActivityCompat.requestPermissions(getActivity(),
        new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION,
            permission.ACCESS_COARSE_LOCATION,
            permission.BLUETOOTH,
        }, 0);
  }

  private boolean hasFineLocationPermissions() {
    return (ContextCompat.checkSelfPermission(getActivity(), permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
  }
  private boolean hasCorseLocationPermissions() {
    return (ContextCompat.checkSelfPermission(getActivity(), permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
  }

  private boolean hasBluetoothAdminPermissions() {
    return (ContextCompat.checkSelfPermission(getActivity(), permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED);
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

  public void setTextViewInfo(String message){
    tvInfo.setText(message);
  }

}