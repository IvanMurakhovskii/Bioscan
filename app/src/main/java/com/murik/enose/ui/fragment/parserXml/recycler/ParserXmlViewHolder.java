package com.murik.enose.ui.fragment.parserXml.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.murik.enose.R;
import java.io.File;

public class ParserXmlViewHolder extends RecyclerView.ViewHolder {

  private TextView tvFileName;
  private File file;

  public ParserXmlViewHolder(@NonNull View itemView) {
    super(itemView);
    tvFileName = itemView.findViewById(R.id.tvFileName);
  }

  public void setTvFileName(File file) {
    this.file = file;
    this.tvFileName.setText(file.getName());
  }

  public File getTvFile() {
    return file;
  }
}
