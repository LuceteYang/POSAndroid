package com.example.android.posandroid.menu;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.android.posandroid.model.Menu;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by User on 2016-11-17.
 */
public class MenuaAdapter extends BaseAdapter {

    List<Menu> items = new ArrayList<Menu >();
    Context mContext;
    Realm realm;
    public MenuaAdapter(Context context) {
        mContext=context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Menu getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MenuView view;

        if (convertView == null) {
            view = new MenuView(parent.getContext());
        } else {
            view = (MenuView) convertView;
        }
        view.setVocaInfo(items.get(position));
        return view;
    }


    public void add(Menu data) {
        items.add(data);
    }
    public void clear(){
        items.clear();
    }

}
