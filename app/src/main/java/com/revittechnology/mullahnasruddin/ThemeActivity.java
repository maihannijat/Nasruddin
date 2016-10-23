package com.revittechnology.mullahnasruddin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.revittechnology.mullahnasruddin.theme.CustomSharedPreferences;
import com.revittechnology.mullahnasruddin.theme.ThemeManager;

/**
 * Created by Maihan Nijat on 2016-10-01.
 * Description: The file creates buttons to select and apply style on app.
 * CustomSharedPreferences.java and ThemeManager.java is used to achieve the goal.
 * Last edit: by Maihan Nijat
 */
public class ThemeActivity extends AppCompatActivity implements View.OnClickListener {

    private AdView adView;
    CustomSharedPreferences appPref;

    // Create style buttons
    Button redButton, pinkButton, purpleButton, indigoButton, blueButton, cyanButton, tealButton,
            greenButton, lightGreenButton, orangeButton, deepOrangeButton, brownButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply theme
        appPref = new CustomSharedPreferences(this);
        ThemeManager.applyCustomTheme(this, appPref.getStyle());
        setContentView(R.layout.activity_theme);

        redButton = (Button) findViewById(R.id.button_red);
        pinkButton = (Button) findViewById(R.id.button_pink);
        purpleButton = (Button) findViewById(R.id.button_purple);
        indigoButton = (Button) findViewById(R.id.button_indigo);
        blueButton = (Button) findViewById(R.id.button_blue);
        cyanButton = (Button) findViewById(R.id.button_cyan);
        tealButton = (Button) findViewById(R.id.button_teal);
        greenButton = (Button) findViewById(R.id.button_green);
        lightGreenButton = (Button) findViewById(R.id.button_light_green);
        orangeButton = (Button) findViewById(R.id.button_orange);
        deepOrangeButton = (Button) findViewById(R.id.button_deep_orange);
        brownButton = (Button) findViewById(R.id.button_brown);

        redButton.setOnClickListener(this);
        pinkButton.setOnClickListener(this);
        purpleButton.setOnClickListener(this);
        indigoButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);
        cyanButton.setOnClickListener(this);
        tealButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        lightGreenButton.setOnClickListener(this);
        orangeButton.setOnClickListener(this);
        deepOrangeButton.setOnClickListener(this);
        brownButton.setOnClickListener(this);

        // Banner ad
        AdRequest adRequest = new AdRequest.Builder().build();
        adView = (AdView) findViewById(R.id.adView);
        adView.loadAd(adRequest);

        setupToolbar();

    }

    // Toolbar to call it in onCreate
    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.themes)); // Set custom title
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_red: {
                appPref.setStyle(R.style.RedTheme);
                break;
            }
            case R.id.button_pink: {
                appPref.setStyle(R.style.PinkTheme);
                break;
            }
            case R.id.button_purple: {
                appPref.setStyle(R.style.PurpleTheme);
                break;
            }
            case R.id.button_indigo: {
                appPref.setStyle(R.style.IndigoTheme);
                break;
            }
            case R.id.button_blue: {
                appPref.setStyle(R.style.AppTheme); // default theme
                break;
            }
            case R.id.button_cyan: {
                appPref.setStyle(R.style.CyanTheme);
                break;
            }
            case R.id.button_teal: {
                appPref.setStyle(R.style.TealTheme);
                break;
            }
            case R.id.button_green: {
                appPref.setStyle(R.style.GreenTheme);
                break;
            }
            case R.id.button_light_green: {
                appPref.setStyle(R.style.LightGreenTheme);
                break;
            }
            case R.id.button_orange: {
                appPref.setStyle(R.style.OrangeTheme);
                break;
            }
            case R.id.button_deep_orange: {
                appPref.setStyle(R.style.DeepOrange);
                break;
            }
            case R.id.button_brown: {
                appPref.setStyle(R.style.BrownTheme);
                break;
            }
        }
        finish();
        startActivity(getIntent());
    }
}