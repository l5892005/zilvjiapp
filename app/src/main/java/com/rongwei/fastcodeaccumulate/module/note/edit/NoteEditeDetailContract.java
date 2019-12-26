package com.rongwei.fastcodeaccumulate.module.note.edit;

import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

public interface NoteEditeDetailContract {
    interface View extends IBaseView {
        void getCardDataSucess(PersionNoteListBean bean);
    }

    interface Presenter extends IBasePresenter {
        void getSetListCatalog(int uid,int nid,String title,String info);
    }

}
