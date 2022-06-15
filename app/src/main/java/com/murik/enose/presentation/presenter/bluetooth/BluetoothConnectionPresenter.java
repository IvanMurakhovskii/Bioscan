package com.murik.enose.presentation.presenter.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.presentation.view.bluetooth.BluetoothConnectionView;
import com.murik.enose.ui.activity.start.StartActivity;
import com.murik.enose.ui.fragment.bluetooth.recycler.BluetoothRecyclerAdapter;
import com.murik.enose.ui.fragment.bluetooth.recycler.BluetoothRecyclerViewHolder;
import java.util.ArrayList;
import java.util.Set;

@InjectViewState
public class BluetoothConnectionPresenter extends MvpPresenter<BluetoothConnectionView> {

  private BluetoothAdapter bluetooth = StartActivity.bluetoothService.getBluetoothAdapter();
  private BluetoothRecyclerAdapter adapter = new BluetoothRecyclerAdapter(this);
  //private Set<BluetoothDevice> bluetoothDevices = bluetooth.getBondedDevices();

  private ArrayList<BluetoothDevice> devices = new ArrayList<>();

  @Override
  public void attachView(BluetoothConnectionView view) {
    super.attachView(view);
    //devices.addAll(bluetoothDevices);
  }

  public BluetoothRecyclerAdapter getAdapter() {
    return adapter;
  }

  public void initRecyclerView() {
    getViewState().initRecyclerView(adapter);
  }

  public void onButtonSearchClick() {
    int devicesCount = devices.size();
    devices.clear();
    getAdapter().notifyItemRangeRemoved(0, devicesCount);
    getViewState().showProgress();
    getViewState().bluetoothConnecting();
  }

  public void findingDevice(BluetoothDevice dev) {

    if(devices.isEmpty()){
      devices.add(dev);
      getAdapter().notifyItemChanged(devices.size() - 1);
    } else {
      for(BluetoothDevice device : devices){
       if(device.equals(dev)){
         return;
       } else {
         devices.add(dev);
         getAdapter().notifyItemChanged(devices.size() - 1);
         return;
       }
      }
    }
  }

  public void OnBindViewHolder( BluetoothRecyclerViewHolder viewHolder, int i) {
    viewHolder.setTvDeviceName(devices.get(i));
  }

  public int getItemCount() {
    return devices.size();
  }

  @SuppressLint("CheckResult")
  public void onItemRecyclerClickListener( BluetoothRecyclerViewHolder viewHolder) {
    bluetooth.cancelDiscovery();
    getViewState().hideProgress();
    getViewState().showProgressBar();
    getViewState().connect(viewHolder.getDevice());
  }
}
