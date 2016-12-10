package com.example.android.posandroid.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.posandroid.R;
import com.example.android.posandroid.model.Menu;

/**
 * Created by User on 2016-10-06.
 */
public class MenuView extends RelativeLayout {
    TextView tv_menu;
    TextView tv_price;

    public MenuView(Context context) {
        super(context);
        init();
    }

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_menu_item, this);
        this.tv_menu = (TextView) findViewById(R.id.tv_item_menu);
        this.tv_price = (TextView) findViewById(R.id.tv_item_price);
    }

    public void setVocaInfo(Menu menuinfo) {
        tv_menu.setText(menuinfo.getName());
        tv_price.setText(String.valueOf(menuinfo.getCost()));
    }
}
