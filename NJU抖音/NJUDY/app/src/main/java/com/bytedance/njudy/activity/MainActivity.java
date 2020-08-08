package com.bytedance.njudy.activity;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bytedance.njudy.R;
import com.bytedance.njudy.base.BaseActivity;
import com.bytedance.njudy.base.CommPagerAdapter;
import com.bytedance.njudy.fragment.MainFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {
    public static int curMainPage;
    /**
     * 连续按返回键退出时间
     */
    private final int EXIT_TIME = 2000;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private CommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private MainFragment mainFragment = new MainFragment();
    /**
     * 上次点击返回键时间
     */
    private long lastTime;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        fragments.add(mainFragment);
        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"", ""});
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        //双击返回退出App
        if (System.currentTimeMillis() - lastTime > EXIT_TIME) {
            if (viewPager.getCurrentItem() == 1) {
                viewPager.setCurrentItem(0);
            } else {
                Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                lastTime = System.currentTimeMillis();
            }
        } else {
            super.onBackPressed();
        }
    }

}
