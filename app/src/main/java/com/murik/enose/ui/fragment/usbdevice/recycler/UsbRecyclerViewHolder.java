package com.murik.enose.ui.fragment.usbdevice.recycler;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.murik.enose.R;

public class UsbRecyclerViewHolder extends RecyclerView.ViewHolder {

  private TextView tvDeviceName;
  private BluetoothDevice device;

  public UsbRecyclerViewHolder(@NonNull View itemView) {
    super(itemView);
    tvDeviceName = itemView.findViewById(R.id.tvDeviceName);
  }

  public BluetoothDevice getDevice() {
    return device;
  }

  public void setTvDeviceName(String deviceName){
//    this.device = device;
    tvDeviceName.setText(deviceName);
  }

}
