package com.murik.enose.presentation.parserXml;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.murik.enose.ui.fragment.parserXml.recycler.ParserXmlAdapter;

public interface ParserXmlView extends MvpView {
  void initRecyclerView(ParserXmlAdapter adapter);
  void setVisibilitySaveButton(int visibility);
  @StateStrategyType(value = SkipStrategy.class)
  void showProgress();
  @StateStrategyType(value = SkipStrategy.class)
  void hideProgress();
}
