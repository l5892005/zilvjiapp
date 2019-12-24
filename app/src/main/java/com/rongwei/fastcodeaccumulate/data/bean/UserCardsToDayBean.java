package com.rongwei.fastcodeaccumulate.data.bean;

public class UserCardsToDayBean {
    /**
     * ucid : 1
     * uid : 1
     * createTime : 2019-12-24 10:57:31
     * udaytime : 0|13:23|0
     * codeCard : 010
     * imageCode : 2|3|4
     * imageName : fengche|jiaoying|kaohuo
     * cardName : fengche|jiaoying|kaohuo
     */

    private int ucid;
    private String uid;
    private String createTime;
    private String udaytime;
    private String codeCard;
    private String imageCode;
    private String imageName;
    private String imageCount;
    private String cardName;

    public int getUcid() {
        return ucid;
    }

    public void setUcid(int ucid) {
        this.ucid = ucid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUdaytime() {
        return udaytime;
    }

    public void setUdaytime(String udaytime) {
        this.udaytime = udaytime;
    }

    public String getCodeCard() {
        return codeCard;
    }

    public void setCodeCard(String codeCard) {
        this.codeCard = codeCard;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getImageCount() {
        return imageCount;
    }

    public void setImageCount(String imageCount) {
        this.imageCount = imageCount;
    }
}
