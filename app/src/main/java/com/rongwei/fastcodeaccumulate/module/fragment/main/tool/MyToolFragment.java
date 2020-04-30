package com.rongwei.fastcodeaccumulate.module.fragment.main.tool;

import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.BuildConfig;
import com.rongwei.fastcodeaccumulate.Cons;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.data.event.EventTag;
import com.rongwei.fastcodeaccumulate.data.event.MessageEvent;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerMyToolComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.MyToolModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseFragment;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.module.me.experience.ExperienceActivity;
import com.rongwei.fastcodeaccumulate.module.me.money.lend.LendRebtActivity;
import com.rongwei.fastcodeaccumulate.module.me.money.total.TotalMoneyActivityActivity;
import com.rongwei.fastcodeaccumulate.module.me.stock.StockActivity;
import com.rongwei.fastcodeaccumulate.module.tool.setting.CardSettingActivity;
import com.rongwei.fastcodeaccumulate.module.user.login.LoginActivity;
import com.rongwei.fastcodeaccumulate.module.video.detail.VideoDetailActivity;
import com.rongwei.fastcodeaccumulate.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import javax.inject.Inject;

import butterknife.BindView;

public class MyToolFragment extends BaseFragment implements MyToolContract.View, BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {

    @Inject
    MyToolContract.Presenter mPresenter;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_code)
    TextView tvCode;
    private BaseQuickAdapter baseQuickAdapter;
    private View inflate;
    private List<String> strings;
    private UserBean user;

    public static MyToolFragment newInstance() {
        MyToolFragment fragment = new MyToolFragment();
        return fragment;
    }

    @Override
    protected void initInjector() {
        DaggerMyToolComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .myToolModule(new MyToolModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_my_tool;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        //tvTitle.setText("我的理财");
        tvCode.setText("V" + BuildConfig.VERSION_CODE);
    }


    @Override
    protected void loadData() {
        strings = Arrays.asList(getResources().getStringArray(R.array.tools));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
        TypedArray array = getResources().obtainTypedArray(R.array.img_list_ids);
        int length = array.length();
        int endlenght = length - strings.size();
        rvList.setLayoutManager(gridLayoutManager);
        baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.user_tool_item, strings) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setImageResource(R.id.iv_img, array.getResourceId(endlenght - 1 + helper.getPosition(), 0));
                helper.setText(R.id.tv_sub, item);
                helper.addOnClickListener(R.id.ll_item);
                if (helper.getPosition() < Cons.EFFECTIVE_NUM) {
                    helper.getView(R.id.ll_item).setBackgroundResource(R.drawable.card_bg_light_rec);
                }
            }
        };
        rvList.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setOnItemChildClickListener(this);
        inflate = LayoutInflater.from(mActivity).inflate(R.layout.item_head_tool, null);
        inflate.findViewById(R.id.iv_img).setOnClickListener(this);
        if (AndroidApplication.getInstance().isLogin()) {
            user = AndroidApplication.getInstance().getUser();
            if (user != null) {
                TextView tvUser = inflate.findViewById(R.id.tv_user);
                tvUser.setText(user.getNick());
            }
        }
        baseQuickAdapter.addHeaderView(inflate);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (strings.get(position)) {
            case "打卡设置":
                if (user != null) {
                    CardSettingActivity.start(mActivity);
                } else {
                    LoginActivity.start(mActivity);
                }
                break;
            case "股票转账":
                StockActivity.start(mActivity);
                break;
            case "日常理财":
                TotalMoneyActivityActivity.start(mActivity);
                break;
            case "借还账本":
                LendRebtActivity.start(mActivity);
                break;
            case "经验总结":
                ExperienceActivity.start(mActivity);
                break;
            case "小建议":
               // VideoDetailActivity.start(mActivity);
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_img:
                if (!AndroidApplication.getInstance().isLogin()) {
                    LoginActivity.start(mActivity);
                }
                break;
        }
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        if (event != null) {
            if (EventTag.loginSucess.equals(event.getEventTag())) {
                UserBean user = AndroidApplication.getInstance().getUser();
                if (user != null) {
                    TextView tvUser = inflate.findViewById(R.id.tv_user);
                    tvUser.setText(user.getNick());
                }
            }
        }
    }
}