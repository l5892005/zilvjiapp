package com.rongwei.fastcodeaccumulate.module.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rongwei.fastcodeaccumulate.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by maoqi on 2018/12/25.
 */
public abstract class ListFragment<T extends IListPresenter, K extends BaseQuickAdapter> extends BaseFragment {
    @Inject
    protected K mAdapter;

    @Inject
    protected T mPresenter;

    @BindView(R.id.rv_list)
    protected RecyclerView mRvList;

    @BindView(R.id.fl_error_content)
    protected FrameLayout mFlErrorContent;
    @BindView(R.id.srl_refresh)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void initView() {
        initRefresh();
        initRecyclerView();
    }

    protected void initRecyclerView() {
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (!mSwipeRefreshLayout.isRefreshing()) {
                    mPresenter.loadData(false);
                }
            }
        }, mRvList);
        if (mRvList != null) {
            mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
            mRvList.setAdapter(mAdapter);
        }
    }

    private void initRefresh() {
        mSwipeRefreshLayout.setEnabled(enableRefresh());
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mAdapter.isLoading()) {
                    mPresenter.refreshData();
                }
            }
        });
    }

    protected boolean enableRefresh() {
        return false;
    }

    @Override
    public void showProgress() {
        if (mSwipeRefreshLayout.isEnabled()) {
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            super.showProgress();
        }
    }

    @Override
    public void removeProgress() {
        if (mSwipeRefreshLayout.isEnabled()) {
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            super.removeProgress();
        }
    }



    public void addData(List data) {
        if (data != null && data.size() > 0) {
            mAdapter.addData(data);
        }
    }

    public void addData(int position, List data) {
        if (data != null && data.size() > 0) {
            mAdapter.addData(position, data);
        }
    }

    public void addData(Object obj) {
        if (obj != null) {
            mAdapter.addData(obj);
        }
    }

    public void addData(int position, Object obj) {
        if (obj != null) {
            mAdapter.addData(position, obj);
        }
    }

    public void replaceData(List data) {
        if (data != null && data.size() > 0) {
            mAdapter.replaceData(data);
        }
    }

    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public void setNewData(List data) {
        if (data != null && data.size() > 0) {
            mAdapter.setNewData(data);
        }
    }

    public void loadComplete() {
        if (mAdapter.getEmptyViewCount() == 0) {
            mAdapter.setEmptyView(setEmptyLayoutId());
        }
        mAdapter.loadMoreComplete();
    }

    public int setEmptyLayoutId() {
        return R.layout.error_no_data_content;
    }

    public void loadFailed() {
        if (mAdapter.getData().size() > 0) {
            mAdapter.loadMoreFail();
        } else {
            loadError();
        }
    }

    public void loadEnd() {
        loadEnd(false);
    }

    public void loadEnd(boolean gone) {
        if (mAdapter.getEmptyViewCount() == 0) {
            mAdapter.setEmptyView(setEmptyLayoutId());
        }
        mAdapter.loadMoreEnd(gone);
    }

    protected void showErrorContent(ViewGroup parentView, View.OnClickListener errorClickListener) {
        if (parentView != null) {
            @LayoutRes int layoutRes;
            layoutRes = R.layout.error_net_content;

            View view = LayoutInflater.from(mActivity).inflate(layoutRes, null);
            View reload = view.findViewById(R.id.btn_reload);
            if (reload != null) {
                reload.setOnClickListener(errorClickListener);
            }
            parentView.addView(view);
        }
    }

    public void loadError() {
        mRvList.setVisibility(View.GONE);
        mFlErrorContent.setVisibility(View.VISIBLE);
        showErrorContent(mFlErrorContent, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.refreshData();
                mRvList.setVisibility(View.VISIBLE);
                mFlErrorContent.setVisibility(View.GONE);
            }
        });
    }
}
