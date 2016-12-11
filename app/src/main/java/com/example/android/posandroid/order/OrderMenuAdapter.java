package com.example.android.posandroid.order;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.android.posandroid.dao.IngredientDao;
import com.example.android.posandroid.ingredient.IngredientView;
import com.example.android.posandroid.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by User on 2016-11-17.
 */
public class OrderMenuAdapter extends BaseAdapter {

    List<OrderMenuItem> items = new ArrayList<OrderMenuItem>();
    Context mContext;
    Realm realm;
    int tagId;
    public OrderMenuAdapter(Context context,int tagId) {
        mContext=context;
        realm = Realm.getDefaultInstance();
        this.tagId = tagId;
/*        id = new IngredientDao();
        items = id.ingredientList();*/
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public OrderMenuItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final OrderMenuView view;

        if (convertView == null) {
            view = new OrderMenuView(parent.getContext());
        } else {
            view = (OrderMenuView) convertView;
        }
        view.setMenuInfo(items.get(position));
        view.tv_item_order_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View views) {
                int now = Integer.valueOf(view.tv_item_order_count.getText().toString());
                if(now>=1){
                    view.tv_item_order_count.setText(String.valueOf(now-1));
                    items.get(position).setCount(now-1);
                }
            }
        });
        view.tv_item_order_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View views) {
                int now = Integer.valueOf(view.tv_item_order_count.getText().toString());
                if(now<10) {
                    view.tv_item_order_count.setText(String.valueOf(now + 1));
                    items.get(position).setCount(now+1);
                }
            }
        });

        return view;
    }


    public void add(OrderMenuItem data) {
        items.add(data);
    }
    public void clear(){
        items.clear();
    }

    public List<OrderMenuItem> getList(){
        return items;
    }

}
