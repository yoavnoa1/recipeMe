package com.recipeme.recipeme;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;

public class MainActivity extends AppCompatActivity
{
    private DrawerLayout mDrawerLayout;
    private ListViewOnClickListener listViewOnClickListener = new ListViewOnClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "nnXR6GoxN8TIVrtRzPkIeh9g2AHupgqIxNBOKX0V", "Xv9uWGiFBVB2nkwmq8dxEpfZf3DLGXty0ZV2iiLE");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null)
        {
            setupDrawerContent(navigationView);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main_activty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private <T extends Fragment> void startFragment(T fragment)
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.drawer_layout, fragment)
                .addToBackStack("tag")
                .commit();
    }

    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        switch (menuItem.getItemId())
                        {
                            case R.id.nav_home:
                            {
                                break;
                            }

                            case R.id.nav_messages:
                            {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("Listener", listViewOnClickListener);

                                IngredientFragment fragment = new IngredientFragment();
                                fragment.setArguments(bundle);

                                startFragment(fragment);
                                break;
                            }

                            case R.id.nav_friends:
                            {

                                break;
                            }
                        }

                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
    @Override
    public void onBackPressed()
    {
        if (getSupportFragmentManager().getBackStackEntryCount() != 0)
        {
            getSupportFragmentManager().popBackStack();

        }
        else
        {
            super.onBackPressed();
        }
    }
}
