package com.rongwei.fastcodeaccumulate.module.user.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.annotation.ContentView;
import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.data.event.EventTag;
import com.rongwei.fastcodeaccumulate.data.event.MessageEvent;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerLoginComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.LoginModule;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.module.main.main.MainHomeActivity;
import com.rongwei.fastcodeaccumulate.module.user.agree.UserAgreementActivity;
import com.rongwei.fastcodeaccumulate.module.user.register.RegisterActivity;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
@ContentView(R.layout.activity_login)
public class LoginActivity extends ToolbarActivity implements LoginContract.View, View.OnClickListener {

    @Inject
    LoginContract.Presenter mPresenter;
    @BindView(R.id.et_accout)
    EditText etAccout;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_user_agree)
    TextView tvUserAgree;
    @BindView(R.id.cb_user_agree)
    CheckBox cbUserAgree;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_message_news, menu);
        final MenuItem item = menu.findItem(R.id.tv_message_dot);
        View actionView = item.getActionView();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.start(mContext);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initData() {
        if (AndroidApplication.getInstance().getUser()!=null) {
            MainHomeActivity.start(mContext);
            finish();
        }
    }

    @Override
    protected void initView() {
        super.initView();
        tvLogin.setOnClickListener(this);
        tvUserAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAgreementActivity.start(mContext);
            }
        });
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
        if (!cbUserAgree.isChecked()){
            toastAlert("需要你同意自律鸡用户协议！");
            return;
        }
        mPresenter.setLogin(account,pwd);
    }

    @Override
    public void setLoginSuceess(UserBean string) {
        toastSucc("登录成功"+string.getNick());
        AndroidApplication.getInstance().setUser(string);
        EventBus.getDefault().post(new MessageEvent(EventTag.loginSucess,null));
        MainHomeActivity.start(this);
        finish();
    }
}
