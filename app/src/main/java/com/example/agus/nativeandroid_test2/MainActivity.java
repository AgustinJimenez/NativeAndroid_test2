package com.example.agus.nativeandroid_test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.agus.nativeandroid_test2.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    ListView lv_clientes;
    ArrayAdapter<Cliente> adapter_client;
    List<Cliente> list_client;
    Button btn_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        set_items();
        load_client_list();
        set_listeners();
    }

    private void set_items()
    {
        lv_clientes = (ListView) findViewById( R.id.lv_clientes );
        btn_agregar = (Button) findViewById( R.id.btn_agregar );
    }

    private void load_client_list()
    {
        list_client = Cliente.listAll( Cliente.class );
        Log.d( "console-log", list_client.toString() );
        adapter_client = new ArrayAdapter<Cliente>( this, android.R.layout.simple_list_item_1, list_client );
        lv_clientes.setAdapter( adapter_client );

    }



    private void set_listeners()
    {
        btn_agregar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent_agregar = new Intent( MainActivity.this, ClienteFormActivity.class );
                startActivity( intent_agregar );
            }
        });





    }
}
