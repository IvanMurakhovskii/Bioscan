package com.murik.lite;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.content.Context;

import com.murik.lite.configuration.ApplicationComponent;
import com.murik.lite.model.RealmController;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import lombok.Getter;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Getter
public class App extends Application {
    public static App INSTANCE;
    private Cicerone<Router> cicirone;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;

    private RealmController realmController;

    public static Context getContext() {
        return INSTANCE.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        cicirone = Cicerone.create();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm")
                .schemaVersion(9)
                .allowWritesOnUiThread(true)
                .allowQueriesOnUiThread(true)
                .migration(new ReamMigration())
                .build();
        Realm.setDefaultConfiguration(config);

        realmController = new RealmController();

//        ApplicationComponent appComponent = DaggerApplicationComponent.create();
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

    public BluetoothGatt getmBluetoothGatt() {
        return mBluetoothGatt;
    }

    public void setmBluetoothGatt(BluetoothGatt gatt) {
        mBluetoothGatt = gatt;
    }
}
