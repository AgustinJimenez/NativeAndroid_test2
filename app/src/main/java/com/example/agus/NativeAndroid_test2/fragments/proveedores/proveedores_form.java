package com.example.agus.NativeAndroid_test2.fragments.proveedores;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.agus.NativeAndroid_test2.Providers.KeyboardProvider;
import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.Proveedor;
import com.example.agus.NativeAndroid_test2.fragments.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link proveedores_form.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class proveedores_form extends BaseFragment
{

    Button btn_guardar, btn_eliminar;
    EditText et_razon_social, et_ruc, et_categoria, et_direccion, et_email, et_telefono, et_celular, et_fax, et_contacto;
    Proveedor item = null;
    Bundle params;
    View vista;

    public proveedores_form() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        vista = inflater.inflate(R.layout.fragment_proveedores_form, container, false);
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
        et_ruc = vista.findViewById( R.id.et_ruc );
        et_categoria = vista.findViewById( R.id.et_categoria );
        et_direccion = vista.findViewById( R.id.et_direccion );
        et_email = vista.findViewById( R.id.et_email );
        et_telefono = vista.findViewById( R.id.et_telefono );
        et_celular = vista.findViewById( R.id.et_celular );
        et_fax = vista.findViewById( R.id.et_fax );
        et_contacto = vista.findViewById( R.id.et_contacto );

        et_razon_social.requestFocus();
        KeyboardProvider.showKeyboard( getContext() );
        et_telefono.setKeyListener(new DigitsKeyListener());
        et_celular.setKeyListener(new DigitsKeyListener());

    }


    private void set_item(String id)
    {
        item = Proveedor.findById( Proveedor.class, Long.valueOf(id) );

        et_razon_social.setText( item.razon_social );
        et_ruc.setText( item.ruc );
        et_categoria.setText( item.categoria );
        et_direccion.setText( item.direccion );
        et_email.setText( item.email );
        et_telefono.setText( item.telefono );
        et_celular.setText( item.celular );
        et_fax.setText( item.fax );
        et_contacto.setText( item.contacto );
    }

    private void actualizar()
    {
        item.razon_social = et_razon_social.getText().toString();
        item.ruc = et_ruc.getText().toString();
        item.categoria = et_categoria.getText().toString();
        item.direccion = et_direccion.getText().toString();
        item.email = et_email.getText().toString();
        item.telefono = et_telefono.getText().toString();
        item.celular = et_celular.getText().toString();
        item.fax = et_fax.getText().toString();
        item.contacto = et_contacto.getText().toString();
    }

    private void crear()
    {
        item = new Proveedor
        (
            et_razon_social.getText().toString(),
            et_ruc.getText().toString(),
            et_categoria.getText().toString(),
            et_direccion.getText().toString(),
            et_email.getText().toString(),
            et_telefono.getText().toString(),
            et_celular.getText().toString(),
            et_fax.getText().toString(),
            et_contacto.getText().toString()
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
        if( et_razon_social.getText().toString().trim().equals(""))
        {
            et_razon_social.setError("La Razon social es requerida");
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
