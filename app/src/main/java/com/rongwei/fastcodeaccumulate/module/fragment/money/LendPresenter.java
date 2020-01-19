package com.rongwei.fastcodeaccumulate.module.fragment.money;

import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class LendPresenter implements LendContract.Presenter {
    private final LendContract.View mLendView;
    private final Repository mRepository;

    public LendPresenter(LendContract.View view, Repository mRepository) {
        this.mLendView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }
}
