package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.tools.ActivityCollector;
import com.huabiao.aoiin.tools.ViewTools;
import com.huabiao.aoiin.wedgit.CheckEdittextTextWatcher;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.constant.RegexConstants;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.StringUtil;
import com.ywy.mylibs.utils.ToastUtils;

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
    private int personType = -1;

    @Bind(R.id.register_card_two_username_et)
    TextInputLayout username_et;//客户姓名
    @Bind(R.id.register_card_two_username_delete_iv)
    ImageView username_delete_iv;
    @Bind(R.id.register_card_two_userphone_et)
    TextInputLayout userphone_et;//电话
    @Bind(R.id.register_card_two_userphone_delete_iv)
    ImageView userphone_delete_iv;
    @Bind(R.id.register_card_two_address_et)
    TextInputLayout address_et;//输入地址
    @Bind(R.id.register_card_two_address_delete_iv)
    ImageView address_delete_iv;
    @Bind(R.id.register_card_two_code_et)
    TextInputLayout code_et;//邮政编码
    @Bind(R.id.register_card_two_code_delete_iv)
    ImageView code_delete_iv;

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
//        userphone_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(userphone_et, "请输入正确的手机号!", 1));
//        userphone_et.setCounterEnabled(true);
//        userphone_et.setCounterMaxLength(11);//最大输入限制数(输入框后边有0/11的字数统计)

//        code_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(code_et, "请输入正确的邮政编码!", 2));
//        code_et.setCounterEnabled(true);
//        code_et.setCounterMaxLength(6);

        natura_tv.setOnClickListener(this);
        individual_tv.setOnClickListener(this);
        company_tv.setOnClickListener(this);
        next_tv.setOnClickListener(this);

        ViewTools.setEdittext(username_et.getEditText(), username_delete_iv
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        username_et.getEditText().setText("");
                    }
                });
        ViewTools.setEdittext(userphone_et.getEditText(), userphone_delete_iv
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userphone_et.getEditText().setText("");
                    }
                });
        ViewTools.setEdittext(address_et.getEditText(), address_delete_iv
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        address_et.getEditText().setText("");
                    }
                });
        ViewTools.setEdittext(code_et.getEditText(), code_delete_iv
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        code_et.getEditText().setText("");
                    }
                });

        if (commitBean.getPersonType() != -1) {
            setPersonTypeSelect(commitBean.getPersonType());
            personType = commitBean.getPersonType();
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
                break;
        }
    }

    private void save() {
        if (personType == -1) {
            showToast("请选择申请人类型");
            return;
        }
        if (StringUtil.isEmpty(username_et.getEditText().getText().toString().trim())) {
            showToast("请输入申请人姓名");
            return;
        }
        if (StringUtil.isEmpty(userphone_et.getEditText().getText().toString().trim())) {
            showToast("请输入联系电话");
            return;
        }
        if (!(userphone_et.getEditText().getText().toString().trim()).matches(RegexConstants.REGEX_MOBILE_EXACT)) {
            showToast("请输入正确的手机号");
            return;
        }
        if (StringUtil.isEmpty(address_et.getEditText().getText().toString().trim())) {
            showToast("请输入合同地址");
            return;
        }
        if (StringUtil.isEmpty(code_et.getEditText().getText().toString().trim())) {
            showToast("请输入邮政编码");
            return;
        }
        if (!(code_et.getEditText().getText().toString().trim()).matches(RegexConstants.REGEX_ZIP_CODE)) {
            showToast("请输入正确的邮政编码");
            return;
        }
        commitBean.setPersonType(personType);
        commitBean.setUsername(username_et.getEditText().getText().toString().trim());
        commitBean.setUserphone(userphone_et.getEditText().getText().toString().trim());
        commitBean.setContractAddress(address_et.getEditText().getText().toString().trim());
        commitBean.setCode(code_et.getEditText().getText().toString().trim());
        JumpUtils.startActivity(this, RegisterThreeActivity.class);
    }

    public void setPersonTypeSelect(int position) {
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setBackgroundResource(R.drawable.weixuanzhong);
            tvs[i].setTextColor(getResources().getColor(R.color.grey_999999));
            if (i == position) {
                tvs[i].setBackground(getResources().getDrawable(R.drawable.xuanzhong));
                tvs[i].setTextColor(getResources().getColor(R.color.black3));
            }
        }
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
