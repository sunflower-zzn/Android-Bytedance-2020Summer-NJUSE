package com.bytedance.njudy.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;

/**
 * RvViewHolder 基类
 */
public class BaseRvViewHolder extends RecyclerView.ViewHolder {

    public BaseRvViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
