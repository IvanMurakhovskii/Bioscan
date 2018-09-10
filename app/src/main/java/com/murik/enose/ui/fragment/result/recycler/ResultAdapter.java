package com.murik.enose.ui.fragment.result.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.murik.enose.R;
import com.murik.enose.presentation.result.ResultPresenter;


public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder>{

  private final ResultPresenter presenter;

  public ResultAdapter(ResultPresenter presenter) {
    this.presenter = presenter;
    //this.resultBySens = resultBySens;
  }

  @Override
  public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_result, parent, false);
    return new ResultViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ResultViewHolder holder, int position) {
    presenter.onBindPlacesViewPosition(position, holder);
  }


  @Override
  public int getItemCount() {
    return presenter.getResultRowsCount();
  }
}
