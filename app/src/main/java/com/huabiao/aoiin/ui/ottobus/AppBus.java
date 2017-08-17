package com.huabiao.aoiin.ui.ottobus;

import com.squareup.otto.Bus;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.ottobus
 * @date 2017-08-02 20:34
 * @description Otto事件
 */
public class AppBus extends Bus {

    private static AppBus bus;

    public static AppBus getInstance() {
        if (bus == null) {
            bus = new AppBus();
        }
        return bus;
    }
}
