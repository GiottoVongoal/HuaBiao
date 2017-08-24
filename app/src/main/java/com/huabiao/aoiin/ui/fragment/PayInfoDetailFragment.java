package com.huabiao.aoiin.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.sevenheaven.iosswitch.ShSwitchView;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-02 11:55
 * @description 支付信息内容展示页面
 */
public class PayInfoDetailFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.pay_info_detail_orderid_tv)
    TextView detail_orderid_tv;//订单编号

    //商标信息展示
    @Bind(R.id.pay_info_detail_trade_logo_iv)
    ImageView pay_info_detail_trade_logo_iv;
    @Bind(R.id.pay_info_detail_tradename_top_tv)
    TextView pay_info_detail_tradename_top_tv;
    @Bind(R.id.pay_info_detail_register_type_top_tv)
    TextView pay_info_detail_register_type_top_tv;
    @Bind(R.id.pay_info_detail_trade_rl)
    RelativeLayout detail_traderl;
    @Bind(R.id.pay_info_detail_tradetype_tv)
    TextView detail_tradetype_tv;//商标类型
    @Bind(R.id.pay_info_detail_tradename_tv)
    TextView detail_tradename_tv;//商标名称
    @Bind(R.id.pay_info_detail_register_type_tv)
    TextView detail_register_type_tv;//注册类别
    @Bind(R.id.pay_info_detail_order_date_tv)
    TextView detail_order_date_tv;//订单日期

    //发票相关
    @Bind(R.id.pay_info_detail_switch_view)
    ShSwitchView detail_switch_view;//发票开关
    @Bind(R.id.pay_info_detail_invoice_rl)
    RelativeLayout detail_invoice_rl;//发票布局
    //发票抬头
    @Bind(R.id.pay_info_detail_rg)
    RadioGroup detail_rg;
    @Bind(R.id.pay_info_detail_personal_rb)
    RadioButton personal_rb;//个人
    @Bind(R.id.pay_info_detail_company_rb)
    RadioButton company_rb;//公司
    @Bind(R.id.pay_info_detail_invoice_header_et)
    TextInputLayout detail_invoice_header_et;//抬头内容
    @Bind(R.id.pay_info_detail_invoice_dutynum_et)
    TextInputLayout detail_invoice_dutynum_et;//纳税人识别号
    @Bind(R.id.pay_info_detail_invoice_dutynum_tip_iv)
    ImageView detail_invoice_dutynum_tip_iv;//纳税人识别号Tip
    private int invoiceHeaderType = 2;

    //支付方式
    @Bind(R.id.pay_info_detail_pay_way_rg)
    RadioGroup detail_pay_way_rg;
    @Bind(R.id.pay_info_detail_pay_wechat_rb)
    RadioButton pay_wechat_rb;//微信支付
    @Bind(R.id.pay_info_detail_pay_alipay_rb)
    RadioButton pay_alipay_rb;//支付宝支付
    private int payWay = 1;

    //最下边显示
    @Bind(R.id.pay_info_detail_pay_total_tv)
    TextView detail_pay_total_tv;//费用总计：5000.00元
    @Bind(R.id.pay_info_detail_pay_tv)
    TextView detail_pay_tv;//立即支付

    private RegisterCommitBean commitBean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("支付信息");
        setBackEnable();
        commitBean = RegisterCommitBean.getInstance();
        ALog.i("commitBean -- >" + commitBean.toString());
        detail_orderid_tv.setText("订单编号:0123456789");
        detail_switch_view.setOn(true);
        detail_switch_view.setOnSwitchStateChangeListener(new ShSwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                if (isOn) {
                    // 打开布局
                    openHiddenView(detail_invoice_rl);
                } else {
                    // 关闭布局
                    closeHiddenView(detail_invoice_rl);
                }
            }
        });

        //发票抬头选择
        detail_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.pay_info_detail_personal_rb:
                        //个人
                        detail_invoice_dutynum_et.setVisibility(View.INVISIBLE);
                        detail_invoice_dutynum_tip_iv.setVisibility(View.GONE);
                        invoiceHeaderType = 1;
                        break;
                    case R.id.pay_info_detail_company_rb:
                        //公司
                        detail_invoice_dutynum_et.setVisibility(View.VISIBLE);
                        detail_invoice_dutynum_tip_iv.setVisibility(View.VISIBLE);
                        invoiceHeaderType = 2;
                        break;
                }
            }
        });
        //支付方式
        detail_pay_way_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.pay_info_detail_pay_wechat_rb:
                        //微信支付
                        payWay = 1;
                        break;
                    case R.id.pay_info_detail_pay_alipay_rb:
                        //支付宝支付
                        payWay = 2;
                        break;
                }
            }
        });
        setOnClick();
    }

    private void setOnClick() {
        detail_traderl.setOnClickListener(this);
        detail_invoice_dutynum_tip_iv.setOnClickListener(this);
        detail_pay_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_info_detail_trade_rl:
                //商标信息
                break;
            case R.id.pay_info_detail_invoice_dutynum_tip_iv:
                showToast("税号是什么,自己百度");
                break;
            case R.id.pay_info_detail_pay_tv:
                //立即支付
                break;
        }
    }

    private void openHiddenView(final View view) {
        view.setVisibility(View.VISIBLE);
        view.measure(0, 0);////measure方法的参数值都设为0
        ValueAnimator valueAnimator = creatDropAnimator(view, 0, view.getMeasuredHeight());//getMeasuredHeight获取组件高度
        valueAnimator.start();
    }

    private void closeHiddenView(final View view) {
        ValueAnimator valueAnimator = creatDropAnimator(view, view.getHeight(), 0);
        //动画结束之后执行隐藏
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        valueAnimator.start();
    }

    private ValueAnimator creatDropAnimator(final View view, int start, int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int Value = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = Value;
                view.setLayoutParams(layoutParams);
            }
        });
        return valueAnimator;
    }

    @Override
    public int getContentLayout() {
        return R.layout.pay_info_detail_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
