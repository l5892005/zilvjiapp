package com.rongwei.fastcodeaccumulate.module.fragment.main.very;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.adapter.VideoAdapter;
import com.rongwei.fastcodeaccumulate.data.bean.VideoBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerVeryDayComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.VeryDayModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseFragment;
import com.rongwei.fastcodeaccumulate.module.video.detail.VideoDetailActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class VeryDayFragment extends BaseFragment implements VeryDayContract.View, BaseQuickAdapter.OnItemChildClickListener {

    @Inject
    VeryDayContract.Presenter mPresenter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private VideoAdapter mVideoAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static VeryDayFragment newInstance() {
        VeryDayFragment fragment = new VeryDayFragment();
        return fragment;
    }

    @Override
    protected void initInjector() {
        DaggerVeryDayComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .veryDayModule(new VeryDayModule(this))
                .build()
                .inject(this);
    }

/*    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mPresenter.requestHomeData(num);
        }
    }*/

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_very_day;

    }

    @Override
    protected void initData() {

    }


    private List<VideoBean.IssueListBean.ItemListBean> lists = new ArrayList<>();
    private boolean loadingMore = false;
    @Override
    protected void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(linearLayoutManager);
        mVideoAdapter = new VideoAdapter(lists);
        rvList.setAdapter(mVideoAdapter);
        mVideoAdapter.setOnItemChildClickListener(this);

        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int childCount = rvList.getChildCount();
                    int itemCount = rvList.getLayoutManager().getItemCount();
                    LinearLayoutManager layoutManager = (LinearLayoutManager) rvList.getLayoutManager();
                    int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                    if (firstVisibleItem + childCount == itemCount) {
                        if (!loadingMore) {
                            loadingMore = true;
                            mPresenter.loadMoreData();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (currentVisibleItemPosition == 0) {
                    //背景设置为透明
                  /*  toolbar.setBackgroundColor(getColor(R.color.color_translucent))
                    iv_search.setImageResource(R.mipmap.ic_action_search_white)
                    tv_header_title.text = ""*/
                } else {
                    if (mVideoAdapter.getData().size() > 1) {
                      /*  toolbar.setBackgroundColor(getColor(R.color.color_title_bg))
                        iv_search.setImageResource(R.mipmap.ic_action_search_black)
                        val itemList = mHomeAdapter!!.mData
                        val item = itemList[currentVisibleItemPosition + mHomeAdapter!!.bannerItemSize - 1]
                        if (item.type == "textHeader") {
                            tv_header_title.text = item.data?.text
                        } else {
                            tv_header_title.text = simpleDateFormat.format(item.data?.date)
                        }*/
                    }
                }
            }
        });
    }

    private int num = 1;

    @Override
    protected void loadData() {
        mPresenter.requestHomeData(num);
    }


    @Override
    public void setHomeData(@NotNull VideoBean homeBean) {
        List<VideoBean.IssueListBean.ItemListBean> itemList = homeBean.getIssueList().get(0).getItemList();
        for (VideoBean.IssueListBean.ItemListBean listBean : itemList) {
            if (listBean.getType().equals("video")) {
                lists.add(listBean);
            }
        }
        mVideoAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        VideoBean.IssueListBean.ItemListBean bean = (VideoBean.IssueListBean.ItemListBean) adapter.getData().get(position);
        VideoDetailActivity.start(getContext(), bean.getData().getPlayUrl(), bean.getData().getTitle());
    }
    @Override
    public void setMoreData(@NotNull VideoBean homeBean) {
        loadingMore = false;
        setHomeData(homeBean);
        mVideoAdapter.notifyDataSetChanged();
    }
}