package com.rongwei.fastcodeaccumulate.module.me.money.lend;

import com.rongwei.fastcodeaccumulate.data.bean.LeadDebotBean;
import com.rongwei.fastcodeaccumulate.data.bean.StockNoteBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class LendRebtPresenter implements LendRebtContract.Presenter {
    private final LendRebtContract.View mLendRebtView;
    private final Repository mRepository;

    public LendRebtPresenter(LendRebtContract.View view, Repository mRepository) {
        this.mLendRebtView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    @Override
    public void getLendRebt(int uid) {
        mRepository.getLendRebt(uid).compose(mLendRebtView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<LeadDebotBean>(mLendRebtView) {
            @Override
            public void onSuccess(LeadDebotBean data) {
                mLendRebtView.getLeadDebotSucess(data.getData());
            }
        });
    }

    @Override
    public void putLendRebt(int uid, int money, int mstate, String mremark, String mname, int nowstatu) {
        mRepository.putLendRebt(uid,money,mstate,mremark,mname,nowstatu).compose(mLendRebtView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<String>(mLendRebtView) {
            @Override
            public void onSuccess(String data) {
                getLendRebt(uid);
                mLendRebtView.toastSucc("添加成功");
            }
        });
    }
}
