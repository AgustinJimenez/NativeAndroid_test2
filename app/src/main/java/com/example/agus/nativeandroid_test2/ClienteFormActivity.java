package com.example.agus.nativeandroid_test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.agus.nativeandroid_test2.entities.Cliente;

import java.util.List;

public class ClienteFormActivity extends AppCompatActivity
{
    Button btn_guardar, btn_eliminar;
    EditText et_razon_social, et_cedula;
    Cliente cliente = null;
    Bundle params;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_form);

        set_elements();
        set_receives_datas();
        set_listeners();

    }

    public void set_receives_datas()
    {
        params = getIntent().getExtras();

        if( params != null )
            data_exist();
        else
            data_not_exist();
    }
    public void data_not_exist()
    {
        btn_eliminar.setVisibility( View.INVISIBLE );
    }

    public void data_exist()
    {
        set_cliente( params.getString("id") );

    }

    public void set_cliente(String id)
    {
        cliente = Cliente.findById( Cliente.class, Long.valueOf(id) );

        et_razon_social.setText( cliente.razon_social );
        et_cedula.setText( cliente.cedula );
    }

    public void set_elements()
    {
        btn_guardar = (Button) findViewById( R.id.btn_guardar );
        btn_eliminar = (Button) findViewById( R.id.btn_eliminar );
        et_razon_social = (EditText) findViewById( R.id.et_razon_social );
        et_cedula = (EditText) findViewById( R.id.et_cedula );
    }

    public void finish()
    {
        startActivity( new Intent( this, MainActivity.class ) );
    }


    public void set_listeners()
    {
        btn_guardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                guardar();
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                eliminar();
            }
        });
    }

    public void actualizar()
    {
        this.cliente.razon_social = et_razon_social.getText().toString();
        this.cliente.cedula = et_cedula.getText().toString();
    }

    public void crear()
    {
        cliente = new Cliente( et_razon_social.getText().toString(), et_cedula.getText().toString() );
    }

    public void guardar()
    {
        if( cliente == null )
            crear();
        else
            actualizar();

        cliente.save();
        finish();
    }

    public void eliminar()
    {
        cliente.delete();
        finish();
    }

}
