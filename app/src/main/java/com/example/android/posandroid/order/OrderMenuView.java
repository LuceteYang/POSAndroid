package com.example.android.posandroid.order;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.posandroid.R;
import com.example.android.posandroid.model.Ingredient;

/**
 * Created by User on 2016-10-06.
 */
public class OrderMenuView extends RelativeLayout {
    TextView tv_item_order_name;
    TextView tv_item_order_plus;
    TextView tv_item_order_minus;
    TextView tv_item_order_count;

    public OrderMenuView(Context context) {
        super(context);
        init();
    }

    public OrderMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_order_item, this);
        this.tv_item_order_name = (TextView) findViewById(R.id.tv_item_order_name);
        this.tv_item_order_plus = (TextView) findViewById(R.id.tv_item_order_plus);
        this.tv_item_order_minus = (TextView) findViewById(R.id.tv_item_order_minus);
        this.tv_item_order_count = (TextView) findViewById(R.id.tv_item_order_count);
    }

    public void setMenuInfo(OrderMenuItem menuInfo) {
        tv_item_order_name.setText(menuInfo.getMenuName());
        tv_item_order_count.setText(String.valueOf(menuInfo.getCount()));
    }
}
