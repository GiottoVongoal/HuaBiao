package com.huabiao.aoiin.selecttool;

public interface DataProvider {
    void provideData(int currentDeep, DataReceiver receiver);

    interface DataReceiver {
        void send();
    }
}
