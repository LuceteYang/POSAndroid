package com.example.android.posandroid.ingredient;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.os.Handler;
import android.widget.ListView;

import com.example.android.posandroid.R;
import com.example.android.posandroid.config.MessageHelper;
import com.example.android.posandroid.menu.MenuAddActivity;
import com.example.android.posandroid.menu.MenuDetailActivity;
import com.example.android.posandroid.model.Ingredient;

public class IngredientActivity extends AppCompatActivity {
    Button btn_cancle;
    ListView lv_ingredient;
    IngredientAdapter ia;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageHelper.MessageType.REFLASH:
                    ia.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        getSupportActionBar().setTitle("Hope's Table");
        btn_cancle = (Button)findViewById(R.id.btn_ingredient_back);
        lv_ingredient= (ListView)findViewById(R.id.lv_ingredient);
        ia = new IngredientAdapter(getApplicationContext());
        lv_ingredient.setAdapter(ia);
        MessageHelper.getInstance().addActivity(new MessageHelper.ActivityData(MessageHelper.ActivityType.INGREDIENTACTIVITY, handler));
        lv_ingredient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),IngredientDetailActivity.class);
                final Ingredient ingredient = (Ingredient)adapterView.getAdapter().getItem(i);
                intent.putExtra("id",ingredient.getId());
                startActivity(intent);
            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
