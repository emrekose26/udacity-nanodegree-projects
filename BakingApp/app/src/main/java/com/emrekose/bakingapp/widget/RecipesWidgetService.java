package com.emrekose.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class RecipesWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipesRemoteViewFactory(getApplication());
    }
}
