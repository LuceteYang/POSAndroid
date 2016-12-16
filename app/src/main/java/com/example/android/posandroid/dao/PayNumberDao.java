package com.example.android.posandroid.dao;

import com.example.android.posandroid.model.OrderMenu;
import com.example.android.posandroid.model.PayNumber;

import java.util.List;

import io.realm.Realm;

/**
 * Created by User on 2016-12-15.
 */
public class PayNumberDao {
    Realm realm;

    public PayNumberDao() {
        this.realm = Realm.getDefaultInstance();
    }

    // 고유번호 저장
    public void saveNumber(int orderId,int num){
        realm.beginTransaction();
        PayNumber payNumber = realm.createObject(PayNumber.class);
        payNumber.setOrderId(orderId);
        payNumber.setPayNumber(num);
        realm.commitTransaction();
    }

}
