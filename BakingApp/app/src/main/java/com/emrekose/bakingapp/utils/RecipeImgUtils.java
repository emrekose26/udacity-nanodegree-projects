package com.emrekose.bakingapp.utils;

import com.emrekose.bakingapp.R;

public class RecipeImgUtils {

    public static int getRecipeImg(int id) {
        int drawable;
        switch (id) {
            case 1:
                drawable = R.drawable.nutellapie;
                break;
            case 2:
                drawable = R.drawable.brownies;
                break;
            case 3:
                drawable = R.drawable.yellowcake;
                break;
            case 4:
                drawable = R.drawable.cheesecake;
                break;
            default: drawable = R.mipmap.ic_launcher;
        }

        return drawable;
    }
}
