package com.murik.lite.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.murik.lite.configuration.AuthService;
import com.murik.lite.enums.BluetoothDimensionAlgorithm;


public class BluetoothDimensionSpinnerAdapter extends ArrayAdapter<BluetoothDimensionAlgorithm> implements SpinnerAdapter {

    private BluetoothDimensionAlgorithm[] items;
    private Context context;
    private int resource;

    public BluetoothDimensionSpinnerAdapter(@NonNull Context context, int resource, @NonNull BluetoothDimensionAlgorithm[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
        this.resource = resource;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(resource, null);
        }
        TextView lbl = (TextView) v.findViewById(android.R.id.text1);
        lbl.setPadding(0, 12, 0, 12);

        String text = items[position].getName();

        if (AuthService.getInstance().isAdmin()) {
            text += " (" + items[position].getMaxSignalTime() + ")";
        }

        lbl.setText(text);

        return v;
    }

}
