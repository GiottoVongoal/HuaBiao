package com.huabiao.aoiin.ui.ottobus;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.adslip
 * @date 2017-08-02 18:05
 * @description 自主注册中收到消息后跳转到下个卡片
 */
public class ChangeRegisterIndexEvent {
    private int index;

    public ChangeRegisterIndexEvent(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
