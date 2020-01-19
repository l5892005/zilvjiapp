package com.rongwei.fastcodeaccumulate.module.me.stock;

import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.bean.StockNoteBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class StockPresenter implements StockContract.Presenter {
    private final StockContract.View mStockView;
    private final Repository mRepository;

    public StockPresenter(StockContract.View view, Repository mRepository) {
        this.mStockView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    /**
     * 获取股票的钱数
     * @param uid
     */
    @Override
    public void getStockMoney(int uid) {
        mRepository.getStockMoney(uid).compose(mStockView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<StockNoteBean>(mStockView) {
            @Override
            public void onSuccess(StockNoteBean data) {
                mStockView.getStockMoneySucess(data.getData());
            }
        });
    }

    /**
     * 上传股票钱数
     * @param take_out
     * @param put_in
     * @param remark_money
     * @param stock_code
     * @param money
     */
    @Override
    public void putStockMoney(int uid, int take_out, int put_in, String remark_money, String stock_code, int money) {
        mRepository.putStockMoney(uid,take_out,money,put_in,remark_money,stock_code).compose(mStockView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<String>(mStockView) {
            @Override
            public void onSuccess(String data) {
                getStockMoney(uid);
                mStockView.toastSucc("添加成功");
            }
        });
    }



    /**
     * 设置总金额
     * @param uid
     * @param money
     */
    @Override
    public void putStockMoney(int uid, int money) {


    }
}
