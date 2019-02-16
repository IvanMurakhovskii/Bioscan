package com.murik.enose.ui.fragment.result;

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
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.ui.fragment.result.tab.ResultTabPageAdapter;

public class ResultTabFragment  extends Fragment {

  public static final String CALCULATE_A_KEY = "RESULT_A";


  DataByMaxParcelable inputDataParcelable;
  private TabLayout tabLayout;
  private ViewPager viewPager;

  public static Fragment newInstance(DataByMaxParcelable resultBySens) {
    ResultTabFragment fragment = new ResultTabFragment();

    Bundle args = new Bundle();
    args.putParcelable(CALCULATE_A_KEY, resultBySens);
    fragment.setArguments(args);

    return fragment;
  }

  @NonNull
  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
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

    ResultTabPageAdapter adapter = new ResultTabPageAdapter(getChildFragmentManager(), getActivity().getApplicationContext(), inputDataParcelable);
    viewPager.setAdapter(adapter);

    tabLayout.setupWithViewPager(viewPager);
  }
}
