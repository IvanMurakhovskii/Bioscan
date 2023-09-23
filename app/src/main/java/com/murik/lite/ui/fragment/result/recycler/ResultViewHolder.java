package com.murik.lite.ui.fragment.result.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.murik.lite.R;



public class ResultViewHolder extends RecyclerView.ViewHolder{

  private View divider;
  private TextView tvComment;

  public ResultViewHolder(View itemView) {
    super(itemView);
    divider = itemView.findViewById(R.id.divider);
    tvComment = itemView.findViewById(R.id.tvComment);
  }

  public void setDivider(int color){
    divider.setBackgroundColor(color);
  }

  public void setTvComment(String comment){
    tvComment.setText(comment);
  }

}
