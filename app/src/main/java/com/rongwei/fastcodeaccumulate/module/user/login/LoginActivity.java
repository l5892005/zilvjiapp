package com.rongwei.fastcodeaccumulate.module.user.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerLoginComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.LoginModule;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.module.main.main.MainHomeActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends ToolbarActivity implements LoginContract.View, View.OnClickListener {

    @Inject
    LoginContract.Presenter mPresenter;
    @BindView(R.id.et_accout)
    EditText etAccout;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerLoginComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        tvLogin.setOnClickListener(this);

    }

    @Override
    protected String setToolbarTitle() {
        return getResources().getString(R.string.longin_name);
    }

    @Override
    protected void loadData() {
    }

    @Override
    public void onClick(View v) {
        String account = etAccout.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        if (account.length()<6||pwd.length()<6){
            toastAlert(getResources().getString(R.string.account_pwd_error));
            return;
        }
        mPresenter.setLogin(account,pwd);
    }

    @Override
    public void setLoginSuceess(UserBean string) {
        AndroidApplication.getInstance().setUser(string);
        MainHomeActivity.start(this);
    }
}
