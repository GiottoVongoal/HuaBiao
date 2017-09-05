package com.huabiao.aoiin.third;

/**
 * @author fanxiaofeng
 *         登录成功的回调
 * @date 2015年10月15日 20:37:27
 */
public interface LoginCallBack {

    /**
     * 失败
     */
    public void onFailed(String msg);

    /**
     * 成功
     *
     * @param unionid 授权返回的唯一id
     */
    public void onSuccess(String unionid, String name, String avatar, String token);
}
