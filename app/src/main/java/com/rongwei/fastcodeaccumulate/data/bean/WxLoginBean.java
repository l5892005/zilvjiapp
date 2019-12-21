package com.rongwei.fastcodeaccumulate.data.bean;

/**
 * Created by maoqi on 2018/12/27.
 */
public class WxLoginBean {

    /**
     * action : 1
     * token : iAJhIej0t1Kx3LsPDev4mU7R8zdkq39c
     * wx_code : sdfadfadf23sdfasdfa
     */

    private int action;
    private String token;
    private String wx_code;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWx_code() {
        return wx_code;
    }

    public void setWx_code(String wx_code) {
        this.wx_code = wx_code;
    }
}
