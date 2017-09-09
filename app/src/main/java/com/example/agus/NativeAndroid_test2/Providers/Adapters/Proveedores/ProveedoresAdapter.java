package com.example.agus.NativeAndroid_test2.Providers.Adapters.Proveedores;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.Proveedor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agus on 08/09/17.
 */

public class ProveedoresAdapter  extends ArrayAdapter<com.example.agus.NativeAndroid_test2.entities.Proveedor> implements View.OnClickListener
{

    private List<Proveedor> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder
    {
        TextView tv_razon_social;
        TextView tv_ruc;
        TextView tv_telefono;
        ImageView item_editar;
        ImageView item_eliminar;
    }

    public ProveedoresAdapter(List<Proveedor> data, Context context)
    {
        super(context, R.layout.list_view_proveedores, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Check if an existing view is being reused, otherwise inflate the view
        ProveedoresAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null)
        {

            viewHolder = new ProveedoresAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_view_empleados, parent, false);

            viewHolder.tv_razon_social = convertView.findViewById(R.id.tv_razon_social);
            viewHolder.tv_ruc = convertView.findViewById(R.id.tv_ruc);
            viewHolder.tv_telefono = convertView.findViewById(R.id.tv_telefono);

            viewHolder.item_editar = convertView.findViewById(R.id.item_editar);
            viewHolder.item_eliminar = convertView.findViewById(R.id.item_eliminar);

            result=convertView;

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ProveedoresAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        // Get the data item for this position
        Proveedor element = getItem(position);

        Log.d( "=============>", String.valueOf( element.razon_social ));
        viewHolder.tv_razon_social.setText( "abc" );
/*
        viewHolder.tv_razon_social.setText( element.razon_social );
        viewHolder.tv_ruc.setText( element.ruc );
        viewHolder.tv_telefono.setText( element.telefono );
        viewHolder.item_editar.setOnClickListener(this);
        viewHolder.item_eliminar.setOnClickListener(this);
*/
        //viewHolder.info.setTag(position);

        return convertView;

    }

    @Override
    public void onClick(View v)
    {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Proveedor dataModel=(Proveedor)object;

        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "HELLO THERE ", Snackbar.LENGTH_LONG).setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public int getCount()
    {
        return super.getCount();
    }



}
