package com.murik.lite.ui.activity.start;

import android.Manifest;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.LocationManager;
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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.lite.App;
import com.murik.lite.R;
import com.murik.lite.Screens;
import com.murik.lite.configuration.AuthService;
import com.murik.lite.dto.AdditionalParcelable;
import com.murik.lite.dto.DataByMaxParcelable;
import com.murik.lite.dto.MeasureDataParcelable;
import com.murik.lite.dto.SensorDataFullParcelable;
import com.murik.lite.dto.SummaryParcelable;
import com.murik.lite.presentation.presenter.start.StartPresenter;
import com.murik.lite.presentation.view.start.StartView;
import com.murik.lite.service.Impl.BluetoothImplService;
import com.murik.lite.ui.activity.ProgressDisplay;
import com.murik.lite.ui.fragment.additionalCalculation.AdditionalCalculationFragment;
import com.murik.lite.ui.fragment.additionalResult.AdditionalResultFragment;
import com.murik.lite.ui.fragment.bar_chart.ResultBarChartFragment;
import com.murik.lite.ui.fragment.bluetooth.BluetoothConnectionFragment;
import com.murik.lite.ui.fragment.dimension.BluetoothDimensionFragment;
import com.murik.lite.ui.fragment.input.InputFragment;
import com.murik.lite.ui.fragment.liveBluetoothChart.LiveBluetoothChartFragment;
import com.murik.lite.ui.fragment.login.LoginFragment;
import com.murik.lite.ui.fragment.measurementLineChart.MeasurementLineChart;
import com.murik.lite.ui.fragment.oneSensorMeasure.OneSensorTabContainerFragment;
import com.murik.lite.ui.fragment.parserXml.ParserXmlFragment;
import com.murik.lite.ui.fragment.realm.RealmFragment;
import com.murik.lite.ui.fragment.result.ResultTabFragment;
import com.murik.lite.ui.fragment.resultRadarChart.RadarTabContentFragment;
import com.murik.lite.ui.fragment.settings.SettingsFragment;
import com.murik.lite.ui.fragment.substances.SubstancesFragment;
import com.murik.lite.ui.fragment.summaryResult.SummaryResultFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Locale;

import lombok.val;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class StartActivity extends MvpAppCompatActivity implements StartView, OnNavigationItemSelectedListener,
        ProgressDisplay {
    public static final String TAG = "StartActivity";

    @InjectPresenter
    StartPresenter mStartPresenter;

    private TextView login;
    private TextView logout;

    private MenuItem parseXml;
    private MenuItem inputData;
    private MenuItem additionalInfo;
    private DrawerLayout drawer;
    private TextView tvAppRole;

    private LinearLayout bleConnectionStatus;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothImplService.ACTION_GATT_DISCONNECTED.equals(action)) {
                Toast.makeText(getApplicationContext(), "Прибор отключен!", Toast.LENGTH_SHORT).show();
                bleConnectionStatus.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                App.INSTANCE.getmBluetoothGatt().close();
            }
        }
    };

    private Toolbar toolbar;
    private int REQUEST_ENABLE_BT = 0;
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
                    case Screens.ADDITIONAL_FRAGMENT:
                        return AdditionalCalculationFragment.newInstance();
                    case Screens.ADDITIONAL_RESULT_FRAGMENT:
                        return AdditionalResultFragment.newInstance((AdditionalParcelable) data);
                    case Screens.LOGIN_FRAGMENT:
                        return LoginFragment.newInstance();
                    case Screens.SETTINGS_FRAGMENT:
                        return SettingsFragment.newInstance();
                    case Screens.SUBSTANCES_FRAGMENT:
                        return SubstancesFragment.newInstance((MeasureDataParcelable) data);
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

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setOnClickListener(v -> drawer.closeDrawer(Gravity.START));
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        val headerView = navigationView.getHeaderView(0);
        login = headerView.findViewById(R.id.btn_app_login);
        logout = headerView.findViewById(R.id.btn_app_logout);
        tvAppRole = headerView.findViewById(R.id.tv_app_role);


        parseXml = navigationView.getMenu().findItem(R.id.app_parse_xml);
        inputData = navigationView.getMenu().findItem(R.id.app_input);
        additionalInfo = navigationView.getMenu().findItem(R.id.additional_info);

        bleConnectionStatus = findViewById(R.id.ble_connection_status);

        if (AuthService.getInstance().isAuth()) {
            login.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
        }

        visibleAdminMenuItems(AuthService.getInstance().isAdmin());

        login.setOnClickListener(v -> {
            login.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);

            tvAppRole.setText(AuthService.getInstance().getUserRole().name());

            App.INSTANCE.getRouter().navigateTo(Screens.LOGIN_FRAGMENT);

            drawer.closeDrawer(Gravity.START);
        });

        logout.setOnClickListener(v -> {
            AuthService.getInstance().logout();

            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);

            visibleAdminMenuItems(false);

            drawer.closeDrawer(Gravity.START);

            tvAppRole.setText("");

            App.INSTANCE.getRouter().navigateTo(Screens.REALM_FRAGMENT);
        });

