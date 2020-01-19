package com.rongwei.fastcodeaccumulate.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LeadDebotBean {

    /**
     * total : 2
     * max_page : 2
     * data : [{"mid":1,"uid":5,"money":20000,"mstate":1,"mremark":"投入股票钱","mname":"老妹","nowstatu":0},{"mid":2,"uid":5,"money":1000,"mstate":1,"mremark":"之前欠账","mname":"老妹","nowstatu":0}]
     */

    private int total;
    private int max_page;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMax_page() {
        return max_page;
    }

    public void setMax_page(int max_page) {
        this.max_page = max_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * mid : 1
         * uid : 5
         * money : 20000
         * mstate : 1
         * mremark : 投入股票钱
         * mname : 老妹
         * nowstatu : 0
         */

        private int mid;
        private int uid;
        private int money;
        private int mstate;
        private String mremark;
        private String mname;
        private int nowstatu;
        private String createtime;

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getMstate() {
            return mstate;
        }

        public void setMstate(int mstate) {
            this.mstate = mstate;
        }

        public String getMremark() {
            return mremark;
        }

        public void setMremark(String mremark) {
            this.mremark = mremark;
        }

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

        public int getNowstatu() {
            return nowstatu;
        }

        public void setNowstatu(int nowstatu) {
            this.nowstatu = nowstatu;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mid);
            dest.writeInt(this.uid);
            dest.writeInt(this.money);
            dest.writeInt(this.mstate);
            dest.writeString(this.mremark);
            dest.writeString(this.mname);
            dest.writeInt(this.nowstatu);
            dest.writeString(this.createtime);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.mid = in.readInt();
            this.uid = in.readInt();
            this.money = in.readInt();
            this.mstate = in.readInt();
            this.mremark = in.readString();
            this.mname = in.readString();
            this.nowstatu = in.readInt();
            this.createtime = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
