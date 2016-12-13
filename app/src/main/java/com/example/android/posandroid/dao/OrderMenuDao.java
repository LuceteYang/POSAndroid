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

    public void insertOrderMenu(String menuName,int orderId,int count){
        realm.beginTransaction();
        OrderMenu orderMenu = realm.createObject(OrderMenu.class);
        orderMenu.setMenuName(menuName);
        orderMenu.setOrderId(orderId);
        orderMenu.setCount(count);
        realm.commitTransaction();
    }

    public void updateOrderMenu(String menuName,int orderId,int count){
        realm.beginTransaction();
        OrderMenu menu = realm.where(OrderMenu.class).equalTo("menuName",menuName).equalTo("orderId",orderId).findFirst();
        menu.setCount(count);
        realm.commitTransaction();
    }

    //    메뉴상세정보조회
    public OrderMenu menuInfo(String menuName, int orderId){
        return realm.where(OrderMenu.class).equalTo("menuName",menuName).equalTo("orderId",orderId).findFirst();
    }

    public void deleteOrderMenu(String menuName, int orderId){
        realm.beginTransaction();
        OrderMenu menu = realm.where(OrderMenu.class).equalTo("menuName",menuName).equalTo("orderId",orderId).findFirst();
        menu.deleteFromRealm();
        realm.commitTransaction();
    }
    //    메뉴목록출력
    public List<OrderMenu> orderMenuList(int orderId){
        return realm.where(OrderMenu.class).equalTo("orderId",orderId).findAll();
    }

/*    //    메뉴추가
    public void insertMenu(String name,int cost,String detail, int calory){
        Date now = new Date();
        realm.beginTransaction();
        Menu menu = realm.createObject(Menu.class);
        menu.setName(name);
        menu.setCost(cost);
        menu.setCalory(calory);
        menu.setDetail(detail);
        menu.setRegisterDate(now);
        realm.commitTransaction();
    }
    //    메뉴목록출력
    public List<Menu> menuList(){
        return realm.where(Menu.class).findAll();
    }

    //    메뉴상세정보조회
    public Menu menuInfo(String menuName){
        return realm.where(Menu.class).equalTo("name",menuName).findFirst();
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

//    메뉴가격조회
//    수입삭제
//    메뉴통계조회

}
