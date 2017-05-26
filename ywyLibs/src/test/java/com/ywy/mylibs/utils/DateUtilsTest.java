package com.ywy.mylibs.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by PC on 2017/5/24.
 */
public class DateUtilsTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void formatMsgDate() throws Exception {

    }

    @Test
    public void formatDateHHmm() throws Exception {

    }

    @Test
    public void getCurrentYMD() throws Exception {

    }

    @Test
    public void formatTimeMillis() throws Exception {

    }

    @Test
    public void seconds2Hour() throws Exception {

    }

    @Test
    public void isToday() throws Exception {
        Assert.assertEquals(true, DateUtils.isToday(System.currentTimeMillis()));
    }

    @Test
    public void getPublishTime() throws Exception {
        Assert.assertEquals("刚刚", DateUtils.getPublishTime(System.currentTimeMillis()));
    }

}