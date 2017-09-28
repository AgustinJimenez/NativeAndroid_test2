package com.example.agus.NativeAndroid_test2;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.agus.NativeAndroid_test2.Providers.KeyboardProvider;
import com.example.agus.NativeAndroid_test2.fragments.BaseFragment;
import com.example.agus.NativeAndroid_test2.fragments.clientes.cliente_list;
import com.example.agus.NativeAndroid_test2.fragments.empleados.empleados_list;
import com.example.agus.NativeAndroid_test2.fragments.noticias.noticias_list;
import com.example.agus.NativeAndroid_test2.fragments.productos.categorias.categorias_productos_list;
import com.example.agus.NativeAndroid_test2.fragments.proveedores.proveedores_list;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BaseFragment.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        KeyboardProvider.hideKeyboard( this );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        KeyboardProvider.hideKeyboard( this );
        int id = item.getItemId();

        Fragment fragment = null;
        boolean fragment_transaction = false;

        if (id == R.id.nav_clientes)
        {
            fragment = new cliente_list();
            fragment_transaction = true;
        }
        else if (id == R.id.nav_proveedores)
        {
            fragment = new proveedores_list();
            fragment_transaction = true;
        }
        else if (id == R.id.nav_categorias_productos)
        {
            fragment = new categorias_productos_list();
            fragment_transaction = true;
        }
        else if (id == R.id.nav_empleados)
        {
            fragment = new  empleados_list();
            fragment_transaction = true;
        }
        else if (id == R.id.nav_noticias)
        {
            fragment = new noticias_list();
            fragment_transaction = true;
        }


        if( fragment_transaction )
        {
            getSupportFragmentManager()
            .beginTransaction()
            .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left)
            .replace(R.id.fragment_container, fragment)
            .commit();


            item.setChecked( true );
            getSupportActionBar()
                    .setTitle( item.getTitle() );
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }

}
