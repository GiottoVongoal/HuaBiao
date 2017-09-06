package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.tools.TimeCount;
import com.huabiao.aoiin.tools.ViewTools;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.constant.RegexConstants;

import butterknife.Bind;

public class GetVerificationCodeActivity extends BaseActivity implements View.OnClickListener {
    private TimeCount time;
    //电话输入框
    @Bind(R.id.et_phone)
    TextInputLayout et_phone;
    //电话一键删除
    @Bind(R.id.iv_delete_phone)
    ImageView iv_delete_phone;
    //验证码输入框
    @Bind(R.id.et_code)
    EditText et_code;
    //验证码一键删除
    @Bind(R.id.iv_delete_code)
    ImageView iv_delete_code;
    //获取验证码按钮
    @Bind(R.id.tv_getcode)
    TextView tv_getcode;
    //下一步按钮
    @Bind(R.id.tv_next)
    TextView tv_next;


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {

        //初始化短信倒计时内容
        time = new TimeCount(6000, 100);// 构造CountDownTimer对象
        time.init(this, tv_getcode);

        ViewTools.setEdittext( et_phone.getEditText(), iv_delete_phone
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        et_phone.getEditText().setText("");
                    }
                });
        ViewTools.setEdittext(et_code, iv_delete_code
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        et_code.setText("");
                    }
                });
        tv_getcode.setOnClickListener(this);
        tv_next.setOnClickListener(this);
    }

    @Override
    public int getContentLayout() {
        return R.layout.get_verification_code_layout;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getcode:
                if ((et_phone.getEditText().getText().toString().trim()).matches(RegexConstants.REGEX_MOBILE_EXACT)) {
                    time.start();// 开始计时
                }
                break;
            case  R.id.tv_next:
                String phone = et_phone.getEditText().getText().toString();
                String code = et_code.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    showToast("下一步");
                }
                break;
        }
    }
}
