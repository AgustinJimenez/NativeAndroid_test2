package com.example.agus.NativeAndroid_test2.fragments.noticias;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agus.NativeAndroid_test2.Providers.PicassoProvider;
import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.Noticia;
import com.example.agus.NativeAndroid_test2.fragments.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class noticias_read extends BaseFragment
{
    View vista;
    Bundle params;
    String titulo, contenido, imagen_url = "";
    TextView tv_titulo, tv_contenido;
    ImageView iv_imagen;
    public noticias_read()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        vista = inflater.inflate(R.layout.fragment_noticias_read, container, false);

        set_elements();
        return vista;
    }

    private void set_elements()
    {
        tv_titulo = vista.findViewById( R.id.tv_titulo );
        tv_contenido = vista.findViewById( R.id.tv_contenido );
        iv_imagen = vista.findViewById( R.id.iv_noticia_imagen );

        params = getArguments();

        titulo = params.getString("titulo");
        contenido = params.getString("contenido");
        imagen_url = params.getString("imagen_url");

        tv_titulo.setText( titulo );
        tv_contenido.setText( contenido );
        iv_imagen = PicassoProvider.load_image_from_url( imagen_url, this.getContext() ,iv_imagen );
    }
}
