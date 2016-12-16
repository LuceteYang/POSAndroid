package com.example.android.posandroid.sales;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.android.posandroid.model.Menu;
import com.example.android.posandroid.model.Order;
import com.example.android.posandroid.model.OrderMenu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class SalesDetailActivity extends AppCompatActivity {
    int itemType, itemId, viewType;//1 상세보기 2 수정
    Button btn_sales_edit,btn_sales_delete,btn_sales_ok;
    TextView tv_sales_detail_1,tv_sales_detail_2,tv_sales_detail_3,tv_sales_detail_4;
    EditText et_sales_detail_2;
    OrderDao od ;
    OrderMenuDao omd;
    MenuDao md;
    IngredientDao id;
    IngredientOrderDao iod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_detail);
        btn_sales_edit = (Button)findViewById(R.id.btn_sales_edit);
        btn_sales_delete = (Button)findViewById(R.id.btn_sales_delete);
        btn_sales_ok = (Button)findViewById(R.id.btn_sales_ok);
        tv_sales_detail_1 = (TextView)findViewById(R.id.tv_sales_detail_1);
        tv_sales_detail_2 = (TextView)findViewById(R.id.tv_sales_detail_2);
        tv_sales_detail_3 = (TextView)findViewById(R.id.tv_sales_detail_3);
        tv_sales_detail_4 = (TextView)findViewById(R.id.tv_sales_detail_4);
        et_sales_detail_2 = (EditText)findViewById(R.id.et_sales_detail_2);
        itemId  = getIntent().getExtras().getInt("id");
        itemType  = getIntent().getExtras().getInt("type");
        od = new OrderDao();
        id = new IngredientDao();
        iod = new IngredientOrderDao();
        omd = new OrderMenuDao();
        md = new MenuDao();
        viewType = 1;
        initValue();
        btn_sales_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewType==1){
                    viewType=2;
                    initValue();
                }else if(viewType==2){
                    int num = Integer.valueOf(et_sales_detail_2.getText().toString());
                    if(itemType==1){
                        Order o = od.orderInfoById(itemId);
                        od.updateCount(o.getId(),num);
                    }else if(itemType==2){
                        IngredientOrder io = iod.IngredientOrderInfo(itemId);
                        iod.updateStock(io.getId(),num);
                    }
                    viewType=1;
                    MessageHelper.getInstance().sendMessage(MessageHelper.ActivityType.SALESACTIVITY, MessageHelper.MessageType.REFLASH);
                    initValue();
                }
            }
        });
        btn_sales_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(SalesDetailActivity.this);
                alert.setTitle("삭제 하시겠습니까?");
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (itemType == 1) {
                            Order o = od.orderInfoById(itemId);
                            od.deleteOrder(o.getId());
                        } else if (itemType == 2) {
                            IngredientOrder io = iod.IngredientOrderInfo(itemId);
                            iod.deleteIngOrder(io.getId());
                        }
                        MessageHelper.getInstance().sendMessage(MessageHelper.ActivityType.SALESACTIVITY, MessageHelper.MessageType.REFLASH);
                        finish();
                    }
                });
                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });
                alert.show();

            }
        });
    }
    public void initValue(){
        if(viewType==1){
            btn_sales_edit.setText("수정");
            et_sales_detail_2.setText("");
            et_sales_detail_2.setVisibility(View.GONE);
            if(itemType==1){
                //수입
                Order o = od.orderInfoById(itemId);
                String text = "";
                List<OrderMenu> omlist = omd.orderMenuList(o.getId());
                text+="테이블 번호 : "+String.valueOf(o.getTable())+"\n메뉴 및 수량 : ";
                int total=0;
                for(int i=0;i<omlist.size();i++){
                    Menu menu = md.menuInfo(omlist.get(i).getMenuName());
                    text += omlist.get(i).getMenuName()+" X "+String.valueOf(omlist.get(i).getCount())+"  "+String.valueOf(menu.getCost()*omlist.get(i).getCount())+"원\n";
                    total+=menu.getCost()*omlist.get(i).getCount();
                }
                tv_sales_detail_1.setText(text);
                tv_sales_detail_2.setText("인원 : ");
                tv_sales_detail_4.setText(String.valueOf(o.getHeadcount()));
                DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
                String tempDate = sdFormat.format(o.getDate());
                String text1="총 금액 : "+String.valueOf(total)+"원\n"+"날짜 : "+tempDate;
                tv_sales_detail_3.setText(text1);
            }else if(itemType==2){
                //지출
                IngredientOrder io = iod.IngredientOrderInfo(itemId);
                Ingredient ing = id.ingInfo(io.getIngId());
                String text = "";
                text+="재료명 : "+ing.getMenuName()+"\n"+"단위금액 : "+String.valueOf(ing.getCost())+'원';
                tv_sales_detail_1.setText(text);
                tv_sales_detail_2.setText("주문수량 : ");
                tv_sales_detail_4.setText(String.valueOf(io.getStock()));
                DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
                String tempDate = sdFormat.format(io.getDate());
                String text1="총 금액 : "+"-"+String.valueOf(ing.getCost()*io.getStock())+"원\n"+"날짜 : "+tempDate;
                tv_sales_detail_3.setText(text1);
            }
            tv_sales_detail_4.setVisibility(View.VISIBLE);
        }else if(viewType==2){
            btn_sales_edit.setText("저장");
            tv_sales_detail_4.setVisibility(View.GONE);
            et_sales_detail_2.setText(tv_sales_detail_4.getText());
            et_sales_detail_2.setVisibility(View.VISIBLE);
        }
    }
}
