package com.example.android.posandroid.dao;

import com.example.android.posandroid.model.Ingredient;
import com.example.android.posandroid.model.IngredientOrder;

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
//    재료추가
    public void insertIngredientOrder(int ingId, int stock){
        Date now = new Date();
        realm.beginTransaction();
        IngredientOrder ing = realm.createObject(IngredientOrder.class);
        ing.setId(realm.where(IngredientOrder.class).max("id").intValue() + 1);
        ing.setStock(stock);
        ing.setIngId(ingId);
        ing.setDate(now);
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
//    재료주문



//    지출정보요청
//    지출상세정보조회
//    지출수정
//    지출삭제
//    지출정보출력






}
