package com.rongwei.fastcodeaccumulate.data.bean;

import java.util.List;

public class CardBean {


    /**
     * total : 2
     * max_page : 2
     * data : [{"cardid":7,"imgcard":"pisa","ername":"英语1小时","useid":"6","createtime":1577354767000,"imgstatic":1,"cardnum":2},{"cardid":8,"imgcard":"pisa","ername":"54545","useid":"6","createtime":1577375124000,"imgstatic":1,"cardnum":0}]
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

    public static class DataBean {
        /**
         * cardid : 7
         * imgcard : pisa
         * ername : 英语1小时
         * useid : 6
         * createtime : 1577354767000
         * imgstatic : 1
         * cardnum : 2
         */

        private int cardid;
        private String imgcard;
        private String ername;
        private String useid;
        private long createtime;
        private int imgstatic;
        private int cardnum;

        public int getCardid() {
            return cardid;
        }

        public void setCardid(int cardid) {
            this.cardid = cardid;
        }

        public String getImgcard() {
            return imgcard;
        }

        public void setImgcard(String imgcard) {
            this.imgcard = imgcard;
        }

        public String getErname() {
            return ername;
        }

        public void setErname(String ername) {
            this.ername = ername;
        }

        public String getUseid() {
            return useid;
        }

        public void setUseid(String useid) {
            this.useid = useid;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public int getImgstatic() {
            return imgstatic;
        }

        public void setImgstatic(int imgstatic) {
            this.imgstatic = imgstatic;
        }

        public int getCardnum() {
            return cardnum;
        }

        public void setCardnum(int cardnum) {
            this.cardnum = cardnum;
        }
    }
}
