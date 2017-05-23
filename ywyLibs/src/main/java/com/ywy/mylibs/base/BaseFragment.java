package com.ywy.mylibs.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.blankj.ALog;
import com.umeng.analytics.MobclickAgent;
import com.ywy.mylibs.base.impl.IBase;
import com.ywy.mylibs.base.impl.IBaseView;

import butterknife.ButterKnife;

/**
 * Created by sysadminl on 2015/12/10.
 */
public abstract class BaseFragment<T extends BasePresenter<IBaseView>> extends Fragment implements IBase {
    public String TAG;

    protected BasePresenter mPresenter;
    protected Context mContext;
    protected boolean isFirstLoad = true;

    public String setTAG() {
        return "BaseFragment";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG = setTAG();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mPresenter = getPresenter();
        if (mPresenter != null && this instanceof IBaseView) {
            mPresenter.attach((IBaseView) this);
        }
        ALog.i(TAG + "   onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ALog.i(TAG + "   onResume");
        MobclickAgent.onPageStart(this.getClass().getSimpleName()); //统计页面，"MainScreen"为页面名称，可自定义
    }

    @Override
    public void onPause() {
        super.onPause();
        ALog.i(TAG + "   onPause");
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }

    protected View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ALog.i(TAG + "   onCreateView");
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        } else {
            mRootView = createView(inflater, container, savedInstanceState);
        }

        mContext = mRootView.getContext();
        isFirstLoad = false;
        return mRootView;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayout(), container, false);
        ButterKnife.bind(this, view);
        ALog.i(TAG + "   createView");
        return view;
    }

    @Nullable
    @Override
    public View getView() {
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ALog.i(TAG + "   onActivityCreated");
        getIntentValue();
        bindView(savedInstanceState);
    }

    public void getIntentValue() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ALog.i(TAG + "   onSaveInstanceState");
    }

    @Override
    public void onDestroy() {
        ALog.i(TAG + "   onDestroy");
        if (mPresenter != null && this instanceof IBaseView) {
            mPresenter.detachView();
            mPresenter = null;
        }
        mContext = null;
        super.onDestroy();
    }

    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int msgId) {
        Toast.makeText(mContext, msgId, Toast.LENGTH_SHORT).show();
    }
}
