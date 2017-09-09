package com.example.agus.NativeAndroid_test2.Providers.Network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by agus on 05/09/17.
 */

public class VolleyRequestQueueProvider
{
    private static VolleyRequestQueueProvider singleton_instance;
    private RequestQueue request_queue;
    private static Context ctx;

    private VolleyRequestQueueProvider(Context ctx )
    {
        this.ctx = ctx;
        request_queue = getRequestQueue();
    }

    public RequestQueue getRequestQueue()
    {
        if( request_queue == null )
            request_queue = Volley.newRequestQueue( ctx.getApplicationContext() );

        return request_queue;
    }

    public static synchronized VolleyRequestQueueProvider getInstance(Context ctx )
    {
        if( singleton_instance == null )
            singleton_instance = new VolleyRequestQueueProvider( ctx );

        return singleton_instance;
    }

    public <T> void addToRequestqueue(Request<T> request)
    {
        request_queue.add( request );
    }



}
