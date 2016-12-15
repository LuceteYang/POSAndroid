package com.example.android.posandroid.order;

import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.posandroid.MainActivity;
import com.example.android.posandroid.R;
import com.example.android.posandroid.dao.MenuDao;
import com.example.android.posandroid.dao.OrderDao;
import com.example.android.posandroid.dao.OrderMenuDao;
import com.example.android.posandroid.dao.PayNumberDao;
import com.example.android.posandroid.model.Menu;
import com.example.android.posandroid.model.OrderMenu;

import java.util.List;

public class PayActivity extends AppCompatActivity {
    TextView tv_pay_order,tv_pay_title,tv_pay_type;
    Button btn_pay_cash, btn_pay_coupon, btn_pay_card,btn_pay_complete,btn_pay_type_change;
    EditText et_number;
    int orderId;
    int tableId;
    OrderMenuDao omd;
    MenuDao md;
    OrderDao od;
    PayNumberDao pnd;
    int type;
    int payType;    //1.현금 2,쿠폰 3.카드
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        tv_pay_order = (TextView)findViewById(R.id.tv_pay_order);
        tv_pay_title = (TextView)findViewById(R.id.tv_pay_title);
        tv_pay_type = (TextView)findViewById(R.id.tv_pay_type);
        btn_pay_cash = (Button)findViewById(R.id.btn_pay_cash);
        btn_pay_coupon = (Button)findViewById(R.id.btn_pay_coupon);
        btn_pay_card = (Button)findViewById(R.id.btn_pay_card);
        btn_pay_complete = (Button)findViewById(R.id.btn_pay_complete);
        btn_pay_type_change = (Button)findViewById(R.id.btn_pay_type_change);
        et_number = (EditText)findViewById(R.id.et_number);
        orderId  = getIntent().getExtras().getInt("orderId");
        tableId  = getIntent().getExtras().getInt("tableId");
        omd = new OrderMenuDao();
        od = new OrderDao();
        md = new MenuDao();
        pnd = new PayNumberDao();
        String text="";
        List<OrderMenu> omlist = omd.orderMenuList(orderId);
        for(int i=0;i<omlist.size();i++){
            Menu a = md.menuInfo(omlist.get(i).getMenuName());
            text += omlist.get(i).getMenuName()+"X"+String.valueOf(omlist.get(i).getCount())+"------"+String.valueOf(a.getCost()*omlist.get(i).getCount())+"원"+ "\n";
        }
        text+="총금액 : "+od.orderInfo(tableId).getPrice()+"원";
        tv_pay_order.setText(text);
        btn_pay_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현금
                type=2;
                payType=1;
                initValue();
            }
        });
        btn_pay_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = et_number.getText().toString();
                if(TextUtils.isEmpty(str)){
                    Toast.makeText(getApplicationContext(),"쿠폰번호를 입력해주세요!",Toast.LENGTH_SHORT).show();
                }else{
                    //쿠폰
                    type=2;
                    payType=2;
                    initValue();
                }
            }
        });
        btn_pay_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = et_number.getText().toString();
                if(TextUtils.isEmpty(str)){
                    Toast.makeText(getApplicationContext(),"카드번호를 입력해주세요!",Toast.LENGTH_SHORT).show();
                }else{
                    //카드
                    type=2;
                    payType=3;
                    initValue();
                }
            }
        });
        btn_pay_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                od.orderPay(orderId,payType);
                if(payType!=1){
                    int num= Integer.valueOf(et_number.getText().toString());
                    pnd.saveNumber(orderId,num);
                }
                Intent intent = new Intent(PayActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btn_pay_type_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=1;
                initValue();
            }
        });
        type=1;
        initValue();
    }
    private void initValue(){
        if(type==1){
            et_number.setText("");
            tv_pay_title.setText("결제내역");
            tv_pay_type.setVisibility(View.GONE);
            btn_pay_complete.setVisibility(View.GONE);
            btn_pay_type_change.setVisibility(View.GONE);
            btn_pay_coupon.setVisibility(View.VISIBLE);
            btn_pay_cash.setVisibility(View.VISIBLE);
            btn_pay_card.setVisibility(View.VISIBLE);
            et_number.setVisibility(View.VISIBLE);
        }else if(type==2){
            tv_pay_title.setText("영수증");
            if(payType==1){
                tv_pay_type.setText("결제수단 : 현금");
            }else if(payType==2){
                tv_pay_type.setText("결제수단 : 쿠폰");
            }else if(payType==3){
                tv_pay_type.setText("결제수단 : 카드");
            }
            btn_pay_coupon.setVisibility(View.GONE);
            btn_pay_cash.setVisibility(View.GONE);
            btn_pay_card.setVisibility(View.GONE);
            et_number.setVisibility(View.GONE);
            tv_pay_type.setVisibility(View.VISIBLE);
            btn_pay_type_change.setVisibility(View.VISIBLE);
            btn_pay_complete.setVisibility(View.VISIBLE);

        }
    }
}
