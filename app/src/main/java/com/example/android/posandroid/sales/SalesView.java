package com.example.android.posandroid.sales;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.posandroid.R;
import com.example.android.posandroid.order.OrderMenuItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by User on 2016-10-06.
 */
public class SalesView extends RelativeLayout {
    TextView tv_item_sales;
    TextView tv_item_sales_cost;
    TextView tv_item_sales_date;

    public SalesView(Context context) {
        super(context);
        init();
    }

    public SalesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_sales_item, this);
        this.tv_item_sales = (TextView) findViewById(R.id.tv_item_sales);
        this.tv_item_sales_cost = (TextView) findViewById(R.id.tv_item_sales_cost);
        this.tv_item_sales_date = (TextView) findViewById(R.id.tv_item_sales_date);
    }

    public void setMenuInfo(SalesItem salesInfo) {
        if(salesInfo.getType()==1){
            tv_item_sales.setText(salesInfo.getSaleTitle()+"번 테이블 수입");
            tv_item_sales_cost.setText("+"+String.valueOf(salesInfo.getCost()));
        }else if(salesInfo.getType()==2){
            tv_item_sales.setText(salesInfo.getSaleTitle()+" 구입");
            tv_item_sales_cost.setText(("-"+String.valueOf(salesInfo.getCost())));
        }
        DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate = sdFormat.format(salesInfo.getDate());
        tv_item_sales_date.setText(tempDate);
    }
}
