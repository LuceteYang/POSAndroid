package com.example.android.posandroid.menu;

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
import com.example.android.posandroid.model.Menu;

public class MenuActivity extends AppCompatActivity {
    Button btn_menu_cancle, btn_menu_add;
    ListView lv_menu;
    MenuAdapter ma;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageHelper.MessageType.REFLASH:
                    ma.notifyDataSetChanged();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setTitle("Hope's Table");
        btn_menu_add = (Button)findViewById(R.id.btn_menu_add);
        btn_menu_cancle = (Button)findViewById(R.id.btn_menu_cancle);
        lv_menu= (ListView)findViewById(R.id.lv_menu);
        ma = new MenuAdapter(getApplicationContext());
        lv_menu.setAdapter(ma);
        MessageHelper.getInstance().addActivity(new MessageHelper.ActivityData(MessageHelper.ActivityType.MENUACTIVITY, handler));
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),MenuDetailActivity.class);
                final Menu menu = (Menu)adapterView.getAdapter().getItem(i);
                intent.putExtra("name",menu.getName());
                startActivity(intent);
            }
        });
        btn_menu_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_menu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MenuAddActivity.class));
            }
        });
    }
}
