package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.wedgit.CheckEdittextTextWatcher;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.activity
 * @date 2017-08-18 15:03
 * @description 登录页面
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.textInputLayout_password)
    TextInputLayout textInputLayout_password;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("登录");
        setBackEnable();

        //密码
        textInputLayout_password.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(textInputLayout_password, "输入长度大于6!", 4));
        //开启计数
        textInputLayout_password.setCounterEnabled(true);
        textInputLayout_password.setCounterMaxLength(6);//最大输入限制数(输入框后边有0/6的字数统计)
    }

    @Override
    public int getContentLayout() {
        return R.layout.login_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}