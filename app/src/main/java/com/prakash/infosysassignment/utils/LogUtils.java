package com.prakash.infosysassignment.utils;


import android.util.Log;

import com.prakash.infosysassignment.InfosysAssignment;

/* This is a helper class so that logging can be enabled and disabled
 * for development and distribution phase */

public class LogUtils {

    public static void d(String tag, String message) {
        if (InfosysAssignment.isLoggingOn)
            Log.d(tag, tag + ": " + message);
    }

    public static void e(String tag, String message) {
        if (InfosysAssignment.isLoggingOn)
            Log.e(tag, tag + ": " + message);
    }

    public static void i(String tag, String message) {
        if (InfosysAssignment.isLoggingOn)
            Log.i(tag, tag + ": " + message);
    }

    public static void v(String tag, String message) {
        if (InfosysAssignment.isLoggingOn)
            Log.v(tag, tag + ": " + message);
    }

    public static void w(String tag, String message) {
        if (InfosysAssignment.isLoggingOn)
            Log.w(tag, tag + ": " + message);
    }

    public static void printStackTrace(Exception e) {
        if (InfosysAssignment.isLoggingOn)
            e.printStackTrace();
    }

}
