package com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit;

import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter;
import com.rongwei.fastcodeaccumulate.module.base.IBaseView;

import java.util.List;

public interface MyHabitContract {
    interface View extends IBaseView {
        void getNoteCatalogSucess(NoteCatalogBean uid);
    }

    interface Presenter extends IBasePresenter {
        void getNoteCatalog(int uid);
        void setNoteType(int uid,String name,int ispri);

    }

}
