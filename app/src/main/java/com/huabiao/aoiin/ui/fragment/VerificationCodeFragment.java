package com.huabiao.aoiin.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.wedgit.VerficationCodeView;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-06-05 14:07
 * @description 输入验证码页面
 */
public class VerificationCodeFragment extends BaseFragment implements VerficationCodeView.InputCompleteListener {

    @Bind(R.id.code_view)
    VerficationCodeView code_view;
    @Bind(R.id.code_tv)
    TextView code_tv;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {

    }

    @Override
    public void inputComplete() {
        ALog.i("验证码是：" + code_view.getEditContent());
        //输入完成后的监听

    }

    @Override
    public void deleteContent(boolean isDelete) {
        if (isDelete) {
            code_tv.setText("输入验证码表示同意《用户协议》");
            code_tv.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.verficationcode_layout;
    }
}
