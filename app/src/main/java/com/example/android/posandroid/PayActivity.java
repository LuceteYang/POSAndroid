package com.example.android.posandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.posandroid.dao.MenuDao;
import com.example.android.posandroid.dao.OrderDao;
import com.example.android.posandroid.dao.OrderMenuDao;
import com.example.android.posandroid.model.Menu;
import com.example.android.posandroid.model.Order;
import com.example.android.posandroid.model.OrderMenu;

import java.util.List;

public class PayActivity extends AppCompatActivity {
    TextView tv_pay_order;
    Button btn_pay_cash, btn_pay_coupon, btn_pay_card;
    int orderId;
    int tableId;
    OrderMenuDao omd;
    MenuDao md;
    OrderDao od;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        tv_pay_order = (TextView)findViewById(R.id.tv_pay_order);
        btn_pay_cash = (Button)findViewById(R.id.btn_pay_cash);
        btn_pay_coupon = (Button)findViewById(R.id.btn_pay_coupon);
        btn_pay_card = (Button)findViewById(R.id.btn_pay_card);
        orderId  = getIntent().getExtras().getInt("orderId");
        tableId  = getIntent().getExtras().getInt("tableId");
        omd = new OrderMenuDao();
        od = new OrderDao();
        md = new MenuDao();
        String text="";
        List<OrderMenu> omlist = omd.orderMenuList(orderId);
        for(int i=0;i<omlist.size();i++){
            Menu a = md.menuInfo(omlist.get(i).getMenuName());
            text += omlist.get(i).getMenuName()+"X"+String.valueOf(omlist.get(i).getCount())+"------"+String.valueOf(a.getCost()*omlist.get(i).getCount())+"원"+ "\n";
        }
        text+="총금액 : "+od.orderInfo(tableId).getPrice()+"원";
        tv_pay_order.setText(text);

    }
}
