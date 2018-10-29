package com.murik.enose.presentation.parserXml;

import com.arellomobile.mvp.MvpView;

public interface ParserXmlView extends MvpView {
  void initRecyclerView();
  void setVisibilitySaveButton(int visibility);
}
