package com.murik.enose.service.Impl;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.murik.enose.App;
import com.murik.enose.service.BluetoothService;

import java.util.List;
import java.util.Objects;

public class BluetoothImplService /*extends Service implements BluetoothService */{
//    private final static String TAG = BluetoothImplService.class.getSimpleName();
//
//    private BluetoothGatt mBluetoothGatt = App.INSTANCE.getmBluetoothGatt();
//    private BluetoothDevice device;
//
//    private int mConnectionState = STATE_DISCONNECTED;
//
//    private static final int STATE_DISCONNECTED = 0;
//    private static final int STATE_CONNECTING = 1;
//    private static final int STATE_CONNECTED = 2;
//
//
    public final static String ACTION_SEND_DEVICE =
            "com.murik.enose.ACTION_SEND_DEVICE";
    public final static String ACTION_GATT_CONNECTED =
            "com.murik.enose.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =
            "com.murik.enose.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED =
            "com.murik.enose.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =
            "com.murik.enose.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =
            "com.murik.enose.EXTRA_DATA";
    public final static String ACTION_CHARACTERISTIC_CHANGE =
            "com.murik.enose.CHARACTERISTIC_CHANGE";
//
//    private final BluetoothGattCallback mBluetoothGattCallback = new BluetoothGattCallback() {
//        @Override
//        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
//            super.onReadRemoteRssi(gatt, rssi, status);
//        }
//
//        @Override
//        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
//            String intentAction;
//            super.onConnectionStateChange(gatt, status, newState);
//            if (newState == BluetoothProfile.STATE_CONNECTED) {
//                intentAction = ACTION_GATT_CONNECTED;
//                mConnectionState = STATE_CONNECTED;
//                broadcastUpdate(intentAction);
//                Log.i(TAG, "Connected to GATT server.");
//                Log.i(TAG, "Attempting to start service discovery:" +
//                        mBluetoothGatt.discoverServices());
//            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
//                intentAction = ACTION_GATT_DISCONNECTED;
//                mConnectionState = STATE_DISCONNECTED;
//                Log.i(TAG, "Disconnected from GATT server.");
//                broadcastUpdate(intentAction);
//            }
//        }
//
//        @Override
//        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor,
//                                     int status) {
//        }
//
//        @Override
//        public void onCharacteristicRead(BluetoothGatt gatt,
//                                         BluetoothGattCharacteristic characteristic,
//                                         int status) {
//        }
//
//        @Override
//        public void onCharacteristicChanged(BluetoothGatt gatt,
//                                            BluetoothGattCharacteristic characteristic) {
//            super.onCharacteristicChanged(gatt, characteristic);
//            broadcastUpdate(ACTION_CHARACTERISTIC_CHANGE, characteristic);
//        }
//
//        @Override
//        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
//            super.onServicesDiscovered(gatt, status);
//            List<BluetoothGattService> services = gatt.getServices();
//            for (BluetoothGattService ser : services) {
//                Log.d(TAG, "UUID  services " + ser.getUuid().toString());
//                Log.d(TAG, "services.toString " + ser.toString());
//                Log.d(TAG, "services Type" + ser.getType());
//                List<BluetoothGattCharacteristic> ch = ser.getCharacteristics();
//
//                for (BluetoothGattCharacteristic c : ch) {
//
//                    gatt.setCharacteristicNotification(c, true);
//                    for (BluetoothGattDescriptor descriptor : c.getDescriptors()) {
//                        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
//                        gatt.writeDescriptor(descriptor);
//                    }
//                }
//            }
//        }
//
//    };
//
//    public BluetoothImplService() {
//    }
//
//    private void broadcastUpdate(final String action) {
//        final Intent intent = new Intent(action);
//        sendBroadcast(intent);
//    }
//
//    private void broadcastUpdate(final String action,
//                                 final BluetoothGattCharacteristic characteristic) {
//        final Intent intent = new Intent(action);
//
//        byte[] a = characteristic.getValue();
//        byte[] tmp = new byte[a.length];
//        for (int i = 0; i < a.length; i++) {
//            tmp[i] = a[a.length - 1 - i];
//        }
//        String str = byteArrayToHex(tmp);
//        intent.putExtra(EXTRA_DATA, str);
//        sendBroadcast(intent);
//    }
//
//    public static String byteArrayToHex(byte[] a) {
//        StringBuilder sb = new StringBuilder(a.length * 2);
//        for (byte b : a)
//            sb.append(String.format("%02x", b));
//        return sb.toString();
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        String action = intent.getAction();
//        if (Objects.equals(action, ACTION_SEND_DEVICE)) {
//            device = intent.getParcelableExtra("DEVICE");
//            mBluetoothGatt = device.connectGatt(getApplicationContext(), false, mBluetoothGattCallback);
//        }
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//        String action = intent.getAction();
//        if (Objects.equals(action, ACTION_SEND_DEVICE)) {
//            device = intent.getParcelableExtra("DEVICE");
//
//            mBluetoothGatt = device.connectGatt(getApplicationContext(), false, mBluetoothGattCallback);
//            App.INSTANCE.setmBluetoothGatt(mBluetoothGatt);
//            return START_NOT_STICKY;
//        }
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//    }
}
