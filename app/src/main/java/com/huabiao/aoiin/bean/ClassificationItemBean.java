package com.huabiao.aoiin.bean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-07-10 11:13
 * @description 分类--小类bean
 */
public class ClassificationItemBean extends ClassificationBean {

    private boolean nextlevel;
    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isNextlevel() {
        return nextlevel;
    }

    public void setNextlevel(boolean nextlevel) {
        this.nextlevel = nextlevel;
    }

    @Override
    public String toString() {
        return "ClassificationItemBean{" +
                "nextlevel=" + nextlevel +
                ", isChecked=" + isChecked +
                '}';
    }
}
