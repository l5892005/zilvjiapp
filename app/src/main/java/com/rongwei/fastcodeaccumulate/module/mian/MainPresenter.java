package com.rongwei.fastcodeaccumulate.module.mian;

import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mMainView;
    private final Repository mRepository;

    public MainPresenter(MainContract.View view, Repository mRepository) {
        this.mMainView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    /**
     * 检查更新
     */
    @Override
    public void getAllData() {
        mRepository.getAllData().compose(mMainView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<FastCodeBean>(mMainView) {
            @Override
            public void onSuccess(FastCodeBean data) {
                mMainView.getAllDataSucess(data);
            }
        });
    }
}
