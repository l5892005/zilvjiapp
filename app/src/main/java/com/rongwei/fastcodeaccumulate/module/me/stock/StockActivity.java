package com.rongwei.fastcodeaccumulate.module.me.stock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bin.david.form.core.SmartTable;
import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.StockNoteBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerStockComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.StockModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseActivity;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.utils.KeyboardUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StockActivity extends ToolbarActivity implements StockContract.View {

    @Inject
    StockContract.Presenter mPresenter;
    @BindView(R.id.table)
    SmartTable table;
    @BindView(R.id.cb_no_take)
    CheckBox cbNoTake;
    @BindView(R.id.et_take_out)
    EditText etTakeOut;
    @BindView(R.id.et_put_in)
    EditText etPutIn;
    @BindView(R.id.et_remake)
    EditText etRemake;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    private int uid;
    private String userName;

    public static void start(Context context) {
        Intent intent = new Intent(context, StockActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerStockComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .stockModule(new StockModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_stock;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();

    }
    @OnClick({R.id.tv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                submitAddMoneyRecord();
                break;
        }
    }
    @Override
    protected String setToolbarTitle() {
        return getResources().getString(R.string.stock_reconrd);
    }

    private List<StockNoteBean.DataBean> beans;
    @Override
    protected void loadData() {
        uid = AndroidApplication.getInstance().getUser().getUid();
        userName = AndroidApplication.getInstance().getUser().getAccount();
        mPresenter.getStockMoney(uid);
        table.setZoom(true);
        table.setMinimumHeight(20);
    }

    @Override
    public void getStockMoneySucess(List<StockNoteBean.DataBean> beans) {
        this.beans = beans;
        int totle=0;
        for (StockNoteBean.DataBean bean : beans) {
            totle+=Integer.parseInt(bean.getTakeout());
            bean.setUsername(userName);
        }
        StockNoteBean.DataBean bean=new StockNoteBean.DataBean();
        bean.setTakeout(totle+"");
        bean.setTotalmoney(beans.get(beans.size()-1).getTotalmoney());
        beans.add(bean);
        table.setData(beans);

    }

    /**
     * 提交金钱记录
     */
    private void submitAddMoneyRecord() {
        if (beans == null || beans.size() == 0) {
            toastFailed("无法保存你的数据");
            return;
        }
        String etTakeOutString = etTakeOut.getText().toString().trim();
        String etPutInString = etPutIn.getText().toString().trim();
        String etRemakeString = etRemake.getText().toString().trim();
        if (TextUtils.isEmpty(etPutInString) && TextUtils.isEmpty(etTakeOutString)) {
            toastFailed("无法保存你的数据");
            return;
        }
        int outNum = 0;
        int intNum = 0;
        String remake = etRemakeString;
        StockNoteBean.DataBean dataBean = beans.get(beans.size() - 1);
        String stockCode = dataBean.getStockcode();
        int money = dataBean.getTotalmoney();
        if (!TextUtils.isEmpty(etTakeOutString)) {
            outNum = Integer.parseInt(etTakeOutString);
        }
        if (!TextUtils.isEmpty(etPutInString)) {
            intNum = Integer.parseInt(etPutInString);
        }
        if (!cbNoTake.isChecked()){
            money = money + intNum - outNum;
        }
        if (TextUtils.isEmpty(etRemakeString)) {
            if (outNum > 0) {
                remake = "取出" + outNum + "元";
            }
            if (intNum > 0) {
                remake = "存入" + intNum + "元";
            }
        }
        mPresenter.putStockMoney(uid, outNum, intNum, remake, stockCode, money);
        etTakeOut.setText("");
        etPutIn.setText("");
        KeyboardUtils.hideSoftInput(this);
    }
}
