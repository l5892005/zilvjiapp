package com.rongwei.fastcodeaccumulate.module.base;

import java.util.List;

/**
 * Created by maoqi on 2018/7/23.
 */
public interface IListView extends IBaseView {
    void addData(List data);

    void addData(int position, List data);

    void addData(Object obj);

    void addData(int position, Object obj);

    void replaceData(List data);

    void setNewData(List data);

    void loadComplete();

    void loadFailed();

    void loadEnd();

    void loadEnd(boolean gone);

    void loadError();

    void notifyDataSetChanged();

}
