package com.prakash.infosysassignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* This is a helper class to check the internet status before making api calls */

public class InternetStatus {

    private final String TAG = "InternetStatus";
    private static InternetStatus instance = new InternetStatus();

    public static InternetStatus getInstance() {
        return instance;
    }

    public boolean isConnectedToInternet(Context ctx) {
        boolean connected = false;
        try {
            ConnectivityManager cManager = (ConnectivityManager) ctx
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable()
                    && networkInfo.isConnected();
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage());
        }
        return connected;
    }

}
