package com.emrekose.bakingapp.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ColumnUtils {

    // TODO: 10.04.2018 custom grid layout manager for dynamic spanCount
    public static int numberOfColumns(Activity activity) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 600;
        int width = displayMetrics.widthPixels;
        return width / widthDivider;
    }
}
