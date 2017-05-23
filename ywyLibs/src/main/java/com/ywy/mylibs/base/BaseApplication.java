package com.ywy.mylibs.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.blankj.ALog;
import com.orm.SugarContext;
import com.ywy.mylibs.base.impl.ApplicationInter;


/**
 * Created by yangweiyi on 16/5/19.
 */
public class BaseApplication extends MultiDexApplication implements ApplicationInter {

    private static Context mInstance = null;
    public boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = getApplicationContext();
        isDebug = isDebug();

        SugarContext.init(this);

        ALog.Builder builder = new ALog.Builder(this)
                .setLogSwitch(isDebug)// 设置log总开关，默认开
                .setGlobalTag("")// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLogHeadSwitch(true)// 设置log头部是否显示，默认显示
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setLogFilter(ALog.V);// log过滤器，和logcat过滤器同理，默认Verbose

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    public static Context getContext() {
        return mInstance;
    }

    @Override
    public boolean isDebug() {
        return true;
    }
}
