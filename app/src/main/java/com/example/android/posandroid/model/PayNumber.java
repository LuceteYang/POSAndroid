package com.example.android.posandroid.model;

import io.realm.RealmObject;

/**
 * Created by User on 2016-12-10.
 */
public class PayNumber extends RealmObject {
    //주문 번호
    int orderId;
    int payNumber;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(int payNumber) {
        this.payNumber = payNumber;
    }
}
