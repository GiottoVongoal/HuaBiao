package com.huabiao.aoiin.ui.fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.huabiao.aoiin.R;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-02 21:51
 * @description 自主注册第二张卡片(联系方式)
 */
public class RegisterCardTwoView extends CardView {
    private View view;
    private Context context;

    public RegisterCardTwoView(Context context) {
        super(context);
        view = inflate(context, R.layout.register_card_two_layout, this);
        this.context = context;
    }
}
