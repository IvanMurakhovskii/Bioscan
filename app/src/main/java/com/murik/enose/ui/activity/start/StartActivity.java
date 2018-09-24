package com.murik.enose.ui.activity.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
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
import com.murik.enose.model.dto.InputDataParcelable;
import com.murik.enose.presentation.start.StartPresenter;
import com.murik.enose.presentation.start.StartView;
import com.murik.enose.ui.fragment.input.InputFragment;
import com.murik.enose.ui.fragment.realm.RealmFragment;
import com.murik.enose.ui.fragment.result.ResultTabFragment;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class StartActivity extends MvpAppCompatActivity implements StartView{
    public static final String TAG = "StartActivity";

  @InjectPresenter
  StartPresenter mStartPresenter;

  private BottomAppBar bottomAppBar;

  public static Intent getIntent(final Context context) {
    Intent intent = new Intent(context, StartActivity.class);

    return intent;
  }
  private Navigator navigator;

  {
    navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.main_container) {
      @Override
      protected Fragment createFragment(String screenKey, Object data) {
        switch (screenKey) {
          case Screens.RESULT_FRAGMENT:
            return ResultTabFragment.newInstance((InputDataParcelable) data);
          case Screens.INPUT_FRAGMENT:
            return InputFragment.newInstance();
          case Screens.REALM_FRAGMENT:
            return RealmFragment.newInstance();
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);
    bottomAppBar = findViewById(R.id.bottom_app_bar);
    bottomAppBar.setHideOnScroll(true);
    setSupportActionBar(bottomAppBar);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener((View view) ->{
      //todo replaceFragment
        App.INSTANCE.getRouter().navigateTo(Screens.INPUT_FRAGMENT);
      });

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
    switch (item.getItemId()) {
      case R.id.app_bar_home:
        App.INSTANCE.getRouter().newScreenChain(Screens.REALM_FRAGMENT);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
