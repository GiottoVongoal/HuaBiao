package com.ywy.mylibs.wedgit.customWedgit;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ywy.mylibs.R;
import com.ywy.mylibs.enums.LoadingState;
import com.ywy.mylibs.listener.OnRetryListener;
import com.ywy.mylibs.listener.OnTipsListener;
import com.ywy.mylibs.utils.NetWorkUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sysadminl on 2016/1/21.
 */
public class LoadingView extends FrameLayout {

    public LoadingView(Context context) {
        super(context);
        mContext = context;
        build();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (View.GONE == visibility && mState == LoadingState.STATE_LOADING && animation != null && animation.isRunning()) {
            animation.stop();
        }
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        build();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        build();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
    }

    private Context mContext;
    LinearLayout ll_over;
    LinearLayout ll_loading;
    TextView tv_loaded;
    TextView tv_loading;
    Button btn_loaded;

    public void ywy_lib_loading_btn_loaded_clicked() {
        if (mState == LoadingState.STATE_TIPS) {
            if (mOnTipsListener != null) {
                mOnTipsListener.onTipsClick();
                return;
            }
        }
        if (mOnRetryListener != null) {
            setState(LoadingState.STATE_LOADING);
            mOnRetryListener.onRetry();
        }
    }

    ImageView iv_loading;
    ImageView iv_loaded;

    private void findById(View view) {
        ll_over= (LinearLayout) view.findViewById(R.id.ywy_lib_loading_ll_over);
        ll_loading= (LinearLayout) view.findViewById(R.id.ywy_lib_loading_ll_loading);
        tv_loaded= (TextView) view.findViewById(R.id.ywy_lib_loading_tv_loaded);
        tv_loading= (TextView) view.findViewById(R.id.ywy_lib_loading_tv_loading);
        btn_loaded= (Button) view.findViewById(R.id.ywy_lib_loading_btn_loaded);
        iv_loading= (ImageView) view.findViewById(R.id.ywy_lib_loading_iv_loading);
        iv_loaded= (ImageView) view.findViewById(R.id.ywy_lib_loading_iv_loaded);
    }

    /**
     * 加载中提示文字
     */
    private String mLoadingText;
    private int mLoadingIco;

    public LoadingView withLoadingIco(int resId) {
        mLoadingIco = resId;
        return this;
    }


    /**
     * 加载数据为空提示文字
     */
    private String mLoaded_empty_text;
    private int mEmptyIco;

    public LoadingView withEmptyIco(int resId) {
        mEmptyIco = resId;
        return this;
    }

    /**
     * 没网提示
     */
    private String mLoaded_not_net_text;
    private int mNoNetIco;

    public LoadingView withNoNetIco(int resId) {
        mNoNetIco = resId;
        return this;
    }

    public OnRetryListener mOnRetryListener;
    public OnTipsListener mOnTipsListener;

    public LoadingView withOnRetryListener(OnRetryListener mOnRetryListener) {
        this.mOnRetryListener = mOnRetryListener;
        return this;
    }

    public LoadingView withOnTipsListener(OnTipsListener mOnTipsListener) {
        this.mOnTipsListener = mOnTipsListener;
        return this;
    }

    private LoadingState mState;

    public void setState(LoadingState state) {
        if (mState == state) {
            return;
        } else if (state == LoadingState.STATE_LOADING) {
            ll_over.setVisibility(GONE);
            ll_loading.setVisibility(VISIBLE);
        } else if (state != LoadingState.STATE_LOADING) {
            ll_loading.setVisibility(GONE);
            ll_over.setVisibility(VISIBLE);
            if (animation != null && mState == LoadingState.STATE_LOADING)
                animation.stop();
        }
        changeState(state);
    }

    public boolean btn_empty_ennable = true;
    public boolean btn_error_ennable = true;
    public boolean btn_nonet_ennable = true;
    public boolean btn_tips_ennable = true;

    public LoadingView withBtnNoNetEnnable(boolean ennable) {
        btn_nonet_ennable = ennable;
        return this;
    }

    public LoadingView withBtnErrorEnnable(boolean ennable) {
        btn_error_ennable = ennable;
        return this;
    }


    public LoadingView withBtnEmptyEnnable(boolean ennable) {
        btn_empty_ennable = ennable;
        return this;
    }

    public LoadingView withBtnTipEnnable(boolean ennable) {
        btn_tips_ennable = ennable;
        return this;
    }

