package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterOneIndustryBean;
import com.huabiao.aoiin.model.RegisterOneModel;
import com.huabiao.aoiin.ui.adapter.IndustryPopupWindowAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.IndustryPopupWindow;
import com.huabiao.aoiin.wedgit.RegisterOneFinishPopupWindow;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-12 09:36
 * @description Tab中的注册输入页面
 */
public class RegisterOneFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.register_one_tradename_et)
    EditText tradename_et;//商标名
    @Bind(R.id.register_one_industry_tv)
    TextView industry_tv;//选择行业
    @Bind(R.id.register_one_register_tv)
    TextView register_tv;//注册按钮
    //选择行业的弹框
    private IndustryPopupWindow industryWindow;
    private int place = 0;
    private String industry = "", tradename = "";
    //选择客服弹出框
    private RegisterOneFinishPopupWindow finishPopupWindow;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        industry_tv.setOnClickListener(this);
        register_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.register_one_industry_tv:
                //选择行业
                RegisterOneModel.getIndustryList(getContext(), new InterfaceManager.CallBackCommon() {
                    @Override
                    public void getCallBackCommon(Object mData) {
                        if (mData != null) {
                            RegisterOneIndustryBean bean = (RegisterOneIndustryBean) mData;
                            showIndustryWindow(view, bean.getRegisteroneindustrylist());
                        }
                    }
                });
                break;
            case R.id.register_one_register_tv:
                //注册按钮
                tradename = tradename_et.getText().toString();
                if (TextUtils.isEmpty(tradename)) {
                    showToast("请输入商标名");
                    return;
                }
                if (TextUtils.isEmpty(industry)) {
                    showToast("请选择行业");
                    return;
                }
                showFinishPopupWindow(view);
                break;
        }
    }

    //选择行业弹出框
    private void showIndustryWindow(View view, final List<RegisterOneIndustryBean.IndustrylistBean> industryList) {
        industryWindow = new IndustryPopupWindow(getContext(), "标题", industryList, place, new IndustryPopupWindowAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                industry = industryList.get(position).getIndustryname();
                industry_tv.setText(industry);
                place = position;
                industryWindow.dismiss();
            }
        });
        //显示窗口
        industryWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    //选择客服弹出框
    private void showFinishPopupWindow(View view) {
        finishPopupWindow = new RegisterOneFinishPopupWindow(getContext(), new RegisterOneFinishPopupWindow.DialogClickListener() {
            @Override
            public void selectDefault() {
                showToast("默认注册\n" + tradename + "\n" + industry);
            }

            @Override
            public void selectRecommand() {
                showToast("客服推荐\n" + tradename + "\n" + industry);
            }
        });
        finishPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public int getContentLayout() {
        return R.layout.register_one_layout;
    }
}
