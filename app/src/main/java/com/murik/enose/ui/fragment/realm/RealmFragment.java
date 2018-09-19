package com.murik.enose.ui.fragment.realm;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.R;
import com.murik.enose.model.RealmController;
import com.murik.enose.presentation.realm.RealmPresenter;
import com.murik.enose.presentation.realm.RealmView;
import com.murik.enose.ui.fragment.realm.recycler.RealmAdapter;

public class RealmFragment extends MvpAppCompatFragment implements RealmView {

  public static final String TAG = "RealmFragment";
  @InjectPresenter
  RealmPresenter mRealmPresenter;

  private RecyclerView recyclerView;
  private RealmController realmController;

  public static RealmFragment newInstance() {
    RealmFragment fragment = new RealmFragment();

    Bundle args = new Bundle();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_realm_recycler, container, false);
  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    recyclerView = view.findViewById(R.id.realm_recycler);
    realmController = new RealmController();
    initRecyclerView();

  }
  public void initRecyclerView(){
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    RealmAdapter adapter = new RealmAdapter(realmController.getInfo(), true, mRealmPresenter);
    recyclerView.setAdapter(adapter);
  }
}