    private AnimationDrawable animation;

    private void changeState(LoadingState state) {
        switch (state) {
            case STATE_LOADING:
                mState = LoadingState.STATE_LOADING;
                iv_loading.setImageResource(mLoadingIco);
                tv_loading.setText(mLoadingText);
//                if (animation == null) {
                animation = (AnimationDrawable) iv_loading.getDrawable();
//                }
                if (animation != null)
                    animation.start();
                break;
            case STATE_EMPTY:
                mState = LoadingState.STATE_EMPTY;
                iv_loaded.setImageResource(mEmptyIco);
                tv_loaded.setText(mLoaded_empty_text);
                if (btn_empty_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_empty_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
            case STATE_ERROR:
                mState = LoadingState.STATE_ERROR;
                iv_loaded.setImageResource(mErrorIco);
                tv_loaded.setText(mLoaded_error_text);
                if (btn_error_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_error_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
            case STATE_NO_NET:
                mState = LoadingState.STATE_NO_NET;
                iv_loaded.setImageResource(mNoNetIco);
                tv_loaded.setText(mLoaded_not_net_text);
                if (btn_nonet_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_nonet_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
            case STATE_TIPS:
                mState = LoadingState.STATE_TIPS;
                iv_loaded.setImageResource(mTipsIco);
                tv_loaded.setText(mLoaded_tips_text);
                if (btn_tips_ennable) {
                    btn_loaded.setVisibility(VISIBLE);
                    btn_loaded.setText(btn_tips_text);
                } else {
                    btn_loaded.setVisibility(GONE);
                }
                break;
        }

    }


    public LoadingState getmState(){
        return mState;
    }
    /**
     * 服务器返回提示
     */
    private String mLoaded_tips_text;
    private int mTipsIco;

    public LoadingView withTipsIco(int resId) {
        mTipsIco = resId;
        return this;
    }

    public LoadingView withbtnTipText(int resId) {
        btn_tips_text = getResources().getString(resId);
        return this;
    }

    public LoadingView withbtnTipText(String text) {
        this.btn_tips_text = text;
        return this;
    }

    public LoadingView withLoadedTipText(String mTipsText) {
        this.mLoaded_tips_text = mTipsText;
        return this;
    }

    public LoadingView withLoadedTipText(int resId) {
        mLoaded_tips_text = getResources().getString(resId);
        return this;
    }


    /**
     * 后台或者本地出现错误提示
     */
    private String mLoaded_error_text;
    private int mErrorIco;

    public LoadingView withErrorIco(int resId) {
        mErrorIco = resId;
        return this;
    }

    public LoadingView withLoadedEmptyText(int resId) {
        mLoaded_empty_text = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadedEmptyText(String mLoadedemptyText) {
        this.mLoaded_empty_text = mLoadedemptyText;
        return this;
    }

    public String btn_empty_text = "重试";
    public String btn_error_text = "重试";
    public String btn_nonet_text = "重试";
    public String btn_tips_text = "重试";

    public LoadingView withbtnEmptyText(String text) {
        this.btn_empty_text = text;
        return this;
    }

    public LoadingView withbtnErrorText(String text) {
        this.btn_error_text = text;
        return this;
    }

    public LoadingView withbtnNoNetText(String text) {
        this.btn_nonet_text = text;
        return this;
    }

    public LoadingView withLoadedNoNetText(int resId) {
        mLoaded_not_net_text = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadedNoNetText(String mLoadedNoNetText) {
        this.mLoaded_not_net_text = mLoadedNoNetText;
        return this;
    }

    public LoadingView withLoadedErrorText(int resId) {
        mLoaded_error_text = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadedErrorText(String mLoadedErrorText) {
        this.mLoaded_error_text = mLoadedErrorText;
        return this;
    }

    public LoadingView withLoadingText(int resId) {
        mLoadingText = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadingText(String mLoadingText) {
        this.mLoadingText = mLoadingText;
        return this;
    }


    public void build() {
        View view = View.inflate(mContext, R.layout.ywy_lib_loading_iv_loading, this);
//        ButterKnife.bind(this, view);
        findById(view);
    }

    public void setCurrentState() {
        if (NetWorkUtils.isNetWorkConnected(mContext)) {
            setState(LoadingState.STATE_LOADING);
        } else {
            setState(LoadingState.STATE_NO_NET);
        }
    }
}
