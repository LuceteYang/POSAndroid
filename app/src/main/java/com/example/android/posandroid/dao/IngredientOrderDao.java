package com.example.android.posandroid.dao;

import com.example.android.posandroid.model.IngredientOrder;
import com.example.android.posandroid.model.Order;

import java.util.Date;
import java.util.List;

import io.realm.Realm;

/**
 * Created by User on 2016-12-09.
 */
public  class IngredientOrderDao {

    public IngredientOrderDao() {
        this.realm =  Realm.getDefaultInstance();
    }

    Realm realm;
//    재료주문
    public void insertIngredientOrder(int ingId, int stock, int price){
        Date now = new Date();
        realm.beginTransaction();
        IngredientOrder ing = realm.createObject(IngredientOrder.class);
        ing.setId(realm.where(IngredientOrder.class).max("id").intValue() + 1);
        ing.setStock(stock);
        ing.setIngId(ingId);
        ing.setPrice(price);
        ing.setDate(now);
        realm.commitTransaction();
    }

    public List<IngredientOrder> allIngredientOrderInfo(){
        return realm.where(IngredientOrder.class).findAll();
    }


//    지출정보요청
//    지출상세정보조회
//    지출수정
//    지출삭제
//    지출정보출력
}
