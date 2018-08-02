package com.emrekose.famula.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class LocationUtils {
    public static void openGoogleMaps(Context context, Double lat, Double lon) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+ lat + "," + lon));
        i.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        context.startActivity(i);
    }
}
