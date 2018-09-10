package com.murik.enose.ui.fragment.realm.recycler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.murik.enose.R;
import com.murik.enose.model.InputData;
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
  InputData inputData = new InputData();
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
      /*if(resultDto.getDescription() == null){tmp = "null";} else {
        tmp = resultDto.getDescription();
      }*/

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
        ArrayList<Integer> dataSens= new ArrayList<>();
        dataSens.add(data.getDataSens1());
        dataSens.add(data.getDataSens2());
        dataSens.add(data.getDataSens3());
        dataSens.add(data.getDataSens4());
        dataSens.add(data.getDataSens5());
        dataSens.add(data.getDataSens6());
        dataSens.add(data.getDataSens7());
        dataSens.add(data.getDataSens8());

        inputData.setDatasens(dataSens);
        inputData.setPractice(data.isPractice());
        presenter.onItemRecyclerClick(inputData);
      });
  }

  @Override
  public int getItemCount() {
    return getData().size();
  }
}
