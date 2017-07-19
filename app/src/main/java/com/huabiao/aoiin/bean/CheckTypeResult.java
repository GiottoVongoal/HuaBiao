package com.huabiao.aoiin.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-19 14:04
 * @description
 */
public class CheckTypeResult {
    private volatile static CheckTypeResult instance = null;

    public static CheckTypeResult getInstance(int deep) {
        if (instance == null) {
            synchronized (CheckTypeResult.class) {
                if (instance == null) {
                    instance = new CheckTypeResult();
                    initSelectList(deep);
                }
                return instance;
            }
        }
        return instance;
    }

    private static boolean isChange = false;
    private static List<List<ClassificationItemBean>> selectList;

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }

    public List<List<ClassificationItemBean>> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<List<ClassificationItemBean>> selectList) {
        this.selectList = selectList;
    }

    public static void initSelectList(int deep) {
        selectList = new ArrayList<>();
        for (int i = 0; i < deep; i++) {
            selectList.add(new ArrayList<ClassificationItemBean>());
        }
    }

    public void clearList() {
        isChange = false;
        selectList.clear();
    }
}
