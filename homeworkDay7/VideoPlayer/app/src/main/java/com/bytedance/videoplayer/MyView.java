package com.bytedance.videoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.VideoView;

public class MyView extends VideoView {
    //最终的视频资源宽度
    private int mVideoWidth=480;
    //最终视频资源高度
    private int mVideoHeight=480;
    //视频资源原始宽度
    private int videoRealW=1;
    //视频资源原始高度
    private int videoRealH=1;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
                int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
               setMeasuredDimension(width,height);
           }

}
