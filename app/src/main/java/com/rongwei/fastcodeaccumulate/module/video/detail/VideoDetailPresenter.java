package com.rongwei.fastcodeaccumulate.module.video.detail;

import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class VideoDetailPresenter implements VideoDetailContract.Presenter {
    private final VideoDetailContract.View mVideoDetailView;
    private final Repository mRepository;

    public VideoDetailPresenter(VideoDetailContract.View view, Repository mRepository) {
        this.mVideoDetailView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }
}
