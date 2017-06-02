package com.huabiao.aoiin;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.huabiao.aoiin.constant.Config;
import com.umeng.analytics.MobclickAgent;
import com.ywy.mylibs.base.BaseApplication;
import com.ywy.mylibs.constant.Const;
import com.ywy.mylibs.utils.ChannelUtil;

/**
 * Created by PC on 2017/5/23.
 */

public class HuaBiaoApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
//                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())// 添加图片加载时模糊效果
//                .setBitmapMemoryCacheParamsSupplier(bitmapCache)
                .build();
        Fresco.initialize(this, config);//注册图片加载框架
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

    @Override
    public boolean isDebug() {
        return Const.DBG;
    }
}
