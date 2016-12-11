package com.example.android.posandroid.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by User on 2016-12-10.
 */
public class Order extends RealmObject {
    //주문번호
    int id;
    //주문시간
    Date date;
    //주문총액
    int price;
    //테이블번호
    int table;
    //테이블인원수
    int headcount;
    //결제시간
    Date paymentDate;
    //결제수단
    int paymentType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getHeadcount() {
        return headcount;
    }

    public void setHeadcount(int headcount) {
        this.headcount = headcount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }
}
