package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.wedgit.CheckEdittextTextWatcher;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.ClickUtil;

import butterknife.Bind;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

import static com.huabiao.aoiin.R.id.tv;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-18 18:02
 * @description 新增地址
 */
public class UserAddressAddFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.user_address_add_name_et)
    TextInputLayout add_name_et;
    @Bind(R.id.user_address_add_phone_et)
    TextInputLayout add_phone_et;
    @Bind(R.id.user_address_add_area_et)
    TextInputLayout add_area_et;
    @Bind(R.id.user_address_add_address_et)
    TextInputLayout add_address_et;
    @Bind(R.id.user_address_add_code_et)
    TextInputLayout add_code_et;

    @Bind(R.id.user_address_add_tv)
    TextView add_tv;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("新增地址");
        setBackEnable();

        add_phone_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(add_phone_et, "请输入正确的手机号!", 1));
        add_phone_et.setCounterEnabled(true);
        add_phone_et.setCounterMaxLength(11);//最大输入限制数(输入框后边有0/11的字数统计)

        add_code_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(add_code_et, "请输入正确的邮政编码!", 2));
        add_code_et.setCounterEnabled(true);
        add_code_et.setCounterMaxLength(6);

        add_area_et.setOnClickListener(this);
        add_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_address_add_area_et:
                getAddress();
                break;
            case R.id.user_address_add_tv:
                showToast("保存");
                ClickUtil.onBackClick();
                break;
        }
    }

    /**
     * 选择地址
     */
    private void getAddress() {
        final BottomDialog dialog = new BottomDialog(getContext());
        dialog.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(Province province, City city, County county, Street street) {
                String address = (province == null ? "" : province.name) +
                        (city == null ? "" : "\t" + city.name) +
                        (county == null ? "" : "\t" + county.name) +
                        (street == null ? "" : "\t" + street.name);
                add_area_et.getEditText().setText(address.trim());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public int getContentLayout() {
        return R.layout.user_address_add_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
