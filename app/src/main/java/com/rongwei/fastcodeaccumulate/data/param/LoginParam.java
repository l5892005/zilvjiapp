package com.rongwei.fastcodeaccumulate.data.param;

/**
 * Created by maoqi on 2018/12/27.
 */
public class LoginParam {

    /**
     * mobile : 13125175339
     * sms_code : 1234
     * wx_code :
     */

    private String mobile;
    private String sms_code;
    private String wx_code;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSms_code() {
        return sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }

    public String getWx_code() {
        return wx_code;
    }

    public void setWx_code(String wx_code) {
        this.wx_code = wx_code;
    }
}
