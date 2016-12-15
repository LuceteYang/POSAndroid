package com.example.android.posandroid.sales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.posandroid.R;

public class SalesActivity extends AppCompatActivity {
    Button btn_sales_back, btn_sales_income, btn_sales_expense, btn_sales_all;
    ListView lv_sales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        btn_sales_back = (Button)findViewById(R.id.btn_sales_back);
        btn_sales_income = (Button)findViewById(R.id.btn_sales_income);
        btn_sales_expense = (Button)findViewById(R.id.btn_sales_expense);
        btn_sales_all = (Button)findViewById(R.id.btn_sales_all);
        lv_sales = (ListView)findViewById(R.id.lv_sales);
        btn_sales_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
