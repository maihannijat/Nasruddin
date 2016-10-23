package com.revittechnology.mullahnasruddin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.revittechnology.mullahnasruddin.theme.CustomSharedPreferences;
import com.revittechnology.mullahnasruddin.theme.ThemeManager;
/**
 * Created by Maihan Nijat on 2016-10-01.
 * Description: Display about us text on the page. The text is placed in string.xml and displayed through layout.
 * Last edit: by Maihan Nijat
 */
public class AboutActivity extends AppCompatActivity {

    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply Theme
        CustomSharedPreferences appPref = new CustomSharedPreferences(this);
        ThemeManager.applyCustomTheme(this, appPref.getStyle());
        setContentView(R.layout.activity_about);

        // Interstitial Ad
        interstitialAd = new InterstitialAd(this);
        // set the ad unit ID
        interstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest = new AdRequest.Builder().build();
        // Load ads into Interstitial Ads
        interstitialAd.loadAd(adRequest);

        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }
        });

        // call toolbar
        setupToolbar();
    }

    // Toolbar
    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.about_us));
    }

    public void displayInterstitial() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
}