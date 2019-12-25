package com.rongwei.fastcodeaccumulate.data.bean;

import java.util.List;

public class NoteCatalogBean {


    /**
     * total : 1
     * max_page : 1
     * data : [{"bid":1,"uid":1,"notename":"安卓面试","notecount":0,"notetype":0,"isopen":1,"notecover":null}]
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
         * bid : 1
         * uid : 1
         * notename : 安卓面试
         * notecount : 0
         * notetype : 0
         * isopen : 1
         * notecover : null
         */

        private int bid;
        private int uid;
        private String notename;
        private int notecount;
        private int notetype;
        private int isopen;
        private String notecover;

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getNotename() {
            return notename;
        }

        public void setNotename(String notename) {
            this.notename = notename;
        }

        public int getNotecount() {
            return notecount;
        }

        public void setNotecount(int notecount) {
            this.notecount = notecount;
        }

        public int getNotetype() {
            return notetype;
        }

        public void setNotetype(int notetype) {
            this.notetype = notetype;
        }

        public int getIsopen() {
            return isopen;
        }

        public void setIsopen(int isopen) {
            this.isopen = isopen;
        }

        public String getNotecover() {
            return notecover;
        }

        public void setNotecover(String notecover) {
            this.notecover = notecover;
        }
    }
}
