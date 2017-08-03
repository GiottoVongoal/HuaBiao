package com.huabiao.aoiin.ui.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.huabiao.aoiin.R;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-03 18:09
 * @description 自主注册第四张卡片(资料提交)
 */
public class RegisterCardFourView extends RegisterCardBaseView {
    private ImageView register_card_four_trade_logo_iv, register_card_four_proxy_iv, register_card_four_business_licence_iv;

    private View view;
    private Context context;

    public RegisterCardFourView(Context context) {
        super(context);
        view = inflate(context, R.layout.register_card_four_layout, this);
        this.context = context;
    }
}
