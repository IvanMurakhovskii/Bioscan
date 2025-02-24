package com.murik.lite.ui.fragment.result.stress;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.murik.lite.R;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.ui.fragment.result.stress.tab.StressResultTabPageAdapter;

import java.util.Objects;

public class StressResultTabFragment  extends Fragment {

  public static final String CALCULATE_A_KEY = "RESULT_A";
  public static final String MEASURE_TYPE = "MEASURE_TYPE";

  DataByMaxParcelable inputDataParcelable;
  private TabLayout tabLayout;
  private ViewPager viewPager;

  public static Fragment newInstance(DataByMaxParcelable resultBySens) {
    StressResultTabFragment fragment = new StressResultTabFragment();

    Bundle args = new Bundle();
    args.putParcelable(CALCULATE_A_KEY, resultBySens);
    fragment.setArguments(args);

    return fragment;
  }

  @NonNull
  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                           final Bundle savedInstanceState) {
    Bundle bundle = getArguments();
    if(bundle != null){
      inputDataParcelable =  bundle.getParcelable(CALCULATE_A_KEY);
    }
    setHasOptionsMenu(true);
    return inflater.inflate(R.layout.fragment_tab_content, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    tabLayout = view.findViewById(R.id.sliding_tabs);
    viewPager = view.findViewById(R.id.viewpager);

    StressResultTabPageAdapter adapter = new StressResultTabPageAdapter(getChildFragmentManager(), Objects.requireNonNull(getActivity()).getApplicationContext(), inputDataParcelable);
    viewPager.setAdapter(adapter);

    tabLayout.setupWithViewPager(viewPager);
  }
}
