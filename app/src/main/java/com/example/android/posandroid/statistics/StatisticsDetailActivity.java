package com.example.android.posandroid.statistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.posandroid.R;
import com.example.android.posandroid.dao.MenuDao;
import com.example.android.posandroid.dao.OrderDao;
import com.example.android.posandroid.dao.OrderMenuDao;

public class StatisticsDetailActivity extends AppCompatActivity {
    TextView tv_best_statistics, tv_best_content,tv_worst_statistics,tv_worst_content;
    Button btn_statistics_ok;
    int type;
    OrderDao od;
    MenuDao md;
    OrderMenuDao omd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_detail);
        tv_best_statistics = (TextView)findViewById(R.id.tv_best_statistics);
        tv_best_content = (TextView)findViewById(R.id.tv_best_content);
        tv_worst_statistics = (TextView)findViewById(R.id.tv_worst_statistics);
        tv_worst_content = (TextView)findViewById(R.id.tv_worst_content);
        btn_statistics_ok= (Button)findViewById(R.id.btn_statistics_ok);
        type  = getIntent().getExtras().getInt("type");
        od = new OrderDao();
        md = new MenuDao();
        omd = new OrderMenuDao();
        btn_statistics_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
