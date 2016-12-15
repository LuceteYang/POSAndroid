package com.example.android.posandroid.sales;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.android.posandroid.order.OrderMenuItem;
import com.example.android.posandroid.order.OrderMenuView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by User on 2016-11-17.
 */
public class SalesAdapter extends BaseAdapter {

    List<SalesItem> items = new ArrayList<SalesItem>();
    Context mContext;
    Realm realm;
    public SalesAdapter(Context context) {
        mContext=context;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public SalesItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final SalesView view;

        if (convertView == null) {
            view = new SalesView(parent.getContext());
        } else {
            view = (SalesView) convertView;
        }
        view.setMenuInfo(items.get(position));

        return view;
    }


    public void add(SalesItem data) {
        items.add(data);
    }
    public void clear(){
        items.clear();
    }

    public List<SalesItem> getList(){
        return items;
    }

    public void setList(List<SalesItem> list){
        this.items = list;
    }

}
