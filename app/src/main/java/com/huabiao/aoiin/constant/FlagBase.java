package com.huabiao.aoiin.constant;

import android.os.Environment;

import java.io.File;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.constant
 * @date 2017-06-07 14:19
 * @description 公共常量
 */
public class FlagBase {

    //我的页面
    public static final int ME_COLLECTION = 1;// 我的收藏
    public static final int ME_BROWSE_RECORD = 2;// 我的足迹
    public static final int ME_MESSAGE = 3;// 我的消息
    public static final int ME_SETTING = 4;// 设置

    //设置页面
    public static final int SETTING_ACCOUNT_SAFE = 0;// 账户安全
    public static final int SETTING_ADDRESS = 1;// 地址管理
    public static final int SETTING_FEEDBACK = 2;// 建议反馈
    public static final int SETTING_ABOUT_US = 3;// 关于我们
    public static final int SETTING__AGREEMENT = 4;// 服务与隐私协议
    public static final int SETTING_CLEAR_CACHE = 5;// 清除缓存

    //拍照选照相关
    //移动端存放头像的本地路径，即attachment
    public static final String SDCARD_FJ_PATH = Environment.getExternalStorageDirectory() + File.separator;
    public static final int MEDIA_PHOTO = 10061;// 拍照标识
    public static final int MEDIA_SPHOTO = 10062;// 选照标识

    public static final int REGISTER_PHOTO = 10070;// 注册页面标识
    public static final int TRADE_LOGO = 10071;// 商标图样标识
    public static final int PROXY = 10072;// 委托书标识
    public static final int BUSINESS_LICENCE = 10073;// 营业执照标识
}
