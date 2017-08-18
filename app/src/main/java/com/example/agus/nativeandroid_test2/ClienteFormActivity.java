package com.example.agus.nativeandroid_test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.agus.nativeandroid_test2.entities.Cliente;

import java.util.List;

public class ClienteFormActivity extends AppCompatActivity
{
    Button btn_guardar;
    EditText et_razon_social, et_cedula;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_form);

        btn_guardar = (Button) findViewById( R.id.btn_guardar );
        et_razon_social = (EditText) findViewById( R.id.et_razon_social );
        et_cedula = (EditText) findViewById( R.id.et_cedula );

        btn_guardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                guardar();
            }
        });

    }

    public void guardar()
    {
        cliente = new Cliente( et_razon_social.getText().toString(), et_cedula.getText().toString() );
        cliente.save();
        startActivity( new Intent( this, MainActivity.class ) );
    }

}
