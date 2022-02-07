package com.murik.enose.ui.activity.start;

import android.Manifest;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.App;
import com.murik.enose.R;
import com.murik.enose.Screens;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.dto.SensorDataFullParcelable;
import com.murik.enose.dto.SummaryParcelable;
import com.murik.enose.presentation.presenter.start.StartPresenter;
import com.murik.enose.presentation.view.start.StartView;
import com.murik.enose.service.Impl.BluetoothImplService;
import com.murik.enose.ui.activity.ProgressDisplay;
import com.murik.enose.ui.fragment.LiveBluetoothChart.LiveBluetoothChartFragment;
import com.murik.enose.ui.fragment.bar_chart.ResultBarChartFragment;
import com.murik.enose.ui.fragment.bluetooth.BluetoothConnectionFragment;
import com.murik.enose.ui.fragment.dimension.BluetoothDimensionFragment;
import com.murik.enose.ui.fragment.input.InputFragment;
import com.murik.enose.ui.fragment.summary_result.SummaryResultFragment;
import com.murik.enose.ui.fragment.measurementLineChart.MeasurementLineChart;
import com.murik.enose.ui.fragment.oneSensorMeasure.OneSensorTabContainerFragment;
import com.murik.enose.ui.fragment.parserXml.ParserXmlFragment;
import com.murik.enose.ui.fragment.realm.RealmFragment;
import com.murik.enose.ui.fragment.result.ResultTabFragment;
import com.murik.enose.ui.fragment.resultRadarChart.RadarTabContentFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import lombok.val;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class StartActivity extends MvpAppCompatActivity implements StartView, OnNavigationItemSelectedListener,
        ProgressDisplay {
    public static final String TAG = "StartActivity";

    @InjectPresenter
    StartPresenter mStartPresenter;

    private Toolbar toolbar;
    private int REQUEST_ENABLE_BT = 0;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, StartActivity.class);
        return intent;
    }

    private Navigator navigator;

    {
        navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.main_container) {
            @Override
            protected Fragment createFragment(String screenKey, Object data) {

                if (screenKey.equals(Screens.RESULT_BAR_CHART_FRAGMENT)) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }

                switch (screenKey) {
                    case Screens.RESULT_FRAGMENT:
                        return ResultTabFragment.newInstance((DataByMaxParcelable) data);
                    case Screens.INPUT_FRAGMENT:
                        return InputFragment.newInstance();
                    case Screens.REALM_FRAGMENT:
                        return RealmFragment.newInstance();
                    case Screens.PARSER_XML_FRAGMENT:
                        return ParserXmlFragment.newInstance();
                    case Screens.FULL_RESULT_FRAGMENT:
                        return RadarTabContentFragment.newInstance((SensorDataFullParcelable) data);
                    case Screens.BLUETOOTH_FRAGMENT:
                        return BluetoothConnectionFragment.newInstance();
                    case Screens.BLUETOOTH_LIVE_CHART_FRAGMENT:
                        return LiveBluetoothChartFragment.newInstance();
                    case Screens.BLUETOOTH_DIMENSION_FRAGMENT:
                        return BluetoothDimensionFragment.newInstance();
                    case Screens.ONE_SENSOR_MEASURE_FRAGMENT:
                        return OneSensorTabContainerFragment.newInstance((DataByMaxParcelable) data);
                    case Screens.RESULT_BAR_CHART_FRAGMENT:
                        return ResultBarChartFragment.newInstance((DataByMaxParcelable) data);
                    case Screens.TEST_DIMENSION:
                        return BluetoothDimensionFragment.newInstance();
                    case Screens.MEASUREMENT_LINE_CHART:
                        return MeasurementLineChart.newInstance((SensorDataFullParcelable) data);
                    case Screens.SUMMARY_RESULT:
                        return SummaryResultFragment.newInstance((SummaryParcelable) data);
                    default:
                        throw new RuntimeException("Unknown screen key");

                }
            }

            @Override
            protected void showSystemMessage(String message) {
                Toast.makeText(StartActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void exit() {
                finish();
            }
        };
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void requestPermissions() {
        int androidVersion = Build.VERSION.SDK_INT;
        if (androidVersion >= 23) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                    }, 0);
        }
    }

    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            App.INSTANCE.getRouter().replaceScreen(Screens.RESULT_FRAGMENT);
        }
        if (count == 1) {
            AlertDialog.Builder builder = new Builder(this);
            builder.setMessage("Вы уверены, что хотите выйти?")
                    .setPositiveButton("да", (dialog, witch) -> finish())
                    .setNegativeButton("нет", ((dialog, which) -> dialog.cancel()))
                    .create();
            builder.show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_start, menu);
        return true;
    }

    public void onScreenshotButtonClick() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        int check = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (check == PackageManager.PERMISSION_GRANTED) {
            try {

                File directory = new File(
                        Environment.getExternalStorageDirectory().toString() + File.separator + "Enose");
                directory.mkdirs();
                String mPath = Environment.getExternalStorageDirectory().toString() + "/Enose/" + now + ".jpg";

                View v1 = this.getWindow().getDecorView().getRootView();
                v1.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
                v1.setDrawingCacheEnabled(false);

                File imageFile = new File(mPath);

                FileOutputStream outputStream = new FileOutputStream(imageFile);
                int quality = 100;
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                Toast.makeText(this, "Screenshot saved..!", Toast.LENGTH_LONG).show();
                outputStream.flush();
                outputStream.close();


            } catch (Throwable e) {
                Log.d("ScreenShotActivity", "Failed to capture screenshot because:" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            requestAppPermissions();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_disconnect_bluetooth:
                App.INSTANCE.getmBluetoothGatt().disconnect();
            case R.id.app_bar_screen: {
                onScreenshotButtonClick();
                return true;
            }
        }

        return false;
    }


    @SuppressLint("ShowToast")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.app_bar_home: {
                App.INSTANCE.getRouter().newScreenChain(Screens.REALM_FRAGMENT);
                return true;
            }
            case R.id.app_input: {
                App.INSTANCE.getRouter().replaceScreen(Screens.INPUT_FRAGMENT);
                return true;
            }
            case R.id.app_parse_xml: {
                App.INSTANCE.getRouter().replaceScreen(Screens.PARSER_XML_FRAGMENT);
                return true;
            }
            case R.id.app_bar_bluetooth: {
                requestAppPermissions();
                if (App.INSTANCE.getmBluetoothAdapter() == null) {
                    return false;
                }
                if (!App.INSTANCE.getmBluetoothAdapter().isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
                if (App.INSTANCE.getmBluetoothAdapter().isEnabled()) {
                    App.INSTANCE.getRouter().replaceScreen(Screens.BLUETOOTH_FRAGMENT);
                }
                return true;
            }
            case R.id.app_bar_live_chart: {
                if (isConnected()) {
                    App.INSTANCE.getRouter().replaceScreen(Screens.TEST_DIMENSION);
                    return true;
                }
                Toast.makeText(this, "Прибор не подключен", Toast.LENGTH_LONG).show();
                Log.i(TAG, "onNavigationItemSelected: измерение");
                return false;

            }
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    protected void requestAppPermissions() {
        if (hasReadPermissions() && hasWritePermissions() && hasCorseLocationPermissions() && hasBluetoothAdminPermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        permission.ACCESS_COARSE_LOCATION,
                        permission.BLUETOOTH_ADMIN,
                }, 0);
    }

    private boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasCorseLocationPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasBluetoothAdminPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.INSTANCE.getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                App.INSTANCE.getRouter().replaceScreen(Screens.BLUETOOTH_FRAGMENT);
                Toast.makeText(this, "bluetooth is Enable", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "bluetooth is Disable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.INSTANCE.getNavigatorHolder().removeNavigator();
        Intent intent = new Intent(this, BluetoothImplService.class);
        stopService(intent);

    }

    public void showProgress() {
        findViewById(R.id.toolbar_progress_bar).setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        findViewById(R.id.toolbar_progress_bar).setVisibility(View.GONE);

    }

    public boolean isConnected() {

        val mGatt = App.INSTANCE.getmBluetoothGatt();
        if (mGatt != null) {

            BluetoothManager btm =
                    (BluetoothManager)
                            StartActivity.this.getSystemService(Context.BLUETOOTH_SERVICE);

            int state = btm.getConnectionState(mGatt.getDevice(), BluetoothProfile.GATT);

            return state == 2;
        }

        // The gat service is null, the device is not connected, return false.
        return false;
    }

}


