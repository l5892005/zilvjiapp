package com.rongwei.fastcodeaccumulate.module.user.register;

import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

public interface RegisterContract {
    interface View extends IBaseView {
        void  setRegisterSuceess(UserBean string);
    }

    interface Presenter extends IBasePresenter {
        void  setRegister(String account,String pwd);
    }

}
