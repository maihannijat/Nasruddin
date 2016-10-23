package com.revittechnology.mullahnasruddin;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.revittechnology.mullahnasruddin.adapter.ItemListBaseAdapter;
import com.revittechnology.mullahnasruddin.data.DatabaseManager;
import com.revittechnology.mullahnasruddin.model.Joke;
import com.revittechnology.mullahnasruddin.theme.ThemeManager;
import com.revittechnology.mullahnasruddin.theme.CustomSharedPreferences;

import java.util.ArrayList;

/**
 * Created by Maihan Nijat on 2016-10-01.
 * Description: Display all jokes in the item list.
 * This is the only file which works for favorite, Pashto, Farsi and English jokes.
 * Last edit: by Maihan Nijat
 */
public class JokeActivity extends AppCompatActivity {

    private ArrayList<Joke> joke_item = new ArrayList<Joke>();

    private static DatabaseManager db;
    private ListView listview;
    String cat = "";
    InterstitialAd interstitialAd;
    private AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply Theme
        CustomSharedPreferences appPref = new CustomSharedPreferences(this);
        ThemeManager.applyCustomTheme(this, appPref.getStyle());
        setContentView(R.layout.activity_joke);

        // call toolbar
        setupToolbar();

        listview = (ListView) findViewById(R.id.joke_list);
        db = new DatabaseManager(this);
        cat = getIntent().getExtras().getString("cat");

        // Display jokes based on what user selected in MainActivity.java
        switch (cat) {
            case "pa":
                getSupportActionBar().setTitle(getResources().getString(R.string.pashto_jokes));
                fillList(cat);
                break;
            case "fa":
                getSupportActionBar().setTitle(getResources().getString(R.string.farsi_jokes));
                fillList(cat);
                break;
            case "en":
                getSupportActionBar().setTitle(getResources().getString(R.string.english_jokes));
                fillList(cat);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    listview.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                }
                break;
            case "fav":
                getSupportActionBar().setTitle(getResources().getString(R.string.favorite));
                fillFavList();
                break;
            default:
                break;
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long idInDB) {

                Intent i = new Intent(getApplicationContext(), JokeActivityDetail.class);
                i.putExtra("id", position);
                i.putExtra("array", joke_item);

                startActivity(i);

            }
        });

        // Banner ad
        adView = (AdView) findViewById(R.id.adView);

        // Interstitial ad
        interstitialAd = new InterstitialAd(this);
        // set the ad unit ID
        interstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest = new AdRequest.Builder().build();
        // Load ads into Interstitial Ads
        interstitialAd.loadAd(adRequest);

        adView.loadAd(adRequest);

        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }
        });

    }

    // Method for Pashto, Farsi and English jokes.
    private void fillList(String category) {
        joke_item = db.getAllRow(category);
        listview.setAdapter(new ItemListBaseAdapter(getApplicationContext(), joke_item));
    }

    // Method for favorite jokes.
    private void fillFavList() {
        joke_item = (ArrayList<Joke>) db.getFavoritesRow();
        listview.setAdapter(new ItemListBaseAdapter(getApplicationContext(), joke_item));
    }

    // Setup toolbar
    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void displayInterstitial() {
        if (interstitialAd.isLoaded()) {
            switch (cat) {
                case "pa":
                case "fa":
                case "en":
                    interstitialAd.show();
                    break;
            }
        }
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

    public void onRestart() {
        super.onRestart();
        switch (cat) {
            case "fav":
                finish();
                startActivity(getIntent());
                break;
        }
    }
}