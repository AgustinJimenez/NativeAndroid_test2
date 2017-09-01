package com.example.agus.NativeAndroid_test2.entities;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.orm.SugarRecord;
/**
 * Created by agus on 31/08/17.
 */

public class Proveedor extends SugarRecord<Proveedor> implements Adapter
{
    public String razon_social = "";
    public String ruc = "";
    public String categoria = "";
    public String direccion = "";
    public String email = "";
    public String telefono = "";
    public String celular = "";
    public String fax = "";
    public String contacto = "";

    public Proveedor
            (){}
    public Proveedor
    (
         String razon_social,
         String ruc,
         String categoria,
         String direccion,
         String email,
         String telefono,
         String celular,
         String fax,
         String contacto
    )
    {
        this.razon_social = razon_social;
        this.ruc = ruc;
        this.categoria = categoria;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.celular = celular;
        this.fax = fax;
        this.contacto = contacto;
    }

    @Override
    public String toString()
    {
        return this.getId()+" - "+ razon_social + " - " + ruc;
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
