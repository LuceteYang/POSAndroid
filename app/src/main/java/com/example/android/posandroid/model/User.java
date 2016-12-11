package com.example.android.posandroid.model;

import io.realm.RealmObject;

/**
 * Created by User on 2016-12-10.
 */
public class User  extends RealmObject {
    //비밀번호
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
