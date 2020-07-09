package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        final TextView text = (TextView) findViewById(R.id.text);
        final TextView text1 = (TextView) findViewById(R.id.text1);
        final EditText phone = (EditText)findViewById(R.id.phone);
        final EditText password = (EditText)findViewById(R.id.password);
        final Switch switch1 = (Switch)findViewById(R.id.switch1);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if("登录".equals(button.getText().toString())){
                    if(TextUtils.isEmpty(phone.getText().toString().trim())){  //判断手机号不为空【后续可加正则】
                        Toast.makeText(MainActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(password.getText().toString().trim())){  //判断密码不为空【后续可加正则】
                        Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        phone.setVisibility(View.INVISIBLE); //设置不可见
                        password.setVisibility(View.INVISIBLE);
                        switch1.setVisibility(View.INVISIBLE);
                        text1.setVisibility(View.VISIBLE);  //这里报错text1为null
                        button.setText("再来一遍？");
                    }
                }
                else{
                    refresh();
                }

            }

            private void refresh() {
                finish();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    switch1.setText("隐藏密码");
                }
                else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    switch1.setText("显示密码");

                }
            }
        });

    }

}