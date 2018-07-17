package com.emrekose.famula.util;

import android.content.Context;
import android.location.LocationManager;

public class GPSUtils {
    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
