package com.rongwei.fastcodeaccumulate.module.fragment.main.home;

import android.widget.Toast;

import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;
import com.rongwei.fastcodeaccumulate.utils.ToastUtil;


public class HomePresenter implements HomeContract.Presenter {
    private final HomeContract.View mHomeView;
    private final Repository mRepository;

    public HomePresenter(HomeContract.View view, Repository mRepository) {
        this.mHomeView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    /**
     * 获取每日便签
     */
    @Override
    public void getMemoData(String userId) {
        mRepository.getMemoData(userId).compose(mHomeView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<MemoBean>(mHomeView) {
            @Override
            public void onSuccess(MemoBean data) {
                mHomeView.getAllDataSucess(data);
            }
        });
    }


    /**
     * 获取每天打卡
     * @param userId
     */
    @Override
    public void getCardDataToDay(String userId) {
        mRepository.getCardDataToDay(userId).compose(mHomeView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<UserCardsToDayBean>(mHomeView) {
            @Override
            public void onSuccess(UserCardsToDayBean data) {
                mHomeView.getCardDataSucess(data);
            }
        });
    }

    /**
     * 去打卡
     * @param userId
     * @param order
     * @param isCard
     */
    @Override
    public void setCardTodayData(String userId, int order, int isCard) {
        mRepository.setCardTodayData(userId,order,isCard).compose(mHomeView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<String>(mHomeView) {
            @Override
            public void onSuccess(String data) {
                mHomeView.toastSucc("打卡成功");
                mHomeView.setStatusSucess(userId,order,isCard);
            }
        });
    }

    /**
     * 设置每日便签
     * @param userId
     * @param info
     */
    @Override
    public void setMemoInfo(int userId, String info) {
        mRepository.setMemoInfo(userId,info).compose(mHomeView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<MemoBean>(mHomeView) {
            @Override
            public void onSuccess(MemoBean data) {
                mHomeView.getAllDataSucess(data);
            }
        });
    }
}
