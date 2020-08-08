package com.bytedance.njudy.API;

import com.google.gson.annotations.SerializedName;

/**
 * 自定义请求返回值
 */
public class VideoItem {
    @SerializedName("feedurl")
    public String videoUrl;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public String likecount;
    @SerializedName("avatar")
    public String avatarUrl;
}
