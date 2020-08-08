package com.bytedance.njudy.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 用于加载iconfont的TextView
 */
public class IconFontTextView extends AppCompatTextView {
    /**
     * 所有IconFontTextView公用typeface
     */
    private static Typeface typeface;

    {
        typeface = Typeface.createFromAsset(getContext().getAssets(), "iconfont.ttf");
        setTypeface(typeface);
    }

    public IconFontTextView(Context context) {
        super(context);
    }

    public IconFontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

}
