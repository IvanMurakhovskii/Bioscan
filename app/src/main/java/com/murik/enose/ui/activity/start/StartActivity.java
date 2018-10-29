package com.murik.enose.ui.activity.start;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.Toast;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.App;
import com.murik.enose.R;
import com.murik.enose.Screens;
import com.murik.enose.model.dto.DataByMaxParcelable;
import com.murik.enose.model.dto.SensorDataFullParcelable;
import com.murik.enose.presentation.start.StartPresenter;
import com.murik.enose.presentation.start.StartView;
import com.murik.enose.ui.fragment.input.InputFragment;
import com.murik.enose.ui.fragment.parserXml.ParserXmlFragment;
import com.murik.enose.ui.fragment.realm.RealmFragment;
import com.murik.enose.ui.fragment.result.ResultTabFragment;
import com.murik.enose.ui.fragment.resultRadarChart.RadarTabContentFragment;
import java.util.Set;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class StartActivity extends MvpAppCompatActivity implements StartView, OnNavigationItemSelectedListener{
    public static final String TAG = "StartActivity";

  @InjectPresenter
  StartPresenter mStartPresenter;
  BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
  private int REQUEST_ENABLE_BT = 0;

  public static Intent getIntent(final Context context) {
    Intent intent = new Intent(context, StartActivity.class);
    return intent;
  }

  private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      Log.d("MyLog", "searching");

      if (BluetoothDevice.ACTION_FOUND.equals(action)) {
        // Discovery has found a device. Get the BluetoothDevice
        // object and its info from the Intent.
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
         String deviceName = device.getName();
        String deviceHardwareAddress = device.getAddress(); // MAC address
        Log.d("MyLog", "Name: " + device.getName() + "  Address: " + device.getAddress());

      }
    }
  };

  private Navigator navigator;
  {
    navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.main_container) {
      @Override
      protected Fragment createFragment(String screenKey, Object data) {
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
          default:
            throw new RuntimeException("Unkown screen key");

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
    Toolbar toolbar =  findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer =  findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

  }

  public void onBackPressed() {
    int count = getSupportFragmentManager().getBackStackEntryCount();
    if(count == 1){
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

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
     return false;
  }



  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    switch (menuItem.getItemId()){
      case R.id.app_bar_home:{
        App.INSTANCE.getRouter().newScreenChain(Screens.REALM_FRAGMENT);
        return true;
      }
      case R.id.app_input:{
        App.INSTANCE.getRouter().replaceScreen(Screens.INPUT_FRAGMENT);
        return true;
      }
      case R.id.app_parse_xml:{
        App.INSTANCE.getRouter().replaceScreen(Screens.PARSER_XML_FRAGMENT);
        return true;
      }
      case R.id.app_bar_bluetooth:{

        bluetoothConnenction();
       return true;
      }
      default:
        return super.onOptionsItemSelected(menuItem);
    }
  }

  public void bluetoothConnenction() {

    if ( mBluetoothAdapter == null ) {
      return;
    }

    if (!mBluetoothAdapter.isEnabled()) {
      Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    } else  {
      searchBluetoothDevices();
    }

  }
  private void searchBluetoothDevices(){
    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

    if (pairedDevices.size() > 0) {
      for (BluetoothDevice device : pairedDevices) {
        Log.d("MyLog", "Name: " + device.getName() + "  Address: " + device.getAddress());
      }
     // IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
     // registerReceiver(mReceiver, filter);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(resultCode == RESULT_OK){
      searchBluetoothDevices();
      Toast.makeText(this, "bluetooth is Enable", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "bluetooth is Disable", Toast.LENGTH_SHORT).show();
    }
  }

  protected void requestAppPermissions() {
    if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      return;
    }

    if (hasReadPermissions() && hasWritePermissions()) {
      return;
    }

    ActivityCompat.requestPermissions(this,
        new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 0); // your request code
  }

  private boolean hasReadPermissions() {
    return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
  }

  private boolean hasWritePermissions() {
    return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
  }

  @Override
  protected void onResume() {
    super.onResume();
    App.INSTANCE.getNavigatorHolder().setNavigator(navigator);
  }

  @Override
  protected void onPause() {
    super.onPause();
    App.INSTANCE.getNavigatorHolder().removeNavigator();
   // unregisterReceiver(mReceiver);
  }
}
