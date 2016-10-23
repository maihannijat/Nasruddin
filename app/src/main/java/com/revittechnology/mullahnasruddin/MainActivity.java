package com.revittechnology.mullahnasruddin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.revittechnology.mullahnasruddin.theme.CustomSharedPreferences;
import com.revittechnology.mullahnasruddin.theme.ThemeManager;

/**
 * Created by Maihan Nijat on 2016-10-01.
 * Description: MainActivity, the launcher file
 * Last edit: by Maihan Nijat
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Button pashtoButton, farsiButton, englishButton;
    Intent intent;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply Theme
        CustomSharedPreferences appPref = new CustomSharedPreferences(this);
        ThemeManager.applyCustomTheme(this, appPref.getStyle());

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Banner ad.
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        pashtoButton = (Button) findViewById(R.id.button_pashto);
        farsiButton = (Button) findViewById(R.id.button_farsi);
        englishButton = (Button) findViewById(R.id.button_english);

        pashtoButton.setOnClickListener(this);
        farsiButton.setOnClickListener(this);
        englishButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        intent = new Intent(getApplicationContext(), JokeActivity.class);
        switch (v.getId()) {
            case R.id.button_pashto:
                intent.putExtra("cat", "pa");
                break;
            case R.id.button_farsi:
                intent.putExtra("cat", "fa");
                break;
            case R.id.button_english:
                intent.putExtra("cat", "en");
                break;
            default:
        }
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (id == R.id.nav_fav) {
            intent = new Intent(getApplicationContext(), JokeActivity.class);
            intent.putExtra("cat", "fav"); // using the value in JokeActivity.java method
        } else if (id == R.id.nav_themes) {
            intent = new Intent(getApplicationContext(), ThemeActivity.class);
        } else if (id == R.id.nav_about) {
            intent = new Intent(getApplicationContext(), AboutActivity.class);
        } else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Download the " + getResources().getString(R.string.app_name) + " Android App: ");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
        } else if (id == R.id.nav_rate) {
            intent.setData(Uri.parse("market://details?id=" + getPackageName()));
        } else if (id == R.id.nav_more) {
            intent.setData(Uri.parse("market://search?q=pub:Maihan+Nijat"));
        }
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
