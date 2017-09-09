package com.example.agus.NativeAndroid_test2.Providers.Network;

import com.example.agus.NativeAndroid_test2.config.Config;

import java.util.Map;

/**
 * Created by agus on 05/09/17.
 */

public class ServerRoutesProvider
{
    public static String getBaseUrl()
    {
        return Config.SERVER_URL;
    }

    public static String getClientesUrl(Map<String, String> params)
    {
        return getBaseUrl() + "clientes" + serialize( params );
    }


    private static String serialize( Map<String, String> params )
    {
        String tmp = "?";

        for( Map.Entry<String, String> param : params.entrySet() )
            tmp += param.getKey() + "=" + param.getValue() + "&";

        return remove_lasts_character( tmp );
    }

    private static String remove_lasts_character( String value )
    {
        return value.substring( 0 , value.length() - 1);
    }
}
