package com.revolut.noble.android.task.Model.repository;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

class SharedPrefManager {
    private SharedPreferences sharedPreferences;
    private final String SHARED_PREFS_NAME = "LOCAL_CACHE";
    private final String SAVED_DATA_NAME = "CashedData";

    SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
    }

    synchronized void save(String data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_DATA_NAME, data);
        editor.apply();
    }

    synchronized String load() {
        String DEFAULT_VALUE = "{}";
        if (sharedPreferences != null)
            return sharedPreferences.getString(SAVED_DATA_NAME, "{}");
        else
            return DEFAULT_VALUE;
    }
 }
