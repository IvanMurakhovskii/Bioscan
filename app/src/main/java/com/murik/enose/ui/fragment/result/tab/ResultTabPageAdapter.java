package com.murik.enose.ui.fragment.result.tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.ui.fragment.result.ResultFragment;

public class ResultTabPageAdapter extends FragmentPagerAdapter {
  final int PAGE_COUNT = 3;
  DataByMaxParcelable inputDataParcelable;
  private String tabTitles[] = new String[] {"ЛЕВАЯ ", "ПРАВАЯ", "СРЕДНЕЕ"};
  private Context context;


  public ResultTabPageAdapter(FragmentManager fm, Context context, DataByMaxParcelable inputDataParcelable) {
    super(fm);
    this.context = context;
    this.inputDataParcelable = inputDataParcelable;
  }

  public Context getContext() {
    return context;
  }

  @Override public int getCount() {
    return PAGE_COUNT;
  }

  @Override public Fragment getItem(int position) {
    return ResultFragment.newInstance(inputDataParcelable, position);
  }

  @Override public CharSequence getPageTitle(int position) {
    return tabTitles[position];
  }
}
