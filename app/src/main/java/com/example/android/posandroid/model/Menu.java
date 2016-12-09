package com.example.android.posandroid.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by User on 2016-11-30.
 */
public class Menu  extends RealmObject{
    //메뉴명
    String name;
    //메뉴설명
    String detail;
    //칼로리
    int calory;
    //가격
    int cost;
    //등록일자
    Date registerDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCalory() {
        return calory;
    }

    public void setCalory(int calory) {
        this.calory = calory;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
