package com.example.agus.NativeAndroid_test2.fragments.proveedores;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.agus.NativeAndroid_test2.Providers.Adapters.Empleados.EmpleadosAdapter;
import com.example.agus.NativeAndroid_test2.Providers.Adapters.Proveedores.ProveedoresAdapter;
import com.example.agus.NativeAndroid_test2.Providers.KeyboardProvider;
import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.Proveedor;
import com.example.agus.NativeAndroid_test2.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class proveedores_list extends BaseFragment
{

    ListView lv_lista;
    List<Proveedor> list_items;
    Button btn_agregar;
    View vista;
    private static ProveedoresAdapter adapter_items;
    ImageView iv_editar;


    public proveedores_list()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        vista = inflater.inflate(R.layout.fragment_proveedores_list, container, false);
        set_elements();
        load_item_list();
        set_listeners();
        KeyboardProvider.hideKeyboard( getContext() );
        return vista;
    }

    private void set_elements()
    {
        lv_lista = vista.findViewById( R.id.lv_lista );
        btn_agregar = vista.findViewById( R.id.btn_agregar );
        iv_editar = vista.findViewById( R.id.item_editar );
    }

    private void load_item_list()
    {
        list_items = Proveedor.listAll( Proveedor.class );
        //Log.i("item", String.valueOf(list_items));
        adapter_items = new ProveedoresAdapter( list_items, getContext() , getActivity());
        lv_lista.setAdapter( adapter_items );
    }

    public void open_fragment(Fragment target_fragment)
    {

        getFragmentManager()
        .beginTransaction()
        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
        .replace(R.id.fragment_container, target_fragment)
        .addToBackStack(null)
        .commit();

    }

    private void set_listeners()
    {

        btn_agregar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                open_fragment( new proveedores_form() );
            }
        });


/*
        lv_lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {

                Proveedor item_selected = (Proveedor) adapterView.getItemAtPosition( position );
                proveedores_form next_fragment = new proveedores_form();
                Bundle params = new Bundle();
                params.putString( "id", String.valueOf( item_selected.getId() ) );
                next_fragment.setArguments(params);
                open_fragment( next_fragment );

            }
        });
*/


    }
}
