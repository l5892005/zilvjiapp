package com.rongwei.fastcodeaccumulate.module.base;

/**
 * Created by maoqi on 2018/7/24.
 */
public abstract class ListPresenter implements IListPresenter {
    protected int mCurrentPage = 1;
    protected int mTotalPage = 1;
    protected int mMinId = 0;

    public void refreshData() {
        mCurrentPage = 1;
        mMinId = 0;
        loadData(true);
    }

    protected boolean isEnd() {
        return ++mCurrentPage > mTotalPage;
    }

    protected void setTotalPage(int totalPage) {
        this.mTotalPage = totalPage;
    }

}
