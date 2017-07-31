package com.huabiao.aoiin;

//import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.huabiao.aoiin.constant.Config;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.ywy.mylibs.base.BaseApplication;
import com.ywy.mylibs.constant.Const;
import com.ywy.mylibs.utils.ChannelUtil;
import com.umeng.socialize.utils.Log;
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
        initBitmap();

    }
    private void initBitmap() {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new UsingFreqLimitedMemoryCache(((int) Runtime.getRuntime().maxMemory()) / 8))
                .diskCache(new UnlimitedDiskCache(StorageUtils.getOwnCacheDirectory(this, "huabiao/images")))
                // .memoryCacheSize(8 * 1024 * 1024)
                .memoryCacheSize(((int) Runtime.getRuntime().maxMemory()) / 8).diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .threadPoolSize(3).imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000))
                .denyCacheImageMultipleSizesInMemory().build();
        ImageLoader.getInstance().init(configuration);

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

        UMShareAPI.get(this);
        Log.LOG = false;
        com.umeng.socialize.Config.DEBUG = false;
        //其中qq 微信会跳转到下载界面进行下载，其他应用会跳到应用商店进行下载
        com.umeng.socialize.Config.isJumptoAppStore = true;

        //第三方账号
        PlatformConfig.setWeixin("key", "secret");
        PlatformConfig.setQQZone("key", "secret");
        PlatformConfig.setSinaWeibo("key", "secret", "http://sns.whalecloud.com/sina2/callback");
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setDebugMode(Const.DBG);
    }

    @Override
    public boolean isDebug() {
        return Const.DBG;
    }
}
