package com.bytedance.videoplayer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private LinearLayout layout;
    private Button start;
    private Button pause;
    private Button replay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.play);
        videoView.setVideoPath(getVideoPath(R.raw.video));
        videoView.setMediaController(new MediaController(this));
        layout = findViewById(R.id.layout);
        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        replay = findViewById(R.id.replay);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(0);
                videoView.start();
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //video布局设置
        ViewGroup.LayoutParams params = videoView.getLayoutParams();
        //标题栏
        ActionBar supportActionBar = MainActivity.this.getSupportActionBar();
        //系统状态栏
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            assert supportActionBar != null;
            supportActionBar.hide();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            layout.setVisibility(View.GONE);
        } else {
            params.height = 700;  //注意这里的单位是px！
            assert supportActionBar != null;
            supportActionBar.show();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            layout.setVisibility(View.VISIBLE);
        }
        videoView.setLayoutParams(params);
    }

    private String getVideoPath(int resId) {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }
}
