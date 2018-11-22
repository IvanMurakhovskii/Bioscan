package com.murik.enose.presentation.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.support.annotation.NonNull;
import android.util.Log;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.enose.App;
import com.murik.enose.ui.fragment.bluetooth.recycler.BluetoothRecyclerAdapter;
import com.murik.enose.ui.fragment.bluetooth.recycler.BluetoothRecyclerViewHolder;
import io.reactivex.Observable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@InjectViewState
public class BluetoothConnectionPresenter extends MvpPresenter<BluetoothConnectionView> {


  private BluetoothAdapter bluetooth =  App.INSTANCE.getmBluetoothAdapter();
  private BluetoothRecyclerAdapter adapter = new BluetoothRecyclerAdapter(this);
  private Set<BluetoothDevice> bluetoothDevices = bluetooth.getBondedDevices();

  public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

  private ArrayList<BluetoothDevice> devices = new ArrayList<>();

  @Override
  public void attachView(BluetoothConnectionView view) {
    super.attachView(view);
    devices.addAll(bluetoothDevices);

  }

  public BluetoothRecyclerAdapter getAdapter() {
    return adapter;
  }

  public void initRecyclerView() {
    getViewState().initRecyclerView(adapter);
  }

  public void onButtonSearchClick() {
    getViewState().bluetoothConnecting();
  }

  public void findingDevice(BluetoothDevice dev) {

    Set<BluetoothDevice> setDev = new HashSet<>();

    Iterator iter1 = setDev.iterator();
    while(iter1.hasNext()){}



    if(devices.isEmpty()){
      devices.add(dev);
      getAdapter().notifyItemChanged(devices.size() - 1);
    } else {
      Iterator<BluetoothDevice> iter = devices.iterator();
      while(iter.hasNext()){
        if(dev.getName() != null){
          if(!iter.next().getName().equals(dev.getName())){
            devices.add(dev);
          }
        } else {
          devices.add(dev);
        }
        getAdapter().notifyItemChanged(devices.size() - 1);
      }
    }

  }

  public void OnBindViewHolder(@NonNull BluetoothRecyclerViewHolder viewHolder, int i) {
    viewHolder.setTvDeviceName(devices.get(i));
  }

  public int getItemCount() {
    return devices.size();
  }

  @SuppressLint("CheckResult")
  public void onItemRecyclerClickListener(@NonNull BluetoothRecyclerViewHolder viewHolder) {
    bluetooth.cancelDiscovery();
    getViewState().hideProgress();



    getViewState().connect(viewHolder.getDevice());
    BluetoothSocket tmp = null;


/*
    try {
      tmp = viewHolder.getDevice().createInsecureRfcommSocketToServiceRecord(MY_UUID);
    } catch (IOException e) {
      Log.e("MyLog", "Socket's listen() method failed", e);
    }
    BluetoothSocket finalBluetoothSocket = tmp;
    try{
      finalBluetoothSocket.connect();

    } catch(IOException connectException){
      Log.d("MyLog", "error connection: " + connectException.getMessage());
      try{
        finalBluetoothSocket.close();
      } catch(IOException closeException){}
      return;
    }

    if(finalBluetoothSocket.isConnected()){
      //emmiter.onComplete();
      Log.d("MyLog", "connected");
    } else {
      Log.d("MyLog", "cancel");
    }
*/
 /*connection(tmp)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Observer<BluetoothSocket>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(BluetoothSocket bluetoothSocket) {
          finalBluetoothSocket1 = bluetoothSocket;
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
      });*/

     // manageConnectedSocket(finalBluetoothSocket);
  }



  public Observable<BluetoothSocket> connection( BluetoothSocket finalBluetoothSocket ){
    return Observable.create(emmiter -> {
      try{
         finalBluetoothSocket.connect();
      } catch(IOException connectException){
        Log.d("MyLog", "error connection = " + connectException.getMessage());
        try{
          finalBluetoothSocket.close();
        } catch(IOException closeException){}
        return;
      }

      if(finalBluetoothSocket.isConnected()){
          emmiter.onComplete();
        Log.d("MyLog", "connected");
      } else {
        Log.d("MyLog", "cancel");
      }
    });
  }
}
