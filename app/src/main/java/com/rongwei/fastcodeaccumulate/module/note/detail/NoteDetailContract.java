package com.rongwei.fastcodeaccumulate.module.note.detail;

import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

public interface NoteDetailContract {
    interface View extends IBaseView {
        void getCardDataSucess(PersionNoteListBean bean);
    }

    interface Presenter extends IBasePresenter {
        void getNoteListCatalog(int uid,int nid);

    }

}
