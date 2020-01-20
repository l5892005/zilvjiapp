package com.rongwei.fastcodeaccumulate.module.me.money.lend;

import android.util.TimeUtils;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.LeadDebotBean;
import com.rongwei.fastcodeaccumulate.data.bean.StockNoteBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


public class LendRebtPresenter implements LendRebtContract.Presenter {
    private final LendRebtContract.View mLendRebtView;
    private final Repository mRepository;

    public LendRebtPresenter(LendRebtContract.View view, Repository mRepository) {
        this.mLendRebtView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    /**
     *获取数据
     * @param uid
     */
    @Override
    public void getLendRebt(int uid) {
        mRepository.getLendRebt(uid).compose(mLendRebtView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<LeadDebotBean>(mLendRebtView) {
            @Override
            public void onSuccess(LeadDebotBean data) {
                mLendRebtView.getLeadDebotSucess(data.getData());
            }
        });
    }

    /**
     * 更换状态
     * @param mid
     */
    @Override
    public void getLendRebtStauts(int mid) {
        mRepository.getLendRebtStauts(mid).compose(mLendRebtView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<String>(mLendRebtView) {
            @Override
            public void onSuccess(String data) {
                mLendRebtView.toastSucc("修改成功");
                Observable.timer(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getLendRebt(AndroidApplication.getInstance().getUser().getUid());
                    }
                });

            }
        });
    }

    /**
     * 添加数据
     * @param uid
     * @param money
     * @param mstate
     * @param mremark
     * @param mname
     * @param nowstatu
     */
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
