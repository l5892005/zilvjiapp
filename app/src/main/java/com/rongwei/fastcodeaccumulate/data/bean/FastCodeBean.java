package com.rongwei.fastcodeaccumulate.data.bean;

import java.util.List;

public class FastCodeBean {

    /**
     * data : [{"createtime":"2019-11-09 19:07:02","id":4,"importd":5,"name":"dassds f"},{"createtime":"2019-11-09 23:33:35","id":5,"importd":1,"name":"zhangpei","type":1,"typedesc":1}]
     * max_page : 10
     * total : 10
     */

    private int max_page;
    private int total;
    private List<DataBean> data;

    public int getMax_page() {
        return max_page;
    }

    public void setMax_page(int max_page) {
        this.max_page = max_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2019-11-09 19:07:02
         * id : 4
         * importd : 5
         * name : dassds f
         * type : 1
         * typedesc : 1
         */

        private String createtime;
        private int id;
        private int importd;
        private String name;
        private String content;
        private int type;
        private int typedesc;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getImportd() {
            return importd;
        }

        public void setImportd(int importd) {
            this.importd = importd;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getTypedesc() {
            return typedesc;
        }

        public void setTypedesc(int typedesc) {
            this.typedesc = typedesc;
        }
    }
}
