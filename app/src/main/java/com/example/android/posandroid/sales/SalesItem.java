package com.example.android.posandroid.sales;

import java.util.Date;

/**
 * Created by User on 2016-12-11.
 */
public class SalesItem {
    private String saleTitle;
    private String cost;
    private Date date;

    public String getSaleTitle() {
        return saleTitle;
    }

    public void setSaleTitle(String saleTitle) {
        this.saleTitle = saleTitle;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
