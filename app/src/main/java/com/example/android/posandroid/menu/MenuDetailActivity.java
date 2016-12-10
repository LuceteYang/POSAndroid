package com.example.android.posandroid.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.posandroid.R;
import com.example.android.posandroid.config.MessageHelper;
import com.example.android.posandroid.config.PropertyManager;
import com.example.android.posandroid.dao.IngredientDao;
import com.example.android.posandroid.dao.MenuDao;
import com.example.android.posandroid.model.Menu;

public class MenuDetailActivity extends AppCompatActivity {
    MenuDao md;
    IngredientDao id;
    Button btn_menu_detail_back,btn_menu_detail_delete,btn_menu_detail_edit;
    TextView tv_menu_detail_name,tv_menu_detail_calory,tv_menu_detail_cost,tv_menu_detail_detail;
    EditText et_menu_detail_name,et_menu_detail_calory,et_menu_detail_cost,et_menu_detail_detail;
    int type;
    String menuName;
    Menu menuinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        getSupportActionBar().setTitle("Hope's Table");
        md = new MenuDao();
        id = new IngredientDao();
        type=1;
        menuName  = getIntent().getExtras().getString("name");
        btn_menu_detail_back = (Button)findViewById(R.id.btn_menu_detail_back);
        btn_menu_detail_delete = (Button)findViewById(R.id.btn_menu_detail_delete);
        btn_menu_detail_edit = (Button)findViewById(R.id.btn_menu_detail_edit);
        tv_menu_detail_name = (TextView)findViewById(R.id.tv_menu_detail_name);
        tv_menu_detail_calory = (TextView)findViewById(R.id.tv_menu_detail_calory);
        tv_menu_detail_cost = (TextView)findViewById(R.id.tv_menu_detail_cost);
        tv_menu_detail_detail = (TextView)findViewById(R.id.tv_menu_detail_detail);
        et_menu_detail_name = (EditText)findViewById(R.id.et_menu_detail_name);
        et_menu_detail_calory = (EditText)findViewById(R.id.et_menu_detail_calory);
        et_menu_detail_cost = (EditText)findViewById(R.id.et_menu_detail_cost);
        et_menu_detail_detail = (EditText)findViewById(R.id.et_menu_detail_detail);
        btn_menu_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_menu_detail_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type==1){
                    btn_menu_detail_edit.setText("완료");
                    type=2;
                    initValue();
                }else if(type==2){
                    btn_menu_detail_edit.setText("수정");
                    checkEdit();
                }
            }
        });
        btn_menu_detail_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MenuDetailActivity.this);
                alert.setTitle("메뉴를 삭제하시겠습니까?");
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        md.deleteMenu(menuinfo.getName());
//                id.deleteIngredientByMenu(menuinfo.getName());
                        MessageHelper.getInstance().sendMessage(MessageHelper.ActivityType.MENUACTIVITY, MessageHelper.MessageType.REFLASH);
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

        initValue();
    }
    private void initValue(){
        menuinfo = md.menuInfo(menuName);
        if(type==1){
        //조회
            tv_menu_detail_name.setText(menuinfo.getName());
            tv_menu_detail_calory.setText(String.valueOf(menuinfo.getCalory()));
            tv_menu_detail_cost.setText(String.valueOf(menuinfo.getCost()));
            tv_menu_detail_detail.setText(menuinfo.getDetail());
            et_menu_detail_name.setVisibility(View.GONE);
            et_menu_detail_calory.setVisibility(View.GONE);
            et_menu_detail_cost.setVisibility(View.GONE);
            et_menu_detail_detail.setVisibility(View.GONE);
            tv_menu_detail_name.setVisibility(View.VISIBLE);
            tv_menu_detail_calory.setVisibility(View.VISIBLE);
            tv_menu_detail_cost.setVisibility(View.VISIBLE);
            tv_menu_detail_detail.setVisibility(View.VISIBLE);

        }else if(type==2){
        //수정
            et_menu_detail_name.setText(menuinfo.getName());
            et_menu_detail_calory.setText(String.valueOf(menuinfo.getCalory()));
            et_menu_detail_cost.setText(String.valueOf(menuinfo.getCost()));
            et_menu_detail_detail.setText(menuinfo.getDetail());
            tv_menu_detail_name.setVisibility(View.GONE);
            tv_menu_detail_calory.setVisibility(View.GONE);
            tv_menu_detail_cost.setVisibility(View.GONE);
            tv_menu_detail_detail.setVisibility(View.GONE);
            et_menu_detail_name.setVisibility(View.VISIBLE);
            et_menu_detail_calory.setVisibility(View.VISIBLE);
            et_menu_detail_cost.setVisibility(View.VISIBLE);
            et_menu_detail_detail.setVisibility(View.VISIBLE);
        }
    }
    private void checkEdit(){
        String menuName = et_menu_detail_name.getText().toString();
        String menuCalory = et_menu_detail_calory.getText().toString();
        String menuCost = et_menu_detail_cost.getText().toString();
        String menuDetail = et_menu_detail_detail.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(menuName)) {
            et_menu_detail_name.setError(getString(R.string.error_field_required));
            focusView = et_menu_detail_name;
            cancel = true;
        }
        if (TextUtils.isEmpty(menuCalory)) {
            et_menu_detail_calory.setError(getString(R.string.error_field_required));
            focusView = et_menu_detail_calory;
            cancel = true;
        }
        if (TextUtils.isEmpty(menuCost)) {
            et_menu_detail_cost.setError(getString(R.string.error_field_required));
            focusView = et_menu_detail_cost;
            cancel = true;
        }
        if (TextUtils.isEmpty(menuDetail)) {
            et_menu_detail_detail.setError(getString(R.string.error_field_required));
            focusView = et_menu_detail_detail;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            id.editIngredientByMenu(menuinfo.getName(),menuName);
            md.editMenu(menuinfo.getName(),menuName,Integer.parseInt(menuCost),menuDetail,Integer.parseInt(menuCalory));
            this.menuName=menuName;
            MessageHelper.getInstance().sendMessage(MessageHelper.ActivityType.MENUACTIVITY, MessageHelper.MessageType.REFLASH);
            type=1;
            initValue();
        }
    }
}
