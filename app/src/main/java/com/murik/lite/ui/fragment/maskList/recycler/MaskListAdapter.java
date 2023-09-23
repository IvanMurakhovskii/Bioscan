package com.murik.lite.ui.fragment.maskList.recycler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.murik.lite.App;
import com.murik.lite.R;
import com.murik.lite.model.entity.DataSensorRealm;
import com.murik.lite.presentation.presenter.realm.RealmPresenter;

import java.text.SimpleDateFormat;
import java.util.Objects;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

import static android.widget.Toast.LENGTH_LONG;

public class MaskListAdapter extends RealmRecyclerViewAdapter<DataSensorRealm, MaskListViewHolder> {

    private RealmPresenter presenter;
    private Activity activity;

    public MaskListAdapter(@Nullable OrderedRealmCollection<DataSensorRealm> data, boolean autoUpdate, @NonNull RealmPresenter presenter, @NonNull Activity activity) {
        super(data, autoUpdate);
        this.presenter = presenter;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MaskListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_realm, viewGroup, false);
        return new MaskListViewHolder(view);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull MaskListViewHolder realmViewHolder, int i) {
        Realm realm = Realm.getDefaultInstance();
        DataSensorRealm data = Objects.requireNonNull(getData()).get(i);

        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(data.getTime());
        realmViewHolder.setTvDescription(data.getDescriptions());

        realmViewHolder.setTvTime(date);

        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setTitle("Вы действительно хотите удалить измерение?")
                .setPositiveButton("Да", (DialogInterface dialog, int id) -> realm.executeTransaction(r -> {
                    RealmResults<DataSensorRealm> result = r.where(DataSensorRealm.class).equalTo("id", data.getId()).findAll();
                    result.deleteAllFromRealm();
                }))
                .setNegativeButton("Нет", (DialogInterface dialog, int id) -> dialog.dismiss())
                .create();

        realmViewHolder.btnDelete.setOnClickListener(event -> alertDialog.show());


        realmViewHolder.btnExport.setOnClickListener(e -> {
            Toast.makeText(App.INSTANCE.getApplicationContext(), "Загрузка...", LENGTH_LONG).show();
            presenter.downloadMeasurement(data);
        });

        realmViewHolder.itemView.setOnClickListener(event -> presenter.onItemRecyclerClick(data));
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(getData()).size();
    }
}
