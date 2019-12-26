package com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit;

import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;

import java.util.List;


public class MyHabitPresenter implements MyHabitContract.Presenter {
    private final MyHabitContract.View mMyHabitView;
    private final Repository mRepository;

    public MyHabitPresenter(MyHabitContract.View view, Repository mRepository) {
        this.mMyHabitView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    @Override
    public void getNoteCatalog(int uid) {
        mRepository.getNoteCatalog(uid).compose(mMyHabitView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<NoteCatalogBean>(mMyHabitView) {
            @Override
            public void onSuccess(NoteCatalogBean data) {
                mMyHabitView.getNoteCatalogSucess(data);
            }
        });
    }


    /**
     * 设置笔记名字
     * @param uid
     * @param name
     */
    @Override
    public void setNoteType(int uid, String name,int ispri) {
        mRepository.setNoteType(uid,name,ispri).compose(mMyHabitView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<NoteCatalogBean>(mMyHabitView) {
            @Override
            public void onSuccess(NoteCatalogBean data) {
                mMyHabitView.getNoteCatalogSucess(data);
            }
        });
    }


}
