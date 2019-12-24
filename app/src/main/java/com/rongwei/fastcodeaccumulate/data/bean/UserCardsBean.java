package com.rongwei.fastcodeaccumulate.data.bean;

import java.util.List;

public class UserCardsBean {
    /**
     * cardid : 2 id
     * imgcard : fengche
     * ername : 瑜伽
     * useid : 1
     * createtime : 1577108104000
     * imgstatic : 1 状态0为未打卡
     */

    private String cardid;
    private String imgName;
    private String imgId;
    private String cardName;
    private long createtime;
    private String imgstatic;
    private String imgsCount;

    public String getImgsCount() {
        return imgsCount;
    }

    public void setImgsCount(String imgsCount) {
        this.imgsCount = imgsCount;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getImgstatic() {
        return imgstatic;
    }

    public void setImgstatic(String imgstatic) {
        this.imgstatic = imgstatic;
    }

    @Override
    public String toString() {
        return "UserCardsBean{" +
                "cardid='" + cardid + '\'' +
                ", imgName='" + imgName + '\'' +
                ", imgId='" + imgId + '\'' +
                ", cardName='" + cardName + '\'' +
                ", createtime=" + createtime +
                ", imgstatic='" + imgstatic + '\'' +
                '}';
    }
}
