package com.example.agus.NativeAndroid_test2.Providers;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.example.agus.NativeAndroid_test2.R;
import com.squareup.picasso.Picasso;

/**
 * Created by agus on 27/09/17.
 */

public class PicassoProvider
{

    public static ImageView load_image_from_url(String url, Context ctx, ImageView img)
    {
        Picasso.with( ctx )
        .load( url )
        .placeholder( R.drawable.ic_launcher )//optional
        .error( R.drawable.ic_launcher )//if error
        .into(img, new com.squareup.picasso.Callback()
        {

            @Override
            public void onSuccess()
            {

            }

            @Override
            public void onError()
            {

            }

        });

        return img;
    }


}
