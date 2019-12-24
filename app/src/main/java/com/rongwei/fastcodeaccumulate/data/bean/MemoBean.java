package com.rongwei.fastcodeaccumulate.data.bean;

public class MemoBean {


    /**
     * mid : 1
     * userid : 001
     * contentVery : 早上起来10个拉腹轮|学习1-2个视频|记得打卡|10点睡觉
     * createTime : 1576940858000
     * comSchedule : 0000
     */

    private int mid;
    private String userid;
    private String contentVery;
    private long createTime;
    private String comSchedule;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContentVery() {
        return contentVery;
    }

    public void setContentVery(String contentVery) {
        this.contentVery = contentVery;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getComSchedule() {
        return comSchedule;
    }

    public void setComSchedule(String comSchedule) {
        this.comSchedule = comSchedule;
    }
}
