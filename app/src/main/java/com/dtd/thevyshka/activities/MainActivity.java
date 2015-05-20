package com.dtd.thevyshka.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dtd.thevyshka.R;
import com.dtd.thevyshka.fragments.LeftDrawerMenu.ScreenOne;


public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResourceIdentifier() {
        return R.layout.activity_main;
    }
    @Override
    protected String getTitleToolBar() {
        return "MeetUp";
    }

    @Override
    protected boolean getDisplayHomeAsUp() {
        return false;
    }

    @Override
    protected boolean getHomeButtonEnabled() {
        return false;
    }

    private CharSequence mDrawerTitle;
    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;


    private View mLeftDrawerView;
    private View mRightDrawerView;
    private ListView mLeftDrawerList;
    private ListView mRightDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;


    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitle = mDrawerTitle = getTitle();
        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mLeftDrawerView = findViewById(R.id.DrawerRootLeft);
        mRightDrawerView = findViewById(R.id.DrawerRootRight);

        mLeftDrawerList = (ListView) mLeftDrawerView.findViewById(R.id.left_drawer_listview);
        mLeftDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mScreenTitles));
        // Set the list's click listener
        mLeftDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mRightDrawerList = (ListView) mRightDrawerView.findViewById(R.id.right_drawer_listview);
        mRightDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mScreenTitles));//TODO names of campuses

        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };


        // Set the drawer toggle as the DrawerListener
        //mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Initialize the first fragment when the application first loads.
        if (savedInstanceState == null) {
            selectItem(0);
        }



    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ScreenOne();
                break;
            case 1:
                //fragment = new ScreenTwo();
                break;
            case 2:
                //fragment = new ScreenThree();
                break;
            default:
                break;
        }

        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();

            // Highlight the selected item, update the title, and close the drawer
            mLeftDrawerList.setItemChecked(position, true);
            setTitle(mScreenTitles[position]);
            mDrawerLayout.closeDrawer(mLeftDrawerView);
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() { super.onDestroy();}

}
