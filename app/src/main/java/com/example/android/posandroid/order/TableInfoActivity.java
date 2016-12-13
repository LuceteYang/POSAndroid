package com.example.android.posandroid.order;

import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.posandroid.PayActivity;
import com.example.android.posandroid.R;
import com.example.android.posandroid.config.MessageHelper;
import com.example.android.posandroid.dao.MenuDao;
import com.example.android.posandroid.dao.OrderDao;
import com.example.android.posandroid.dao.OrderMenuDao;
import com.example.android.posandroid.model.Menu;
import com.example.android.posandroid.model.Order;
import com.example.android.posandroid.model.OrderMenu;
import com.wefika.horizontalpicker.HorizontalPicker;

import java.util.List;

public class TableInfoActivity extends AppCompatActivity {
    int tableId;
    Order o;
    TextView tv_table_info_head_minus,tv_table_info_head_plus,tv_table_info_head,tv_table_info_number;
    Button btn_table_info_back,btn_table_info_order,btn_table_info_pay, btn_table_info_edit;
    OrderMenuAdapter oma;
    ListView lv_table_menu;
    MenuDao md;
    OrderDao od;
    OrderMenuDao omd;
    List<Menu> menulist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_info);
        tv_table_info_head_minus = (TextView)findViewById(R.id.tv_table_info_head_minus);
        tv_table_info_head_plus = (TextView)findViewById(R.id.tv_table_info_head_plus);
        tv_table_info_head = (TextView)findViewById(R.id.tv_table_info_head);
        tv_table_info_number = (TextView)findViewById(R.id.tv_table_info_number);
        btn_table_info_back = (Button)findViewById(R.id.btn_table_info_back);
        btn_table_info_order = (Button)findViewById(R.id.btn_table_info_order);
        btn_table_info_pay = (Button)findViewById(R.id.btn_table_info_pay);
        btn_table_info_edit = (Button)findViewById(R.id.btn_table_info_edit);
        lv_table_menu = (ListView)findViewById(R.id.lv_table_menu);
        tableId  = getIntent().getExtras().getInt("tableId");
        oma = new OrderMenuAdapter(getApplicationContext(),tableId);
        md = new MenuDao();
        od = new OrderDao();
        omd = new OrderMenuDao();
        menulist = md.menuList();
        lv_table_menu.setAdapter(oma);
        btn_table_info_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_table_info_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o = od.orderInfo(tableId);
                od.updateCount(o.getId(),Integer.valueOf(tv_table_info_head.getText().toString()));
                int total=0;
                for(int i=0;i<menulist.size();i++){
                    OrderMenuItem omi = oma.getItem(i);
                    OrderMenu menuInfo = omd.menuInfo(omi.getMenuName(),o.getId());
                    Menu a = md.menuInfo(omi.getMenuName());
                    if(omi.getCount()>0) {
                        if (menuInfo == null) {
                            omd.insertOrderMenu(omi.getMenuName(), o.getId(), omi.getCount());
                        } else {
                            omd.updateOrderMenu(omi.getMenuName(), o.getId(), omi.getCount());
                        }
                        total += a.getCost()*omi.getCount();
                    }else{
                        if (menuInfo != null) {
                            omd.deleteOrderMenu(omi.getMenuName(), o.getId());
                        }
                    }
                }
                od.updatePrice(o.getId(),total);
                MessageHelper.getInstance().sendMessage(MessageHelper.ActivityType.MAINACTIVITY, MessageHelper.MessageType.REFLASH);
                finish();
            }
        });
        btn_table_info_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result=true;
                for(int i=0;i<menulist.size();i++){
                    if(oma.getItem(i).getCount()>0){
                        result=false;
                    }
                }
                if(result){
                    Toast.makeText(getApplication(),"메뉴가 선택되지 않았습니다.",Toast.LENGTH_SHORT).show();
                }else {
                    int headcount = Integer.valueOf(tv_table_info_head.getText().toString());
                    Order o = od.insertOrder(tableId, headcount);
                    int total=0;
                    for (int i = 0; i < menulist.size(); i++) {
                        if (oma.getItem(i).getCount() > 0) {
                            OrderMenuItem omi = oma.getItem(i);
                            Menu a = md.menuInfo(omi.getMenuName());
                            total += a.getCost()*omi.getCount();
                            omd.insertOrderMenu(omi.getMenuName(), o.getId(), omi.getCount());
                        }
                    }
                    od.updatePrice(o.getId(),total);
                    MessageHelper.getInstance().sendMessage(MessageHelper.ActivityType.MAINACTIVITY, MessageHelper.MessageType.REFLASH);
                    finish();
                }
            }
        });
        tv_table_info_head_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int now = Integer.valueOf(tv_table_info_head.getText().toString());
                if(now>=1) {
                    tv_table_info_head.setText(String.valueOf(now - 1));
                }
            }
        });
        tv_table_info_head_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int now = Integer.valueOf(tv_table_info_head.getText().toString());
                if(now<10) {
                    tv_table_info_head.setText(String.valueOf(now + 1));
                }
            }
        });
        btn_table_info_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),PayActivity.class);
                i.putExtra("orderId",o.getId());
                i.putExtra("tableId",tableId);
                startActivity(i);
            }
        });

        tv_table_info_number.setText("<"+ String.valueOf(tableId)+"번테이블>");
        initValue();
    }
    private void initValue(){
        oma.clear();
        o = od.orderInfo(tableId);
        if(o==null){
            tv_table_info_head.setText(String.valueOf(0));
            btn_table_info_order.setVisibility(View.VISIBLE);
            btn_table_info_pay.setVisibility(View.GONE);
            btn_table_info_edit.setVisibility(View.GONE);
        }else{
            btn_table_info_order.setVisibility(View.GONE);
            btn_table_info_edit.setVisibility(View.VISIBLE);
            btn_table_info_pay.setVisibility(View.VISIBLE);
            tv_table_info_head.setText(String.valueOf(o.getHeadcount()));
        }
        for(int i=0;i<menulist.size();i++){
            OrderMenuItem omi;
            omi = new OrderMenuItem();
            omi.setMenuName(menulist.get(i).getName());
            if(o==null){
                omi.setCount(0);
            }else{
                OrderMenu om = omd.menuInfo(menulist.get(i).getName(),o.getId());
                if(om!=null){
                    omi.setCount(om.getCount());
                }else{
                    omi.setCount(0);
                }
            }
            oma.add(omi);
        }
        oma.notifyDataSetChanged();
    }
}
