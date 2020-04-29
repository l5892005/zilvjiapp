package com.rongwei.fastcodeaccumulate.module.me.experience;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.adapter.ExperienceAdapter;
import com.rongwei.fastcodeaccumulate.data.bean.ExperienceBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerExperienceComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.ExperienceModule;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ExperienceActivity extends ToolbarActivity implements ExperienceContract.View {

    @Inject
    ExperienceContract.Presenter mPresenter;
    @BindView(R.id.v_toolbar)
    View vToolbar;
    @BindView(R.id.fl_toolbar)
    FrameLayout flToolbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private List<ExperienceBean.DataBean> beans;
    private ExperienceAdapter experienceAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, ExperienceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerExperienceComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .experienceModule(new ExperienceModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_experience;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        beans = new ArrayList<>();
        experienceAdapter = new ExperienceAdapter(beans);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(experienceAdapter);
    }

    @Override
    protected String setToolbarTitle() {
        return getResources().getString(R.string.experience_text);
    }

    @Override
    protected void loadData() {
        mPresenter.getExperienceInfo(AndroidApplication.getInstance().getUser().getUid());
    }



    @Override
    public void getExperienceInfoSucess(ArrayList<ExperienceBean.DataBean> bean) {
        beans.clear();
        beans.addAll(bean);
        experienceAdapter.notifyDataSetChanged();
    }
}
