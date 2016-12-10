package com.example.android.posandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.posandroid.config.PropertyManager;

public class LoginActivity extends AppCompatActivity {
    ActionBar actionBar;
    EditText et_pw;
    Button btn_login,btn_change_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView textView = new TextView(getApplicationContext());
        textView.setText("Hope's Table");
        textView.setTextSize(20);
        textView.setTextColor(getResources().getColor(R.color.colorWhite));
        actionBar = getSupportActionBar();
        actionBar.setCustomView(textView, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        et_pw = (EditText)findViewById(R.id.et_pw);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_change_pw = (Button)findViewById(R.id.btn_change_pw);
        btn_change_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ChangePwActivity.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }


    private void attemptLogin() {
        et_pw.setError(null);

        String password = et_pw.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            et_pw.setError(getString(R.string.error_invalid_password));
            focusView = et_pw;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            if(PropertyManager.getInstance().isPassword().equals(password)){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(getApplication(),"Welcome!!",Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(getApplication(),"비밀번호를 확인해주세요!",Toast.LENGTH_SHORT).show();
                et_pw.setText("");
            }
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


}
