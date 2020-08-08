package com.bytedance.njudy.activity;

import com.bytedance.njudy.R;
import com.bytedance.njudy.base.BaseActivity;
import com.bytedance.njudy.fragment.RecommendFragment;

/**
 * 推荐界面全屏播放，仿抖音
 */
public class PlayListActivity extends BaseActivity {
    public static int initPos;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_play_list;
    }

    @Override
    protected void init() {
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new RecommendFragment()).commit();
    }
}
