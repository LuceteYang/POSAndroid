package com.example.android.posandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.posandroid.config.PropertyManager;

public class ChangePwActivity extends AppCompatActivity {

    EditText et_new_pw, et_old_pw;

    Button btn_change_cancel, btn_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);
        et_new_pw = (EditText)findViewById(R.id.et_new_pw);
        et_old_pw = (EditText)findViewById(R.id.et_old_pw);
        btn_change_cancel = (Button)findViewById(R.id.btn_change_cancel);
        btn_change = (Button)findViewById(R.id.btn_change);
        btn_change_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old_pw = et_old_pw.getText().toString();
                String new_pw = et_new_pw.getText().toString();
                if(old_pw.equals(PropertyManager.getInstance().isPassword())){
                    if(isPasswordValid(new_pw)){
                        PropertyManager.getInstance().setPassword(new_pw);
                        Toast.makeText(getApplication(),"비밀번호가 변경되었습니다.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else{
                        et_new_pw.setError(getString(R.string.error_invalid_password));
                        et_new_pw.requestFocus();
                        Toast.makeText(getApplication(),"비밀번호가 너무 짧습니다.",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    et_old_pw.setError(getString(R.string.error_invalid_password));
                    et_old_pw.requestFocus();
                    Toast.makeText(getApplication(),"기존 비밀번호를 확인해주세요!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
