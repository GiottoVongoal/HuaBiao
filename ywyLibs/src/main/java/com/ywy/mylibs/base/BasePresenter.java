package com.ywy.mylibs.base;


import com.ywy.mylibs.base.impl.IBaseView;
import com.ywy.mylibs.exception.NotBindViewException;

/**
 * Created by ywy on 2016/5/30.
 */
public abstract class BasePresenter<T extends IBaseView> {
    public T mView;

    public void attach(T mView) {
        if (mView != null) {
            this.mView = mView;
        } else {
            throw new NotBindViewException();
        }
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
