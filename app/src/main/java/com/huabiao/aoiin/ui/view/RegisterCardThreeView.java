package com.huabiao.aoiin.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.ui.fragment.RegisterPersonTypeFragment;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ChangeRegisterIndexEvent;
import com.squareup.otto.Produce;
import com.ywy.mylibs.utils.JumpUtils;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-03 13:36
 * @description 自主注册第三张卡片(申请人类型)
 */
public class RegisterCardThreeView extends RegisterCardBaseView implements View.OnClickListener {
    private TextView legal_tv;//法人
    private TextView individual_tv;//个体工商户
    private TextView average_tv;//自然人

    private TextView[] tvs = new TextView[3];

    private TextView next_tv;//下一步

    private RegisterCommitBean commitBean;

    private View view;
    private Context context;

    public RegisterCardThreeView(Context context) {
        super(context);
        view = inflate(context, R.layout.register_card_three_layout, this);
        this.context = context;
        legal_tv = (TextView) view.findViewById(R.id.register_card_three_legal_tv);
        individual_tv = (TextView) view.findViewById(R.id.register_card_three_individual_tv);
        average_tv = (TextView) view.findViewById(R.id.register_card_three_average_tv);
        next_tv = (TextView) view.findViewById(R.id.register_card_three_next_tv);

        tvs[0] = legal_tv;
        tvs[1] = individual_tv;
        tvs[2] = average_tv;

        commitBean = RegisterCommitBean.getInstance();

        legal_tv.setOnClickListener(this);
        individual_tv.setOnClickListener(this);
        average_tv.setOnClickListener(this);
        next_tv.setOnClickListener(this);
    }

    public void setPersonTypeSelect(int position) {
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setBackgroundResource(R.drawable.g_white_gray);
            if (i == position) {
                tvs[i].setBackground(getResources().getDrawable(R.drawable.g_white_white));
            }
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.register_card_three_legal_tv:
                //法人
                bundle.putInt("type", 1);
                bundle.putString("title", "法人或其他组织");
                JumpUtils.startFragmentByName(context, RegisterPersonTypeFragment.class, bundle);
                break;
            case R.id.register_card_three_individual_tv:
                //个体工商户
                bundle.putInt("type", 2);
                bundle.putString("title", "个体工商户");
                JumpUtils.startFragmentByName(context, RegisterPersonTypeFragment.class, bundle);
                break;
            case R.id.register_card_three_average_tv:
                //自然人
                bundle.putInt("type", 3);
                bundle.putString("title", "自然人");
                JumpUtils.startFragmentByName(context, RegisterPersonTypeFragment.class, bundle);
                break;
            case R.id.register_card_three_next_tv:
                //下一步
                AppBus.getInstance().post(produceChangeIndex());
                break;
        }
    }

    /**
     * 产生事件
     *
     * @return
     */
    @Produce
    public ChangeRegisterIndexEvent produceChangeIndex() {
        return new ChangeRegisterIndexEvent(3);
    }
}
