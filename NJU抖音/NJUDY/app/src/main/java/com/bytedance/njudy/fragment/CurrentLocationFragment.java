package com.bytedance.njudy.fragment;

import android.os.CountDownTimer;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bytedance.njudy.R;
import com.bytedance.njudy.adapter.GridVideoAdapter;
import com.bytedance.njudy.base.BaseFragment;
import com.bytedance.njudy.bean.DataCreate;

import butterknife.BindView;

//瀑布流展示效果
public class CurrentLocationFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;
    private GridVideoAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_current_location;
    }

    @Override
    protected void init() {
        //设置附近页面的瀑布流上下滑动
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //设置adapter绑定数据
        adapter = new GridVideoAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(adapter);


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

}
