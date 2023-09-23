package com.murik.lite.ui.fragment.maskList.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.murik.lite.R;

public class MaskListViewHolder extends RecyclerView.ViewHolder{

   TextView tvDescription;
   TextView tvTime;
   Button btnDelete;
   Button btnExport;

   public MaskListViewHolder(final View view) {
     super(view);
     tvDescription = view.findViewById(R.id.result_descriptions);
     tvTime = view.findViewById(R.id.tvTime);
     btnDelete = view.findViewById(R.id.btnDelete);
     btnExport = view.findViewById(R.id.btn_export);
   }

   public void setTvDescription(String discription){
     tvDescription.setText(discription);
   }

  public void setTvTime(String time) {
    tvTime.setText(time);
  }
}
