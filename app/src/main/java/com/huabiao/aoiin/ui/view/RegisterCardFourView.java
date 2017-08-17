package com.huabiao.aoiin.ui.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ToNextPageEvent;
import com.squareup.otto.Produce;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.view
 * @date 2017-08-04 10:23
 * @description 自主注册第四张卡片(服务方式)
 */
public class RegisterCardFourView extends RegisterCardBaseView implements View.OnClickListener {
    private RadioGroup service_mode_rg;
    private RadioButton general_rb, urgent_rb, guarantee_rb;//普通注册;加急注册;退费担保
    private TextView total_cost_tv;//总费用
    private TextView finish_tv;//确定

    private RegisterCommitBean commitBean;

    private View view;
    private Context context;

    public RegisterCardFourView(Context context) {
        super(context);
        view = inflate(context, R.layout.register_card_four_layout, this);
        this.context = context;
        service_mode_rg = (RadioGroup) view.findViewById(R.id.register_card_five_service_mode_rg);
        general_rb = (RadioButton) view.findViewById(R.id.register_card_five_general_rb);
        urgent_rb = (RadioButton) view.findViewById(R.id.register_card_five_urgent_rb);
        guarantee_rb = (RadioButton) view.findViewById(R.id.register_card_five_guarantee_rb);
        total_cost_tv = (TextView) view.findViewById(R.id.register_card_five_total_cost_tv);
        finish_tv = (TextView) view.findViewById(R.id.register_card_five_finish_tv);

        commitBean = RegisterCommitBean.getInstance();

        service_mode_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.register_card_five_general_rb:
                        //普通注册
                        commitBean.setServiceMode(1);
                        break;
                    case R.id.register_card_five_urgent_rb:
                        //加急注册
                        commitBean.setServiceMode(2);
                        break;
                    case R.id.register_card_five_guarantee_rb:
                        commitBean.setServiceMode(3);
                        //退费担保
                        break;
                }
            }
        });

        finish_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_card_five_finish_tv:
                //确定
//                ToastUtils.getInstance().showToast("请确认都已填写完整");
                AppBus.getInstance().post(toNextPageEvent());
                break;
        }
    }

    /**
     * 产生事件
     *
     * @return
     */
    @Produce
    public ToNextPageEvent toNextPageEvent() {
        return new ToNextPageEvent();
    }
}
