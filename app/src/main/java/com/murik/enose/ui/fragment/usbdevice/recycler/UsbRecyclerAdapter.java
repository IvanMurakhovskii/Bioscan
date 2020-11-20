package com.murik.enose.ui.fragment.usbdevice.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.murik.enose.R;
import com.murik.enose.presentation.presenter.bluetooth.BluetoothConnectionPresenter;
import com.murik.enose.presentation.presenter.usbdevice.UsbPresenter;

public class UsbRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

  UsbPresenter presenter;

  public UsbRecyclerAdapter(UsbPresenter presenter){
    this.presenter = presenter;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_bluetooth_device, viewGroup, false);
    return new UsbRecyclerViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    presenter.OnBindViewHolder((UsbRecyclerViewHolder) viewHolder, i);
    viewHolder.itemView
        .setOnClickListener(event -> presenter.onItemRecyclerClickListener((UsbRecyclerViewHolder) viewHolder));

  }

  @Override
  public int getItemCount() {
    return presenter.getItemCount();
  }
}
