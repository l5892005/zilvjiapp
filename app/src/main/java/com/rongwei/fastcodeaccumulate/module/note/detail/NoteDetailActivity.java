package com.rongwei.fastcodeaccumulate.module.note.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.Cons;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerNoteDetailComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.NoteDetailModule;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.utils.DateUtils;
import com.rongwei.fastcodeaccumulate.utils.StringUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteDetailActivity extends ToolbarActivity implements NoteDetailContract.View {

    @Inject
    NoteDetailContract.Presenter mPresenter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.floatbutton)
    FloatingActionButton floatbutton;
    private int nid;
    private BaseQuickAdapter baseQuickAdapter;

    public static void start(Context context, int nid, String title) {
        Intent intent = new Intent(context, NoteDetailActivity.class);
        intent.putExtra("nid", nid);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerNoteDetailComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .noteDetailModule(new NoteDetailModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_note_detail;

    }

    @Override
    protected void initData() {
        nid = getIntent().getIntExtra("nid", 0);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected String setToolbarTitle() {
        return getIntent().getStringExtra("title");
    }

    @Override
    protected void loadData() {
        if (nid != 0) {
            mPresenter.getNoteListCatalog(Cons.USER_ID_INT, nid);
        }
    }

    @Override
    public void getCardDataSucess(PersionNoteListBean bean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rvList.setLayoutManager(linearLayoutManager);
        baseQuickAdapter = new BaseQuickAdapter<PersionNoteListBean.DataBean, BaseViewHolder>(R.layout.note_detail_item, bean.getData()) {
            @Override
            protected void convert(BaseViewHolder helper, PersionNoteListBean.DataBean item) {
                helper.setText(R.id.tv_title, item.getSubtitle());
                helper.setText(R.id.tv_content, item.getNotecontent());
                helper.setText(R.id.tv_time, DateUtils.formatChatTime(Long.parseLong(item.getCreateTime())));
            }
        };
        rvList.setAdapter(baseQuickAdapter);
    }

}
