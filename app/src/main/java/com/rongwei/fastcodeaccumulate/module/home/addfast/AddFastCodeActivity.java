package com.rongwei.fastcodeaccumulate.module.home.addfast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.param.InserFastCodeBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerAddFastCodeComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.AddFastCodeModule;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddFastCodeActivity extends ToolbarActivity implements AddFastCodeContract.View {

    @Inject
    AddFastCodeContract.Presenter mPresenter;
    @BindView(R.id.fl_toolbar)
    FrameLayout flToolbar;
    @BindView(R.id.name_l)
    TextView nameL;
    @BindView(R.id.name_r)
    TextView nameR;
    @BindView(R.id.content_l)
    TextView contentL;
    @BindView(R.id.et_content_r)
    EditText etContentR;
    @BindView(R.id.time_l)
    TextView timeL;
    @BindView(R.id.time_r)
    TextView timeR;

    public static void start(Context context) {
        Intent intent = new Intent(context, AddFastCodeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerAddFastCodeComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .addFastCodeModule(new AddFastCodeModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_add_fast_code;

    }
    public void submit(View view){
        InserFastCodeBean inserFastCodeBean = new InserFastCodeBean(nameR.getText().toString().trim(),1,1,etContentR.getText().toString().trim(),5);
        mPresenter.inserFastCode(inserFastCodeBean);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected String setToolbarTitle() {
        return "快速添加代码";
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void inserFastCodeSuceess(String str) {
        toastSucc(str);
        finish();
    }
}
