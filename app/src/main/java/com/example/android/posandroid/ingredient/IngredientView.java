package com.example.android.posandroid.ingredient;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.posandroid.R;
import com.example.android.posandroid.model.Ingredient;
import com.example.android.posandroid.model.Menu;

/**
 * Created by User on 2016-10-06.
 */
public class IngredientView extends RelativeLayout {
    TextView tv_ingredient;
    TextView tv_stock;
    TextView tv_cost;

    public IngredientView(Context context) {
        super(context);
        init();
    }

    public IngredientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_ingredient_item, this);
        this.tv_ingredient = (TextView) findViewById(R.id.tv_ingredient_item_name);
        this.tv_stock = (TextView) findViewById(R.id.tv_ingredient_item_stock);
        this.tv_cost = (TextView) findViewById(R.id.tv_ingredient_item_cost);
    }

    public void setIngredientInfo(Ingredient ingredientInfo) {
        tv_ingredient.setText(ingredientInfo.getName());
        tv_cost.setText(String.valueOf(ingredientInfo.getCost())+" 원");
        tv_stock.setText(String.valueOf(ingredientInfo.getStock()));
    }
}
