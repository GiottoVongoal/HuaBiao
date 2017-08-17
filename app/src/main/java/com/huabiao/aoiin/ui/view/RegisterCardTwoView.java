package com.huabiao.aoiin.ui.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.ui.fragment.RegisterPersonTypeFragment;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ChangeRegisterIndexEvent;
import com.huabiao.aoiin.wedgit.CheckEdittextTextWatcher;
import com.squareup.otto.Produce;
import com.ywy.mylibs.utils.JumpUtils;

import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-02 21:51
 * @description 自主注册第二张卡片(申请人类型 + 联系方式)
 */
public class RegisterCardTwoView extends RegisterCardBaseView implements View.OnClickListener {
    //申请人类型
    private TextView person_type_tv;
    private Dialog mDialog = null;
    private String[] popList = {"法人或其他组织", "个体工商户", "自然人"};

    private TextInputLayout username_et, userphone_et;//客户姓名,电话
    private TextView select_address_tv;//选择地址
    private TextInputLayout address_et;//输入地址
    private TextInputLayout code_et;//邮政编码
    private TextView next_tv;//下一步

    private RegisterCommitBean commitBean;

    private View view;
    private Context context;

    public RegisterCardTwoView(Context context) {
        super(context);
        view = inflate(context, R.layout.register_card_two_layout, this);
        this.context = context;
        commitBean = RegisterCommitBean.getInstance();
        person_type_tv = (TextView) view.findViewById(R.id.register_card_two_person_type_tv);
        username_et = (TextInputLayout) view.findViewById(R.id.register_card_two_username_et);
        userphone_et = (TextInputLayout) view.findViewById(R.id.register_card_two_userphone_et);
        select_address_tv = (TextView) view.findViewById(R.id.register_card_two_select_address_tv);
        address_et = (TextInputLayout) view.findViewById(R.id.register_card_two_address_et);
        code_et = (TextInputLayout) view.findViewById(R.id.register_card_two_code_et);
        next_tv = (TextView) view.findViewById(R.id.register_card_two_next_tv);

        userphone_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(userphone_et, "请输入正确的手机号!", 1));
        userphone_et.setCounterEnabled(true);
        userphone_et.setCounterMaxLength(11);//最大输入限制数(输入框后边有0/11的字数统计)

        code_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(code_et, "请输入正确的邮政编码!", 2));
        code_et.setCounterEnabled(true);
        code_et.setCounterMaxLength(6);

        person_type_tv.setOnClickListener(this);
        select_address_tv.setOnClickListener(this);
        next_tv.setOnClickListener(this);
    }

    //选择申请人类型弹框
    private Dialog selectPersonType() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择申请人类型");
        builder.setCancelable(true);
        builder.setSingleChoiceItems(popList, R.mipmap.ic_launcher,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", which + 1);
                        JumpUtils.startFragmentByName(context, RegisterPersonTypeFragment.class, bundle);
                    }
                });
        mDialog = builder.create();
        mDialog.show();
        return mDialog;
    }

    public void setPersonTypeSelect() {
        int personType = commitBean.getPersonType();
        if (personType != 0) {
            person_type_tv.setText(popList[personType - 1]);
        } else {
            person_type_tv.setText("请选择申请人类型");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_card_two_person_type_tv:
                //申请人类型
                selectPersonType();
                break;
            case R.id.register_card_two_select_address_tv:
                //选择地址
                getAddress(select_address_tv);
                break;
            case R.id.register_card_two_next_tv:
                //下一步
                AppBus.getInstance().post(produceChangeIndex());
                break;
        }
    }

    @Override
    public void save() {
        super.save();
        commitBean.setUsername(username_et.getEditText().getText().toString());
        commitBean.setUserphone(userphone_et.getEditText().getText().toString());
        String address = select_address_tv.getText().toString().equals("请选择合同地址地区") ? "" : select_address_tv.getText().toString();
//        commitBean.setContractSelectAddress(address);
        commitBean.setContractAddress(address_et.getEditText().getText().toString());
        commitBean.setCode(code_et.getEditText().getText().toString());
    }

    /**
     * 产生事件
     *
     * @return
     */
    @Produce
    public ChangeRegisterIndexEvent produceChangeIndex() {
        return new ChangeRegisterIndexEvent(2);
    }

    /**
     * 选择地址
     *
     * @param tv 显示选择结果
     */
    private void getAddress(final TextView tv) {
        final BottomDialog dialog = new BottomDialog(getContext());
        dialog.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(Province province, City city, County county, Street street) {
                String address = (province == null ? "" : province.name) +
                        (city == null ? "" : "\t" + city.name) +
                        (county == null ? "" : "\t" + county.name) +
                        (street == null ? "" : "\t" + street.name);

                tv.setText(address.trim());
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
