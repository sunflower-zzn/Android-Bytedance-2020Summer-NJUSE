package com.bytedance.njudy.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * API请求，利用retrofit2
 */
public interface APIService {
    // https://beiyou.bytedance.com/api/invoke/video/invoke/video
    @GET("api/invoke/video/invoke/video")
    Call<List<VideoItem>> getDouyinitems();
}
