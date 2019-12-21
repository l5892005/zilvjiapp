package com.rongwei.fastcodeaccumulate.module.home.addfast;

import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.param.InserFastCodeBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class AddFastCodePresenter implements AddFastCodeContract.Presenter {
    private final AddFastCodeContract.View mAddFastCodeView;
    private final Repository mRepository;

    public AddFastCodePresenter(AddFastCodeContract.View view, Repository mRepository) {
        this.mAddFastCodeView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    @Override
    public void inserFastCode(InserFastCodeBean inserFastCodeBean) {
        mRepository.inserFastCode(inserFastCodeBean).compose(mAddFastCodeView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<String>(mAddFastCodeView) {
            @Override
            public void onSuccess(String data) {
                mAddFastCodeView.inserFastCodeSuceess(data);
            }
        });

    }
}
