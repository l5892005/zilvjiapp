package com.rongwei.fastcodeaccumulate.module.mian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.adapter.FastCodeAdapter;
import com.rongwei.fastcodeaccumulate.annotation.ContentView;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.param.InserFastCodeBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerMainComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.MainModule;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.module.home.addfast.AddFastCodeActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dalvik.system.BaseDexClassLoader;
@ContentView(R.layout.activity_main)
public class MainActivity extends ToolbarActivity implements MainContract.View {

    @Inject
    MainContract.Presenter mPresenter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    private List<FastCodeBean.DataBean> data=new ArrayList<>();
    private FastCodeAdapter fastCodeAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerMainComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }



    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        super.initView();
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFastCodeActivity.start(mContext);
            }
        });
        fastCodeAdapter = new FastCodeAdapter(data);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(fastCodeAdapter);
    }

    @Override
    protected String setToolbarTitle() {
        return "代码列表";
    }

    @Override
    protected void loadData() {
        mPresenter.getAllData();
    }

    @Override
    public void getAllDataSucess(FastCodeBean bean) {
        fastCodeAdapter.setNewData(bean.getData());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.getAllData();
    }


}
