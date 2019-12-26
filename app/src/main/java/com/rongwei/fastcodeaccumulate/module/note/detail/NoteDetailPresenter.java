package com.rongwei.fastcodeaccumulate.module.note.detail;

import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver;
import com.rongwei.fastcodeaccumulate.utils.NullUtils;


public class NoteDetailPresenter implements NoteDetailContract.Presenter {
    private final NoteDetailContract.View mNoteDetailView;
    private final Repository mRepository;

    public NoteDetailPresenter(NoteDetailContract.View view, Repository mRepository) {
        this.mNoteDetailView = NullUtils.checkNotNull(view);
        this.mRepository = NullUtils.checkNotNull(mRepository);
    }

    @Override
    public void getNoteListCatalog(int uid, int nid) {
        mRepository.getNoteListCatalog(uid,nid).compose(mNoteDetailView.initNetLifecycler()).subscribe(new SimpleWrapperObserver<PersionNoteListBean>(mNoteDetailView) {
            @Override
            public void onSuccess(PersionNoteListBean data) {
                mNoteDetailView.getCardDataSucess(data);
            }
        });
    }


}
