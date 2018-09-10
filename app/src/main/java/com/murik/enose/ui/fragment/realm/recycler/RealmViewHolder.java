package com.murik.enose.ui.fragment.realm.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.murik.enose.R;

public class RealmViewHolder extends RecyclerView.ViewHolder{

   TextView tvDescription;
   TextView tvTime;
   Button btnDelete;

   public RealmViewHolder(final View view) {
     super(view);
     tvDescription = view.findViewById(R.id.result_descriptions);
     tvTime = view.findViewById(R.id.tvTime);
     btnDelete = view.findViewById(R.id.btnDelete);
   }

   public void setTvDescription(String discription){
     tvDescription.setText(discription);
   }

  public void setTvTime(String time) {
    tvTime.setText(time);
  }
}
