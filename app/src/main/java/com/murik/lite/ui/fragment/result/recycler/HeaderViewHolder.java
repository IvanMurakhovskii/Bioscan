package com.murik.lite.ui.fragment.result.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.murik.lite.R;

public class HeaderViewHolder extends RecyclerView.ViewHolder {

  private TextView tvDescriptions;

  public HeaderViewHolder(@NonNull View itemView) {
    super(itemView);
    tvDescriptions = itemView.findViewById(R.id.et_rec_descriptions);
  }

  public void setTvDescriptions(String descriptions) {
    tvDescriptions.setText(descriptions.toUpperCase());
  }
}
