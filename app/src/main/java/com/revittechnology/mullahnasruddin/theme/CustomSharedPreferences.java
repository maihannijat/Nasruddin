package com.revittechnology.mullahnasruddin.theme;

import android.content.Context;
import android.content.SharedPreferences;

import com.revittechnology.mullahnasruddin.R;

/**
 * Created by Maihan Nijat on 2016-10-01.
 * Description: The file helps ThemeManager.java to apply current style.
 * An object is instantiated in ThemeActivity.java file to store the style resource id.
 * Last edit: by Maihan Nijat
 */

public class CustomSharedPreferences {
    private SharedPreferences sharedPreferences;

    public static final String STYLE_KEY = "STYLE_KEY";


    public CustomSharedPreferences(Context context) {
        super();
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void setStyle(int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(STYLE_KEY, value);
        editor.apply();
    }

    public int getStyle() {
        return sharedPreferences.getInt(STYLE_KEY, -1);
    }
}