package com.murik.enose.ui.fragment.bluetooth.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.murik.enose.R;
import com.murik.enose.presentation.presenter.bluetooth.BluetoothConnectionPresenter;

public class BluetoothRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  BluetoothConnectionPresenter presenter;

  public BluetoothRecyclerAdapter(BluetoothConnectionPresenter presenter){
    this.presenter = presenter;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_bluetooth_device, viewGroup, false);
    return new BluetoothRecyclerViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    presenter.OnBindViewHolder((BluetoothRecyclerViewHolder) viewHolder, i);
    viewHolder.itemView
        .setOnClickListener(event -> presenter.onItemRecyclerClickListener((BluetoothRecyclerViewHolder) viewHolder));

  }

  @Override
  public int getItemCount() {
    return presenter.getItemCount();
  }
}
