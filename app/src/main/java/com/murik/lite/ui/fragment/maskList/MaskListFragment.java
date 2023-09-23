package com.murik.lite.ui.fragment.maskList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.lite.R;
import com.murik.lite.model.RealmController;
import com.murik.lite.presentation.presenter.realm.RealmPresenter;
import com.murik.lite.presentation.view.realm.RealmView;
import com.murik.lite.ui.dialog.ChooseTypeMeasureDialogFragment;
import com.murik.lite.ui.dialog.DialogListener;
import com.murik.lite.ui.fragment.maskList.recycler.MaskListAdapter;

import java.util.Objects;

public class MaskListFragment extends MvpAppCompatFragment implements RealmView, DialogListener {

  public static final String TAG = "MaskListFragment";
  @InjectPresenter
  RealmPresenter mRealmPresenter;

  private RecyclerView recyclerView;
  private RealmController realmController;
  private ChooseTypeMeasureDialogFragment chooseTypeMeasureDialogFragment;

  public static final String CHOOSEN_DIALOG_TAG = "Choose_measure_type_dialog";

  public static MaskListFragment newInstance() {
    MaskListFragment fragment = new MaskListFragment();

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
  public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    recyclerView = view.findViewById(R.id.realm_recycler);
    realmController = new RealmController();
    chooseTypeMeasureDialogFragment = new ChooseTypeMeasureDialogFragment();
    chooseTypeMeasureDialogFragment.setDialogListener(this);

    initRecyclerView();
  }
  public void initRecyclerView(){
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    MaskListAdapter adapter = new MaskListAdapter(realmController.getInfo(), true, mRealmPresenter, Objects.requireNonNull(getActivity()));
    recyclerView.setAdapter(adapter);


  }

  @Override
  public void onDialogPositiveClick(int id) {
    mRealmPresenter.createMeasureByType(
            chooseTypeMeasureDialogFragment.getMeasureType(),
            chooseTypeMeasureDialogFragment.getSelectedSensor(),
            chooseTypeMeasureDialogFragment.getTimeRegistrationMaxSignal(),
            chooseTypeMeasureDialogFragment.getSensorType(),
            chooseTypeMeasureDialogFragment.getExpertType(),
            chooseTypeMeasureDialogFragment.isAnimalsSelected()
    );
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
