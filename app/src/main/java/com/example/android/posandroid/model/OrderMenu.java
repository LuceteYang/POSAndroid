package com.example.android.posandroid.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by User on 2016-12-10.
 */
public class OrderMenu extends RealmObject {
    //주문번호
    int orderId;
    //메뉴명
    String menuName;
    //수량
    int count;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
