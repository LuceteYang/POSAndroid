package com.example.android.posandroid.dao;

import com.example.android.posandroid.model.Menu;
import com.example.android.posandroid.model.OrderMenu;

import java.util.Date;
import java.util.List;

import io.realm.Realm;

/**
 * Created by User on 2016-12-09.
 */
public class OrderMenuDao {
    Realm realm;

    public OrderMenuDao() {
        this.realm = Realm.getDefaultInstance();
    }
    //  주문내역 입력
    public void insertOrderMenu(String menuName,int orderId,int count){
        realm.beginTransaction();
        OrderMenu orderMenu = realm.createObject(OrderMenu.class);
        orderMenu.setMenuName(menuName);
        orderMenu.setOrderId(orderId);
        orderMenu.setCount(count);
        realm.commitTransaction();
    }
    //  주문내역 수정
    public void updateOrderMenu(String menuName,int orderId,int count){
        realm.beginTransaction();
        OrderMenu menu = realm.where(OrderMenu.class).equalTo("menuName",menuName).equalTo("orderId",orderId).findFirst();
        menu.setCount(count);
        realm.commitTransaction();
    }

    //    주문내역 상제정보 조회
    public OrderMenu menuInfo(String menuName, int orderId){
        return realm.where(OrderMenu.class).equalTo("menuName",menuName).equalTo("orderId",orderId).findFirst();
    }
    //   주문내역 삭제
    public void deleteOrderMenu(String menuName, int orderId){
        realm.beginTransaction();
        OrderMenu menu = realm.where(OrderMenu.class).equalTo("menuName",menuName).equalTo("orderId",orderId).findFirst();
        menu.deleteFromRealm();
        realm.commitTransaction();
    }
    //    주문내역 조회
    public List<OrderMenu> orderMenuList(int orderId){
        return realm.where(OrderMenu.class).equalTo("orderId",orderId).findAll();
    }
    //   수입내역 조회
    public List<OrderMenu> orderMenuListByMenu(String menuName){
        return realm.where(OrderMenu.class).equalTo("menuName",menuName).findAll();
    }

    //    수입내역 삭제
    public void deleteByOrderId(int orderId){
        realm.beginTransaction();
        List<OrderMenu> menuList = realm.where(OrderMenu.class).equalTo("orderId",orderId).findAll();
        for(int i=0;i<menuList.size();i++){
            menuList.get(i).deleteFromRealm();
        }
        realm.commitTransaction();
    }

}
