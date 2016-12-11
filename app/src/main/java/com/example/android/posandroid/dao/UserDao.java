package com.example.android.posandroid.dao;

import com.example.android.posandroid.model.User;

import io.realm.Realm;

/**
 * Created by User on 2016-12-09.
 */
public class UserDao {
    Realm realm;

    public UserDao() {
        this.realm = Realm.getDefaultInstance();
    }

    //    유저 등록
    public void insertUser(String  pw){
        realm.beginTransaction();
        User user = realm.createObject(User.class);
        user.setPassword(pw);
        realm.commitTransaction();
    }

    //    비밀번호 변경
    public void changePassword(String pw){
        User user = realm.where(User.class).findFirst();
        realm.beginTransaction();
        user.setPassword(pw);
        realm.commitTransaction();
    }
    //    로그인
    public boolean Login(String pw){
        if(pw.equals(realm.where(User.class).findFirst().getPassword())){
            return true;
        }else{
            return false;
        }
    }
}
