package com.rongwei.fastcodeaccumulate.data.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

import java.util.List;

public class StockNoteBean {


    /**
     * total : 2
     * max_page : 2
     * data : [{"idstock":3,"username":"1","uid":1,"totalmoney":100,"takeout":null,"putin":null,"remarkm":null,"stockcode":null,"createtime":1579085452000},{"idstock":4,"username":"1","uid":1,"totalmoney":200,"takeout":null,"putin":null,"remarkm":null,"stockcode":null,"createtime":1579085469000}]
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
    @SmartTable(name="收入支出表",count = true)
    public static class DataBean {
        /**
         * idstock : 3
         * username : 1
         * uid : 1
         * totalmoney : 100
         * takeout : null
         * putin : null
         * remarkm : null
         * stockcode : null
         * createtime : 1579085452000
         */

        private int idstock;
        // id为该字段所在表格排序位置
        @SmartColumn(id =6,name = "用户")
        private String username;
        private int uid;
        @SmartColumn(id =1,name = "总计")
        private int totalmoney;
        @SmartColumn(id =2,name = "取出")
        private String takeout;
        @SmartColumn(id =3,name = "存入")
        private String putin;
        @SmartColumn(id =4,name = "备注")
        private String remarkm;
        private String stockcode;
        @SmartColumn(id =5,name = "创建时间")
        private long createtime;

        public int getIdstock() {
            return idstock;
        }

        public void setIdstock(int idstock) {
            this.idstock = idstock;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getTotalmoney() {
            return totalmoney;
        }

        public void setTotalmoney(int totalmoney) {
            this.totalmoney = totalmoney;
        }

        public String getTakeout() {
            return takeout;
        }

        public void setTakeout(String takeout) {
            this.takeout = takeout;
        }

        public String getPutin() {
            return putin;
        }

        public void setPutin(String putin) {
            this.putin = putin;
        }

        public String getRemarkm() {
            return remarkm;
        }

        public void setRemarkm(String remarkm) {
            this.remarkm = remarkm;
        }

        public String getStockcode() {
            return stockcode;
        }

        public void setStockcode(String stockcode) {
            this.stockcode = stockcode;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }
    }
}
