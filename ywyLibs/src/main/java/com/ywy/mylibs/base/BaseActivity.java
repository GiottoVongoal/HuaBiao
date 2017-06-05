package com.ywy.mylibs.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.ywy.mylibs.R;
import com.ywy.mylibs.base.impl.IBase;
import com.ywy.mylibs.base.impl.IBaseView;
import com.ywy.mylibs.manager.AppManager;
import com.ywy.mylibs.manager.SystemBarTintManager;
import com.ywy.mylibs.utils.ClickUtil;
import com.ywy.mylibs.utils.ContextUtils;
import com.ywy.mylibs.utils.StringUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter<IBaseView>> extends AppCompatActivity implements IBase {
    /**
     * 主线程
     */
    private long mUIThreadId;

    public void setActionBar() {

    }

    public void getIntentValue() {

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
        MobclickAgent.onPause(this);
    }

    /**
     * 是否设置沉浸式
     *
     * @return
     */
    protected boolean isSetStatusBar() {
        return false;
    }


    protected BasePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUIThreadId = android.os.Process.myTid();
        AppManager.getAppManager().addActivity(this);
        mPresenter = getPresenter();
        if (mPresenter != null && this instanceof IBaseView) {
            mPresenter.attach((IBaseView) this);
        }

        initWindow();
        getIntentValue();
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
//        mToolbar = (Toolbar) findViewById(getToolBarId());
//        setSupportActionBar(mToolbar);
        setActionBar();
        initTitleBar(mRootView);
        bindView(savedInstanceState);
    }

    protected TextView tv_title;
    protected ImageView iv_left;

    /**
     * 初始化title
     */
    private void initTitleBar(View mRootView) {
        tv_title = (TextView) mRootView.findViewById(R.id.tv_title);
        iv_left = (ImageView) mRootView.findViewById(R.id.iv_left);
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


//    public abstract int getToolBarId();
//
//    public Toolbar getToolbar() {
//        return mToolbar;
//    }

    //    private Toolbar mToolbar;
    protected View mRootView;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ContextUtils.inflate(this, getContentLayout());
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public View getView() {
        return mRootView;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        mUIThreadId = android.os.Process.myTid();
        super.onNewIntent(intent);
    }

    /**
     * 获取UI线程ID
     *
     * @return UI线程ID
     */
    public long getUIThreadId() {
        return mUIThreadId;
    }

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && isSetStatusBar()) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.color_my_libs_ff6565);
        }
    }

    private SystemBarTintManager tintManager;

    protected void setStatusBarTintRes(int color) {
        if (tintManager != null) {
            tintManager.setStatusBarTintResource(color);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void exit() {
        finish();
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        if (mPresenter != null && this instanceof IBaseView) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int msgId) {
        showToast(getResources().getString(msgId));
    }

}
