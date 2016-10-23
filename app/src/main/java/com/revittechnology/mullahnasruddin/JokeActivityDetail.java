package com.revittechnology.mullahnasruddin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.revittechnology.mullahnasruddin.data.DatabaseManager;
import com.revittechnology.mullahnasruddin.model.Joke;
import com.revittechnology.mullahnasruddin.theme.ThemeManager;
import com.revittechnology.mullahnasruddin.theme.CustomSharedPreferences;

import java.util.ArrayList;

/**
 * Created by Maihan Nijat on 2016-10-01.
 * Description: Display single joke with full text.
 * The JokeActivityDetail launches when a single joke is clicked.
 * Last edit: by Maihan Nijat
 */
public class JokeActivityDetail extends AppCompatActivity implements View.OnClickListener {

    private int ID;
    private Joke joke;
    private DatabaseManager db;
    private ArrayList<Joke> jokeList = new ArrayList<Joke>();
    private TextView jokeTitle, jokeText;
    private ImageButton nextButton, previousButton, shareButton, favoriteButton;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply Theme
        CustomSharedPreferences appPref = new CustomSharedPreferences(this);
        ThemeManager.applyCustomTheme(this, appPref.getStyle());

        setContentView(R.layout.activity_joke_detail);

        // call toolbar
        setupToolbar();

        db = new DatabaseManager(this);

        jokeTitle = (TextView) findViewById(R.id.text_joke_title);
        jokeText = (TextView) findViewById(R.id.text_joke);

        nextButton = (ImageButton) findViewById(R.id.button_next);
        previousButton = (ImageButton) findViewById(R.id.button_previous);
        shareButton = (ImageButton) findViewById(R.id.button_share);
        shareButton.setVisibility(View.VISIBLE);

        favoriteButton = (ImageButton) findViewById(R.id.button_favorite);
        favoriteButton.setVisibility(View.VISIBLE);

        // Set Listeners on buttons
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
        favoriteButton.setOnClickListener(this);

        ID = getIntent().getExtras().getInt("id");
        jokeList = (ArrayList<Joke>) getIntent().getSerializableExtra("array");
        joke = jokeList.get(ID);
        jokeTitle.setText(joke.getTitle());
        jokeText.setText(joke.getJoke());

        // Set favorite button according to joke status.
        if (joke.getFavorites() == 1) {
            favoriteButton.setImageResource(R.drawable.ic_menu_favorite);

        }

        // Banner ad
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    // Setup a toolbar
    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.joke_detail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_next:
                if (ID < (jokeList.size() - 1)) {
                    ID++;
                    joke = jokeList.get(ID);
                    jokeText.setText(joke.getJoke());
                    jokeTitle.setText(joke.getTitle());
                }
                break;
            case R.id.button_previous:
                if (ID > 0) {
                    ID--;
                    joke = jokeList.get(ID);
                    jokeText.setText(joke.getJoke());
                    jokeTitle.setText(joke.getTitle());
                }
                break;
            case R.id.button_favorite:
                if (joke.getFavorites() == 1) {
                    joke.setFavorites(0);
                    favoriteButton.setImageResource(R.drawable.ic_menu_unfavorite);
                    Snackbar.make(v, "The current joke is removed from favorite.", Snackbar.LENGTH_SHORT).show();
                } else {
                    joke.setFavorites(1);
                    favoriteButton.setImageResource(R.drawable.ic_menu_favorite);
                    Snackbar.make(v, "The current joke is added to favorite.", Snackbar.LENGTH_SHORT).show();
                }
                db.updateFavorites(joke);
                break;
            case R.id.button_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, joke.getJoke());
                startActivity(Intent.createChooser(shareIntent, "Share Joke!"));
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
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

}