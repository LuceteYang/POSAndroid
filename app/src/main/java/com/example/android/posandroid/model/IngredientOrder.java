package com.example.android.posandroid.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by User on 2016-11-30.
 */
public class IngredientOrder extends RealmObject{
    //납품번호
    int id;
    //주문수량
    int stock;
    //재료번호
    int ingId;
    //납품일자
    Date date;
    //납품일자
    int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIngId() {
        return ingId;
    }

    public void setIngId(int ingId) {
        this.ingId = ingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}


