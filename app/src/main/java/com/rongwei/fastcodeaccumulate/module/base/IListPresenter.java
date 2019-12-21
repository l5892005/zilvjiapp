package com.rongwei.fastcodeaccumulate.module.base;

/**
 * Created by maoqi on 2018/7/21.
 */
public interface IListPresenter extends IBasePresenter {
    void loadData(boolean isRefresh);

    void refreshData();
}
