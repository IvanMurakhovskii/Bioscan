package com.murik.enose.ui.fragment.usbdevice;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.murik.enose.R;
import com.murik.enose.presentation.presenter.usbdevice.UsbPresenter;
import com.murik.enose.presentation.view.usbdevice.UsbFragmentView;
import com.murik.enose.ui.fragment.usbdevice.recycler.UsbRecyclerAdapter;

public class UsbFragment extends MvpAppCompatFragment implements UsbFragmentView {

    public static final String TAG = "UsbFragment";

    public static UsbFragment newInstance() {

        Bundle args = new Bundle();

        UsbFragment fragment = new UsbFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
     UsbPresenter usbPresenter;

    private RecyclerView rvUsb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_usb_connection, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*tvInfo = view.findViewById(R.id.tvInfo);
        btnSearch = view.findViewById(R.id.btnSearchDevice);
        btnSearch.setOnClickListener(event ->{
            mBluetoothConnectionPresenter.onButtonSearchClick();
        });*/
        rvUsb = view.findViewById(R.id.rvUsbDevice);
        usbPresenter.initRecyclerView();
        usbPresenter.searchDevices();

//        progressBar = view.findViewById(R.id.progressBar_bluetooth);

    }

    @Override
    public void initRecyclerView(UsbRecyclerAdapter adapter){
        rvUsb.setLayoutManager(new LinearLayoutManager(getContext()));
        rvUsb.setAdapter(adapter);
    }
}
