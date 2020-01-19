package com.rongwei.fastcodeaccumulate.module.me.chardata;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerCharComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.CharModule;
import com.rongwei.fastcodeaccumulate.module.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharActivity extends BaseActivity implements CharContract.View {

    @Inject
    CharContract.Presenter mPresenter;
    @BindView(R.id.chart)
    BarChart chart;

    public static void start(Context context) {
        Intent intent = new Intent(context, CharActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerCharComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .charModule(new CharModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_char;

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {


    }

    @Override
    protected void loadData() {
        float randomMultiplier = 2 ;
        List<BarEntry> entriesGroup1 = new ArrayList<>();
        entriesGroup1.add(new BarEntry(1, (float) (Math.random() * randomMultiplier)));
        entriesGroup1.add(new BarEntry(2, (float) (Math.random() * randomMultiplier)));
        entriesGroup1.add(new BarEntry(3, (float) (Math.random() * randomMultiplier)));
        entriesGroup1.add(new BarEntry(4, (float) (Math.random() * randomMultiplier)));
        entriesGroup1.add(new BarEntry(5, (float) (Math.random() * randomMultiplier)));
        List<BarEntry> entriesGroup2 = new ArrayList<>();
        entriesGroup2.add(new BarEntry(1, (float) (Math.random() * randomMultiplier)));
        entriesGroup2.add(new BarEntry(2, (float) (Math.random() * randomMultiplier)));
        entriesGroup2.add(new BarEntry(3, (float) (Math.random() * randomMultiplier)));
        entriesGroup2.add(new BarEntry(4, (float) (Math.random() * randomMultiplier)));
        entriesGroup2.add(new BarEntry(5, (float) (Math.random() * randomMultiplier)));

//两组数据
        BarDataSet set1 = new BarDataSet(entriesGroup1, "Group 1");
        BarDataSet set2 = new BarDataSet(entriesGroup2, "Group 2");
        set1.setGradientColor(0xffBCD5FF,0xff7CB1F1);
        set2.setGradientColor(0xffFDE2C3,0xffF3C185);

        float groupSpace = 0.06f;//群组间的间隔
        float barSpace = 0.02f; // 每一个柱状条间隔
        float barWidth = 0.45f; // 每一个柱状条的宽度

// (0.02 + 0.45) * 2 + 0.06 = 1.00 -> 总共合起来还是1f
        BarData data = new BarData(set1, set2); //设置组数据
        chart.setData(data);
        // specify the width each bar should have
        chart.getBarData().setBarWidth(barWidth);
        // restrict the x-axis range
        float startYear=12;
        chart.getXAxis().setAxisMinimum(startYear);
        //chart.getXAxis().setDrawGridLines(false);//是否显示竖直标尺线
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        //chart.getXAxis().setAxisMaximum(startYear + chart.getBarData().getGroupWidth(groupSpace, barSpace)*entriesGroup2.size() );
        chart.getXAxis().setAxisMaximum(1+entriesGroup2.size() );
        chart.getXAxis().setAxisMinimum(0);
        chart.groupBars(startYear, groupSpace, barSpace);
        chart.setDragEnabled(true);
        chart.invalidate();
    }

}
