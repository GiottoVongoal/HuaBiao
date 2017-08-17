package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.tools.ActivityCollector;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.wedgit.CheckEdittextTextWatcher;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.StringUtil;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.activity
 * @date 2017-08-15 14:58
 * @description 自主注册第二步(申请人类型 + 联系方式)
 */
public class RegisterTwoActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.register_card_five_natura_tv)
    TextView natura_tv;//自然人
    @Bind(R.id.register_card_five_individual_tv)
    TextView individual_tv;//个体工商户
    @Bind(R.id.register_card_five_company_tv)
    TextView company_tv;//公司或其他组织
    private TextView[] tvs = new TextView[3];
    private int personType;

    @Bind(R.id.register_card_two_username_et)
    TextInputLayout username_et;//客户姓名
    @Bind(R.id.register_card_two_userphone_et)
    TextInputLayout userphone_et;//电话
    @Bind(R.id.register_card_two_address_et)
    TextInputLayout address_et;//输入地址
    @Bind(R.id.register_card_two_code_et)
    TextInputLayout code_et;//邮政编码

    @Bind(R.id.register_card_two_next_tv)
    TextView next_tv;//下一步

    private RegisterCommitBean commitBean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("注册信息");
        setBackEnable();
        setRightIvResourse(getResources().getDrawable(R.mipmap.kefu_icon));
        setRightIvOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("客服");
            }
        });
        ActivityCollector.addActivity(this);

        tvs[0] = natura_tv;
        tvs[1] = individual_tv;
        tvs[2] = company_tv;

        commitBean = RegisterCommitBean.getInstance();
        userphone_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(userphone_et, "请输入正确的手机号!", 1));
        userphone_et.setCounterEnabled(true);
        userphone_et.setCounterMaxLength(11);//最大输入限制数(输入框后边有0/11的字数统计)

        code_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(code_et, "请输入正确的邮政编码!", 2));
        code_et.setCounterEnabled(true);
        code_et.setCounterMaxLength(6);

        natura_tv.setOnClickListener(this);
        individual_tv.setOnClickListener(this);
        company_tv.setOnClickListener(this);
        next_tv.setOnClickListener(this);

        if (commitBean.getPersonType() != -1) {
            setPersonTypeSelect(commitBean.getPersonType());
        }
        if (!StringUtil.isEmpty(commitBean.getUsername())) {
            username_et.getEditText().setText(commitBean.getUsername());
        }
        if (!StringUtil.isEmpty(commitBean.getUserphone())) {
            userphone_et.getEditText().setText(commitBean.getUserphone());
        }
        if (!StringUtil.isEmpty(commitBean.getContractAddress())) {
            address_et.getEditText().setText(commitBean.getContractAddress());
        }
        if (!StringUtil.isEmpty(commitBean.getCode())) {
            code_et.getEditText().setText(commitBean.getCode());
        }
    }

    public void setPersonTypeSelect(int position) {
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setBackgroundResource(R.mipmap.leibei_weixuanzhong);
            tvs[i].setTextColor(getResources().getColor(R.color.grey_8E8E8E));
            if (i == position) {
                tvs[i].setBackground(getResources().getDrawable(R.mipmap.leibei_xuanzhong));
                tvs[i].setTextColor(getResources().getColor(R.color.black3));
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_card_five_natura_tv:
                //自然人
                personType = 0;
                setPersonTypeSelect(personType);
                break;
            case R.id.register_card_five_individual_tv:
                //个体工商户
                personType = 1;
                setPersonTypeSelect(personType);
                break;
            case R.id.register_card_five_company_tv:
                //公司或其他组织
                personType = 2;
                setPersonTypeSelect(personType);
                break;
            case R.id.register_card_two_next_tv:
                //下一步
//                AppBus.getInstance().post(produceChangeIndex());
                save();
                JumpUtils.startActivity(this, RegisterThreeActivity.class);
                break;
        }
    }

    private void save() {
        commitBean.setPersonType(personType);
        commitBean.setUsername(username_et.getEditText().getText().toString());
        commitBean.setUserphone(userphone_et.getEditText().getText().toString());
        commitBean.setContractAddress(address_et.getEditText().getText().toString());
        commitBean.setCode(code_et.getEditText().getText().toString());
    }


    @Override
    public int getContentLayout() {
        return R.layout.register_two_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
