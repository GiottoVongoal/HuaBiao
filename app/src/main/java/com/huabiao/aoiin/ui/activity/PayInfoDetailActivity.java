package com.huabiao.aoiin.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.tools.ActivityCollector;
import com.huabiao.aoiin.tools.AnimatorUtils;
import com.huabiao.aoiin.tools.ViewTools;
import com.huabiao.aoiin.wedgit.CommonSimpleDialog;
import com.huabiao.aoiin.wedgit.XCRoundRectImageView;
import com.sevenheaven.iosswitch.ShSwitchView;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.ClickUtil;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-02 11:55
 * @description 支付信息内容展示页面
 */
public class PayInfoDetailActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.pay_info_detail_sv)
    ScrollView pay_info_detail_sv;

    @Bind(R.id.pay_info_detail_orderid_tv)
    TextView detail_orderid_tv;//订单编号

    //商标信息展示
    @Bind(R.id.pay_info_detail_trade_logo_iv)
    XCRoundRectImageView pay_info_detail_trade_logo_iv;
    @Bind(R.id.pay_info_detail_tradename_top_tv)
    TextView pay_info_detail_tradename_top_tv;
    @Bind(R.id.pay_info_detail_register_type_top_tv)
    TextView pay_info_detail_register_type_top_tv;
    @Bind(R.id.pay_info_detail_trade_ll)
    LinearLayout detail_trade_ll;
    @Bind(R.id.pay_info_detail_tradetype_tv)
    TextView detail_tradetype_tv;//商标类型
    @Bind(R.id.pay_info_detail_tradename_tv)
    TextView detail_tradename_tv;//商标名称
    @Bind(R.id.pay_info_detail_register_type_tv)
    TextView detail_register_type_tv;//注册类别
    @Bind(R.id.pay_info_detail_order_date_tv)
    TextView detail_order_date_tv;//订单日期

    //订单支付相关显示
    @Bind(R.id.pay_info_detail_pay_ll)
    LinearLayout detail_pay_ll;
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
    @Bind(R.id.pay_info_detail_invoice_header_delete_iv)
    ImageView header_delete_iv;
    @Bind(R.id.pay_info_detail_invoice_dutynum_et)
    TextInputLayout detail_invoice_dutynum_et;//纳税人识别号
    @Bind(R.id.pay_info_detail_invoice_dutynum_delete_iv)
    ImageView dutynum_delete_iv;
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
    private int payWay = 1;//微信支付

    //最下边显示
    @Bind(R.id.pay_info_detail_pay_bottom_ll)
    LinearLayout detail_pay_bottom_ll;
    @Bind(R.id.pay_info_detail_pay_total_tv)
    TextView detail_pay_total_tv;//费用总计：5000.00元
    @Bind(R.id.pay_info_detail_pay_tv)
    TextView detail_pay_tv;//立即支付

    private RegisterCommitBean commitBean;

    private int orderStatus;

    private AnimatorUtils animatorUtils;

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        orderStatus = getIntent().getExtras().getInt("status", 1);
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        pay_info_detail_sv.smoothScrollTo(0, 20);
        setTitle("支付信息");
        setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        animatorUtils = new AnimatorUtils();

        switch (orderStatus) {
            case 1:
                //有支付相关内容展示
                detail_pay_ll.setVisibility(View.VISIBLE);
                detail_pay_bottom_ll.setVisibility(View.VISIBLE);
                initPayIndo();
                break;
            default:
                //无支付相关内容展示
                detail_pay_ll.setVisibility(View.GONE);
                detail_pay_bottom_ll.setVisibility(View.GONE);
                break;
        }
        commitBean = RegisterCommitBean.getInstance();
        ALog.i("commitBean -- >" + commitBean.toString());
        detail_orderid_tv.setText("订单编号:0123456789");

        setOnClick();
    }

    private void initPayIndo() {
        detail_switch_view.setOn(true);
        detail_switch_view.setOnSwitchStateChangeListener(new ShSwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                if (isOn) {
                    // 打开布局
                    animatorUtils.openHiddenView(detail_invoice_rl);
                } else {
                    // 关闭布局
                    animatorUtils.closeHiddenView(detail_invoice_rl);
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
        ViewTools.setEdittext(detail_invoice_header_et.getEditText(), header_delete_iv
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        detail_invoice_header_et.getEditText().setText("");
                    }
                });
        ViewTools.setEdittext(detail_invoice_dutynum_et.getEditText(), dutynum_delete_iv
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        detail_invoice_dutynum_et.getEditText().setText("");
                    }
                });
    }

    private void setOnClick() {
        detail_trade_ll.setOnClickListener(this);
        detail_invoice_dutynum_tip_iv.setOnClickListener(this);
        detail_pay_tv.setOnClickListener(this);
    }

    //弹框提示是否关闭订单
    private void showDialog() {
        if (orderStatus == 1) {
            View.OnClickListener left = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            };
            View.OnClickListener right = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickUtil.onBackClick();
                    PayInfoDetailActivity.this.finish();
//                    overridePendingTransition(R.anim.default_anim_in, R.anim.default_anim_out);
                }
            };
            CommonSimpleDialog dialog = new CommonSimpleDialog(this);
            dialog.setContent("你要离开订单啦!!!")
                    .setButton(true, "我再想想", "去意已决", left, right)
                    .build()
                    .show();
        } else {
            PayInfoDetailActivity.this.finish();
//            overridePendingTransition(R.anim.default_anim_in, R.anim.default_anim_out);
        }
    }

    private Visibility buildReturnTransition() {
        Visibility enterTransition = new Slide();
        enterTransition.setDuration(300);
        return enterTransition;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_info_detail_trade_ll:
                //商标信息
                break;
            case R.id.pay_info_detail_invoice_dutynum_tip_iv:
                showToast("税号是什么,自己百度");
                break;
            case R.id.pay_info_detail_pay_tv:
                //立即支付
                showToast(payWay == 1 ? "微信支付" : "支付宝支付");
                break;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            showDialog();
            return true;
        }
        return super.dispatchKeyEvent(event);
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
