package com.rongwei.fastcodeaccumulate.module.user.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.rongwei.fastcodeaccumulate.injector.components.DaggerRegisterComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.RegisterModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseActivity;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.module.main.main.MainHomeActivity;
import com.rongwei.fastcodeaccumulate.module.user.agree.UserAgreementActivity;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
@ContentView(R.layout.activity_login)
public class RegisterActivity extends ToolbarActivity implements RegisterContract.View, View.OnClickListener {

    @Inject
    RegisterContract.Presenter mPresenter;
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
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerRegisterComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        tvLogin.setText("注册");
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
        return getResources().getString(R.string.register_name);
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
        mPresenter.setRegister(account,pwd);
    }

    @Override
    public void setRegisterSuceess(UserBean string) {
        toastSucc("注册成功"+string.getNick());
        AndroidApplication.getInstance().setUser(string);
        EventBus.getDefault().post(new MessageEvent(EventTag.loginSucess,null));
        MainHomeActivity.start(this);
        finish();
    }
}
