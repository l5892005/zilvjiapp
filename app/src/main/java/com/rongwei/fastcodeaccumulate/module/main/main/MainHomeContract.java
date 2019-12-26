package com.rongwei.fastcodeaccumulate.module.main.main;

import com.rongwei.fastcodeaccumulate.data.bean.VersionBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

public interface MainHomeContract {
    interface View extends IBaseView {
        void getVersionCodeSucess(VersionBean bean);
    }

    interface Presenter extends IBasePresenter {
        void getVersionCode();
    }

}
