package com.murik.enose;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

//import io.realm.Realm;

public class App extends Application {
    public static App INSTANCE;
    private Cicerone<Router> cicirone;

  @Override
  public void onCreate() {
    super.onCreate();
    INSTANCE = this;
    cicirone = Cicerone.create();
    Realm.init(this);
    RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm")
        .schemaVersion(5)
        .migration(new ReamMigration())
        .build();
    Realm.setDefaultConfiguration(config);


  }

  public NavigatorHolder getNavigatorHolder() {
    return cicirone.getNavigatorHolder();
  }

  public Router getRouter() {
    return cicirone.getRouter();
  }
}
