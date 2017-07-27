package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterApplicantPersonBean;
import com.huabiao.aoiin.ui.activity.MainActivity;
import com.huabiao.aoiin.wedgit.CheckEdittextTextWatcher;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.ClickUtil;
import com.ywy.mylibs.utils.StringUtil;

import butterknife.Bind;
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
public class RegisterApplicantPersonFragment extends BaseFragment implements View.OnClickListener {
    //公有
    @Bind(R.id.applicant_person_name_et)
    TextInputLayout person_name_et;//申请人姓名
    @Bind(R.id.applicant_select_address_tv)
    TextView select_address_tv;//选择行政区划
    @Bind(R.id.applicant_person_address_et)
    TextInputLayout person_address_et;//申请人地址
    @Bind(R.id.applicant_contacts_person_name_et)
    TextInputLayout contacts_person_name_et;//联系人
    @Bind(R.id.applicant_contacts_person_phone_et)
    TextInputLayout contacts_person_phone_et;//联系人电话
    @Bind(R.id.applicant_contacts_person_code_et)
    TextInputLayout contacts_person_code_et;//邮政编码
    @Bind(R.id.applicant_collect_person_name_et)
    TextInputLayout collect_person_name_et;//收件人姓名
    @Bind(R.id.applicant_collect_person_phone_et)
    TextInputLayout collect_person_phone_et;//收件人电话
    @Bind(R.id.applicant_collect_person_address_tv)
    TextView collect_person_address_tv;//选择收件人地址
    @Bind(R.id.applicant_collect_person_address_et)
    TextInputLayout collect_person_address_et;//收件人地址

    // 法人或其他组织
    @Bind(R.id.applicant_legal_person_name_et)
    TextInputLayout legal_person_name_et;//法人姓名

    // 个体工商户(无法人姓名)

    // 自然人(无法人姓名)
    @Bind(R.id.applicant_certificates_type_et)
    TextInputLayout certificates_type_et;//身份证件文件名称
    @Bind(R.id.applicant_certificates_id_et)
    TextInputLayout certificates_id_et;//身份证件文件号码

    @Bind(R.id.applicant_save_tv)
    TextView save_tv;//保存

    private int type;
    private String title;

