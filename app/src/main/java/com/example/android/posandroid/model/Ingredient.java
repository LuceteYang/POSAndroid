package com.example.android.posandroid.model;

import io.realm.RealmObject;

/**
 * Created by User on 2016-11-30.
 */
public class Ingredient extends RealmObject{
    //쟈료번호
    int id;
    //재료명
    String name;
    //재료가격
    int cost;
    //상세설명
    String detail;
    //현재량
    int stock;
    //메뉴 이름
    String menuName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}


