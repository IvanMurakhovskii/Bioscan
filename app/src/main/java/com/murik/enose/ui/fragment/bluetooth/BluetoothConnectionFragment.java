package com.murik.enose.ui.fragment.bluetooth;

import android.Manifest;
import android.Manifest.permission;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
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
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.App;
import com.murik.enose.R;
import com.murik.enose.presentation.bluetooth.BluetoothConnectionPresenter;
import com.murik.enose.presentation.bluetooth.BluetoothConnectionView;
import com.murik.enose.ui.activity.start.ProgressDisplay;
import com.murik.enose.ui.fragment.bluetooth.recycler.BluetoothRecyclerAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BluetoothConnectionFragment extends MvpAppCompatFragment implements BluetoothConnectionView {

  public static final String TAG = "BluetoothConnectionFragment";
  @InjectPresenter
  BluetoothConnectionPresenter mBluetoothConnectionPresenter;
  private final String LOG_TAG = "MyLog";


  public UUID jdyUUID = UUID.fromString("00002a00-0000-1000-8000-00805f9b34fb");
  BluetoothAdapter bluetooth = App.INSTANCE.getmBluetoothAdapter();

  private int mConnectionState = STATE_DISCONNECTED;
  private static final int STATE_DISCONNECTED = 0;
  private static final int STATE_CONNECTING = 1;
  private static final int STATE_CONNECTED = 2;

  private int REQUEST_ENABLE_BT = 0;

  private FloatingActionButton btnSearch;
  private TextView tvInfo;
  private RecyclerView rvBluetoothDevices;
  private BluetoothGatt bluetoothGatt;

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
    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
    getActivity().registerReceiver(mReceiver, filter);
  }

  @Override
  public void onPause() {
    super.onPause();
    getActivity().unregisterReceiver(mReceiver);
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
      }
    }
  };

  public void connect(BluetoothDevice device){

    bluetoothGatt = device.connectGatt(getContext(), false, new BluetoothGattCallback() {
      @Override
      public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
        super.onReadRemoteRssi(gatt, rssi, status);
      }

      @Override
      public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
        super.onConnectionStateChange(gatt, status, newState);
        if (newState == BluetoothProfile.STATE_CONNECTED) {
          mConnectionState = STATE_CONNECTED;
          Log.i(LOG_TAG, "Connected to GATT server.");
          Log.i(LOG_TAG, "Attempting to start service discovery:" +
              gatt.discoverServices());
        } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
          mConnectionState = STATE_DISCONNECTED;
          setTextViewInfo("Disconnected from GATT server.");
          Log.i(LOG_TAG, "Disconnected from GATT server.");
        } else if (newState == BluetoothProfile.STATE_CONNECTING) {
          Log.i(LOG_TAG, "Connecting from GATT server.");
        }
      }

      @Override
      public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor,
          int status) {
        if(status == BluetoothGatt.GATT_READ_NOT_PERMITTED){
          Log.i(LOG_TAG,"GATT_READ_NOT_PERMITTED");
        }
        super.onDescriptorRead(gatt, descriptor, status);

        byte[] des = descriptor.getValue();
        Log.i(LOG_TAG,"descriptor " +  descriptor.getValue().length);
      }

      @Override
      public void onCharacteristicRead(BluetoothGatt gatt,
          BluetoothGattCharacteristic characteristic,
          int status) {
        super.onCharacteristicRead(gatt, characteristic, status);

        Log.d(LOG_TAG,"characteristic String value " +  characteristic.getStringValue(0));
        Log.d(LOG_TAG,"characteristic UUID " +  characteristic.getUuid());

        int charaProp = characteristic.getProperties();

        if((charaProp & BluetoothGattCharacteristic.PROPERTY_READ) > 0){
          Log.d(LOG_TAG,"BluetoothGattCharacteristic.PROPERTY_READ");
        }
        if((charaProp & BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0){
          Log.d(LOG_TAG,"BluetoothGattCharacteristic.PROPERTY_NOTIFY");
        }

        int   flag = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 0);
        Log.d(LOG_TAG,"sensor  = " +  flag);
        int   flag1 = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 1);
        Log.d(LOG_TAG,"sensor1  = " +  flag1);
        int   flag2 = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 2);
        Log.d(LOG_TAG,"sensor2  = " +  flag2);
        int   flag3 = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 3);
        Log.d(LOG_TAG,"sensor  = " +  flag3);

        byte[] res = characteristic.getValue();
        for(int i = 0; i < res.length; i++){
          int n = (res[i] >> 3) - 1;
          Log.d(LOG_TAG,"value[" + i + "] = "+  res[i] + " n = " + n);
        }
        for (int i = 0; i < 4; i++)
        {
          int n = res[i * 4 + 3] >> 4;
          int d0 = (res[i * 4 + 3] & 0xF) * 0x100 * 0x100 * 0x100;
          int d1 = res[i * 4 + 2] * 0x100 * 0x100;
          int d2 = res[i * 4 + 1] * 0x100;
          int d3 = res[i * 4];
          Log.d(LOG_TAG,"n=" + n + "  value = " + (d0 + d1 + d2 + d3));
        }

        int a = res[0] + res[1] + res[2] + res[3];
        Log.d(LOG_TAG,"low = " + a%256);
        Log.d(LOG_TAG,"high = " + a/256);

        List<BluetoothGattDescriptor> des = characteristic.getDescriptors();
        for(BluetoothGattDescriptor d : des) {
          gatt.readDescriptor(d);
        }


        ArrayList<Integer> sens = new ArrayList<>();
        ArrayList<Integer> data = new ArrayList<>();
        for(int i = 0; i < res.length; i++){
          byte lowerBit = (byte) (res[i] & 0x1);
          byte higherBit = (byte) (res[i] >>> 63);
          sens.add(byteToInt(higherBit));
          data.add(byteToInt(lowerBit));

        }
      }

      @Override
      public void onCharacteristicChanged(BluetoothGatt gatt,
          BluetoothGattCharacteristic characteristic) {
        super.onCharacteristicChanged(gatt, characteristic);

        int flag = characteristic.getProperties();

        //gatt.readCharacteristic(characteristic);
        final int heartRate = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        String binary = Integer.toBinaryString(heartRate);
        //Log.d(LOG_TAG, "int  = " + Integer.toBinaryString(heartRate));
        //Log.d(LOG_TAG, "int  = " + heartRate);

        byte[] buffer = characteristic.getValue();
        Log.d(LOG_TAG,"value" + Integer.toHexString(characteristic.
            getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8,0)));


        //Log.d(LOG_TAG,"length = " + buffer.length);
            /*for(int i = 0; i < buffer.length; i++){
              Log.d(LOG_TAG,"value[" + i + "] = "+  buffer[i]);
              byte high = (byte) ( (buffer[i] & 0xf0) >> 4);
              byte low =  (byte)   (buffer[i] & 0x0f);
              if(i == 3){
                Log.d(LOG_TAG,"low = " + high + "   high = " + low);
              }
             // Log.d(LOG_TAG,"low = " + high + "   high = " + low);
            }*/




       /* for(int i = 0; i < 4; i++){
          int n = buffer[i * 4 + 3] >> 4;
          int d0 = (buffer[i * 4 + 3] & 0xF) * 0x100 * 0x100 * 0x100;
          int d1 = buffer[i * 4 + 2] * 0x100 * 0x100;
          int d2 = buffer[i * 4 + 1] * 0x100;
          int d3 = buffer[i * 4];
          Log.d(LOG_TAG,"n=" + n + "  value = " + (d0 + d1 + d2 + d3));
        }*/



      }

      @Override
      public void onServicesDiscovered(BluetoothGatt gatt, int status) {
        super.onServicesDiscovered(gatt, status);
        List<BluetoothGattService> services = gatt.getServices();
        for (BluetoothGattService ser : services) {
          Log.d(LOG_TAG, "UUID  services " + ser.getUuid().toString());
          Log.d(LOG_TAG, "services.toString " + ser.toString());
          Log.d(LOG_TAG, "services Type" + ser.getType());
          List<BluetoothGattCharacteristic> ch = ser.getCharacteristics();
          for (BluetoothGattCharacteristic c : ch) {

            gatt.setCharacteristicNotification(c, true);
            for (BluetoothGattDescriptor descriptor : c.getDescriptors()) {
              descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
              gatt.writeDescriptor(descriptor);
            }
          }
        }
      }
    });
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

  public static int byteToInt(byte b)
  {
    int value = 0;
    for (int i = 0; i < 4; i++) {
      int shift = (4 - 1 - i) * 8;
      value += (b & 0x000000FF) << shift;
    }
    return value;
  }
  public static int byteArrayToInt(byte[] b)
  {
    int value = 0;
    for (int i = 0; i < 4; i++) {
      int shift = (4 - 1 - i) * 8;
      value += (b[i] & 0x000000FF) << shift;
    }
    return value;
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

  public void hideProgress() {
    if (getActivity() instanceof ProgressDisplay) {
      ((ProgressDisplay) getActivity()).hideProgress();
    }
  }

  public void setTextViewInfo(String message){
    tvInfo.setText(message);
  }
}