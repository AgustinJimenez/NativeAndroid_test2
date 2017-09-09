package com.example.agus.NativeAndroid_test2.fragments.clientes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.agus.NativeAndroid_test2.Providers.KeyboardProvider;
import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.Cliente;
import com.example.agus.NativeAndroid_test2.fragments.BaseFragment;

public class cliente_form extends BaseFragment
{

    public cliente_form() {}

    Button btn_guardar, btn_eliminar;
    EditText et_razon_social, et_cedula;
    Cliente cliente = null;
    Bundle params;
    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {

        vista = inflater.inflate(R.layout.fragment_cliente_form, container, false);
        set_elements();
        set_receives_datas();
        set_listeners();

        return vista;
    }

    private void set_elements()
    {
        btn_guardar = vista.findViewById( R.id.btn_guardar );
        btn_eliminar = vista.findViewById( R.id.btn_eliminar );
        et_razon_social = vista.findViewById( R.id.et_razon_social );
        et_cedula = vista.findViewById( R.id.et_cedula );

        et_razon_social.requestFocus();
        KeyboardProvider.showKeyboard( getContext() );
    }

    private void set_receives_datas()
    {
        params = getArguments();

        if( params != null )
            data_exist();
        else
            data_not_exist();

    }
    private void data_not_exist()
    {
        btn_eliminar.setVisibility( View.INVISIBLE );
    }

    private void data_exist()
    {
        set_cliente( params.getString("id") );
    }

    private void set_cliente(String id)
    {
        cliente = Cliente.findById( Cliente.class, Long.valueOf(id) );

        et_razon_social.setText( cliente.razon_social );
        et_cedula.setText( cliente.cedula );
    }


    private void set_listeners()
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

    private void actualizar()
    {
        this.cliente.razon_social = et_razon_social.getText().toString();
        this.cliente.cedula = et_cedula.getText().toString();
    }

    private void crear()
    {
        cliente = new Cliente( et_razon_social.getText().toString(), et_cedula.getText().toString() );
    }

    private void guardar()
    {
        if( cliente == null )
            crear();
        else
            actualizar();

        cliente.save();
        back();
    }

    private void eliminar()
    {
        cliente.delete();
        back();
    }

}
