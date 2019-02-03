package com.murik.enose.presentation.view.bluetooth;

import android.bluetooth.BluetoothDevice;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.murik.enose.ui.fragment.bluetooth.recycler.BluetoothRecyclerAdapter;

public interface BluetoothConnectionView extends MvpView {
  void bluetoothConnecting();
  void searchBluetoothDevices();
  void initRecyclerView(BluetoothRecyclerAdapter adapter);
  @StateStrategyType(value = SkipStrategy.class)
  void hideProgress();
  @StateStrategyType(value = SkipStrategy.class)
  void showProgress();
  @StateStrategyType(value = SkipStrategy.class)
  void hideProgressBar();
  @StateStrategyType(value = SkipStrategy.class)
  void showProgressBar();
  void connect(BluetoothDevice device);
}