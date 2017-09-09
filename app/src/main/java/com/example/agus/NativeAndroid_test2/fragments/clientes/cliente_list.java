package com.example.agus.NativeAndroid_test2.fragments.clientes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.Cliente;

import java.util.List;

public class cliente_list extends Fragment
{
    public cliente_list()
    {
        // Required empty public constructor
    }
    ListView lv_clientes;
    ArrayAdapter<Cliente> adapter_client;
    List<Cliente> list_client;
    Button btn_agregar;
    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        vista = inflater.inflate( R.layout.fragment_cliente_list, container, false );

        set_elements();
        load_client_list();
        set_listeners();
        return vista;
    }

    private void set_elements()
    {
        lv_clientes = (ListView) vista.findViewById( R.id.lv_clientes );
        btn_agregar = (Button) vista.findViewById( R.id.btn_agregar );
    }

    private void load_client_list()
    {
        list_client = Cliente.listAll( Cliente.class );
        adapter_client = new ArrayAdapter<Cliente>( getActivity(), android.R.layout.simple_list_item_1, list_client );
        lv_clientes.setAdapter( adapter_client );
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
                open_fragment( new cliente_form() );
            }
        });

        lv_clientes.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                Cliente cliente = (Cliente) adapterView.getItemAtPosition( position );
                cliente_form next_fragment = new cliente_form();
                Bundle params = new Bundle();
                params.putString("id", String.valueOf(cliente.getId()));
                next_fragment.setArguments(params);
                open_fragment( next_fragment );
            }
        });


    }

}
