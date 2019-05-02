package com.murik.enose.ui.fragment.dimension;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import com.anychart.AnyChartView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.App;
import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.Screens;
import com.murik.enose.presentation.presenter.dimension.BluetoothDimensionPresenter;
import com.murik.enose.presentation.view.dimension.BluetoothDimensionView;
import com.murik.enose.service.Impl.BluetoothImplService;
import com.murik.enose.ui.dialog.ContinueDimensionDialogFragment;
import com.murik.enose.ui.dialog.DialogListener;
import com.murik.enose.ui.dialog.StartDimensionDialogFragment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BluetoothDimensionFragment extends MvpAppCompatFragment implements BluetoothDimensionView,
    DialogListener {


  private static final int ID_DIALOG_START = 1;
  private static final int ID_DIALOG_CONTINUE = 2;
  public static final String DIALOG_CONTINUE_TAG = "DIALOG_CONTINUE";
  public static final String DIALOG_START_TAG = "DIALOG_START";
  public static final String TAG = "BluetoothDimensionFragment";
  public static final int DIMENSION_TIME = 200;

  @InjectPresenter
  BluetoothDimensionPresenter mBluetoothDimensionPresenter;

  private StartDimensionDialogFragment startDimensionDialogFragment;
  private ContinueDimensionDialogFragment continueDimensionDialogFragment;

  private AnyChartView lineChart;
  private String description;
  private boolean isPractice;
  private int gender = Const.GENDER_MALE;
  private boolean isDimensoinStart = false;
  private boolean isLeftHand = false;

  private WebView webView;

  public boolean isFirstDimension = true;
  private int count = 0;


  BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
    @SuppressLint("CheckResult")
    @Override
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();

      if (BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE.equals(action)) {
        String str = intent.getStringExtra(BluetoothImplService.EXTRA_DATA);
        for (int i = 0; i < str.length(); i = i + 8) {
          Log.d("MyLog", "sens_count =  " + Integer.decode(str.substring(i, i + 1)) + " value =  "
              + Integer.parseInt(str.substring(i + 1, i + 8), 16));

        }
        /*timerDisposable =  Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
            .take(12)
            .map(v -> 12 - v)
            .subscribe(
                onNext -> {
                  if(isDimensoinStart) {
                     mBluetoothDimensionPresenter.getDimensionData(str);
                    }
                  },
                omError -> {},
                () -> {},
                onSubscribe -> {

                }
            );*/

        if(isDimensoinStart){
          if(count < DIMENSION_TIME*2){
            mBluetoothDimensionPresenter.getDimensionData(str);
            count++;
          } else {
            if(isFirstDimension){
              isDimensoinStart = false;
              stopChartRender();
              showContinueDialog();
            } else {
              mBluetoothDimensionPresenter.saveData(isLeftHand);
              mBluetoothDimensionPresenter.save(description, isPractice, gender);
              isDimensoinStart = false;
            }
          }
        }
      }
    }
  };

    public static BluetoothDimensionFragment newInstance() {
      BluetoothDimensionFragment fragment = new BluetoothDimensionFragment();

      Bundle args = new Bundle();
      fragment.setArguments(args);

      return fragment;
    }


  @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
        final Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_bluetooth_dimension, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      webView = view.findViewById(R.id.web_view_bluetooth_dimension);
      WebSettings webSettings = webView.getSettings();
      webSettings.setAllowFileAccess(true);
      webSettings.setAllowFileAccessFromFileURLs(true);
      webView.setWebChromeClient(new WebChromeClient());
      webSettings.setJavaScriptEnabled(true);
      startDimensionDialogFragment = new StartDimensionDialogFragment();
      startDimensionDialogFragment.show(getFragmentManager(), DIALOG_START_TAG);
      startDimensionDialogFragment.setDialogListener(this);
      webView.addJavascriptInterface(new WebAppInterface(getContext()), "Android");
    }

  private String readHtml(String remoteUrl) {
    String out = "";
    BufferedReader in = null;
    try {
      URL url = new URL(remoteUrl);
      in = new BufferedReader(new InputStreamReader(url.openStream()));
      String str;
      while ((str = in.readLine()) != null) {
        out += str;
      }
    } catch (MalformedURLException e) {
    } catch (IOException e) {
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return out;
  }

  @Override
  public void onResume() {
    super.onResume();
    IntentFilter filter = new IntentFilter();
    filter.addAction(BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE);
    getActivity().registerReceiver(mBroadcastReceiver, filter);

  }

  @Override
  public void onStop() {
    super.onStop();
    getActivity().unregisterReceiver(mBroadcastReceiver);
  }

  @Override
  public void onDialogPositiveClick(int id) {
      if(id == ID_DIALOG_START){
        description = startDimensionDialogFragment.getDescriptions();
        isPractice = startDimensionDialogFragment.isPractice();
        gender = startDimensionDialogFragment.getGender();
        String selectedItem = startDimensionDialogFragment.getSpinnerDimensionMode();
        isLeftHand = startDimensionDialogFragment.isLeftHand();
        isDimensoinStart = true;
        webView.loadUrl("file:android_asset/test.html");
      }
      else if(id == ID_DIALOG_CONTINUE){
        mBluetoothDimensionPresenter.saveData(isLeftHand);
        isLeftHand = !isLeftHand;
        isFirstDimension = false;
        count = 0;
        mBluetoothDimensionPresenter.clearData();
        isDimensoinStart = true;
        webView.loadUrl("file:android_asset/test.html");

      }
  }



  @Override
  public void onDialogNegativeClick(int id) {
      if(id == ID_DIALOG_CONTINUE){
        mBluetoothDimensionPresenter.saveData(isLeftHand);
        mBluetoothDimensionPresenter.save(description, isPractice, gender);
      } else if(id == ID_DIALOG_START){
        App.INSTANCE.getRouter().replaceScreen(Screens.REALM_FRAGMENT);
      }

  }

  @Override
  public void stopChartRender() {
    if(webView != null){
      webView.loadUrl("javascript:stop()");
      //webView.loadUrl("file:android_asset/test.html");
    }
  }

  @Override
  public void showContinueDialog() {
    if(continueDimensionDialogFragment  == null){
      continueDimensionDialogFragment = new ContinueDimensionDialogFragment();
      continueDimensionDialogFragment.setDialogListener(this);
      continueDimensionDialogFragment.show(getFragmentManager(), DIALOG_CONTINUE_TAG);
    }
  }


  public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
      mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
      Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public int getDataSens1() {
      return mBluetoothDimensionPresenter.getLastDataSens1();
    }
    @JavascriptInterface
    public int getDataSens2() {
      return mBluetoothDimensionPresenter.getLastDataSens2();
    }
    @JavascriptInterface
    public int getDataSens3() {
      return mBluetoothDimensionPresenter.getLastDataSens3();
    }
    @JavascriptInterface
    public int getDataSens4() {
      return mBluetoothDimensionPresenter.getLastDataSens4();
    }
    @JavascriptInterface
    public int getDataSens5() {
      return mBluetoothDimensionPresenter.getLastDataSens5();
    }
    @JavascriptInterface
    public int getDataSens6() {
      return mBluetoothDimensionPresenter.getLastDataSens6();
    }
    @JavascriptInterface
    public int getDataSens7() {
      return mBluetoothDimensionPresenter.getLastDataSens7();
    }
    @JavascriptInterface
    public int getDataSens8() {
      return mBluetoothDimensionPresenter.getLastDataSens8();
    }
  }
}


