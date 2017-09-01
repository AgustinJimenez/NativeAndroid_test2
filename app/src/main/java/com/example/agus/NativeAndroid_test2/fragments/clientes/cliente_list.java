package com.example.agus.NativeAndroid_test2.fragments.clientes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.agus.NativeAndroid_test2.Providers.FragmentProvider;
import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.Cliente;
import com.example.agus.NativeAndroid_test2.fragments.proveedores.proveedores_form;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link cliente_list.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link cliente_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cliente_list extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public cliente_list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cliente_list.
     */
    // TODO: Rename and change types and number of parameters
    public static cliente_list newInstance(String param1, String param2)
    {
        cliente_list fragment = new cliente_list();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
                //FragmentProvider.getInstance().call_fragment( new cliente_form(), true );
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
               // FragmentProvider.getInstance().call_fragment( next_fragment, true );
            }
        });


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
