package com.bytedance.njudy.fragment;

import android.net.Uri;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bytedance.njudy.R;
import com.bytedance.njudy.activity.MainActivity;
import com.bytedance.njudy.activity.PlayListActivity;
import com.bytedance.njudy.adapter.VideoAdapter;
import com.bytedance.njudy.base.BaseFragment;
import com.bytedance.njudy.bean.DataCreate;
import com.bytedance.njudy.utils.OnVideoControllerListener;
import com.bytedance.njudy.view.ControllerView;
import com.bytedance.njudy.view.FullScreenVideoView;
import com.bytedance.njudy.view.LikeView;
import com.bytedance.njudy.view.viewpagerlayoutmanager.OnViewPagerListener;
import com.bytedance.njudy.view.viewpagerlayoutmanager.ViewPagerLayoutManager;

import butterknife.BindView;

/**
 * 全屏展示页面
 */
public class RecommendFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;
    private VideoAdapter adapter;
    private ViewPagerLayoutManager viewPagerLayoutManager;
    /**
     * 当前播放视频位置
     */
    private int curPlayPos = -1;
    private FullScreenVideoView videoView;
    private ImageView ivCurCover;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {

        adapter = new VideoAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(adapter);

        videoView = new FullScreenVideoView(getActivity());

        setViewPagerLayoutManager();

        setRefreshEvent();
    }

    @Override
    public void onResume() {
        super.onResume();

        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.curPage == 1) {
            videoView.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();

        videoView.stopPlayback();
    }

    private void setViewPagerLayoutManager() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewPagerLayoutManager);
        recyclerView.scrollToPosition(PlayListActivity.initPos);

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                playCurVideo(PlayListActivity.initPos);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (ivCurCover != null) {
                    ivCurCover.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                playCurVideo(position);
            }
        });
    }

    private void setRefreshEvent() {
        refreshLayout.setColorSchemeResources(R.color.color_link);
        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
            }
        }.start());
    }

    private void playCurVideo(int position) {
        if (position == curPlayPos) {
            return;
        }

        View itemView = viewPagerLayoutManager.findViewByPosition(position);
        if (itemView == null) {
            return;
        }

        ViewGroup rootView = itemView.findViewById(R.id.rl_container);
        LikeView likeView = rootView.findViewById(R.id.likeview);
        ControllerView controllerView = rootView.findViewById(R.id.controller);
        ImageView ivPlay = rootView.findViewById(R.id.iv_play);
        ImageView ivCover = rootView.findViewById(R.id.iv_cover);
        ivPlay.setAlpha(0.4f);

        //播放暂停事件
        likeView.setOnPlayPauseListener(() -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                ivPlay.setVisibility(View.VISIBLE);
            } else {
                videoView.start();
                ivPlay.setVisibility(View.GONE);
            }
        });

        curPlayPos = position;

        //切换播放器位置
        dettachParentView(rootView);

        autoPlayVideo(curPlayPos, ivCover);
    }

    /**
     * 移除videoview父view
     */
    private void dettachParentView(ViewGroup rootView) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view
        ViewGroup parent = (ViewGroup) videoView.getParent();
        if (parent != null) {
            parent.removeView(videoView);
        }
        rootView.addView(videoView, 0);
    }

    /**
     * 自动播放视频
     */
    private void autoPlayVideo(int position, ImageView ivCover) {
        videoView.setVideoURI(Uri.parse(DataCreate.datas.get(position).getUrl()));
        videoView.start();
        videoView.setOnPreparedListener(mp -> {
            mp.setLooping(true);

            //延迟取消封面，避免加载视频黑屏
            new CountDownTimer(200, 200) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    ivCover.setVisibility(View.GONE);
                    ivCurCover = ivCover;
                }
            }.start();
        });
    }

}
