package com.example.android.posandroid.statistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.posandroid.R;
import com.example.android.posandroid.order.PayActivity;

public class StatisticsActivity extends AppCompatActivity {
    Button btn_statistics_menu, btn_statistics_table, btn_statistics_weekday,btn_statistics_time,btn_statistics_back;
    int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        btn_statistics_menu = (Button)findViewById(R.id.btn_statistics_menu);
        btn_statistics_table = (Button)findViewById(R.id.btn_statistics_table);
        btn_statistics_weekday = (Button)findViewById(R.id.btn_statistics_weekday);
        btn_statistics_time = (Button)findViewById(R.id.btn_statistics_time);
        btn_statistics_back = (Button)findViewById(R.id.btn_statistics_back);
        btn_statistics_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),StatisticsDetailActivity.class);
                i.putExtra("type",1);
                startActivity(i);

            }
        });
        btn_statistics_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),StatisticsDetailActivity.class);
                i.putExtra("type",2);
                startActivity(i);

            }
        });
        btn_statistics_weekday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),StatisticsDetailActivity.class);
                i.putExtra("type",3);
                startActivity(i);

            }
        });
        btn_statistics_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),StatisticsDetailActivity.class);
                i.putExtra("type",4);
                startActivity(i);
            }
        });
        btn_statistics_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
