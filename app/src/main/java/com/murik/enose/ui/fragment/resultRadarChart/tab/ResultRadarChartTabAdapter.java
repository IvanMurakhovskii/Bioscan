package com.murik.enose.ui.fragment.resultRadarChart.tab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.murik.enose.model.dto.SensorDataFullParcelable;
import com.murik.enose.ui.fragment.resultRadarChart.ResultRadarChartFragment;

public class ResultRadarChartTabAdapter  extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 4;

    private SensorDataFullParcelable sensorDataFullParcelable;

    private String tabTitle[] = new String[] {"Total", "Health", "Energy", "Bad"};
    private Context context;

    public ResultRadarChartTabAdapter(FragmentManager fm, Context context, SensorDataFullParcelable sensorDataFullParcelable) {
      super(fm);
      this.context = context;
      this.sensorDataFullParcelable = sensorDataFullParcelable;
    }

    @Override
    public Fragment getItem(int position) {
      return ResultRadarChartFragment.newInstance(sensorDataFullParcelable, position);
    }

    @Override
    public int getCount() {
      return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      return tabTitle[position];
    }

}
