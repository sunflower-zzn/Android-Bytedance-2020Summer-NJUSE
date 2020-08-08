package com.bytedance.njudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.njudy.R;
import com.bytedance.njudy.base.BaseRvAdapter;
import com.bytedance.njudy.base.BaseRvViewHolder;
import com.bytedance.njudy.bean.VideoBean;
import com.bytedance.njudy.view.ControllerView;
import com.bytedance.njudy.view.LikeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 全屏视频adapter
 */
public class VideoAdapter extends BaseRvAdapter<VideoBean, VideoAdapter.VideoViewHolder> {

    public VideoAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(VideoViewHolder holder, VideoBean videoBean, int position) {
        //绑定全屏视频数据，包括视频信息、封面图、点赞事件监听
        holder.controllerView.setVideoData(videoBean);
        Glide.with(this.context)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1000000) //获取第一帧的图片
                                .centerCrop()
                )
                .load(videoBean.getUrl())
                .into(holder.ivCover);

        holder.likeView.setOnLikeListener(() -> {
            if (!videoBean.isLiked()) {  //未点赞，会有点赞效果，否则无
                holder.controllerView.like();
            }

        });
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    public class VideoViewHolder extends BaseRvViewHolder {
        @BindView(R.id.likeview)
        LikeView likeView;
        @BindView(R.id.controller)
        ControllerView controllerView;
        @BindView(R.id.iv_cover)
        ImageView ivCover;

        public VideoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
