package com.rongwei.fastcodeaccumulate.module.me.stock;

import com.rongwei.fastcodeaccumulate.data.bean.StockNoteBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

import java.util.List;

public interface StockContract {
    interface View extends IBaseView {
        void getStockMoneySucess(List<StockNoteBean.DataBean> beans);
    }

    interface Presenter extends IBasePresenter {
        void getStockMoney(int uid);
        void putStockMoney(int uId,int take_out,int put_in,String remark_money,String stock_code,int money);
        void putStockMoney(int uId,int money);
    }

}
