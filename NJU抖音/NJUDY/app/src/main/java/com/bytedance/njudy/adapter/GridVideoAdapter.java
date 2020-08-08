package com.bytedance.njudy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.njudy.R;
import com.bytedance.njudy.activity.PlayListActivity;
import com.bytedance.njudy.base.BaseRvAdapter;
import com.bytedance.njudy.base.BaseRvViewHolder;
import com.bytedance.njudy.bean.VideoBean;
import com.bytedance.njudy.utils.NumUtils;
import com.bytedance.njudy.view.IconFontTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 瀑布流adapter
 */
public class GridVideoAdapter extends BaseRvAdapter<VideoBean, GridVideoAdapter.GridVideoViewHolder> {

    public GridVideoAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(GridVideoViewHolder holder, VideoBean videoBean, int position) {
        //绑定数据，包括封面、内容、点赞数量、头像、点击事件
        holder.ivCover.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this.context)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1000000) //获取第一帧的图片
                                .centerCrop()
                )
                .load(videoBean.getUrl())
                .into(holder.ivCover);

        holder.tvContent.setText(videoBean.getContent());
        Glide.with(this.context).load(videoBean.getUserBean().getAvatar()).into(holder.ivHead);
        holder.tvLikecount.setText(NumUtils.numberFilter(videoBean.getLikeCount()));

        holder.itemView.setOnClickListener(v -> {
            PlayListActivity.initPos = position;
            context.startActivity(new Intent(context, PlayListActivity.class));
        });
    }

    @NonNull
    @Override
    public GridVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gridvideo, parent, false);
        return new GridVideoViewHolder(view);
    }

    public class GridVideoViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_likecount)
        IconFontTextView tvLikecount;
        @BindView(R.id.iv_head)
        ImageView ivHead;

        public GridVideoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
