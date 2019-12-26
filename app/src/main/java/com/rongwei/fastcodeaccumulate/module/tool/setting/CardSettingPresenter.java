package com.rongwei.fastcodeaccumulate.module.tool.setting;

import com.rongwei.fastcodeaccumulate.data.bean.CardBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class CardSettingPresenter implements CardSettingContract.Presenter {
    private final CardSettingContract.View mCardSettingView;
    private final Repository mRepository;

    public CardSettingPresenter(CardSettingContract.View view, Repository mRepository) {
        this.mCardSettingView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }
    @Override
    public void getCardDataToDay(String userId) {
        mRepository.getCardDataToDay(userId).compose(mCardSettingView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<UserCardsToDayBean>(mCardSettingView) {
            @Override
            public void onSuccess(UserCardsToDayBean data) {
                //mCardSettingView.getCardDataSucess(data);
            }
        });
    }
    @Override
    public void getCardData(String userId) {
        mRepository.getCardData(userId).compose(mCardSettingView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<CardBean>(mCardSettingView) {
            @Override
            public void onSuccess(CardBean data) {
                mCardSettingView.getCardDataSucess(data);
            }
        });
    }

    /**
     * 添加一张卡片
     * @param userId
     * @param name
     * @param imageName
     * @param colorBg
     */
    @Override
    public void AddCardType(String userId, String name, String imageName, String colorBg) {
        mRepository.AddCardType(userId,name,imageName,colorBg).compose(mCardSettingView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<CardBean>(mCardSettingView) {
            @Override
            public void onSuccess(CardBean data) {
                mCardSettingView.toastSucc("添加"+name+"成功,会在第二天生效。");
                mCardSettingView.getCardDataSucess(data);
            }
        });
    }

    @Override
    public void setReModeCard(String userId, int cid, String name, String imageName, String colorBg) {
        mRepository.setReModeCard(userId,cid+"",name,imageName,colorBg).compose(mCardSettingView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<CardBean>(mCardSettingView) {
            @Override
            public void onSuccess(CardBean data) {
                mCardSettingView.toastSucc("修改"+name+"成功,会在第二天生效。");
                mCardSettingView.getCardDataSucess(data);
            }
        });
    }
}
