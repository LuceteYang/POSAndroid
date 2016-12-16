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

    //    재료목록출력
    public List<Ingredient> ingredientList(){
        return realm.where(Ingredient.class).findAll();
    }

   //    재료상세조회
    public Ingredient ingInfo(int id){
        return realm.where(Ingredient.class).equalTo("id",id).findFirst();

    }
   //    재료수정
    public void editIngredient(int id, int cost, String detail, int stock){
        realm.beginTransaction();
        Ingredient ing = realm.where(Ingredient.class).equalTo("id",id).findFirst();
        ing.setCost(cost);
        ing.setStock(stock);
        ing.setDetail(detail);
        realm.commitTransaction();
    }
    //  재료주문
    public void editIngredientOrder(int id, int stock){
        realm.beginTransaction();
        Ingredient ing = realm.where(Ingredient.class).equalTo("id",id).findFirst();
        ing.setStock(ing.getStock()+stock);
        realm.commitTransaction();
    }
    //주문시 재료소모
    public void spentIngredient(String menuName, int stock){
        realm.beginTransaction();
        Ingredient ing = realm.where(Ingredient.class).equalTo("menuName",menuName).findFirst();
        ing.setStock(ing.getStock()-stock);
        realm.commitTransaction();
    }
}
