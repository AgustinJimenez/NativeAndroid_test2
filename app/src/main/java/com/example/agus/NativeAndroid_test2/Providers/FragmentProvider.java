package com.example.agus.NativeAndroid_test2.Providers;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.agus.NativeAndroid_test2.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by agus on 01/09/17.
 */

public class FragmentProvider extends Fragment
{
    /*
    private static FragmentProvider instance = null;
    public FragmentProvider() {}

    public static FragmentProvider getInstance()
    {
        if(instance == null)
            instance = new FragmentProvider();
        return instance;
    }
    */

    public void call_fragment(Fragment fragment_target, boolean backstack)
    {
        getFragmentManager()
        .beginTransaction()
        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
        .replace(R.id.fragment_container, fragment_target)
        .addToBackStack(null)
        .commit();
    }
}
