package com.murik.enose.ui.fragment.realm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.App;
import com.murik.enose.R;
import com.murik.enose.Screens;
import com.murik.enose.model.RealmController;
import com.murik.enose.presentation.presenter.realm.RealmPresenter;
import com.murik.enose.presentation.view.realm.RealmView;
import com.murik.enose.ui.dialog.ChooseTypeMeasureDialogFragment;
import com.murik.enose.ui.dialog.DialogListener;
import com.murik.enose.ui.fragment.realm.recycler.RealmAdapter;

import java.util.Objects;

public class RealmFragment extends MvpAppCompatFragment implements RealmView, DialogListener {

  public static final String TAG = "RealmFragment";
  @InjectPresenter
  RealmPresenter mRealmPresenter;

  private FloatingActionButton fab;
  private RecyclerView recyclerView;
  private RealmController realmController;
  private ChooseTypeMeasureDialogFragment chooseTypeMeasureDialogFragment;

  public static final String CHOOSEN_DIALOG_TAG = "Choose_measure_type_dialog";

  public static RealmFragment newInstance() {
    RealmFragment fragment = new RealmFragment();

    Bundle args = new Bundle();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
      final Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_realm, container, false);

  }

  @Override
  public void onViewCreated(final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    recyclerView = view.findViewById(R.id.realm_recycler);
    realmController = new RealmController();
    chooseTypeMeasureDialogFragment = new ChooseTypeMeasureDialogFragment();
    chooseTypeMeasureDialogFragment.setDialogListener(this);
    fab = view.findViewById(R.id.fab);
    fab.setOnClickListener((View v) -> App.INSTANCE.getRouter().navigateTo(Screens.INPUT_FRAGMENT));

    initRecyclerView();

  }
  public void initRecyclerView(){
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    RealmAdapter adapter = new RealmAdapter(realmController.getInfo(), true, mRealmPresenter, Objects.requireNonNull(getFragmentManager()));
    recyclerView.setAdapter(adapter);


  }

  @Override
  public void onDialogPositiveClick(int id) {
    mRealmPresenter.createMeasureByType(chooseTypeMeasureDialogFragment.getMeasureType(), chooseTypeMeasureDialogFragment.getChoosenSensor());
  }

  @Override
  public void onDialogNegativeClick(int id) {
    chooseTypeMeasureDialogFragment.isCancelable();
  }

  @Override
  public void showDialog() {
    chooseTypeMeasureDialogFragment.show(Objects.requireNonNull(getFragmentManager()), CHOOSEN_DIALOG_TAG);
  }
}
