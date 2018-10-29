package com.murik.enose.ui.fragment.resultRadarChart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.murik.enose.R;
import com.murik.enose.model.dto.SensorDataFullParcelable;
import com.murik.enose.ui.fragment.resultRadarChart.tab.ResultRadarChartTabAdapter;

public class RadarTabContentFragment extends Fragment {

  private SensorDataFullParcelable inputDataParcelable;
  private TabLayout tabLayout;
  private ViewPager viewPager;

  /*public static Fragment newInstance(DataByMaxParcelable resultBySens) {
    ResultTabFragment fragment = new ResultTabFragment();

    Bundle args = new Bundle();
    args.putParcelable(ResultRadarChartFragment.DATA, resultBySens);
    fragment.setArguments(args);

    return fragment;
  }*/

  public static Fragment newInstance(SensorDataFullParcelable resultBySens) {
    RadarTabContentFragment fragment = new RadarTabContentFragment();

    Bundle args = new Bundle();
    args.putParcelable(ResultRadarChartFragment.DATA, resultBySens);
    fragment.setArguments(args);

    return fragment;
  }
  @NonNull
  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {
    Bundle bundle = getArguments();
    if(bundle != null){
      inputDataParcelable =  bundle.getParcelable(ResultRadarChartFragment.DATA);
    }
    setHasOptionsMenu(true);
    return inflater.inflate(R.layout.fragment_tab_content, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    tabLayout = view.findViewById(R.id.sliding_tabs);
    viewPager = view.findViewById(R.id.viewpager);

    ResultRadarChartTabAdapter adapter = new ResultRadarChartTabAdapter(getChildFragmentManager(), getActivity().getApplicationContext(), inputDataParcelable);
    viewPager.setAdapter(adapter);

    //viewPager.setOffscreenPageLimit(3);
    tabLayout.setupWithViewPager(viewPager);
  }

}
