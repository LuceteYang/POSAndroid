package com.example.android.posandroid.sales;

import java.util.Date;

/**
 * Created by User on 2016-12-11.
 */
public class SalesItem {
    private String saleTitle;
    private int cost;
    private Date date;
    private int type;   //1 수입 2 지출
    private int id;   //item id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSaleTitle() {
        return saleTitle;
    }

    public void setSaleTitle(String saleTitle) {
        this.saleTitle = saleTitle;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
