package com.example.android.posandroid.dao;

import com.example.android.posandroid.model.Ingredient;

import io.realm.Realm;

/**
 * Created by User on 2016-12-09.
 */
public class IngredientDao {
    Realm realm;
//    재료추가
    public void InsertIngredient(String name,int cost,String detail, int stock,String menuName){
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Ingredient ing = realm.createObject(Ingredient.class);
        ing.setId(realm.where(Ingredient.class).max("id").intValue() + 1);
        ing.setName(name);
        ing.setCost(cost);
        ing.setDetail(detail);
        ing.setStock(stock);
        ing.setMenuName(menuName);
        realm.commitTransaction();
    }
    //    재료목록출력
//    재료상세조회
//    재료삭제
//    재료수정
//    재료주문
}
