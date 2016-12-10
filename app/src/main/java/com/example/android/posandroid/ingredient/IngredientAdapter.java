package com.example.android.posandroid.ingredient;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.android.posandroid.dao.IngredientDao;
import com.example.android.posandroid.dao.MenuDao;
import com.example.android.posandroid.menu.MenuView;
import com.example.android.posandroid.model.Ingredient;
import com.example.android.posandroid.model.Menu;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by User on 2016-11-17.
 */
public class IngredientAdapter extends BaseAdapter {

    List<Ingredient> items = new ArrayList<Ingredient>();
    Context mContext;
    Realm realm;
    IngredientDao id;
    public IngredientAdapter(Context context) {
        mContext=context;
        realm = Realm.getDefaultInstance();
        id = new IngredientDao();
        items = id.ingredientList();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Ingredient getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        IngredientView view;

        if (convertView == null) {
            view = new IngredientView(parent.getContext());
        } else {
            view = (IngredientView) convertView;
        }
        view.setIngredientInfo(items.get(position));
        return view;
    }


    public void add(Ingredient data) {
        items.add(data);
    }
    public void clear(){
        items.clear();
    }

}
