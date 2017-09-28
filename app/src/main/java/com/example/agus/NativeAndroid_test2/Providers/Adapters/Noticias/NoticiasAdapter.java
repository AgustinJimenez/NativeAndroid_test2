package com.example.agus.NativeAndroid_test2.Providers.Adapters.Noticias;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agus.NativeAndroid_test2.Providers.PicassoProvider;
import com.example.agus.NativeAndroid_test2.R;
import com.example.agus.NativeAndroid_test2.entities.Noticia;
import com.example.agus.NativeAndroid_test2.fragments.noticias.noticias_read;
import com.example.agus.NativeAndroid_test2.fragments.proveedores.proveedores_form;

import java.util.List;

/**
 * Created by agus on 23/09/17.
 */

public class NoticiasAdapter extends RecyclerView.Adapter< NoticiasAdapter.NoticiasViewHolder >
{
    List<Noticia> elements;
    Context ctx;
    View vista;
    FragmentActivity fragAct;

    public NoticiasAdapter(List<Noticia> elements, Context ctx, FragmentActivity act)
    {
        this.elements = elements;
        this.ctx = ctx;
        this.fragAct = act;
    }

    public class NoticiasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        CardView cv;
        ImageView noticia_imagen;
        TextView noticia_titulo;
        TextView noticia_resumen;

        NoticiasViewHolder(View itemView)
        {
            super(itemView);
            cv = itemView.findViewById( R.id.cv_noticias );
            noticia_imagen = itemView.findViewById( R.id.iv_imagen );
            noticia_titulo = itemView.findViewById( R.id.tv_titulo );
            noticia_resumen = itemView.findViewById( R.id.tv_resumen );
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Noticia noticia = elements.get( getAdapterPosition() );
            noticias_read next_fragment = new noticias_read();
            Bundle params = new Bundle();
            params.putString( "titulo", noticia.titulo );
            params.putString( "contenido", noticia.contenido );
            params.putString( "imagen_url", noticia.imagen_url );
            next_fragment.setArguments(params);

            fragAct
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.fragment_container, next_fragment)
                .addToBackStack(null)
                .commit();
        }
    }

    @Override
    public NoticiasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        vista = LayoutInflater.from( viewGroup.getContext() ).inflate(R.layout.card_view_noticias, viewGroup, false);
        NoticiasViewHolder nvh = new NoticiasViewHolder( vista );
        return nvh;
    }


    @Override
    public void onBindViewHolder(NoticiasViewHolder noticiasViewHolder, int i)
    {
        noticiasViewHolder.noticia_titulo.setText( elements.get(i).titulo );
        noticiasViewHolder.noticia_resumen.setText( elements.get(i).resumen );
        noticiasViewHolder.noticia_imagen = PicassoProvider.load_image_from_url(elements.get(i).imagen_url, ctx, noticiasViewHolder.noticia_imagen);
    }


    @Override
    public int getItemCount()
    {
        if( elements == null )
            return 0;
        else
            return elements.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
