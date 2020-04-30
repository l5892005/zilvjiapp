package com.rongwei.fastcodeaccumulate.module.fragment.main.very;

import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.module.fragment.main.very.VeryDayContract;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class VeryDayPresenter implements VeryDayContract.Presenter {


    private final VeryDayContract.View mVeryDayView;
    private final Repository mRepository;

    public VeryDayPresenter(VeryDayContract.View view, Repository mRepository) {
        this.mVeryDayView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }


}
