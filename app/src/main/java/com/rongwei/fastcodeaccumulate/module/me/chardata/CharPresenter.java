package com.rongwei.fastcodeaccumulate.module.me.chardata;

import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class CharPresenter implements CharContract.Presenter {
    private final CharContract.View mCharView;
    private final Repository mRepository;

    public CharPresenter(CharContract.View view, Repository mRepository) {
        this.mCharView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }
}
