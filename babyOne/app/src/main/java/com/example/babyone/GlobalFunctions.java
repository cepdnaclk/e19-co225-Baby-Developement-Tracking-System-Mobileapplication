package com.example.babyone;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

public class GlobalFunctions {
    Context mContext;
    Activity mActivity;

    public GlobalFunctions(Context mContext, Activity activity) {
        this.mContext = mContext;
        this.mActivity = activity;
    }

    public void setLocale(String language){
        SharedPreferences sharedPref = mActivity.getSharedPreferences("lang_pref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("lang", language);
        editor.apply();
    }

    public void getLang() {
        SharedPreferences sharedPref =  mActivity.getSharedPreferences("lang_pref",Context.MODE_PRIVATE);
        String language = sharedPref.getString("lang", "en-EN");
        Log.d("Language",language);
    }

    public void getLocale() {
        SharedPreferences sharedPref =  mActivity.getSharedPreferences("lang_pref",Context.MODE_PRIVATE);
        String language = sharedPref.getString("lang", "en-EN");
        LocaleListCompat appLocale = LocaleListCompat.forLanguageTags(language);
        AppCompatDelegate.setApplicationLocales(appLocale);
    }

}
