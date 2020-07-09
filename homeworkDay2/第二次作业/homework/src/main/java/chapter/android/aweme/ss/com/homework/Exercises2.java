package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 *
 * 解决方法：使用递归遍历viewGroup的view数量，通过监听点击事件显示出来
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise2);
        final int childViewCount = getAllChildViewCount(LayoutInflater.from(this).inflate(R.layout.exercise2,null));
        //设置text的点击事件
        final TextView text = (TextView) findViewById(R.id.tv_center);
        text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                text.setText("View数量："+ childViewCount);
            }
        });

    }

    public int getAllChildViewCount(View view) {
        int res = 0;
        //如果view为空，直接返回0
        if (null == view) {
            return 0;
        }
        if (view instanceof ViewGroup) {
            //遍历ViewGroup,是子view加1，是ViewGroup递归调用
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child instanceof ViewGroup) {
                    res += getAllChildViewCount(((ViewGroup) view).getChildAt(i));
                } else {
                    res++;
                }
            }
        } else {
            res++;
        }
        return res;
    }
}
