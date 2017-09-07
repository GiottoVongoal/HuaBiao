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
    //回调状态码
    public static final int CALL_BACK_SUCCESS = 10000;
    public static final int CALL_BACK_FAILED = 10001;

    //刷新与加载
    public static final int PULL_TO_REFRESH = 10000;
    public static final int SCROLL_LOAD_MORE = 10001;

    //拍照选照相关
    //移动端存放头像的本地路径，即attachment
    public static final String SDCARD_FJ_PATH = Environment.getExternalStorageDirectory() + File.separator;
    public static final int MEDIA_PHOTO = 10061;// 拍照标识
    public static final int MEDIA_SPHOTO = 10062;// 选照标识

    public static final int REGISTER_PHOTO = 10070;// 注册页面标识
    public static final int TRADE_LOGO = 10071;// 商标图样标识
    public static final int PROXY = 10072;// 委托书标识
    public static final int BUSINESS_LICENCE = 10073;// 营业执照标识

    public static final String THIRD_PLATFORM = "third_platform";
    public static final int CODE_LOGIN2REGISTER = 103;
}
