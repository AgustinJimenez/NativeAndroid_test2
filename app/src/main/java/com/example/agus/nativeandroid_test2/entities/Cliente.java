package com.example.agus.nativeandroid_test2.entities;


import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.orm.SugarRecord;

/**
 * Created by agus on 16/08/17.
 */

public class Cliente extends SugarRecord<Cliente> implements Adapter {

    public String razon_social = "";
    public String cedula = "";

    public Cliente(  )
    {
    }

    public Cliente( String razon_social, String cedula )
    {
        this.razon_social = razon_social;
        this.cedula = cedula;
    }

    @Override
    public String toString()
    {
        return this.getId()+" - "+ razon_social + " - " + cedula;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
