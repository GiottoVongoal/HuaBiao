package com.huabiao.aoiin.utils;

import com.blankj.ALog;
import com.ywy.mylibs.base.BaseApplication;
import com.ywy.mylibs.utils.ChannelUtil;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by PC on 2017/5/24.
 */
public class ChannelUtilTest {

    private ChannelUtil channelUtil;

    @Before
    public void setUp() throws Exception {
        channelUtil = new ChannelUtil();
    }

    @Test
    public void getChannel() throws Exception {
        String channel = ChannelUtil.getChannel(BaseApplication.getContext());
        ALog.i(channel);
    }

    @Test
    public void getChannel1() throws Exception {

    }

    @Test
    public void getChannelFromApk() throws Exception {
        String channel = ChannelUtil.getChannelFromApk(BaseApplication.getContext(),"cztchannel");
        Assert.assertEquals("test",channel);
    }

    @Test
    public void saveChannelBySharedPreferences() throws Exception {

    }

    @Test
    public void getChannelName() throws Exception {
        String channel = ChannelUtil.getChannelName(BaseApplication.getContext(),"cztchannel");
        Assert.assertEquals("test",channel);
    }

    @Test
    public void getChannelNameByWalle() throws Exception {

    }

}