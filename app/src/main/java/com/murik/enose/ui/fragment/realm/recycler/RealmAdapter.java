package com.murik.enose.ui.fragment.realm.recycler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.murik.enose.R;
import com.murik.enose.model.dto.InputDataParcelable;
import com.murik.enose.model.dto.DataSensorRealm;
import com.murik.enose.presentation.realm.RealmPresenter;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RealmAdapter extends RealmRecyclerViewAdapter<DataSensorRealm, RealmViewHolder> {

  RealmPresenter presenter;
  InputDataParcelable inputDataParcelable = new InputDataParcelable();
  public RealmAdapter(
      @Nullable OrderedRealmCollection<DataSensorRealm> data, boolean autoUpdate, RealmPresenter presenter) {
    super(data, autoUpdate);
    this.presenter = presenter;
  }

  @NonNull
  @Override
  public RealmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_realm, viewGroup, false);
    return new RealmViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RealmViewHolder realmViewHolder, int i) {
    Realm realm = Realm.getDefaultInstance();
      DataSensorRealm data = getData().get(i);

    SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
    String date = df.format(data.getTime());
      realmViewHolder.setTvDescription(data.getDescriptions());

      realmViewHolder.setTvTime(date);

      realmViewHolder.btnDelete.setOnClickListener(event -> {
        realm.executeTransaction( r ->  {
          RealmResults<DataSensorRealm> result = r.where(DataSensorRealm.class).equalTo("id",data.getId()).findAll();
          result.deleteAllFromRealm();
        });
      });

      realmViewHolder.itemView.setOnClickListener(event -> {
        ArrayList<Integer> leftHandDataSens = new ArrayList<>();
        ArrayList<Integer> rightHandDataSens = new ArrayList<>();

        if(data.getLeftHandData() != null){
          leftHandDataSens.add(data.getLeftHandData().getDataSens1());
          leftHandDataSens.add(data.getLeftHandData().getDataSens2());
          leftHandDataSens.add(data.getLeftHandData().getDataSens3());
          leftHandDataSens.add(data.getLeftHandData().getDataSens4());
          leftHandDataSens.add(data.getLeftHandData().getDataSens5());
          leftHandDataSens.add(data.getLeftHandData().getDataSens6());
          leftHandDataSens.add(data.getLeftHandData().getDataSens7());
          leftHandDataSens.add(data.getLeftHandData().getDataSens8());
        }

        if(data.getRightHandData() != null){
          rightHandDataSens.add(data.getLeftHandData().getDataSens1());
          rightHandDataSens.add(data.getLeftHandData().getDataSens2());
          rightHandDataSens.add(data.getLeftHandData().getDataSens3());
          rightHandDataSens.add(data.getLeftHandData().getDataSens4());
          rightHandDataSens.add(data.getLeftHandData().getDataSens5());
          rightHandDataSens.add(data.getLeftHandData().getDataSens6());
          rightHandDataSens.add(data.getLeftHandData().getDataSens7());
          rightHandDataSens.add(data.getLeftHandData().getDataSens8());
        }

        inputDataParcelable.setRightHandDataSensor(rightHandDataSens);
        inputDataParcelable.setLeftHandDataSensor(leftHandDataSens);
        inputDataParcelable.setPractice(data.isPractice());
        inputDataParcelable.setDescriptions(data.getDescriptions());
        inputDataParcelable.setGender(data.getGender());
        presenter.onItemRecyclerClick(inputDataParcelable);
      });
  }

  @Override
  public int getItemCount() {
    return getData().size();
  }
}
