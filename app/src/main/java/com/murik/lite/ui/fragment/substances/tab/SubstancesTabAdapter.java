package com.murik.lite.ui.fragment.substances.tab;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.murik.lite.dto.MeasureDataParcelable;
import com.murik.lite.dto.SensorDataFullParcelable;
import com.murik.lite.ui.fragment.resultRadarChart.ResultRadarChartFragment;
import com.murik.lite.ui.fragment.substances.SubstancesFragment;

public class SubstancesTabAdapter extends FragmentPagerAdapter {


    private MeasureDataParcelable sensorDataFullParcelable;
    private final int PAGE_COUNT = 3;
    private String tabTitle[] = new String[]{"Вещества", "Объектры", "Биопробы"};



    public SubstancesTabAdapter(FragmentManager fm, MeasureDataParcelable sensorDataFullParcelable) {
        super(fm);
        this.sensorDataFullParcelable = sensorDataFullParcelable;

    }


    @Override
    public Fragment getItem(int position) {
        return SubstancesFragment.newInstance(position, sensorDataFullParcelable);
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
