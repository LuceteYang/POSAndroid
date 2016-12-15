package com.example.android.posandroid.dao;

import com.example.android.posandroid.model.IngredientOrder;
import com.example.android.posandroid.model.Menu;
import com.example.android.posandroid.model.Order;

import java.util.Date;
import java.util.List;

import io.realm.Realm;

/**
 * Created by User on 2016-12-09.
 */
public class OrderDao {
    Realm realm;

    public OrderDao() {
        this.realm = Realm.getDefaultInstance();
    }

    //    주문내역입력
    public Order insertOrder(int tableId,int headcount){
        Date now = new Date();
        realm.beginTransaction();
        Order order = realm.createObject(Order.class);
        order.setId(realm.where(Order.class).max("id").intValue() + 1);
        order.setTable(tableId);
        order.setHeadcount(headcount);
        order.setDate(now);
        realm.commitTransaction();
        return order;
    }

    //    주문내역조회
    public Order orderInfo(int tableId){
        return realm.where(Order.class).equalTo("table",tableId).isNull("paymentDate").findFirst();
    }

    public void updateCount(int orderId,int headcount){
        realm.beginTransaction();
        Order order = realm.where(Order.class).equalTo("id",orderId).findFirst();
        order.setHeadcount(headcount);
        realm.commitTransaction();
    }

    public void updatePrice(int orderId, int price){
        realm.beginTransaction();
        Order order = realm.where(Order.class).equalTo("id",orderId).findFirst();
        order.setPrice(price);
        realm.commitTransaction();
    }

    public void orderPay(int orderId, int paymentType){
        Date now = new Date();
        realm.beginTransaction();
        Order order = realm.where(Order.class).equalTo("id",orderId).findFirst();
        order.setPaymentType(paymentType);
        order.setPaymentDate(now);
        realm.commitTransaction();
    }
    public List<Order> allOrderInfo(){
        return realm.where(Order.class).isNotNull("paymentDate").findAll();

    }

/*    //    메뉴목록출력
    public List<Menu> menuList(){
        return realm.where(Menu.class).findAll();
    }
    //    메뉴삭제
    public void deleteMenu(String menuName){
        realm.beginTransaction();
        Menu menu = realm.where(Menu.class).equalTo("name",menuName).findFirst();
        menu.deleteFromRealm();
        realm.commitTransaction();
    }


    //    메뉴수정
    public void editMenu(String name,int cost,String detail, int calory){
        realm.beginTransaction();
        Menu menu = realm.where(Menu.class).equalTo("name",name).findFirst();
        menu.setCost(cost);
        menu.setCalory(calory);
        menu.setDetail(detail);
        realm.commitTransaction();
    }*/

//    주문내역수정
//    주문내역출력
//    영수증출력
//    수입상세조회
//    수입수정
//    수입삭제
//    테이블통계조회
//    요일통계조회
//    시간대통계조회



}
