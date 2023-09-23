package com.murik.lite.ui.fragment.main;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.lite.R;
import com.murik.lite.presentation.presenter.main.MainPresenter;
import com.murik.lite.presentation.view.main.MainView;
import com.murik.lite.service.Impl.BluetoothImplService;

public class MainFragment extends MvpAppCompatFragment implements MainView {

    public static final String TAG = "MainFragment";

    @InjectPresenter
    MainPresenter mainPresenter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        lineChart1 = view.findViewById(R.id.line_graph1);

    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothImplService.ACTION_CHARACTERISTIC_CHANGE);

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
