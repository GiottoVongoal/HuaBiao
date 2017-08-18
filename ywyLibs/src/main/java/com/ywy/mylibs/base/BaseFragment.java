package com.ywy.mylibs.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.ALog;
import com.umeng.analytics.MobclickAgent;
import com.ywy.mylibs.R;
import com.ywy.mylibs.base.impl.IBase;
import com.ywy.mylibs.base.impl.IBaseView;
import com.ywy.mylibs.utils.ClickUtil;
import com.ywy.mylibs.utils.StringUtil;

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
        initTitleBar(view);
        ALog.i(TAG + "   createView");
        return view;
    }

    protected TextView tv_title;
    protected ImageView iv_left;
    protected TextView tv_right;
    protected ImageView title_right_iv;

    /**
     * 初始化title
     */
    private void initTitleBar(View view) {
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        iv_left = (ImageView) view.findViewById(R.id.iv_left);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        title_right_iv = (ImageView) view.findViewById(R.id.title_right_iv);
    }

    /**
     * 设置返回键监听
     */
    public void setBackEnable() {
        if (iv_left != null) {
            iv_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickUtil.onBackClick();
                }
            });
        }
    }

    /**
     * 设置title
     */
    public void setTitle(String title) {
        if (!StringUtil.isEmpty(title) && tv_title != null)
            tv_title.setText(title);
    }

    public void setTitle(int titleId) {
        setTitle(getResources().getString(titleId));
    }

    /**
     * 设置返回鍵图片
     */
    public void setBackResourse(Drawable back) {
        if (back != null && iv_left != null)
            iv_left.setBackgroundDrawable(back);
    }

    public void setBackResourse(int backId) {
        if (iv_left != null)
            iv_left.setBackgroundResource(backId);
    }

    /**
     * 设置右边文字
     */
    public void setRightText(String right) {
        if (!StringUtil.isEmpty(right) && tv_right != null) {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(right);
        }
    }
    /**
     * 设置右边图片
     */
    public void setRightIvResourse(Drawable back) {
        if (back != null && title_right_iv != null) {
            title_right_iv.setVisibility(View.VISIBLE);
            title_right_iv.setBackgroundDrawable(back);
        }
    }

    public void setRightIvResourse(int backId) {
        if (title_right_iv != null) {
            title_right_iv.setVisibility(View.VISIBLE);
            title_right_iv.setBackgroundResource(backId);
        }
    }

    public void setRightIvOnclick(View.OnClickListener click) {
        title_right_iv.setOnClickListener(click);
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
