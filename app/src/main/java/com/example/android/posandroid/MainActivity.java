package com.example.android.posandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.posandroid.config.MessageHelper;
import com.example.android.posandroid.config.PropertyManager;
import com.example.android.posandroid.dao.UserDao;
import com.example.android.posandroid.ingredient.IngredientActivity;
import com.example.android.posandroid.menu.MenuActivity;
import com.example.android.posandroid.order.TableInfoActivity;


public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    RelativeLayout btn_menu, btn_sales, btn_statistic, btn_ingredient;
    UserDao ud;
    TextView tv_main_table_one,tv_main_table_two,tv_main_table_three,tv_main_table_four,tv_main_table_five,tv_main_table_six;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageHelper.MessageType.REFLASH:
                    initValue();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ud = new UserDao();
        TextView textView = new TextView(getApplicationContext());
        textView.setText("Hope's Table");
        textView.setTextSize(20);
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        if (PropertyManager.getInstance().isFirst()) {
            firstDataInput();
        }
        actionBar = getSupportActionBar();
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        MessageHelper.getInstance().addActivity(new MessageHelper.ActivityData(MessageHelper.ActivityType.MAINACTIVITY, handler));
        initControl();
        initValue();
    }
    private void initControl(){
        btn_menu = (RelativeLayout)findViewById(R.id.btn_menu);
        btn_sales = (RelativeLayout)findViewById(R.id.btn_sales);
        btn_statistic = (RelativeLayout)findViewById(R.id.btn_statistic);
        btn_ingredient = (RelativeLayout)findViewById(R.id.btn_ingredient);
        tv_main_table_one = (TextView)findViewById(R.id.tv_main_table_one);
        tv_main_table_two = (TextView)findViewById(R.id.tv_main_table_two);
        tv_main_table_three = (TextView)findViewById(R.id.tv_main_table_three);
        tv_main_table_four = (TextView)findViewById(R.id.tv_main_table_four);
        tv_main_table_five = (TextView)findViewById(R.id.tv_main_table_five);
        tv_main_table_six = (TextView)findViewById(R.id.tv_main_table_six);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkPassword(MenuActivity.class);
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));

            }
        });
        btn_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkPassword(IngredientActivity.class);
                startActivity(new Intent(getApplicationContext(),IngredientActivity.class));

            }
        });
        btn_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkPassword(SalesActivity.class);
                startActivity(new Intent(getApplicationContext(),SalesActivity.class));

            }
        });
        btn_statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkPassword(StatisticsActivity.class);
                startActivity(new Intent(getApplicationContext(),StatisticsActivity.class));

            }
        });
        tv_main_table_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TableInfoActivity.class);
                i.putExtra("tableId",1);
                startActivity(i);
            }
        });
        tv_main_table_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TableInfoActivity.class);
                i.putExtra("tableId",2);
                startActivity(i);
            }
        });
        tv_main_table_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TableInfoActivity.class);
                i.putExtra("tableId",3);
                startActivity(i);
            }
        });
        tv_main_table_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TableInfoActivity.class);
                i.putExtra("tableId",4);
                startActivity(i);
            }
        });
        tv_main_table_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TableInfoActivity.class);
                i.putExtra("tableId",5);
                startActivity(i);
            }
        });
        tv_main_table_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TableInfoActivity.class);
                i.putExtra("tableId",6);
                startActivity(i);
            }
        });

    }

    private void initValue(){

    }

    private void checkPassword(final Class act){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("비밀번호 입력");
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if(ud.Login(value)){
                    startActivity(new Intent(getApplicationContext(),act));
                }else{
                    Toast.makeText(getApplicationContext(),"비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
        alert.show();
    }

    private void firstDataInput(){
        ud.insertUser("000000");
    }

}
