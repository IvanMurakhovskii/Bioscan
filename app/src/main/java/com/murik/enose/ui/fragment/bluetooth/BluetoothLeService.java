package com.murik.enose.ui.fragment.bluetooth;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class BluetoothLeService extends Service {

  private final static String TAG = BluetoothLeService.class.getSimpleName();

  private BluetoothManager mBluetoothManager;
  private BluetoothAdapter mBluetoothAdapter;
  private String mBluetoothDeviceAddress;
  private BluetoothGatt mBluetoothGatt;
  private int mConnectionState = STATE_DISCONNECTED;

  private static final int STATE_DISCONNECTED = 0;
  private static final int STATE_CONNECTING = 1;
  private static final int STATE_CONNECTED = 2;

  public final static String ACTION_GATT_CONNECTED =
      "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
  public final static String ACTION_GATT_DISCONNECTED =
      "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
  public final static String ACTION_GATT_SERVICES_DISCOVERED =
      "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
  public final static String ACTION_DATA_AVAILABLE =
      "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
  public final static String EXTRA_DATA =
      "com.example.bluetooth.le.EXTRA_DATA";

  // Устанавливаем UUID, который используется для услуг измерения пульса
 /* public final static UUID UUID_HEART_RATE_MEASUREMENT =
      UUID.fromString(SampleGattAttributes.HEART_RATE_MEASUREMENT);
*/
  // Различные методы обратного вызова, определённые в BLE API
  private final BluetoothGattCallback mGattCallback =
      new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status,
            int newState) {
          String intentAction;
          if (newState == BluetoothProfile.STATE_CONNECTED) {
            intentAction = ACTION_GATT_CONNECTED;
            mConnectionState = STATE_CONNECTED;
            //broadcastUpdate(intentAction);
            Log.i(TAG, "Connected to GATT server.");
            Log.i(TAG, "Attempting to start service discovery:" +
                mBluetoothGatt.discoverServices());

          } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
            intentAction = ACTION_GATT_DISCONNECTED;
            mConnectionState = STATE_DISCONNECTED;
            Log.i(TAG, "Disconnected from GATT server.");
            //broadcastUpdate(intentAction);
          }
        }

        @Override
        // При обнаружении нового сервиса
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
          if (status == BluetoothGatt.GATT_SUCCESS) {
            //broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
          } else {
            Log.w(TAG, "onServicesDiscovered received: " + status);
          }
        }

        @Override
        // Результат чтения характеристики
        public void onCharacteristicRead(BluetoothGatt gatt,
            BluetoothGattCharacteristic characteristic,
            int status) {
          if (status == BluetoothGatt.GATT_SUCCESS) {
           // broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
          }
        }
  };

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}

