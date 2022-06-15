package com.murik.enose.service.Impl;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.murik.enose.ui.fragment.bluetooth.BluetoothConnectionFragment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static android.bluetooth.BluetoothAdapter.STATE_CONNECTED;
import static android.bluetooth.BluetoothAdapter.STATE_DISCONNECTED;
import static com.murik.enose.service.Impl.BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE;
import static com.murik.enose.service.Impl.BluetoothImplService.ACTION_GATT_CONNECTED;
import static com.murik.enose.service.Impl.BluetoothImplService.ACTION_GATT_DISCONNECTED;
import static com.murik.enose.service.Impl.BluetoothImplService.ACTION_SEND_DEVICE;
import static com.murik.enose.service.Impl.BluetoothImplService.EXTRA_DATA;

public class BluetoothService extends Service {
    private final static String TAG = BluetoothService.class.getSimpleName();

    private Binder binder = new LocalBinder();
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothGatt bluetoothGatt;
    private BluetoothDevice device;
    private int mConnectionState = STATE_DISCONNECTED;

    private final BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            String intentAction;
            super.onConnectionStateChange(gatt, status, newState);
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                intentAction = ACTION_GATT_CONNECTED;
                mConnectionState = STATE_CONNECTED;
                broadcastUpdate(intentAction);
                Log.i(TAG, "Connected to GATT server.");
                Log.i(TAG, "Attempting to start service discovery:" +
                        bluetoothGatt.discoverServices());
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                intentAction = ACTION_GATT_DISCONNECTED;
                mConnectionState = STATE_DISCONNECTED;
                Log.i(TAG, "Disconnected from GATT server.");
                broadcastUpdate(intentAction);
            }
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor,
                                     int status) {
            Log.i(TAG, "onDescriptorRead");
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt,
                                         BluetoothGattCharacteristic characteristic,
                                         int status) {
            Log.i(TAG, "onCharacteristicRead");
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt,
                                            BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            broadcastUpdate(ACTION_CHARACTERISTIC_CHANGE, characteristic);
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            List<BluetoothGattService> services = gatt.getServices();
            for (BluetoothGattService ser : services) {
                Log.d(TAG, "UUID  services " + ser.getUuid().toString());
                Log.d(TAG, "services.toString " + ser.toString());
                Log.d(TAG, "services Type" + ser.getType());
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

    };

    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    private void broadcastUpdate(final String action,
                                 final BluetoothGattCharacteristic characteristic) {
        final Intent intent = new Intent(action);

        byte[] a = characteristic.getValue();
        byte[] tmp = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            tmp[i] = a[a.length - 1 - i];
        }
        String str = byteArrayToHex(tmp);
        intent.putExtra(EXTRA_DATA, str);
        sendBroadcast(intent);
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        if (Objects.equals(action, ACTION_SEND_DEVICE)) {
            device = intent.getParcelableExtra("DEVICE");
            //bluetoothGatt = device.connectGatt(getApplicationContext(), false, mBluetoothGattCallback);
        }
        return binder;
    }

    public class LocalBinder extends Binder {
        public BluetoothService getService() {
            return BluetoothService.this;
        }
    }

    public boolean initialize() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
            return false;
        }
        return true;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }

    public boolean connect(final String address) {
        if (bluetoothAdapter == null || address == null) {
            Log.w(TAG, "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }

        try {
            Thread.sleep(1000);
            device = bluetoothAdapter.getRemoteDevice(address);
//
//            BluetoothSocket bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(address));
//            try{
//                bluetoothSocket.connect();
//                InputStream tmpIn = bluetoothSocket.getInputStream();
//
//                byte[] buffer = new byte[64];// буферный массив
//                int bytes;// bytes returned from read()
//
//                bytes = tmpIn.read(buffer);
//
//                bluetoothSocket.close();
//
//            } catch(IOException connectException){
//                try{
//                    bluetoothSocket.close();
//                } catch(IOException closeException){}
//            }
            // connect to the GATT server on the device
            bluetoothGatt = device.connectGatt(this, false, bluetoothGattCallback,
                    BluetoothDevice.TRANSPORT_LE);
            Log.e(TAG, "Connected???");
            Thread.sleep(1000);
            Log.e(TAG, "Connected???");
            return true;
        } catch (Exception exception) {
            Log.w(TAG, "Device not found with provided address.  Unable to connect.");
            return false;
        }
    }

    public BluetoothGatt getBluetoothGatt(){
        return bluetoothGatt;
    }

    public BluetoothDevice getDevice() {
        return device;
    }
}
