package com.example.agus.NativeAndroid_test2.fragments.clientes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.Cliente;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link cliente_form.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class cliente_form extends Fragment
{

    private OnFragmentInteractionListener mListener;

    public cliente_form() {}

    Button btn_guardar, btn_eliminar;
    EditText et_razon_social, et_cedula;
    Cliente cliente = null;
    Bundle params;
    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        vista = inflater.inflate(R.layout.fragment_cliente_form, container, false);
        set_elements();
        set_receives_datas();
        set_listeners();

        return vista;
    }

    private void set_elements()
    {
        btn_guardar = (Button) vista.findViewById( R.id.btn_guardar );
        btn_eliminar = (Button) vista.findViewById( R.id.btn_eliminar );
        et_razon_social = (EditText) vista.findViewById( R.id.et_razon_social );
        et_cedula = (EditText) vista.findViewById( R.id.et_cedula );
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
        finish();
    }

    private void eliminar()
    {
        cliente.delete();
        finish();
    }

    private void finish()
    {
        getActivity().onBackPressed();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
