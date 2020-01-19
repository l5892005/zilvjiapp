package com.rongwei.fastcodeaccumulate.module.me.money.total;

import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class TotalMoneyActivityPresenter implements TotalMoneyActivityContract.Presenter {
    private final TotalMoneyActivityContract.View mTotalMoneyActivityView;
    private final Repository mRepository;

    public TotalMoneyActivityPresenter(TotalMoneyActivityContract.View view, Repository mRepository) {
        this.mTotalMoneyActivityView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }
}
