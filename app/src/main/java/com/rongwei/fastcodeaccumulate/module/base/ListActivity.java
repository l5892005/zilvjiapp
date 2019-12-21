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
 * Created by maoqi on 2018/7/21.
 */
public abstract class ListActivity<T extends IListPresenter, K extends BaseQuickAdapter> extends ToolbarActivity {
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
        super.initView();
        initRefresh();
        initRecyclerView();
    }

    private void initRefresh() {
        mSwipeRefreshLayout.setEnabled(enableRefresh());
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.main_color));
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

    protected void initRecyclerView() {
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadData(false);
            }
        }, mRvList);

        if (mRvList != null) {
            mRvList.setLayoutManager(new LinearLayoutManager(this));
            mRvList.setAdapter(mAdapter);
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
        mAdapter.loadMoreComplete();
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
        if (gone){
            mAdapter.setEmptyView(setEmptyLayoutId());
            mAdapter.loadMoreEnd(false);
        }else{
            if (mAdapter.getEmptyViewCount() == 0) {
                mAdapter.setEmptyView(setEmptyLayoutId());
            }
            mAdapter.loadMoreEnd(gone);
        }
    }

    protected int setEmptyLayoutId(){
        return R.layout.error_no_data_content;
    };

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

    private void showErrorContent(ViewGroup parentView, View.OnClickListener errorClickListener) {
        if (parentView != null) {
            @LayoutRes int layoutRes;
            layoutRes = R.layout.error_net_content;
            View view = LayoutInflater.from(this).inflate(layoutRes, null);
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

    public void loadLlistEnd() {
        mAdapter.setEmptyView(setEmptyLayoutId());
        mAdapter.loadMoreEnd();
    }

    public void loadEmptyError() {
        if (mAdapter.getEmptyViewCount() == 0) {
            mAdapter.setEmptyView(setErrorLayoutId());
            mAdapter.getEmptyView().findViewById(R.id.btn_reload).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.loadData(true);
                }
            });
        }

    }
    protected int setErrorLayoutId(){
        return R.layout.error_net_content;
    };

}
