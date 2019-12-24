package com.rongwei.fastcodeaccumulate.module.main.main;

import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class MainHomePresenter implements MainHomeContract.Presenter {
    private final MainHomeContract.View mMainHomeView;
    private final Repository mRepository;

    public MainHomePresenter(MainHomeContract.View view, Repository mRepository) {
        this.mMainHomeView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }
}
