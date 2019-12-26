package com.rongwei.fastcodeaccumulate.module.user.register;

import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class RegisterPresenter implements RegisterContract.Presenter {
    private final RegisterContract.View mRegisterView;
    private final Repository mRepository;

    public RegisterPresenter(RegisterContract.View view, Repository mRepository) {
        this.mRegisterView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    @Override
    public void setRegister(String account, String pwd) {
        mRepository.setRegister(account,pwd).compose(mRegisterView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<UserBean>(mRegisterView) {
            @Override
            public void onSuccess(UserBean data) {
                mRegisterView.setRegisterSuceess(data);
            }
        });
    }
}
