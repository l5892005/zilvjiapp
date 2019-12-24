package com.rongwei.fastcodeaccumulate.module.fragment.main.home;

import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

public interface HomeContract {
    interface View extends IBaseView {
        void getAllDataSucess(MemoBean memoBean);
        void getCardDataSucess(UserCardsToDayBean bean);
        void setStatusSucess(String userId,int postion,int isCard);
    }

    interface Presenter extends IBasePresenter {
        void getMemoData(String userId);
        void getCardData(String userId);
        void getCardDataToDay(String userId);
        void setCardTodayData(String userId,int order,int isCard);
    }

}
