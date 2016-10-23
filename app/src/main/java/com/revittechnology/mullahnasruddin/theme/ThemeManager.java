package com.revittechnology.mullahnasruddin.theme;

import android.content.Context;

/**
 * Created by Maihan Nijat on 2016-10-01.
 * Description: This file is created to help use the right theme base on user selection.
 * Non-static method getTheme() cannot be referenced from static context.
 * Last edit: by Maihan Nijat
 */

public class ThemeManager {

    public static void applyCustomTheme(Context context, int styleResId) {
        context.getTheme().applyStyle(styleResId, true);
    }

}