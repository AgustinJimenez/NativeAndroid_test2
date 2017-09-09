package com.example.agus.NativeAndroid_test2.fragments.empleados;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.agus.NativeAndroid_test2.Providers.Adapters.Empleados.EmpleadosAdapter;
import com.example.agus.NativeAndroid_test2.Providers.Adapters.Empleados.Proveedor;
import com.example.agus.NativeAndroid_test2.Providers.Network.CustomObjectRequestProvider;
import com.example.agus.NativeAndroid_test2.Providers.Network.ServerRoutesProvider;
import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.config.Config;
import com.example.agus.NativeAndroid_test2.fragments.BaseFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class empleados_list extends BaseFragment
{
    View vista;
    ListView lv_empleados;
    ArrayList<Proveedor> empleados_list = new ArrayList<>();
    private static EmpleadosAdapter adapter;

    //http://lorempixel.com/200/200/
    public empleados_list(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        vista = inflater.inflate(R.layout.fragment_empleados_list, container, false);

        lv_empleados = vista.findViewById(R.id.lv_empleados);

        empleados_list.add(new Proveedor("Apple Pie", "Android 1.0", "1","September 23, 2008"));
        empleados_list.add(new Proveedor("Banana Bread", "Android 1.1", "2","February 9, 2009"));
        empleados_list.add(new Proveedor("Cupcake", "Android 1.5", "3","April 27, 2009"));
        empleados_list.add(new Proveedor("Donut","Android 1.6","4","September 15, 2009"));
        empleados_list.add(new Proveedor("Eclair", "Android 2.0", "5","October 26, 2009"));
        empleados_list.add(new Proveedor("Froyo", "Android 2.2", "8","May 20, 2010"));
        empleados_list.add(new Proveedor("Gingerbread", "Android 2.3", "9","December 6, 2010"));
        empleados_list.add(new Proveedor("Honeycomb","Android 3.0","11","February 22, 2011"));
        empleados_list.add(new Proveedor("Ice Cream Sandwich", "Android 4.0", "14","October 18, 2011"));
        empleados_list.add(new Proveedor("Jelly Bean", "Android 4.2", "16","July 9, 2012"));
        empleados_list.add(new Proveedor("Kitkat", "Android 4.4", "19","October 31, 2013"));
        empleados_list.add(new Proveedor("Lollipop","Android 5.0","21","November 12, 2014"));
        empleados_list.add(new Proveedor("Marshmallow", "Android 6.0", "23","October 5, 2015"));

        adapter = new EmpleadosAdapter( empleados_list, getContext());
        lv_empleados.setAdapter( adapter );


        lv_empleados.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Proveedor dataModel = empleados_list.get( position );

                Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });

















        Map<String, String> params = new HashMap<String, String>();

        params.put("api_token", "12345");
        String url = ServerRoutesProvider.getClientesUrl( params );
        CustomObjectRequestProvider request = new CustomObjectRequestProvider(getContext());
        request.SendRequest(Request.Method.GET, url, null, new CustomObjectRequestProvider.ServerCallback()
        {
            @Override
            public void onResponse(JSONObject response) throws JSONException
            {
                //Helper.hideProgressDialog(pDialog);
                if (response.has("status") && response.getString("status").equals( "OK" ) )
                {
                    Log.d("==================", "RETRIEVING DATAS==================");
                    JSONArray clientes = response.getJSONArray("clientes");
                    for(int i = 0; i < clientes.length(); i++)
                    {
                        JSONObject cliente = clientes.getJSONObject(i);
                        Log.d("cliente", String.valueOf( cliente.get("id")+" - "+cliente.get("razon_social") ));
                    }
                    Log.d("==================", "ENDRETRIEVING DATAS==================");
                    //JSONArray visitas = response.getJSONArray("visitas");
                    //JSONArray fechas = response.getJSONArray("fechas");
                    //fecha_actual = response.getString("fecha_actual");
                    //listJsonDataVisitas(visitas, fechas, operation);
                }
                else
                    Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                //checkForUpdates();
            }
            @Override
            public void onErrorResponse()
            {
                //Helper.hideProgressDialog(pDialog);
                Toast.makeText(getActivity(), Config.NETWORK_ERROR_MSG, Toast.LENGTH_SHORT).show();
            }
        });

        ServerRoutesProvider.getClientesUrl( params );

        //Log.d("===================", String.valueOf(clientes) );








        return vista;
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
