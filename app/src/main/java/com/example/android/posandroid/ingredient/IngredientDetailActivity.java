package com.example.android.posandroid.ingredient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.posandroid.MainActivity;
import com.example.android.posandroid.R;
import com.example.android.posandroid.config.MessageHelper;
import com.example.android.posandroid.dao.IngredientDao;
import com.example.android.posandroid.dao.IngredientOrderDao;
import com.example.android.posandroid.dao.MenuDao;
import com.example.android.posandroid.model.Ingredient;
//handl
public class IngredientDetailActivity extends AppCompatActivity {
    IngredientDao id;
    IngredientOrderDao iod;
    Button btn_ing_detail_back, btn_ing_detail_edit, btn_ing_detail_order;
    EditText et_ing_detail_cost, et_ing_detail_stock, et_ing_detail_detail;
    TextView tv_ing_detail_name, tv_ing_detail_cost, tv_ing_detail_stock, tv_ing_detail_detail;
    int type;
    int ingId;
    Ingredient ingInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_detail);
        getSupportActionBar().setTitle("Hope's Table");
        id = new IngredientDao();
        iod = new IngredientOrderDao();
        type=1;
        ingId  = getIntent().getExtras().getInt("id");
        btn_ing_detail_back = (Button)findViewById(R.id.btn_ing_detail_back);
        btn_ing_detail_edit = (Button)findViewById(R.id.btn_ing_detail_edit);
        btn_ing_detail_order = (Button)findViewById(R.id.btn_ing_detail_order);
        et_ing_detail_cost = (EditText)findViewById(R.id.et_ing_detail_cost);
        et_ing_detail_stock = (EditText)findViewById(R.id.et_ing_detail_stock);
        et_ing_detail_detail = (EditText)findViewById(R.id.et_ing_detail_detail);
        tv_ing_detail_name = (TextView)findViewById(R.id.tv_ing_detail_name);
        tv_ing_detail_cost = (TextView)findViewById(R.id.tv_ing_detail_cost);
        tv_ing_detail_stock = (TextView)findViewById(R.id.tv_ing_detail_stock);
        tv_ing_detail_detail = (TextView)findViewById(R.id.tv_ing_detail_detail);
        btn_ing_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_ing_detail_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type==1){
                    btn_ing_detail_edit.setText("완료");
                    type=2;
                    initValue();
                }else if(type==2){
                    btn_ing_detail_edit.setText("수정");
                    checkEdit();
                }
            }
        });
        btn_ing_detail_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(IngredientDetailActivity.this);
                alert.setTitle(ingInfo.getName()+" 추가 주문");
                alert.setMessage("구입가격 : "+ingInfo.getCost()+"원");
                final EditText input = new EditText(IngredientDetailActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alert.setView(input);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        iod.insertIngredientOrder(ingId,Integer.valueOf(value),ingInfo.getCost());
                        id.editIngredientOrder(ingId,Integer.valueOf(value));
                        initValue();
                        MessageHelper.getInstance().sendMessage(MessageHelper.ActivityType.INGREDIENTACTIVITY, MessageHelper.MessageType.REFLASH);
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
        initValue();
    }

    private void initValue(){
        ingInfo = id.ingInfo(ingId);
        if(type==1){
            //조회
            tv_ing_detail_name.setText(ingInfo.getName());
            tv_ing_detail_cost.setText(String.valueOf(ingInfo.getCost()));
            tv_ing_detail_stock.setText(String.valueOf(ingInfo.getStock()));
            tv_ing_detail_detail.setText(ingInfo.getDetail());
            et_ing_detail_cost.setVisibility(View.GONE);
            et_ing_detail_stock.setVisibility(View.GONE);
            et_ing_detail_detail.setVisibility(View.GONE);
            tv_ing_detail_cost.setVisibility(View.VISIBLE);
            tv_ing_detail_stock.setVisibility(View.VISIBLE);
            tv_ing_detail_detail.setVisibility(View.VISIBLE);

        }else if(type==2){
            //수정
            et_ing_detail_cost.setText(String.valueOf(ingInfo.getCost()));
            et_ing_detail_stock.setText(String.valueOf(ingInfo.getStock()));
            et_ing_detail_detail.setText(ingInfo.getDetail());
            tv_ing_detail_cost.setVisibility(View.GONE);
            tv_ing_detail_stock.setVisibility(View.GONE);
            tv_ing_detail_detail.setVisibility(View.GONE);
            et_ing_detail_cost.setVisibility(View.VISIBLE);
            et_ing_detail_stock.setVisibility(View.VISIBLE);
            et_ing_detail_detail.setVisibility(View.VISIBLE);
        }
    }
    private void checkEdit(){
        String ingCost = et_ing_detail_cost.getText().toString();
        String ingStock = et_ing_detail_stock.getText().toString();
        String ingDetail = et_ing_detail_detail.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(ingCost)) {
            et_ing_detail_cost.setError(getString(R.string.error_field_required));
            focusView = et_ing_detail_cost;
            cancel = true;
        }
        if (TextUtils.isEmpty(ingStock)) {
            et_ing_detail_stock.setError(getString(R.string.error_field_required));
            focusView = et_ing_detail_stock;
            cancel = true;
        }
        if (TextUtils.isEmpty(ingDetail)) {
            et_ing_detail_detail.setError(getString(R.string.error_field_required));
            focusView = et_ing_detail_detail;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            id.editIngredient(ingId,Integer.parseInt(ingCost),ingDetail,Integer.parseInt(ingStock));
            MessageHelper.getInstance().sendMessage(MessageHelper.ActivityType.INGREDIENTACTIVITY, MessageHelper.MessageType.REFLASH);
            type=1;
            initValue();
        }
    }
}
