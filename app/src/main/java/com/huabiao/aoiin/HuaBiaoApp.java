package com.huabiao.aoiin;

import com.huabiao.aoiin.constant.Config;
import com.umeng.analytics.MobclickAgent;
import com.ywy.mylibs.base.BaseApplication;
import com.ywy.mylibs.constant.Const;
import com.huabiao.aoiin.utils.ChannelUtil;

/**
 * Created by PC on 2017/5/23.
 */

public class HuaBiaoApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initUmeng();
    }

    /**
     * 渠道统计
     */
    protected void initUmeng() {
        String channel = ChannelUtil.getChannel(this);//获取渠道名
        MobclickAgent agent = new MobclickAgent();
        if (Const.DBG) {
            agent.setDebugMode(true);
        }

        agent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, Config.UMENG_APPKEY, channel));

    }
}
