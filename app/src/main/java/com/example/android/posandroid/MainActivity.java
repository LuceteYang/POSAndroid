package com.example.android.posandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.posandroid.dao.UserDao;
import com.example.android.posandroid.ingredient.IngredientActivity;
import com.example.android.posandroid.menu.MenuActivity;


public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    RelativeLayout btn_menu, btn_sales, btn_statistic, btn_ingredient;
    UserDao ud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ud = new UserDao();
        TextView textView = new TextView(getApplicationContext());
        textView.setText("Hope's Table");
        textView.setTextSize(20);
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar = getSupportActionBar();
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        btn_menu = (RelativeLayout)findViewById(R.id.btn_menu);
        btn_sales = (RelativeLayout)findViewById(R.id.btn_sales);
        btn_statistic = (RelativeLayout)findViewById(R.id.btn_statistic);
        btn_ingredient = (RelativeLayout)findViewById(R.id.btn_ingredient);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPassword(MenuActivity.class);
            }
        });
        btn_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPassword(IngredientActivity.class);
            }
        });
        btn_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPassword(SalesActivity.class);
            }
        });
        btn_statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPassword(StatisticsActivity.class);
            }
        });


    }

    public void checkPassword(final Class act){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("비밀번호 입력");
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if(ud.Login(value)){
                    startActivity(new Intent(getApplicationContext(),act));
                }else{
                    Toast.makeText(getApplicationContext(),"비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
        alert.show();
    }



}
