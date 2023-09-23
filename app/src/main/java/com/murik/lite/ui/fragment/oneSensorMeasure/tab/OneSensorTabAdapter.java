package com.murik.lite.ui.fragment.oneSensorMeasure.tab;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.murik.lite.configuration.AuthService;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.ui.fragment.oneSensorMeasure.OneSensorChartFragment;

public class OneSensorTabAdapter extends FragmentPagerAdapter {

    private DataByMaxParcelable dataByMaxParcelable;

//    private final int PAGE_COUNT = 1;
//    private final int ADMIN_PAGE_COUNT = 3;
//    private String tabTitle[] = new String[]{"Полный след молекул"};
//    private String adminTabTitle[] = new String[]{"Дискретное", "Полный след молекул", "Энергия"};

    private int pageCount = 1;
    private String tabTitle[] = new String[]{"Полный след молекул"};

    public OneSensorTabAdapter(FragmentManager fm, DataByMaxParcelable dataByMaxParcelable) {
        super(fm);
        this.dataByMaxParcelable = dataByMaxParcelable;

        if (AuthService.getInstance().isAdmin()) {
            pageCount = 3;
            tabTitle = new String[]{"Полный след молекул", "Дискретное", "Энергия"};
        }

    }


    @Override
    public Fragment getItem(int position) {
        return OneSensorChartFragment.newInstance(position, dataByMaxParcelable);
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
