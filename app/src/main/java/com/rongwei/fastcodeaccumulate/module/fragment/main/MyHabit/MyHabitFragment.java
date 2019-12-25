package com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.Cons;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerMyHabitComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.MyHabitModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseFragment;
import com.rongwei.fastcodeaccumulate.module.note.detail.NoteDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MyHabitFragment extends BaseFragment implements MyHabitContract.View, BaseQuickAdapter.OnItemChildClickListener {

    @Inject
    MyHabitContract.Presenter mPresenter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private List<NoteCatalogBean.DataBean>  noteCatalogBeans=new ArrayList<>();
    private BaseQuickAdapter<NoteCatalogBean.DataBean, BaseViewHolder> baseQuickAdapter;

    public static MyHabitFragment newInstance() {
        MyHabitFragment fragment = new MyHabitFragment();
        return fragment;
    }

    @Override
    protected void initInjector() {
        DaggerMyHabitComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .myHabitModule(new MyHabitModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_my_habit;

    }

    @Override
    protected void initData() {
        NoteCatalogBean.DataBean dataBean = new NoteCatalogBean.DataBean();
        noteCatalogBeans.add(dataBean);
    }

    @Override
    protected void initView() {
        tvTitle.setText(getResources().getString(R.string.my_note));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,3);
        rvList.setLayoutManager(gridLayoutManager);
        baseQuickAdapter = new BaseQuickAdapter<NoteCatalogBean.DataBean, BaseViewHolder>(R.layout.note_list_item, noteCatalogBeans) {

            @Override
            protected void convert(BaseViewHolder helper, NoteCatalogBean.DataBean item) {
                if (helper.getPosition()==0) {
                    helper.getView(R.id.tv_name).setVisibility(View.INVISIBLE);
                    helper.getView(R.id.tv_count).setVisibility(View.INVISIBLE);
                    helper.getView(R.id.iv_content).setVisibility(View.VISIBLE);
                }else{
                    helper.getView(R.id.tv_name).setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_count, item.getNotecount()+"");
                    helper.setText(R.id.tv_name, item.getNotename());
                    helper.getView(R.id.iv_content).setVisibility(View.INVISIBLE);
                }
                helper.addOnClickListener(R.id.rl_bg);
            }
        };
        rvList.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setOnItemChildClickListener(this);
    }


    @Override
    protected void loadData() {
        mPresenter.getNoteCatalog(Cons.USER_ID_INT);
    }
    @Override
    public void getNoteCatalogSucess(NoteCatalogBean bean) {
        if (bean!=null && bean.getData()!=null){
            noteCatalogBeans.addAll(bean.getData());
            baseQuickAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (position==0){
        }else{
            NoteCatalogBean.DataBean dataBean = noteCatalogBeans.get(position);
            NoteDetailActivity.start(getActivity(),dataBean.getBid(),dataBean.getNotename());
        }
    }
}