package com.rongwei.fastcodeaccumulate.data.bean;

public class VersionBean {


    /**
     * vid : 1
     * versioncode : 1
     * url : www.baidu.com
     * ismust : 1
     * subdesc : 不要脸，跑来查垃圾数据
     */

    private int vid;
    private int versioncode;
    private String url;
    private int ismust;
    private String subdesc;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIsmust() {
        return ismust;
    }

    public void setIsmust(int ismust) {
        this.ismust = ismust;
    }

    public String getSubdesc() {
        return subdesc;
    }

    public void setSubdesc(String subdesc) {
        this.subdesc = subdesc;
    }
}
