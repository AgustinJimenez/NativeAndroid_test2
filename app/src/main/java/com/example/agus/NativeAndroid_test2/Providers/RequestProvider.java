package com.example.agus.NativeAndroid_test2.Providers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.agus.NativeAndroid_test2.Providers.Network.VolleyRequestQueueProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by agus on 04/09/17.
 */

public class RequestProvider
{



    public static JSONObject get(Context context, String url)
    {
        final JSONObject array_response = new JSONObject();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONArray array = new JSONArray( response );
                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject element_json = array.getJSONObject(i);
                        Log.d("++++++++++++++", String.valueOf(element_json) );
                        array_response.put( element_json.getString("id"), element_json );
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("===============", String.valueOf(error.getCause()));
            }
        });
        VolleyRequestQueueProvider.getInstance( context ).addToRequestqueue( stringRequest );
        return array_response;

    }

}
