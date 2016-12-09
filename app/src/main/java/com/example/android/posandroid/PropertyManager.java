package com.example.android.posandroid;

/**
 * Created by User on 2016-09-05.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PropertyManager {
    private static PropertyManager instance;
    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private PropertyManager() {
        Context context = POSApplication.getContext();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();
    }

    //패스워드 가져오기
    public String isPassword() {
        return mPrefs.getString("pw","000000");
    }

    //패스워드 설정
    public void setPassword(String pw) {
        mEditor.putString("pw", pw);
        mEditor.commit();
    }

    //처음 앱 설치유무 가져오기
    public boolean isFirst() {
        return mPrefs.getBoolean("First",true);
    }

    //처음 앱 설치유무 설정
    public void setFirst(Boolean bool) {
        mEditor.putBoolean("First", bool);
        mEditor.commit();
    }



}
