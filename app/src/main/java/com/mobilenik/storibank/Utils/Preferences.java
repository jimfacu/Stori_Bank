package com.mobilenik.storibank.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

public class Preferences {

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public static final int NO_VALUE_INT = -1;
    public static final int NO_VALUE_LONG = -1;

    protected Preferences(@Nullable Context context, @Nullable String name) {
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    protected String get(String key) {
        return preferences.getString(key, "");
    }

    protected void set(@Nullable String key, @Nullable String value) {
        editor.putString(key, value);
        editor.apply();
    }

    protected boolean getBoolean(@Nullable String key) {
        return preferences.getBoolean(key, false);
    }

    protected void setBoolean(@Nullable String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    protected int getInt(@Nullable String key) {
        return preferences.getInt(key, NO_VALUE_INT);
    }

    protected void set(@Nullable String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    protected long getLong(@Nullable String key) {
        return preferences.getLong(key, NO_VALUE_LONG);
    }

    protected void set(@Nullable String key, long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    public void clear() {
        editor.clear();
        editor.apply();
    }

    public void commit() {
        editor.commit();
    }
}
