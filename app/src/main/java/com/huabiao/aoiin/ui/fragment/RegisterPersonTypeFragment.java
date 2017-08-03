package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterApplicantPersonBean;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.wedgit.CheckEdittextTextWatcher;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.ClickUtil;
import com.ywy.mylibs.utils.StringUtil;

import butterknife.Bind;
import chihane.jdaddressselector.AddressSelector;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-21 17:44
 * @description 注册--申请人选择
 */
public class RegisterPersonTypeFragment extends BaseFragment implements View.OnClickListener, OnAddressSelectedListener {
    //公有
    @Bind(R.id.person_type_name_et)
    TextInputLayout name_et;//申请人姓名
    @Bind(R.id.person_type_select_address_tv)
    TextView select_address_tv;//选择行政区划
    // 法人/个体
    @Bind(R.id.person_type_legal_name_et)
    TextInputLayout legal_name_et;//法人姓名
    // 自然人(无法人姓名)
    @Bind(R.id.person_type_certificates_id_et)
    TextInputLayout certificates_id_et;//身份证件文件号码

    @Bind(R.id.person_type_select_address_fl)
    FrameLayout  select_address_fl;
    private AddressSelector selector;

    @Bind(R.id.person_type_save_tv)
    TextView save_tv;//保存

    private int type;
    private String title;

    private RegisterCommitBean bean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle(title);
        setBackEnable();
        bean = RegisterCommitBean.getInstance();
        switch (type) {
            case 1:
                //法人或其他组织
                legal_name_et.setVisibility(View.VISIBLE);
                certificates_id_et.setVisibility(View.GONE);
                break;
            case 2:
                //个体工商户
                legal_name_et.setVisibility(View.VISIBLE);
                certificates_id_et.setVisibility(View.GONE);
                break;
            case 3:
                //自然人
                legal_name_et.setVisibility(View.GONE);
                certificates_id_et.setVisibility(View.VISIBLE);
                certificates_id_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(certificates_id_et, "请输入正确的身份证号!", 3));
                certificates_id_et.setCounterEnabled(true);
                certificates_id_et.setCounterMaxLength(18);//最大输入限制数(输入框后边有0/11的字数统计)
                break;
        }
        save_tv.setOnClickListener(this);

        selector = new AddressSelector(getContext());
        selector.setOnAddressSelectedListener(this);
        select_address_fl.addView(selector.getView());
    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getActivity().getIntent().getExtras();
        type = bundle.getInt("type", 1);
        title = bundle.getString("title");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.person_type_save_tv:
                //保存
                save();
                break;
        }
    }

    private void save() {
        if (StringUtil.isEmpty(name_et.getEditText().getText().toString().trim())) {
            showToast("请输入申请人姓名");
            return;
        }
        if ((select_address_tv.getText().toString().equals("请选择行政区划"))) {
            showToast("请选择行政区划");
            return;
        }
        switch (type) {
            case 1://法人或其他组织
            case 2://个体工商户
                if (StringUtil.isEmpty(legal_name_et.getEditText().getText().toString().trim())) {
                    showToast("请输入法人姓名");
                    return;
                }
                break;
            case 3:
                //自然人
                if (StringUtil.isEmpty(certificates_id_et.getEditText().getText().toString().trim())) {
                    showToast("请输入身份证号");
                    return;
                }
                break;
        }
        bean.setPersonName(name_et.getEditText().getText().toString().trim());
        bean.setCollectAddress(select_address_tv.getText().toString().trim());
        switch (type) {
            case 1:
                //法人或其他组织
                bean.setLegalPersonName(legal_name_et.getEditText().getText().toString().trim());
                bean.setCertificatesID("");
                bean.setPersonType(1);
                break;
            case 2:
                //个体工商户
                bean.setLegalPersonName(legal_name_et.getEditText().getText().toString().trim());
                bean.setCertificatesID("");
                bean.setPersonType(2);
                break;
            case 3:
                //自然人
                bean.setLegalPersonName("");
                bean.setCertificatesID(certificates_id_et.getEditText().getText().toString().trim());
                bean.setPersonType(3);
                break;
        }
        ALog.i("申请人数据-->" + bean.toString());
        ClickUtil.onBackClick();
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        String s = (province == null ? "" : province.name) +
                (city == null ? "" : "\t" + city.name) +
                (county == null ? "" : "\t" + county.name) +
                (street == null ? "" : "\t" + street.name);
        select_address_tv.setText(s);
    }

    @Override
    public int getContentLayout() {
        return R.layout.register_legal_person_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
