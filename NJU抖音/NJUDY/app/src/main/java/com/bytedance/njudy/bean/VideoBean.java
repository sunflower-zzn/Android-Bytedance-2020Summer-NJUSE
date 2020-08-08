package com.bytedance.njudy.bean;

/**
 * 视频video类
 */
public class VideoBean {
    //视频url，采用glide获取第一帧做封面
    private String url;
    //视频文案
    private String content;
    //视频作者
    private UserBean userBean;
    /**
     * 是否已点赞
     */
    private boolean isLiked;
    /**
     * 是否已关注
     */
    private boolean isFocused;
    /**
     * 点赞数
     */
    private int likeCount;
    /**
     * 评论数
     */
    private int commentCount;
    /**
     * 转发数
     */
    private int shareCount;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public static class UserBean {
        //用户名
        private String nickName;
        //用户头像链接
        private String avatar;

        public String getNickName() {
            return nickName == null ? "" : nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

    }
}
