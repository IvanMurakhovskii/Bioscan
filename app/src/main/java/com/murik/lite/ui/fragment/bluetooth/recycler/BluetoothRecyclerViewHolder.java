package com.murik.lite.ui.fragment.bluetooth.recycler;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.murik.lite.R;

public class BluetoothRecyclerViewHolder extends RecyclerView.ViewHolder {

  private TextView tvDeviceName;
  private BluetoothDevice device;

  public BluetoothRecyclerViewHolder(@NonNull View itemView) {
    super(itemView);
    tvDeviceName = itemView.findViewById(R.id.tvDeviceName);
  }

  public BluetoothDevice getDevice() {
    return device;
  }

  public void setTvDeviceName(BluetoothDevice device){
    this.device = device;
    if(getDevice().getName() != null){
      tvDeviceName.setText(getDevice().getName());
    } else {
      tvDeviceName.setText(getDevice().getAddress());
    }
  }

}
