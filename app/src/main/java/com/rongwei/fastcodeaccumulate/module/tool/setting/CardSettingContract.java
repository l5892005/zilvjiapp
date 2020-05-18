package com.rongwei.fastcodeaccumulate.module.tool.setting;

import com.rongwei.fastcodeaccumulate.data.bean.CardBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

public interface CardSettingContract {
    interface View extends IBaseView {
        void getCardDataSucess(CardBean bean);
    }

    interface Presenter extends IBasePresenter {
        void getCardDataToDay(String userId);
        void getCardData(String userId);
        void deleteCardData(String userId, int cid);
        void AddCardType(String userId,String name,String imageName,String colorBg);

        void setReModeCard(String userId, int cid, String name, String imageName, String colorBg);
    }

}
