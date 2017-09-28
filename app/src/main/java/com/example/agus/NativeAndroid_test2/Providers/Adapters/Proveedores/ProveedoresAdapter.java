package com.example.agus.NativeAndroid_test2.Providers.Adapters.Proveedores;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agus.NativeAndroid_test2.MainActivity;
import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.Proveedor;
import com.example.agus.NativeAndroid_test2.fragments.proveedores.proveedores_form;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agus on 08/09/17.
 */

public class ProveedoresAdapter extends ArrayAdapter<com.example.agus.NativeAndroid_test2.entities.Proveedor> implements View.OnClickListener
{

    private List<Proveedor> dataSet;
    Context mContext;
    FragmentActivity fragAct;

    // View lookup cache
    private static class ViewHolder
    {
        TextView tv_razon_social;
        TextView tv_ruc;
        TextView tv_telefono;
        ImageView item_editar;
        ImageView item_eliminar;

    }

    public ProveedoresAdapter(List<Proveedor> data, Context context, FragmentActivity act)
    {
        super(context, R.layout.list_view_proveedores, data);
        this.dataSet = data;
        this.mContext = context;
        this.fragAct = act;
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
            convertView = inflater.inflate(R.layout.list_view_proveedores, parent, false);

            viewHolder.tv_razon_social = convertView.findViewById(R.id.tv_razon_social);
            viewHolder.tv_ruc = convertView.findViewById(R.id.tv_ruc);
            viewHolder.tv_telefono = convertView.findViewById(R.id.tv_telefono);

            viewHolder.item_editar = convertView.findViewById(R.id.item_editar);
            viewHolder.item_eliminar = convertView.findViewById(R.id.item_eliminar);

            result = convertView;

            convertView.setTag( viewHolder );
        }
        else
        {
            viewHolder = (ProveedoresAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);
        lastPosition = position;


        // Get the data item for this position
        Proveedor element = getItem(position);

        viewHolder.tv_razon_social.setText( element.razon_social );
        viewHolder.tv_ruc.setText( element.ruc );
        viewHolder.tv_telefono.setText( element.telefono );

        viewHolder.item_editar.setOnClickListener(this);
        viewHolder.item_eliminar.setOnClickListener(this);

        viewHolder.item_editar.setTag(position);
        viewHolder.item_eliminar.setTag(position);

        return convertView;

    }

    @Override
    public void onClick(View v)
    {

        int position = (Integer) v.getTag();
        final Proveedor element = getItem(position);

        switch (v.getId())
        {
            case R.id.item_editar:
                //Snackbar.make(v, "HELLO THERE ", Snackbar.LENGTH_LONG).setAction("No action", null).show();
                proveedores_form next_fragment = new proveedores_form();
                Bundle params = new Bundle();
                params.putString( "id", String.valueOf( element.getId() ) );
                next_fragment.setArguments(params);
                        fragAct
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, next_fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.item_eliminar:
                Log.d("===================>", "you bastard!");
                new AlertDialog.Builder( getContext() )
                .setTitle("ELIMINAR")
                .setMessage("Quiere eliminar el registro?")
                .setIcon( android.R.drawable.ic_dialog_alert )
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {

                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        element.delete();
                        remove(element);
                        Toast.makeText( getContext(), "Se elimino el registro", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
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
