package com.example.agus.NativeAndroid_test2.entities;

import com.example.agus.NativeAndroid_test2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agus on 23/09/17.
 */

public class Noticia
{

    public String titulo = "";
    public String resumen = "";
    public String contenido = "";
    public String imagen_url = "";

    public Noticia(String titulo, String resumen, String contenido, String imagen)
    {
        this.titulo = titulo;
        this.resumen = resumen;
        this.imagen_url = imagen;
        this.contenido = contenido;
    }

    public String toString()
    {
        return "titulo="+titulo+" resumen="+resumen+" imagen="+imagen_url;
    }
}
