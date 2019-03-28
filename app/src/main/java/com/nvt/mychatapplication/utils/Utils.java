package com.nvt.mychatapplication.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }
    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
    public static void hideInputManager(View v, Context c) {
        InputMethodManager mImm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (v != null) {
            if (mImm != null) {
                mImm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            v.clearFocus();
        }
    }
}
