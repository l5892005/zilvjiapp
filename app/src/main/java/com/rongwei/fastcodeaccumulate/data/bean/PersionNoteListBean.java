package com.rongwei.fastcodeaccumulate.data.bean;

import java.util.List;

public class PersionNoteListBean {


    /**
     * total : 1
     * max_page : 1
     * data : [{"did":2,"nid":2,"uid":1,"subtitle":"生活启示录","imgpath1":null,"imgpath2":null,"imgpath3":null,"notecontent":"生活很累呀，累到不行了呀"}]
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
         * did : 2
         * nid : 2
         * uid : 1
         * subtitle : 生活启示录
         * imgpath1 : null
         * imgpath2 : null
         * imgpath3 : null
         * notecontent : 生活很累呀，累到不行了呀
         */

        private int did;
        private int nid;
        private int uid;
        private String subtitle;
        private Object imgpath1;
        private Object imgpath2;
        private Object imgpath3;
        private String notecontent;
        private String createTime;
        private int readcount;

        public int getDid() {
            return did;
        }

        public void setDid(int did) {
            this.did = did;
        }

        public int getNid() {
            return nid;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getReadcount() {
            return readcount;
        }

        public void setReadcount(int readcount) {
            this.readcount = readcount;
        }

        public void setNid(int nid) {
            this.nid = nid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public Object getImgpath1() {
            return imgpath1;
        }

        public void setImgpath1(Object imgpath1) {
            this.imgpath1 = imgpath1;
        }

        public Object getImgpath2() {
            return imgpath2;
        }

        public void setImgpath2(Object imgpath2) {
            this.imgpath2 = imgpath2;
        }

        public Object getImgpath3() {
            return imgpath3;
        }

        public void setImgpath3(Object imgpath3) {
            this.imgpath3 = imgpath3;
        }

        public String getNotecontent() {
            return notecontent;
        }

        public void setNotecontent(String notecontent) {
            this.notecontent = notecontent;
        }
    }
}
