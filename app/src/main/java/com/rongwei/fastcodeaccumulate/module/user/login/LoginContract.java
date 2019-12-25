package com.rongwei.fastcodeaccumulate.module.user.login;

import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;
import com.rongwei.fastcodeaccumulate.utils.StringUtils;

public interface LoginContract {
    interface View extends IBaseView {
        void  setLoginSuceess(UserBean string);
    }

    interface Presenter extends IBasePresenter {
        void  setLogin(String account,String pwd);
    }

}
