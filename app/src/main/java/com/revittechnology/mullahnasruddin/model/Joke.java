package com.revittechnology.mullahnasruddin.model;

/**
 * Created by Maihan Nijat on 2016-10-01.
 * Description: The joke class is created to instantiate object from it.
 * Database provides data for each instance.
 * Last edit: by Maihan Nijat
 */

import java.io.Serializable;

public class Joke implements Serializable {

    // Private variables.
    private int id;
    private String title;
    private String joke;
    private String cat;
    private int fav;

    // Empty constructor.
    public Joke() {

    }

    // Default constructor.
    public Joke(int id, String title, String joke, String cat, int fav) {
        this.id = id;
        this.title = title;
        this.joke = joke;
        this.cat = cat;
        this.fav = fav;
    }

    // Getting ID
    public int getId() {
        return this.id;
    }

    // Setting ID
    public void setId(int id) {
        this.id = id;
    }

    // Getting Joke Title
    public String getTitle() {
        return this.title;
    }

    // Setting Joke Title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getting Joke
    public String getJoke() {
        return this.joke;
    }

    // Setting Joke
    public void setJoke(String joke) {
        this.joke = joke;
    }

    // Getting Category
    public String getCat() {
        return this.cat;
    }

    // Setting Category
    public void setCat(String cat) {
        this.cat = cat;
    }

    // Getting Favorite
    public int getFavorites() {
        return this.fav;
    }

    // Setting Favorite
    public void setFavorites(int fav) {
        this.fav = fav;
    }
}
