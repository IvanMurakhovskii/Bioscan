package com.murik.enose;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class App extends Application {
    public static App INSTANCE;
    private Cicerone<Router> cicirone;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;

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

    public static Context getContext() {
        return INSTANCE.getApplicationContext();
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
