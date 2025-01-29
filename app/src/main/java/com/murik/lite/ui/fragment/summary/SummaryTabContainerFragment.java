package com.murik.lite.ui.fragment.summary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.dto.MeasureDataParcelable;


public class SummaryTabContainerFragment extends MvpAppCompatFragment {

    public static final String RESULT_KEY = "SummaryTabContainerFragment";

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MeasureDataParcelable resultBySens;

    public static Fragment newInstance(DataByMaxParcelable dataByMaxParcelable) {
        SummaryTabContainerFragment fragment = new SummaryTabContainerFragment();

        Bundle args = new Bundle();
        args.putParcelable(RESULT_KEY, dataByMaxParcelable);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.substances_tab_container, container, false);
        setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            resultBySens = bundle.getParcelable(RESULT_KEY);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.substances_tab_container);
        viewPager = view.findViewById(R.id.substances_tab_framer);

        //SummaryTabAdapter adapter = new SummaryTabAdapter(getChildFragmentManager(), resultBySens);
        //viewPager.setAdapter(adapter);
        //tabLayout.setupWithViewPager(viewPager);

    }

}
