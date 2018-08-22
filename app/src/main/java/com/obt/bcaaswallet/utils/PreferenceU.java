package com.obt.bcaaswallet.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
public class PreferenceU {
    private static SharedPreferences sp;
    private static Editor editor;
    private static PreferenceU instance = null;

    @SuppressLint("CommitPrefEdits")
    private PreferenceU(Context context) {
        sp = context.getSharedPreferences("bcaas", 0);
        editor = sp.edit();
    }

    public static synchronized PreferenceU getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceU(context);
        }

        return instance;
    }

    public String getString(String key) {
        return this.getString(key, "");
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public Long getLong(String key) {
        return this.getLong(key, 0L);
    }

    public Long getLong(String key, Long defValue) {
        return sp.getLong(key, defValue);
    }

    public Float getFloat(String key) {
        return this.getFloat(key, 0.0F);
    }

    public Float getFloat(String key, Float defValue) {
        return sp.getFloat(key, defValue);
    }

    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void saveFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void saveLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public void clear(String key) {
        editor.remove(key);
        editor.commit();
    }
}