    private RegisterApplicantPersonBean bean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle(title);
        setBackEnable();
        bean = RegisterApplicantPersonBean.getInstance();
        switch (type) {
            case 1:
                //法人或其他组织
                legal_person_name_et.setVisibility(View.VISIBLE);
                break;
            case 2:
                //个体工商户
                break;
            case 3:
                //自然人
                certificates_type_et.setVisibility(View.VISIBLE);
                certificates_id_et.setVisibility(View.VISIBLE);
                certificates_id_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(certificates_id_et, "请输入正确的身份证号!", 3));
                certificates_id_et.setCounterEnabled(true);
                certificates_id_et.setCounterMaxLength(18);//最大输入限制数(输入框后边有0/11的字数统计)
                break;
        }
        contacts_person_phone_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(contacts_person_phone_et, "请输入正确的手机号!", 1));
        contacts_person_phone_et.setCounterEnabled(true);
        contacts_person_phone_et.setCounterMaxLength(11);
        contacts_person_code_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(contacts_person_code_et, "请输入正确的邮政编码!", 2));
        contacts_person_code_et.setCounterEnabled(true);
        contacts_person_code_et.setCounterMaxLength(6);
        collect_person_phone_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(collect_person_phone_et, "请输入正确的手机号!", 1));
        collect_person_phone_et.setCounterEnabled(true);
        collect_person_phone_et.setCounterMaxLength(11);

        select_address_tv.setOnClickListener(this);
        collect_person_address_tv.setOnClickListener(this);
        save_tv.setOnClickListener(this);
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
            case R.id.applicant_save_tv:
                //保存
                save();
                break;
            case R.id.applicant_select_address_tv:
                //选择行政区划
                getAddress(select_address_tv);
                break;
            case R.id.applicant_collect_person_address_tv:
                //选择收件人地址
                getAddress(collect_person_address_tv);
                break;
        }
    }

    private void save() {
        if (StringUtil.isEmpty(person_name_et.getEditText().getText().toString().trim())) {
            showToast("请输入申请人姓名");
            return;
        }
        if ((select_address_tv.getText().toString().equals("选择行政区划"))) {
            showToast("请选择行政区划");
            return;
        }
        if (StringUtil.isEmpty(person_address_et.getEditText().getText().toString().trim())) {
            showToast("请输入申请人地址");
            return;
        }
        if (StringUtil.isEmpty(contacts_person_name_et.getEditText().getText().toString().trim())) {
            showToast("请输入联系人姓名");
            return;
        }
        if (StringUtil.isEmpty(contacts_person_phone_et.getEditText().getText().toString().trim())) {
            showToast("请输入联系人电话");
            return;
        }
        if (StringUtil.isEmpty(contacts_person_code_et.getEditText().getText().toString().trim())) {
            showToast("请输入邮政编码");
            return;
        }
        if (StringUtil.isEmpty(collect_person_name_et.getEditText().getText().toString().trim())) {
            showToast("请输入收件人姓名");
            return;
        }
        if (StringUtil.isEmpty(collect_person_phone_et.getEditText().getText().toString().trim())) {
            showToast("请输入收件人电话");
            return;
        }
        if ((collect_person_address_tv.getText().toString().equals("选择收件人地址"))) {
            showToast("请选择收件人地址行政区划");
            return;
        }
        if (StringUtil.isEmpty(collect_person_address_et.getEditText().getText().toString().trim())) {
            showToast("请输入收件人地址");
            return;
        }
        switch (type) {
            case 1:
                //法人或其他组织
                if (StringUtil.isEmpty(legal_person_name_et.getEditText().getText().toString().trim())) {
                    showToast("请输入法人姓名");
                    return;
                }
                break;
            case 2:
                //个体工商户
                break;
            case 3:
                //自然人
                if (StringUtil.isEmpty(certificates_type_et.getEditText().getText().toString().trim())) {
                    showToast("请输入身份证件文件名称");
                    return;
                }
                if (StringUtil.isEmpty(certificates_id_et.getEditText().getText().toString().trim())) {
                    showToast("请输入身份证号");
                    return;
                }
                break;
        }
        bean.setPersonName(person_name_et.getEditText().getText().toString().trim());
        bean.setSelectAddress(select_address_tv.getText().toString().trim());
        bean.setPersonAddress(person_address_et.getEditText().getText().toString().trim());
        bean.setContactsPersonName(contacts_person_name_et.getEditText().getText().toString().trim());
        bean.setContactsPersonPhone(contacts_person_phone_et.getEditText().getText().toString().trim());
        bean.setContactsPersonCode(contacts_person_code_et.getEditText().getText().toString().trim());
        bean.setCollectPersonName(collect_person_name_et.getEditText().getText().toString().trim());
        bean.setCollectPersonPhone(collect_person_phone_et.getEditText().getText().toString().trim());
        bean.setCollectPersonSelectAddress(collect_person_address_tv.getText().toString().trim());
        bean.setCollectPersonAddress(collect_person_address_et.getEditText().getText().toString().trim());
        switch (type) {
            case 1:
                //法人或其他组织
                bean.setLegalPersonName(legal_person_name_et.getEditText().getText().toString().trim());
                bean.setCertificatesType("");
                bean.setCertificatesID("");
                bean.setChangeType(1);
                break;
            case 2:
                //个体工商户
                bean.setLegalPersonName("");
                bean.setCertificatesType("");
                bean.setCertificatesID("");
                bean.setChangeType(2);
                break;
            case 3:
                //自然人
                bean.setLegalPersonName("");
                bean.setCertificatesType(certificates_type_et.getEditText().getText().toString().trim());
                bean.setCertificatesID(certificates_id_et.getEditText().getText().toString().trim());
                bean.setChangeType(3);
                break;
        }
        ALog.i("申请人数据-->" + bean.toString());
        ClickUtil.onBackClick();
    }

    private void getAddress(final TextView tv) {
        final BottomDialog dialog = new BottomDialog(getContext());
        dialog.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(Province province, City city, County county, Street street) {
                String address = (province == null ? "" : province.name) +
                        (city == null ? "" : "\t" + city.name) +
                        (county == null ? "" : "\t" + county.name) +
                        (street == null ? "" : "\t" + street.name);
                tv.setText(address);
                dialog.dismiss();
            }
        });
        dialog.show();
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