//        if (AuthService.getInstance().isAdmin()) {
//            MenuItem item = (MenuItem) findViewById(R.id.additional_info);
//            item.setVisible(true);
//            this.invalidateOptionsMenu();
//        }

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.app_bar_screen) {
            onScreenshotButtonClick();
            return true;
        }

        return false;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(updateBaseContextLocale(base));
    }

    private Context updateBaseContextLocale(Context context) {
        Locale locale = new Locale("ru", "RU");
        Locale.setDefault(locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResourcesLocale(context, locale);
        }

        return updateResourcesLocaleLegacy(context, locale);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private Context updateResourcesLocale(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    @SuppressLint({"ShowToast", "NonConstantResourceId"})
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawer.closeDrawer(Gravity.START, false);
        switch (menuItem.getItemId()) {
            case R.id.app_bar_home: {
                App.INSTANCE.getRouter().newScreenChain(Screens.REALM_FRAGMENT);
                return true;
            }
            case R.id.additional_info: {
                App.INSTANCE.getRouter().newScreenChain(Screens.ADDITIONAL_FRAGMENT);
                return true;
            }
            case R.id.app_input: {
                App.INSTANCE.getRouter().replaceScreen(Screens.INPUT_FRAGMENT);
//                App.INSTANCE.getRouter().replaceScreen(Screens.SUBSTANCES_FRAGMENT, 1L);
                return true;
            }
            case R.id.app_parse_xml: {
                App.INSTANCE.getRouter().replaceScreen(Screens.PARSER_XML_FRAGMENT);
                return true;
            }
            case R.id.app_bar_bluetooth: {
                if (isConnected()) {
                    Toast.makeText(this, "Прибор подключен", Toast.LENGTH_LONG).show();
                    return false;
                }

                requestAppPermissions();

                if (!isGpsEnabled()) {
                    Toast.makeText(getApplicationContext(), "Включите GPS!", Toast.LENGTH_SHORT).show();
                }

                if (App.INSTANCE.getmBluetoothAdapter() == null) {
                    return false;
                }
                if (App.INSTANCE.getmBluetoothAdapter().isEnabled()) {
                    App.INSTANCE.getRouter().replaceScreen(Screens.BLUETOOTH_FRAGMENT);

                } else {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }

                return true;
            }
            case R.id.app_bar_dimension: {
                if (isConnected()) {
                    val screen = AuthService.getInstance().isAdmin() ? Screens.BLUETOOTH_LIVE_CHART_FRAGMENT : Screens.BLUETOOTH_DIMENSION_FRAGMENT;
                    App.INSTANCE.getRouter().replaceScreen(screen);
                    return true;
                }
                Toast.makeText(this, "Прибор не подключен", Toast.LENGTH_LONG).show();
                Log.i(TAG, "onNavigationItemSelected: измерение");
                return false;
            }
            case R.id.app_disconnect_device: {
                val gatt = App.INSTANCE.getmBluetoothGatt();
                if (gatt == null) {
                    Toast.makeText(this, "Прибор не подключен", Toast.LENGTH_LONG).show();
                } else {
                    gatt.disconnect();
                }
                return true;
            }
            case R.id.app_settings: {
                App.INSTANCE.getRouter().navigateTo(Screens.SETTINGS_FRAGMENT);
                return true;
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

    private boolean isGpsEnabled() {
        val locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    protected void onResume() {
        super.onResume();

        App.INSTANCE.getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothImplService.ACTION_GATT_DISCONNECTED);
        this.registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                App.INSTANCE.getRouter().replaceScreen(Screens.BLUETOOTH_FRAGMENT);
                Toast.makeText(this, "Bluetooth включен", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bluetooth выключен", Toast.LENGTH_SHORT).show();
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
        this.unregisterReceiver(mReceiver);

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


            return state == BluetoothProfile.STATE_CONNECTED;
        }

        // The gat service is null, the device is not connected, return false.
        return false;
    }

    @Override
    public void setBleConnectionStateColor(int color) {
        bleConnectionStatus.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    @Override
    public void visibleAdminMenuItems(boolean visible) {
        parseXml.setVisible(visible);
        inputData.setVisible(visible);
    }
}


