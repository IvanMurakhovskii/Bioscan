package com.murik.lite.ui.fragment.realm.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.murik.lite.R;

public class RealmViewHolder extends RecyclerView.ViewHolder {

    TextView tvDescription;
    TextView tvTime;
    TextView tvAlgorithm;
    Button btnDelete;
    Button btnExport;

    public RealmViewHolder(final View view) {
        super(view);
        tvDescription = view.findViewById(R.id.result_descriptions);
        tvAlgorithm = view.findViewById(R.id.tvAlgorithm);
        tvTime = view.findViewById(R.id.tvTime);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnExport = view.findViewById(R.id.btn_export);
    }

    public void setTvDescription(String description) {
        tvDescription.setText(description);
    }

    public void setTvTime(String time) {
        tvTime.setText(time);
    }

    public void setAlgorithm(String time) {
        tvAlgorithm.setVisibility(View.VISIBLE);
        tvAlgorithm.setText(time);
    }
}
