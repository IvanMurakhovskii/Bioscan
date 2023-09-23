package com.murik.lite.ui.fragment.result.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.murik.lite.R;

public class TotalIndicatorsViewHolder extends RecyclerView.ViewHolder {

  private TextView tvDescriptions;

  public TotalIndicatorsViewHolder(@NonNull View itemView) {
    super(itemView);
    tvDescriptions = itemView.findViewById(R.id.total_indicators_description);
  }

  public void setTvDescriptions(String descriptions) {
    tvDescriptions.setText(descriptions);
  }
}
