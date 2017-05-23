package com.ywy.mylibs.base.impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ywy.mylibs.base.BasePresenter;


/**
 * Created by ywy on 2016/5/30.
 */
public interface IBase {
    BasePresenter getPresenter();

    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void bindView(Bundle savedInstanceState);

    View getView();

    int getContentLayout();

    void showToast(String msg);

    void showToast(int msgId);
}
