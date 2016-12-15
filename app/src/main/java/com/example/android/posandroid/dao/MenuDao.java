package com.example.android.posandroid.dao;

import com.example.android.posandroid.model.Menu;

import java.util.Date;
import java.util.List;

import io.realm.Realm;

/**
 * Created by User on 2016-12-09.
 */
public class MenuDao {
    Realm realm;

    public MenuDao() {
        this.realm = Realm.getDefaultInstance();
    }

    //    메뉴추가
    public void insertMenu(String name,int cost,String detail, int calory){
        Date now = new Date();
        realm.beginTransaction();
        Menu menu = realm.createObject(Menu.class);
        menu.setName(name);
        menu.setCost(cost);
        menu.setCalory(calory);
        menu.setDetail(detail);
        menu.setRegisterDate(now);
        realm.commitTransaction();
    }
    //    메뉴목록출력
    public List<Menu> menuList(){
        return realm.where(Menu.class).findAll();
    }

    //    메뉴상세정보조회
    public Menu menuInfo(String menuName){
        return realm.where(Menu.class).equalTo("name",menuName).findFirst();
    }

    //    메뉴삭제
    public void deleteMenu(String menuName){
        realm.beginTransaction();
        Menu menu = realm.where(Menu.class).equalTo("name",menuName).findFirst();
        menu.deleteFromRealm();
        realm.commitTransaction();
    }


    //    메뉴수정
    public void editMenu(String name,int cost,String detail, int calory){
        realm.beginTransaction();
        Menu menu = realm.where(Menu.class).equalTo("name",name).findFirst();
        menu.setCost(cost);
        menu.setCalory(calory);
        menu.setDetail(detail);
        realm.commitTransaction();
    }
}
