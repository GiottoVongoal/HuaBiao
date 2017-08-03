package com.huabiao.aoiin.ui.fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;

import com.huabiao.aoiin.R;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-03 13:36
 * @description
 */
public class RegisterCardThreeView extends CardView implements View.OnClickListener {

    private View view;
    private Context context;

    public RegisterCardThreeView(Context context) {
        super(context);
        view = inflate(context, R.layout.register_card_three_layout, this);
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
