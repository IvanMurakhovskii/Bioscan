package com.murik.enose.presentation.presenter.usbdevice;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.presentation.view.usbdevice.UsbFragmentView;
import com.murik.enose.ui.fragment.usbdevice.recycler.UsbRecyclerAdapter;
import com.murik.enose.ui.fragment.usbdevice.recycler.UsbRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class UsbPresenter extends MvpPresenter<UsbFragmentView> {

  private UsbRecyclerAdapter adapter = new UsbRecyclerAdapter(this);
//  private Set<BluetoothDevice> bluetoothDevices = bluetooth.getBondedDevices();

  private List<String> devices = new ArrayList<>();

//  private ArrayList<BluetoothDevice> devices = new ArrayList<>();

  @Override
  public void attachView(UsbFragmentView view) {
    super.attachView(view);
//    devices.addAll(bluetoothDevices);
  }

  public UsbRecyclerAdapter getAdapter() {
    return adapter;
  }

  public void initRecyclerView() {
    getViewState().initRecyclerView(adapter);
  }

  public void onButtonSearchClick() {

  }

  public void searchDevices() {
  }

 /* public void findingDevice(BluetoothDevice dev) {

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
  }*/

  public void OnBindViewHolder(UsbRecyclerViewHolder viewHolder, int i) {
    viewHolder.setTvDeviceName(devices.get(i));
  }

  public int getItemCount() {
    return devices.size();
  }

  @SuppressLint("CheckResult")
  public void onItemRecyclerClickListener( UsbRecyclerViewHolder viewHolder) {
  /*  bluetooth.cancelDiscovery();
    getViewState().hideProgress();
    getViewState().showProgressBar();
    getViewState().connect(viewHolder.getDevice());*/
  }
}
