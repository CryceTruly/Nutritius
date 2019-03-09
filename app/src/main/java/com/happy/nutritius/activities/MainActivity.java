package com.happy.nutritius.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.happy.nutritius.R;
import com.happy.nutritius.SharedPrefManager;
import com.happy.nutritius.fragments.AboutFragment;
import com.happy.nutritius.fragments.FoodsFragment;
import com.happy.nutritius.fragments.FoodsToAvoidFragment;
import com.happy.nutritius.fragments.LogoutFragment;
import com.happy.nutritius.fragments.MapsFragment;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private Context mContext;
        private TextView name;
        private FragmentManager fragmentManager;
        private FragmentTransaction fragmentTransaction;
        ConstraintLayout main;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initApp();

        displayFragment(new FoodsFragment(),"Foods","Foods and Nutrients");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.maps) {
//            // Handle the camera action
//            displayFragment(new MapsFragment(),"Health Centers","Where to get Help");
            startActivity(new Intent(getBaseContext(),MapsActivity.class));
        } else if (id == R.id.about) {

            displayFragment(new AboutFragment(),"About","About App");

        } else if (id == R.id.logout) {
            displayFragment(new LogoutFragment(),"Auth","Logout");
        } else if (id == R.id.foods) {

            displayFragment(new FoodsFragment(),"Foods","Foods and Nutrients");
        
    } else if (id == R.id.foodstoavoid) {
        displayFragment(new FoodsToAvoidFragment(),"FoodsToAvoid","Please avoid these");
    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayFragment(Fragment fragment, String title, String subtitle) {
        Log.d(TAG, "displayFragment: setting data to ui");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subtitle);
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main,fragment);
        fragmentTransaction.commit();

    }

    private void initApp() {
        mContext=this;
        main=findViewById(R.id.main);
        if(!SharedPrefManager.isLoggedIn(mContext))
            startActivity(new Intent(mContext,LoginActivity.class));

    }
}
