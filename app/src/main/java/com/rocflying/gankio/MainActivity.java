package com.rocflying.gankio;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.rocflying.gankio.entity.Article;
import com.rocflying.gankio.tab.GankTabCommon;
import com.rocflying.gankio.tab.GankTabFuli;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FrameLayout content;
    private GankTabCommon tabAll;
    private GankTabCommon tabAndroid;
    private GankTabCommon tabiOs;
    private GankTabCommon tabWeb;
    private GankTabCommon tabRecommend;
    private GankTabCommon tabVideo;
    private GankTabCommon tabElse;
    private GankTabCommon tabApp;
    private GankTabCommon tabFuli;
    private GankTabCommon currentTab;
    private FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intitView();
        initData();
    }

    private void initData() {
        if (tabAll == null) {
            tabAll = new GankTabCommon(this, Article.allType);
        }
        currentTab = tabAll;
        content.addView(tabAll);
    }

    private void intitView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        content = (FrameLayout) findViewById(R.id.content);
        actionButton = (FloatingActionButton) findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTab.getRecyclerView().scrollToPosition(0);
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        updateNavView();
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            if (tabAll == null) {
                tabAll = new GankTabCommon(this, Article.allType);
            }
            currentTab = tabAll;
            content.addView(tabAll);

        } else if (id == R.id.nav_gallery) {

            if (tabFuli == null) {
                tabFuli = new GankTabFuli(this, Article.fuliType);
            }
            currentTab = tabFuli;
            content.addView(tabFuli);

        } else if (id == R.id.nav_slideshow) {
            if (tabAndroid == null) {
                tabAndroid = new GankTabCommon(this, Article.androidType);
            }
            currentTab = tabAndroid;
            content.addView(tabAndroid);

        } else if (id == R.id.nav_manage) {
            if (tabiOs == null) {
                tabiOs = new GankTabCommon(this, Article.iosType);
            }
            currentTab = tabiOs;
            content.addView(tabiOs);

        } else if (id == R.id.nav_manage2) {
            if (tabWeb == null) {
                tabWeb = new GankTabCommon(this, Article.webType);
            }
            currentTab = tabWeb;
            content.addView(tabWeb);

        } else if (id == R.id.nav_manage3) {
            if (tabRecommend == null) {
                tabRecommend = new GankTabCommon(this, Article.recommendType);
            }
            currentTab = tabRecommend;
            content.addView(tabRecommend);


        } else if (id == R.id.nav_manage4) {
            if (tabVideo == null) {
                tabVideo = new GankTabCommon(this, Article.videoType);
            }
            currentTab = tabVideo;
            content.addView(tabVideo);
        } else if (id == R.id.nav_manage5) {
            if (tabElse == null) {
                tabElse = new GankTabCommon(this, Article.elseType);
            }
            currentTab = tabElse;
            content.addView(tabElse);

        } else if (id == R.id.nav_manage6) {
            if (tabApp == null) {
                tabApp = new GankTabCommon(this, Article.appType);
            }
            currentTab = tabApp;
            content.addView(tabApp);

        } else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateNavView() {
        if (content != null && content.getChildCount() > 0) {
            content.removeAllViews();
            tabAll = null;
        }
    }
}
