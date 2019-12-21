package com.rongwei.fastcodeaccumulate.module.home.addfast;

import com.rongwei.fastcodeaccumulate.data.param.InserFastCodeBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

public interface AddFastCodeContract {
    interface View extends IBaseView {
        void  inserFastCodeSuceess(String string);
    }

    interface Presenter extends IBasePresenter {
        void  inserFastCode(InserFastCodeBean inserFastCodeBean);
    }

}
