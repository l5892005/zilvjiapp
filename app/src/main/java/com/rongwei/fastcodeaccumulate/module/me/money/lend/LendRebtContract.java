package com.rongwei.fastcodeaccumulate.module.me.money.lend;

import com.rongwei.fastcodeaccumulate.data.bean.LeadDebotBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

import java.util.List;

public interface LendRebtContract {
    interface View extends IBaseView {
        void getLeadDebotSucess(List<LeadDebotBean.DataBean> beans);
    }

    interface Presenter extends IBasePresenter {
        void getLendRebt(int uid);
        void getLendRebtStauts(int mid);
        void putLendRebt(int uid,int money,int mstate,String mremark,String mname,int nowstatu);
    }

}
