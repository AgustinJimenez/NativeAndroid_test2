package com.example.agus.NativeAndroid_test2.fragments.productos.categorias;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.CategoriaProducto;
import com.example.agus.NativeAndroid_test2.fragments.BaseFragment;


public class categorias_productos_form extends BaseFragment
{
    Button btn_guardar, btn_eliminar;
    EditText et_nombre, et_descripcion;
    CategoriaProducto item = null;
    Bundle params;
    View vista;

    public categorias_productos_form()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        vista = inflater.inflate(R.layout.fragment_categorias_productos_form, container, false);
        set_elements();
        set_receives_datas();
        set_listeners();
        return vista;
    }

    private void set_elements()
    {
        btn_guardar = vista.findViewById( R.id.btn_guardar );
        btn_eliminar = vista.findViewById( R.id.btn_eliminar );

        et_nombre = vista.findViewById( R.id.et_nombre );
        et_descripcion = vista.findViewById( R.id.et_descripcion );

        et_nombre.requestFocus();

    }


    private void set_item(String id)
    {
        item = CategoriaProducto.findById( CategoriaProducto.class, Long.valueOf(id) );

        et_nombre.setText( item.nombre );
        et_descripcion.setText( item.descripcion );
    }

    private void actualizar()
    {
        item.nombre = et_nombre.getText().toString();
        item.descripcion = et_descripcion.getText().toString();
    }

    private void crear()
    {
        item = new CategoriaProducto
                (
                        et_nombre.getText().toString(),
                        et_descripcion.getText().toString()
                );
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
        set_item( params.getString("id") );
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

    private boolean validate_form()
    {
        boolean valid = true;
        if( et_nombre.getText().toString().trim().equals(""))
        {
            et_nombre.setError("El nombre es requerido");
            valid = false;
        }

        return valid;
    }

    private void guardar()
    {
        if( validate_form() )
        {
            if (item == null)
                crear();
            else
                actualizar();

            item.save();
            back();
        }
    }

    private void eliminar()
    {
        item.delete();
        back();
    }





}
