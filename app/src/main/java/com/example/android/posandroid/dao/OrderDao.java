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

    //    주문입력
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

    //    테이블 번호로 주문조회
    public Order orderInfo(int tableId){
        return realm.where(Order.class).equalTo("table",tableId).isNull("paymentDate").findFirst();
    }

    //    주문번호로 주문조회
    public Order orderInfoById(int orderId){
        return realm.where(Order.class).equalTo("id",orderId).findFirst();
    }
    //     인원수 수정
    public void updateCount(int orderId,int headcount){
        realm.beginTransaction();
        Order order = realm.where(Order.class).equalTo("id",orderId).findFirst();
        order.setHeadcount(headcount);
        realm.commitTransaction();
    }
    //     주문 총액 변경
    public void updatePrice(int orderId, int price){
        realm.beginTransaction();
        Order order = realm.where(Order.class).equalTo("id",orderId).findFirst();
        order.setPrice(price);
        realm.commitTransaction();
    }
    //    결제 수단 저장
    public void orderPay(int orderId, int paymentType){
        Date now = new Date();
        realm.beginTransaction();
        Order order = realm.where(Order.class).equalTo("id",orderId).findFirst();
        order.setPaymentType(paymentType);
        order.setPaymentDate(now);
        realm.commitTransaction();
    }

    //  결제된 주문 조회
    public List<Order> allOrderInfo(){
        return realm.where(Order.class).isNotNull("paymentDate").findAll();
    }
    //    수입삭제
    public void deleteOrder(int orderId){
        realm.beginTransaction();
        Order ingOrder = realm.where(Order.class).equalTo("id",orderId).findFirst();
        ingOrder.deleteFromRealm();
        realm.commitTransaction();
    }

}
