package com.rongwei.fastcodeaccumulate.receiver;




/**
 * 自定义JPush message 接收器,包括操作tag/alias的结果回调(仅仅包含tag/alias新接口部分)
 */
//public class JPushLocalReceiver extends JPushMessageReceiver {
public class JPushLocalReceiver {

  /*  *//**
     * tag增删查改
     *
     * @param context
     * @param jPushMessage
     *//*
    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
    }

    *//**
     * 查询某个tag与当前用户的绑定状态
     *
     * @param context
     * @param jPushMessage
     *//*
    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    *//**
     * alias相关的操作
     *
     * @param context
     * @param jPushMessage
     *//*
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        int sequence = jPushMessage.getSequence();
        String alias = jPushMessage.getAlias();

        switch (sequence) {
            case JPushUtils.sSetAliasSequence:
                LogUtils.d("JPush", "绑定成功，别名为：" + alias);
                break;
            case JPushUtils.sDeleteAliasSequence:
                LogUtils.d("JPush", "删除成功，别名为：" + alias);
                break;
        }
        super.onAliasOperatorResult(context, jPushMessage);
    }

    *//**
     * 设置手机号码
     *
     * @param context
     * @param jPushMessage
     *//*
    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }*/
}
