package com.huabiao.aoiin.selecttool;

import com.huabiao.aoiin.bean.ClassificationItemBean;

import java.util.List;

/**
 * Created by dun on 17/2/9.
 */

public interface DataProvider {
    void provideData(int currentDeep, String preId, DataReceiver receiver);

    void getNext(String id);

    interface DataReceiver {
        //        void send(List<ClassificationItemBean> data);
        void send();
    }
}
