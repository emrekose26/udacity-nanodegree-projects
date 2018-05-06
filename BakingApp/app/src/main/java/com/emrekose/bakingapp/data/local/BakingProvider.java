package com.emrekose.bakingapp.data.local;


import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = BakingProvider.AUTHORITY, database = BakingDB.class)
public class BakingProvider {

    static final String AUTHORITY = "com.emrekose.bakingapp.local.provider";

    @TableEndpoint(table = BakingDB.TABLE_NAME)
    public static class RecipeIngredients {

        @ContentUri(path = "ingredients", type = "vnd.android.cursor.dir/ingredients")
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/ingredients");
    }
}
