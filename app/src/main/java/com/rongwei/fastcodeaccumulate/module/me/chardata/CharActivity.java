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
    private List<Entry> entries;

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

    private float[] dataObjects = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Override
    protected void initView() {


    }

    @Override
    protected void loadData() {
        entries = new ArrayList<Entry>();

        for (float data : dataObjects) {
            entries.add(new Entry(data, data));
        }

      /*线性的
        LineDataSet dataSet = new LineDataSet(entries, "Label"); // 添加数据
        dataSet.setColor(getResources().getColor(R.color.color_00d));
        dataSet.setValueTextColor(R.color.text_333); // 自定义数据样式
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);*/
       /*柱状的图
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        // 跳过第五个
        entries.add(new BarEntry(5f, 70f));
        entries.add(new BarEntry(6f, 60f));
        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // 设置数据条的宽度
        chart.setData(data);
        chart.invalidate(); // 刷新*/
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
        float barWidth = 0.40f; // 每一个柱状条的宽度

// (0.02 + 0.45) * 2 + 0.06 = 1.00 -> 总共合起来还是1f
        BarData data = new BarData(set1, set2); //设置组数据
        chart.setData(data);
        // specify the width each bar should have
        chart.getBarData().setBarWidth(barWidth);
        // restrict the x-axis range
        float startYear=1980;
        chart.getXAxis().setAxisMinimum(startYear);
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        chart.getXAxis().setAxisMaximum(startYear + chart.getBarData().getGroupWidth(groupSpace, barSpace)*entriesGroup2.size() );
        chart.groupBars(1980f, groupSpace, barSpace);
        chart.setDragEnabled(true);
        chart.invalidate();
    }

}
