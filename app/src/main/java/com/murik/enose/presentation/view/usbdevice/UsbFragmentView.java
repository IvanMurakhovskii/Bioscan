package com.murik.enose.presentation.view.usbdevice;

import com.arellomobile.mvp.MvpView;
import com.murik.enose.ui.fragment.usbdevice.recycler.UsbRecyclerAdapter;

public interface UsbFragmentView extends MvpView {
    void initRecyclerView(UsbRecyclerAdapter adapter);
}
