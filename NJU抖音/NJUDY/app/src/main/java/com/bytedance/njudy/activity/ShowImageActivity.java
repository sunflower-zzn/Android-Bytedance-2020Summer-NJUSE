package com.bytedance.njudy.activity;

import android.widget.ImageView;

import com.bytedance.njudy.R;
import com.bytedance.njudy.base.BaseActivity;

import butterknife.BindView;

/**
 * 瀑布流页面展示视频
 */
public class ShowImageActivity extends BaseActivity {
    @BindView(R.id.iv_head)
    ImageView ivHead;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_show_image;
    }

    @Override
    protected void init() {
        ivHead.setOnClickListener(v -> finish());

        int headRes = getIntent().getIntExtra("res", 0);
        ivHead.setImageResource(headRes);
    }
}
