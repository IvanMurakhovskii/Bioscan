package com.murik.enose;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import com.polidea.rxandroidble2.RxBleClient;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

//import io.realm.Realm;

public class App extends Application {
    public static App INSTANCE;
    private Cicerone<Router> cicirone;
    private BluetoothAdapter mBluetoothAdapter;
  @Override
  public void onCreate() {
    super.onCreate();
    INSTANCE = this;
    cicirone = Cicerone.create();
    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Realm.init(this);
    RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm")
        .schemaVersion(5)
        .migration(new ReamMigration())
        .build();
    Realm.setDefaultConfiguration(config);


  }

  public BluetoothAdapter getmBluetoothAdapter() {
    return mBluetoothAdapter;
  }

  public NavigatorHolder getNavigatorHolder() {
    return cicirone.getNavigatorHolder();
  }

  public Router getRouter() {
    return cicirone.getRouter();
  }

  public RxBleClient getRxBLEClient(){
    return RxBleClient.create(this);
  }
}
