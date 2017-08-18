package com.example.agus.nativeandroid_test2.entities;


import com.orm.SugarRecord;

/**
 * Created by agus on 16/08/17.
 */

public class Cliente extends SugarRecord<Cliente>
{

    String razon_social;
    String cedula;

    public Cliente(  )
    {
    }

    public Cliente( String razon_social, String cedula )
    {
        this.razon_social = razon_social;
        this.cedula = cedula;
    }

    @Override
    public String toString()
    {
        return razon_social + " - " + cedula;
    }

}
