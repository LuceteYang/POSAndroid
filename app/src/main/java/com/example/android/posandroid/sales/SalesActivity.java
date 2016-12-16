package com.example.android.posandroid.sales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.android.posandroid.R;
import com.example.android.posandroid.config.MessageHelper;
import com.example.android.posandroid.dao.IngredientDao;
import com.example.android.posandroid.dao.IngredientOrderDao;
import com.example.android.posandroid.dao.MenuDao;
import com.example.android.posandroid.dao.OrderDao;
import com.example.android.posandroid.dao.OrderMenuDao;
import com.example.android.posandroid.model.Ingredient;
import com.example.android.posandroid.model.IngredientOrder;
import com.example.android.posandroid.model.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SalesActivity extends AppCompatActivity {
    Button btn_sales_back, btn_sales_income, btn_sales_expense, btn_sales_all;
    ListView lv_sales;
    TextView tv_sales_total;
    SalesAdapter sa;
    OrderDao od;
    IngredientOrderDao iod;
    IngredientDao id;
    int viewType;   //1 다보기 2 수입 3 지출
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageHelper.MessageType.REFLASH:
                    initValue();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        btn_sales_back = (Button)findViewById(R.id.btn_sales_back);
        btn_sales_income = (Button)findViewById(R.id.btn_sales_income);
        btn_sales_expense = (Button)findViewById(R.id.btn_sales_expense);
        btn_sales_all = (Button)findViewById(R.id.btn_sales_all);
        lv_sales = (ListView)findViewById(R.id.lv_sales);
        tv_sales_total = (TextView)findViewById(R.id.tv_sales_total);
        sa = new SalesAdapter(getApplicationContext());
        od = new OrderDao();
        id = new IngredientDao();
        iod = new IngredientOrderDao();
        btn_sales_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_sales_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewType=2;
                initValue();
            }
        });
        btn_sales_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewType=3;
                initValue();
            }
        });
        btn_sales_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewType=1;
                initValue();
            }
        });
        lv_sales.setAdapter(sa);
        lv_sales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplication(),SalesDetailActivity.class);
                SalesItem obj = (SalesItem)adapterView.getAdapter().getItem(i);
                intent.putExtra("type",obj.getType());
                intent.putExtra("id",obj.getId());
                startActivity(intent);
            }
        });
        MessageHelper.getInstance().addActivity(new MessageHelper.ActivityData(MessageHelper.ActivityType.SALESACTIVITY, handler));
        viewType=1;
        initValue();
    }
    public void initValue(){
        List<SalesItem> siList = new ArrayList<>();
        int count = 0;
        if(viewType==2||viewType==1){
            //수입만
            List<Order> orderlist = od.allOrderInfo();
            for(int i=0;i<orderlist.size();i++){
                Order o = orderlist.get(i);
                SalesItem si = new SalesItem();
                si.setCost(o.getPrice());
                si.setDate(o.getDate());
                si.setSaleTitle(String.valueOf(o.getTable()));
                si.setType(1);
                si.setId(o.getId());
                siList.add(si);
                count+=o.getPrice();
            }
        }
        if(viewType==1||viewType==3){
            //지출만
            List<IngredientOrder> ingOrderlist = iod.allIngredientOrderInfo();
            for(int i=0;i<ingOrderlist.size();i++){
                IngredientOrder io = ingOrderlist.get(i);
                Ingredient ing = id.ingInfo(io.getIngId());
                SalesItem si = new SalesItem();
                si.setCost(io.getPrice()*io.getStock());
                si.setDate(io.getDate());
                si.setSaleTitle(ing.getName());
                si.setId(io.getId());
                si.setType(2);
                siList.add(si);
                count-=io.getPrice()*io.getStock();
            }
        }
        Collections.sort(siList, new Comparator<SalesItem>() {
            public int compare(SalesItem o1, SalesItem o2) {
                if (o1.getDate() == null || o2.getDate() == null)
                    return 0;
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        if(viewType==1){
            //다보기
            tv_sales_total.setText("순이익:  "+String.valueOf(count)+"원");
        }else if(viewType==2){
            //수입
            tv_sales_total.setText("총수입:  "+String.valueOf(count)+"원");
        }else if(viewType==3){
            //지출
            tv_sales_total.setText("총지출:  "+String.valueOf(count)+"원");
        }
        sa.setList(siList);
        sa.notifyDataSetChanged();
    }
}
