package com.rongwei.fastcodeaccumulate.module.fragment.main.tool;

import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class MyToolPresenter implements MyToolContract.Presenter {
    private final MyToolContract.View mMyToolView;
    private final Repository mRepository;

    public MyToolPresenter(MyToolContract.View view, Repository mRepository) {
        this.mMyToolView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }
}
