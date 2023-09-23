package com.murik.lite.ui.fragment.oneSensorMeasure;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.ui.fragment.oneSensorMeasure.tab.OneSensorTabAdapter;
import com.murik.lite.ui.fragment.resultRadarChart.ResultRadarChartFragment;

public class OneSensorTabContainerFragment extends MvpAppCompatFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private DataByMaxParcelable dataByMaxParcelable;

    public static Fragment newInstance(DataByMaxParcelable dataByMaxParcelable) {
        OneSensorTabContainerFragment fragment = new OneSensorTabContainerFragment();

        Bundle args = new Bundle();
        args.putParcelable(ResultRadarChartFragment.DATA, dataByMaxParcelable);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null){
            dataByMaxParcelable =  bundle.getParcelable(ResultRadarChartFragment.DATA);
        }

        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_tab_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.sliding_tabs);
        viewPager = view.findViewById(R.id.viewpager);

        OneSensorTabAdapter adapter = new OneSensorTabAdapter(getChildFragmentManager(), dataByMaxParcelable);
        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);

    }
}
