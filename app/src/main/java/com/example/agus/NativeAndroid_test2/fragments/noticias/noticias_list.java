package com.example.agus.NativeAndroid_test2.fragments.noticias;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.agus.NativeAndroid_test2.Providers.Adapters.Noticias.NoticiasAdapter;
import com.example.agus.NativeAndroid_test2.Providers.Network.CustomObjectRequestProvider;
import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.config.Config;
import com.example.agus.NativeAndroid_test2.entities.Noticia;
import com.example.agus.NativeAndroid_test2.fragments.BaseFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class noticias_list extends BaseFragment
{

    View vista;
    RecyclerView rv_lista;
    ArrayList<Noticia> noticias_lists = new ArrayList<>();

    public noticias_list()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        vista = inflater.inflate( R.layout.fragment_noticias, container, false );
        rv_lista = vista.findViewById( R.id.rv_lista );
        rv_lista.setHasFixedSize( true );
        LinearLayoutManager llm = new LinearLayoutManager( this.getContext() );
        rv_lista.setLayoutManager( llm );

        //if( noticias_lists.size() == 0 )
            make_request();

        return vista;
    }

    private void make_request()
    {
        CustomObjectRequestProvider request = new CustomObjectRequestProvider(getContext());
        String url = "https://www.reddit.com/r/nosleep/.json";

        request.SendRequest(Request.Method.GET, url, null, new CustomObjectRequestProvider.ServerCallback()
        {
            @Override
            public void onResponse(JSONObject reddit_response) throws JSONException
            {
                JSONArray response = reddit_response.getJSONObject("data").getJSONArray("children");
                String titulo = "";
                String resumen = "";
                String imagen_url = "";
                String contenido = "";
                for(int i = 0; i < response.length(); i++)
                {
                    JSONObject post = response.getJSONObject(i).getJSONObject("data");

                    titulo = i+")-"+post.get("title").toString();
                    resumen = contenido = post.get("selftext").toString();
                    imagen_url = "http://lorempixel.com/500/"+(300+i)+"/";

                    if( contenido.length() > 80 )
                        resumen = contenido.substring(0, 80);

                    noticias_lists.add( new Noticia( titulo, resumen, contenido, imagen_url ) );
                }
                NoticiasAdapter adapter = new NoticiasAdapter( noticias_lists, getContext(), getActivity() );
                rv_lista.setAdapter( adapter );
            }
            @Override
            public void onErrorResponse()
            {
                //Helper.hideProgressDialog(pDialog);
                Toast.makeText(getActivity(), Config.NETWORK_ERROR_MSG, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
