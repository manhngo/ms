package com.example.manhngo.ms.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class PreferencesUtil {

    private static final String PREF_FILE_NAME = "android_lunarnewyear_pref_file";
    private static final String KEY_PLAYER = "KEY_PLAYER";

    private static final String KEY_FUNCTIONAL_TYPE = "KEY_FUNCTIONAL_TYPE";
    private static final String KEY_SORT_TYPE = "KEY_SORT_TYPE";

    private static final String KEY_SAVE_TITLE = "KEY_SAVE_TITLE";
    private static final String KEY_SAVE_CONTENT = "KEY_SAVE_CONTENT";
    private static final String KEY_SAVE_AUTHOR = "KEY_SAVE_AUTHOR";
    private static final String KEY_SAVE_SOCIAL = "KEY_SAVE_SOCIAL";
    private static final String KEY_SAVE_PHONE_NUMBER = "KEY_SAVE_PHONE_NUMBER";

    private static final String KEY_TIMES_OPEN = "KEY_TIMES_OPEN";
    private static final String KEY_LIMIT_OPEN = "KEY_LIMIT_OPEN";
    private static final String KEY_IS_VERTICAL_LIST = "KEY_IS_VERTICAL_LIST";

    private static SharedPreferences mPreferences;

    public PreferencesUtil(Context context) {
        mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPreferences.edit().clear().apply();
    }

    public long getTimesOpen() {
        return getLong(KEY_TIMES_OPEN);
    }

    public void setTimesOpen(long timesOpen) {
        setLong(KEY_TIMES_OPEN, timesOpen);
    }

    public void setLimitOpen(long timesOpen) {
        setLong(KEY_LIMIT_OPEN, timesOpen);
    }

    public long getLimitOpen(Long defaultValue) {
        return getLong(KEY_LIMIT_OPEN, defaultValue);
    }

    public String getFunctionalType() {
        return getString(KEY_FUNCTIONAL_TYPE);
    }

    public void setFunctionalType(String functionType) {
        setString(KEY_FUNCTIONAL_TYPE, functionType);
    }

    public String getSortType() {
        return getString(KEY_SORT_TYPE);
    }

    public void setSortType(String sortType) {
        setString(KEY_SORT_TYPE, sortType);
    }

    public String getKeySaveTitle() {
        return getString(KEY_SAVE_TITLE);
    }

    public void setKeySaveTitle(String functionType) {
        setString(KEY_SAVE_TITLE, functionType);
    }

    public String getKeySaveContent() {
        return getString(KEY_SAVE_CONTENT);
    }

    public void setKeySaveContent(String functionType) {
        setString(KEY_SAVE_CONTENT, functionType);
    }

    public String getKeySaveAuthor() {
        return getString(KEY_SAVE_AUTHOR);
    }

    public void setKeySaveAuthor(String functionType) {
        setString(KEY_SAVE_AUTHOR, functionType);
    }

    public String getKeySaveSocial() {
        return getString(KEY_SAVE_SOCIAL);
    }

    public void setKeySaveSocial(String functionType) {
        setString(KEY_SAVE_SOCIAL, functionType);
    }

    public String getKeySavePhoneNumber() {
        return getString(KEY_SAVE_PHONE_NUMBER);
    }

    public void setKeySavePhoneNumber(String functionType) {
        setString(KEY_SAVE_PHONE_NUMBER, functionType);
    }


    public boolean isVerticalList() {
        return getBoolean(KEY_IS_VERTICAL_LIST, true);
    }

    public void setVerticalList(boolean isVerticalList) {
        setBoolean(KEY_IS_VERTICAL_LIST, isVerticalList);
    }

    private void setString(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }

        SharedPreferences.Editor edit = mPreferences.edit();
        if (TextUtils.isEmpty(value)) {
            edit.remove(key);
        } else {
            edit.putString(key, value);
        }
        edit.apply();
    }

    private String getString(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        return mPreferences.getString(key, null);
    }

    private String getString(String key, String defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        return mPreferences.getString(key, defaultValue);
    }

    private void setBoolean(String key, boolean bool) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putBoolean(key, bool);
        edit.apply();
    }

    private boolean getBoolean(String key) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        return mPreferences.getBoolean(key, false);
    }

    private boolean getBoolean(String key, boolean defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        return mPreferences.getBoolean(key, defaultValue);
    }

    private void setLong(String key, long l) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putLong(key, l);
        edit.apply();
    }

    private long getLong(String key) {
        if (TextUtils.isEmpty(key)) {
            return 0L;
        }
        return mPreferences.getLong(key, 0L);
    }

    private long getLong(String key, Long defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        return mPreferences.getLong(key, defaultValue);
    }

    private void setInt(String key, int integer) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putInt(key, integer);
        edit.apply();
    }

    private int getInt(String key) {
        if (TextUtils.isEmpty(key)) {
            return 0;
        }

        return mPreferences.getInt(key, 0);
    }

    private boolean removeKey(String key) {
        SharedPreferences.Editor edit = mPreferences.edit();
        boolean result = edit.remove(key).commit();
        edit.apply();
        return result;
    }

    public boolean checkKey(String key) {
        return mPreferences.contains(key);
    }
}