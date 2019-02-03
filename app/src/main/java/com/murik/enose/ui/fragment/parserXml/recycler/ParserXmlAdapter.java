package com.murik.enose.ui.fragment.parserXml.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.murik.enose.R;
import com.murik.enose.presentation.presenter.parserXml.ParserXmlPresenter;

public class ParserXmlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


  ParserXmlPresenter presenter;

  public ParserXmlAdapter(ParserXmlPresenter presenter){
    this.presenter = presenter;
  }
  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    if(presenter.getItemRecyclerCount() == 0){
      View view = LayoutInflater.from(viewGroup.getContext())
          .inflate(R.layout.item_empty_data, viewGroup, false);
      return new ViewHolder(view) {
      };
    }
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_parser_xml, viewGroup, false);
    return new ParserXmlViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

    if(holder instanceof ParserXmlViewHolder){
      presenter.OnBindViewHolder((ParserXmlViewHolder) holder, i);
      holder.itemView
          .setOnClickListener(event -> presenter.onItemRecyclerClickListener((ParserXmlViewHolder) holder));
    }

  }

  @Override
  public int getItemCount() {
    return presenter.getItemRecyclerCount();
  }
}
