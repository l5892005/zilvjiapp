package com.rongwei.fastcodeaccumulate.module.user.login;

import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View mLoginView;
    private final Repository mRepository;

    public LoginPresenter(LoginContract.View view, Repository mRepository) {
        this.mLoginView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    @Override
    public void setLogin(String account, String pwd) {
        mRepository.setLogin(account,pwd).compose(mLoginView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<UserBean>(mLoginView) {
            @Override
            public void onSuccess(UserBean data) {
                mLoginView.setLoginSuceess(data);
            }
        });
    }


}

