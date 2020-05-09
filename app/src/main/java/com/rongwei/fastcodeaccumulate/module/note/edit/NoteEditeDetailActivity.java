package com.rongwei.fastcodeaccumulate.module.note.edit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.data.event.EventTag;
import com.rongwei.fastcodeaccumulate.data.event.MessageEvent;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerNoteEditeDetailComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.NoteEditeDetailModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseActivity;
import com.rongwei.fastcodeaccumulate.module.base.ToolbarActivity;
import com.rongwei.fastcodeaccumulate.module.mian.MainActivity;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteEditeDetailActivity extends ToolbarActivity implements NoteEditeDetailContract.View, View.OnClickListener {

    @Inject
    NoteEditeDetailContract.Presenter mPresenter;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_sub)
    TextView tvSub;
    private int uid;
    private int nid;

    public static void start(Context context,int uid,int nid) {
        Intent intent = new Intent(context, NoteEditeDetailActivity.class);
        intent.putExtra("uid",uid);
        intent.putExtra("nid",nid);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerNoteEditeDetailComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .noteEditeDetailModule(new NoteEditeDetailModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_note_edite_detail;

    }

    @Override
    protected void initData() {
        uid = getIntent().getIntExtra("uid", 0);
        nid = getIntent().getIntExtra("nid", 0);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected String setToolbarTitle() {
        return getResources().getString(R.string.add_note_list);
    }

    @Override
    protected void loadData() {
        tvSub.setOnClickListener(this);

    }
    public  void onClickText(View view){

        etContent.setText(etContent.getText()+"kotlin");

    }

    @Override
    public void onClick(View v) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();

        if (content.length()==0 || content.length()>=1000){
            toastFailed(getResources().getString(R.string.content_lenght_error));
            return;
        }

        if (title.length()==0 || title.length()>200){
            title=content.substring(0,content.length()>15?15:content.length());
        }


        if (uid==0 || nid ==0){
            toastFailed(getResources().getString(R.string.data_transfs_error));
            return;
        }
        mPresenter.getSetListCatalog(uid,nid,title,content);
    }

    @Override
    public void getCardDataSucess(PersionNoteListBean bean) {
        EventBus.getDefault().post(new MessageEvent(EventTag.addmessage));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("添加成功");
        builder.setIcon(R.drawable.a1);
        builder.setCancelable(true);            //点击对话框以外的区域是否让对话框消失
        //设置正面按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
