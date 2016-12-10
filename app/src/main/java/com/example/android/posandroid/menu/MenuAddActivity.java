package com.example.android.posandroid.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.posandroid.R;
import com.example.android.posandroid.config.MessageHelper;
import com.example.android.posandroid.dao.IngredientDao;
import com.example.android.posandroid.dao.MenuDao;

public class MenuAddActivity extends AppCompatActivity {
    EditText et_add_menu_name,et_add_menu_calory,et_add_menu_cost,et_add_menu_detail, et_add_ingredient_cost,et_add_ingredient_detail,et_add_ingredient_stock;
    TextView tv_add_ingredient_name;
    Button btn_menu_add_cancel, btn_menu_add_ok;
    IngredientDao ingredientDao;
    MenuDao menuDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);
        et_add_menu_name = (EditText)findViewById(R.id.et_add_menu_name);
        et_add_menu_calory = (EditText)findViewById(R.id.et_add_menu_calory);
        et_add_menu_cost = (EditText)findViewById(R.id.et_add_menu_cost);
        et_add_menu_detail = (EditText)findViewById(R.id.et_add_menu_detail);
        et_add_ingredient_cost = (EditText)findViewById(R.id.et_add_ingredient_cost);
        et_add_ingredient_detail = (EditText)findViewById(R.id.et_add_ingredient_detail);
        et_add_ingredient_stock = (EditText)findViewById(R.id.et_add_ingredient_stock);
        tv_add_ingredient_name = (TextView) findViewById(R.id.tv_add_ingredient_name);
        btn_menu_add_cancel = (Button) findViewById(R.id.btn_menu_add_cancel);
        btn_menu_add_ok = (Button)findViewById(R.id.btn_menu_add_ok);
        ingredientDao = new IngredientDao();
        menuDao = new MenuDao();

        btn_menu_add_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        et_add_menu_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                tv_add_ingredient_name.setText(s+" 재료");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_menu_add_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String menuName = et_add_menu_name.getText().toString();
                String menuCalory = et_add_menu_calory.getText().toString();
                String menuCost = et_add_menu_cost.getText().toString();
                String menuDetail = et_add_menu_detail.getText().toString();
                String ingredientName = tv_add_ingredient_name.getText().toString();
                String ingredientCost = et_add_ingredient_cost.getText().toString();
                String ingredientDetail = et_add_ingredient_detail.getText().toString();
                String ingredientStock = et_add_ingredient_stock.getText().toString();
                boolean cancel = false;
                View focusView = null;

                if (TextUtils.isEmpty(menuName)) {
                    et_add_menu_name.setError(getString(R.string.error_field_required));
                    focusView = et_add_menu_name;
                    cancel = true;
                }
                if (TextUtils.isEmpty(menuCalory)) {
                    et_add_menu_calory.setError(getString(R.string.error_field_required));
                    focusView = et_add_menu_calory;
                    cancel = true;
                }
                if (TextUtils.isEmpty(menuCost)) {
                    et_add_menu_cost.setError(getString(R.string.error_field_required));
                    focusView = et_add_menu_cost;
                    cancel = true;
                }
                if (TextUtils.isEmpty(menuDetail)) {
                    et_add_menu_detail.setError(getString(R.string.error_field_required));
                    focusView = et_add_menu_detail;
                    cancel = true;
                }
                if (TextUtils.isEmpty(ingredientCost)) {
                    et_add_ingredient_cost.setError(getString(R.string.error_field_required));
                    focusView = et_add_ingredient_cost;
                    cancel = true;
                }
                if (TextUtils.isEmpty(ingredientDetail)) {
                    et_add_ingredient_detail.setError(getString(R.string.error_field_required));
                    focusView = et_add_ingredient_detail;
                    cancel = true;
                }
                if (TextUtils.isEmpty(ingredientStock)) {
                    et_add_ingredient_stock.setError(getString(R.string.error_field_required));
                    focusView = et_add_ingredient_stock;
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();
                } else {
                    menuDao.insertMenu(menuName,Integer.parseInt(menuCost),menuDetail,Integer.parseInt(menuCalory));
                    ingredientDao.insertIngredient(ingredientName,Integer.parseInt(ingredientCost),ingredientDetail,Integer.parseInt(ingredientCost),menuName);
                    MessageHelper.getInstance().sendMessage(MessageHelper.ActivityType.MENUACTIVITY, MessageHelper.MessageType.REFLASH);
                    finish();
                }
            }

        });


    }
}
