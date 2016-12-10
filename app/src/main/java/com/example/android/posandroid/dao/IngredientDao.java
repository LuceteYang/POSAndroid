package com.example.android.posandroid.dao;

import android.util.Log;

import com.example.android.posandroid.model.Ingredient;

import java.util.List;

import io.realm.Realm;

/**
 * Created by User on 2016-12-09.
 */
public  class IngredientDao {

    public IngredientDao() {
        this.realm =  Realm.getDefaultInstance();
    }

    Realm realm;
//    재료추가
    public void insertIngredient(String name,int cost,String detail, int stock,String menuName){
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

    //    재료삭제
    public void deleteIngredientByMenu(String menuName){
        realm.beginTransaction();
        Ingredient ingredient = realm.where(Ingredient.class).equalTo("menuName",menuName).findFirst();
        ingredient.deleteFromRealm();
        realm.commitTransaction();
    }

    //    재료 메뉴이름 수정
    public void editIngredientByMenu(String oldname,String menuName){
        realm.beginTransaction();
        Ingredient ingredient = realm.where(Ingredient.class).equalTo("menuName",oldname).findFirst();
        ingredient.setName(menuName+" 재료");
        ingredient.setMenuName(menuName);
        realm.commitTransaction();
    }

//    재료목록출력
//    재료상세조회
//
//    재료수정
//    재료주문



}
