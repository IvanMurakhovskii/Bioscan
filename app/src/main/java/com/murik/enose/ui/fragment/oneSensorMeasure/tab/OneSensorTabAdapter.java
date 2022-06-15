package com.murik.enose.ui.fragment.oneSensorMeasure.tab;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.ui.fragment.oneSensorMeasure.OneSensorChartFragment;

public class OneSensorTabAdapter extends FragmentPagerAdapter {

    private DataByMaxParcelable dataByMaxParcelable;

    private final int PAGE_COUNT = 3;
    private String tabTitle[] = new String[]{"Дискретное", "Полное", "Энергия"};

    public OneSensorTabAdapter(FragmentManager fm, DataByMaxParcelable dataByMaxParcelable) {
        super(fm);
        this.dataByMaxParcelable = dataByMaxParcelable;
    }


    @Override
    public Fragment getItem(int position) {
        return OneSensorChartFragment.newInstance(position, dataByMaxParcelable);
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
