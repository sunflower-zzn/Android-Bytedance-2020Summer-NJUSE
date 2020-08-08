package com.bytedance.njudy.bean;

import com.bytedance.njudy.API.VideoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 处理请求获取的数据，生成对应类型数据
 */
public class DataCreate {
    public static ArrayList<VideoBean> datas;

    public void initData(List<VideoItem> mDatas) {
        datas = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            VideoBean videoBean = new VideoBean();
            //设置视频信息
            videoBean.setUrl(mDatas.get(i).videoUrl);
            videoBean.setContent(mDatas.get(i).description);
            //设置用户属性
            VideoBean.UserBean userBean = new VideoBean.UserBean();
            userBean.setAvatar(mDatas.get(i).avatarUrl);
            userBean.setNickName(mDatas.get(i).nickname);
            videoBean.setUserBean(userBean);
            //设置点赞数和是否点赞&关注
            videoBean.setLiked(false);
            videoBean.setFocused(false);
            videoBean.setLikeCount(Integer.parseInt(mDatas.get(i).likecount));
            //随机产生评论和分享数
            int min = 4000;
            int max = 8000;
            Random random = new Random();
            videoBean.setCommentCount(random.nextInt(max) % (max - min + 1) + min);
            videoBean.setShareCount(random.nextInt(max) % (max - min + 1) + min);
            datas.add(videoBean);
        }


    }
}
