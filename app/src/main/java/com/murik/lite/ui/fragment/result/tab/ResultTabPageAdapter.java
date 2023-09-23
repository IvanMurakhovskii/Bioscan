package com.murik.lite.ui.fragment.result.tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.ui.fragment.result.ResultFragment;

public class ResultTabPageAdapter extends FragmentPagerAdapter {
  private final int PAGE_COUNT = 2;
  private DataByMaxParcelable inputDataParcelable;
  private String[] tabTitles = new String[]{"Левая сторона", "Правая сторона"};
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
