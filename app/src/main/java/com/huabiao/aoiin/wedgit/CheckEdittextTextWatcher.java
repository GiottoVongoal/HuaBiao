package com.huabiao.aoiin.wedgit;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

import com.ywy.mylibs.constant.RegexConstants;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.wedgit
 * @date 2017-07-21 17:10
 * @description 为TextInputLayout增加的错误检测
 */
public class CheckEdittextTextWatcher implements TextWatcher {
    private String errorStr;
    private TextInputLayout textInputLayout;
    private int type;

    public CheckEdittextTextWatcher(TextInputLayout textInputLayout, String errorStr, int type) {
        this.textInputLayout = textInputLayout;
        this.errorStr = errorStr;
        this.type = type;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        switch (type) {
            case 1://验证手机号
                if ((textInputLayout.getEditText().getText().toString()).matches(RegexConstants.REGEX_MOBILE_EXACT)) {
                    textInputLayout.setErrorEnabled(false);
                } else {
                    textInputLayout.setErrorEnabled(true);
                    //设置错误提示的信息
                    textInputLayout.setError(errorStr);
                }
                break;
            case 2://验证邮政编码
                if ((textInputLayout.getEditText().getText().toString()).matches(RegexConstants.REGEX_ZIP_CODE)) {
                    textInputLayout.setErrorEnabled(false);
                } else {
                    textInputLayout.setErrorEnabled(true);
                    //设置错误提示的信息
                    textInputLayout.setError(errorStr);
                }
                break;
            case 3://验证身份证号
                if ((textInputLayout.getEditText().getText().toString()).matches(RegexConstants.REGEX_ID_CARD18)) {
                    textInputLayout.setErrorEnabled(false);
                } else {
                    textInputLayout.setErrorEnabled(true);
                    //设置错误提示的信息
                    textInputLayout.setError(errorStr);
                }
                break;
        }
    }
}
