package com.bytedance.njudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.bytedance.njudy.API.APIService;
import com.bytedance.njudy.API.VideoItem;
import com.bytedance.njudy.R;
import com.bytedance.njudy.base.BaseActivity;
import com.bytedance.njudy.bean.DataCreate;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 启动APP的过场界面
 */
public class SplashActivity extends BaseActivity {
    List<VideoItem> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //dataCreate.getData();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://beiyou.bytedance.com/")
                        .addConverterFactory(GsonConverterFactory.create()) //添加Gson
                        .build();

                APIService apiService = retrofit.create(APIService.class);
                Call<List<VideoItem>> call = apiService.getDouyinitems();
                try {
                    new DataCreate().initData(call.execute().body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        setFullScreen();

        new CountDownTimer(500, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.start();

    }


}
