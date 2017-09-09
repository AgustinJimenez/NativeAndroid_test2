package com.example.agus.NativeAndroid_test2.fragments.productos.categorias;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.CategoriaProducto;
import com.example.agus.NativeAndroid_test2.fragments.BaseFragment;

import java.util.List;

public class categorias_productos_list extends BaseFragment
{
    ListView lv_lista;
    ArrayAdapter<CategoriaProducto> adapter_items;
    List<CategoriaProducto> list_items;
    Button btn_agregar;
    View vista;
    public categorias_productos_list()
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
        return vista;
    }

    private void set_elements()
    {
        lv_lista = vista.findViewById( R.id.lv_lista );
        btn_agregar = vista.findViewById( R.id.btn_agregar );
    }

    private void load_item_list()
    {
        list_items = CategoriaProducto.listAll( CategoriaProducto.class );
        Log.i("item", String.valueOf(list_items));
        adapter_items = new ArrayAdapter( getActivity(), android.R.layout.simple_list_item_1, list_items);
        lv_lista.setAdapter( adapter_items );
    }

    private void set_listeners()
    {

        btn_agregar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                open_fragment( new categorias_productos_form());
            }
        });



        lv_lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                CategoriaProducto item_selected = (CategoriaProducto) adapterView.getItemAtPosition( position );
                categorias_productos_form next_fragment = new categorias_productos_form();
                Bundle params = new Bundle();
                params.putString( "id", String.valueOf( item_selected.getId() ) );
                next_fragment.setArguments(params);
                open_fragment( next_fragment );

            }
        });




    }






}