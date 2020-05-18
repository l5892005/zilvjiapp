package com.rongwei.fastcodeaccumulate.module.user.agree;

import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class UserAgreementPresenter implements UserAgreementContract.Presenter {
    private final UserAgreementContract.View mUserAgreementView;
    private final Repository mRepository;

    public UserAgreementPresenter(UserAgreementContract.View view, Repository mRepository) {
        this.mUserAgreementView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }
}
