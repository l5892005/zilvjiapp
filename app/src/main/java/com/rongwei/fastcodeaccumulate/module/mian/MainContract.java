package com.rongwei.fastcodeaccumulate.module.mian;

import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

public interface MainContract {
    interface View extends IBaseView {
        void getAllDataSucess(FastCodeBean bean);
    }


    interface Presenter extends IBasePresenter {
        void getAllData();
    }

}
