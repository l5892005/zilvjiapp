package com.rongwei.fastcodeaccumulate.module.fragment.main.home;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.Cons;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerHomeComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.HomeModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseFragment;
import com.rongwei.fastcodeaccumulate.utils.SizeUtils;
import com.rongwei.fastcodeaccumulate.weight.RecycleViewDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements HomeContract.View, BaseQuickAdapter.OnItemChildClickListener {

    @Inject
    HomeContract.Presenter mPresenter;
    @BindView(R.id.tv_memo)
    TextView tvMemo;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private BaseQuickAdapter<UserCardsBean, BaseViewHolder> baseQuickAdapter;
    private List<UserCardsBean> userCardsBeans;
    private boolean isLogin;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected void initInjector() {
        DaggerHomeComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_home;

    }

    @Override
    protected void initData() {
        isLogin = AndroidApplication.getInstance().isLogin();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {
        if (isLogin){
            mPresenter.getMemoData(Cons.USER_ID);
            mPresenter.getCardDataToDay(Cons.USER_ID);
        }
    }

    @Override
    public void getAllDataSucess(MemoBean memoBean) {
        String contentVery = memoBean.getContentVery();
        String[] split = contentVery.split("\\|");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            builder.append(i + 1 + ". " + split[i] + "\n");
        }
        tvMemo.setText(builder);

    }

    @Override
    public void getCardDataSucess(UserCardsToDayBean bean) {
        //List<UserCardsBean.DataBean> data = bean.getData();
        String imageName = bean.getImageName();
        String cardName = bean.getCardName();
        String imageCode = bean.getImgCode();
        String imageCount = bean.getImageCount();
        String[] splitImageName = imageName.split("\\|");
        String[] splitCardName = cardName.split("\\|");
        String[] splitImageCode = imageCode.split("\\|");
        String[] splitImageCount = imageCount.split("\\|");
       // String[] splitCardStauts = bean.getCodeCard().split("\\|");
        char[] splitImageStatus = bean.getCodeCard().toCharArray();
        userCardsBeans = new ArrayList<>();
        for (int i = 0; i < splitImageCode.length; i++) {
            UserCardsBean userCardsBean=new UserCardsBean();
            userCardsBean.setCardid(splitImageCode[i]);
            userCardsBean.setCardName(splitCardName[i]);
            userCardsBean.setImgName(splitImageName[i]);
            userCardsBean.setImgsCount(splitImageCount[i]);
            userCardsBean.setImgstatic(splitImageStatus[i]+"");
            userCardsBeans.add(userCardsBean);
        }
        Log.d("test", userCardsBeans.toString());
        TypedArray array = getResources().obtainTypedArray(R.array.img_list_ids);
        baseQuickAdapter = new BaseQuickAdapter<UserCardsBean, BaseViewHolder>(R.layout.user_cards_item, userCardsBeans) {
            @Override
            protected void convert(BaseViewHolder helper, UserCardsBean item) {
                String[] stringArray = getResources().getStringArray(R.array.img_list);
                for (int i = 0; i < stringArray.length; i++) {
                    if (stringArray[i].equals(item.getImgName())) {
                        helper.setImageResource(R.id.iv_img, array.getResourceId(i, 0));
                        break;
                    }
                }
                if ("0".equals(item.getImgstatic())) {
                    helper.getView(R.id.iv_img).setBackgroundResource(R.drawable.card_bg_gray_cir);
                    helper.getView(R.id.iv_img).setEnabled(true);
                    helper.getView(R.id.tv_count_day).setVisibility(View.GONE);
                    helper.addOnClickListener(R.id.iv_img);
                } else {
                    helper.getView(R.id.iv_img).setBackgroundResource(R.drawable.card_bg_cir);
                    helper.getView(R.id.iv_img).setEnabled(false);
                    helper.getView(R.id.tv_count_day).setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_count_day,"总共"+item.getImgsCount()+"天");
                }
                helper.setText(R.id.tv_sub, item.getCardName());
            }
        };
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
        //rvList.addItemDecoration(new RecycleViewDivider(mActivity,DividerItemDecoration.VERTICAL, SizeUtils.dp2px(20),0,0, getResources().getColor(R.color.white)));
        rvList.setLayoutManager(gridLayoutManager);
        rvList.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void setStatusSucess(String userId, int postion, int isCard) {
        UserCardsBean userCardsBean = userCardsBeans.get(postion-1);
        userCardsBean.setImgstatic(isCard+"");
        if (isCard==1){
            userCardsBean.setImgsCount(Integer.parseInt(userCardsBean.getImgsCount())+1+"");
        }
        baseQuickAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (userCardsBeans!=null && userCardsBeans.get(position)!=null){
            mPresenter.setCardTodayData(Cons.USER_ID,position+1,1);
        }
    }
}