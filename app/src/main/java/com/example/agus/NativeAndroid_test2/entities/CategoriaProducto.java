package com.example.agus.NativeAndroid_test2.entities;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.orm.SugarRecord;

/**
 * Created by agus on 01/09/17.
 */

public class CategoriaProducto extends SugarRecord<CategoriaProducto> implements Adapter
{

    public String nombre = "";
    public String descripcion = "";

    public CategoriaProducto(  )
    {
    }

    public CategoriaProducto(String razon_social, String cedula )
    {
        this.nombre = razon_social;
        this.descripcion = cedula;
    }

    @Override
    public String toString()
    {
        return this.getId()+" - "+ nombre;
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
