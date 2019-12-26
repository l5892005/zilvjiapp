package com.rongwei.fastcodeaccumulate.module.note.edit;

import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class NoteEditeDetailPresenter implements NoteEditeDetailContract.Presenter {
    private final NoteEditeDetailContract.View mNoteEditeDetailView;
    private final Repository mRepository;

    public NoteEditeDetailPresenter(NoteEditeDetailContract.View view, Repository mRepository) {
        this.mNoteEditeDetailView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    @Override
    public void getSetListCatalog(int uid, int nid, String title, String info) {
        mRepository.getSetListCatalog(uid,nid,title,info).compose(mNoteEditeDetailView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<PersionNoteListBean>(mNoteEditeDetailView) {
            @Override
            public void onSuccess(PersionNoteListBean data) {
                mNoteEditeDetailView.getCardDataSucess(data);
            }
        });
    }
}
