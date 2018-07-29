package com.prakash.infosysassignment;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

public class InfosysAssignment extends Application {

    private static OkHttpClient client;
    private static Gson gson;

    /* Enable/Disable logging in logcat */
    public static final boolean isLoggingOn = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        /* Release objects while terminating the app */
        if (client != null)
            client = null;

        if (gson != null)
            gson = null;
    }

    public static OkHttpClient getServerInstance() {
        /* Singleton approach */
        if (client == null)
            client = new OkHttpClient();
        return client;
    }

    public static Gson getGsonInstance() {
        /* Singleton approach */
        if (gson == null)
            gson = new Gson();
        return gson;
    }

}
