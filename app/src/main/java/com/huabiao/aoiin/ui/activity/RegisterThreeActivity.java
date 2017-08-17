package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.tools.ActivityCollector;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.activity
 * @date 2017-08-15 15:55
 * @description 自主注册第三步(服务方式)
 */
public class RegisterThreeActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.register_card_five_service_mode_rg)
    RadioGroup service_mode_rg;
    @Bind(R.id.register_card_five_general_rb)
    RadioButton general_rb;//普通注册
    @Bind(R.id.register_card_five_urgent_rb)
    RadioButton urgent_rb;//加急注册
    @Bind(R.id.register_card_five_guarantee_rb)
    RadioButton guarantee_rb;//退费担保

    @Bind(R.id.register_card_five_total_cost_tv)
    TextView total_cost_tv;//总费用
    @Bind(R.id.register_card_five_finish_tv)
    TextView finish_tv;//确定

    private int totalCost;

    private RegisterCommitBean commitBean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("注册信息");
        setBackEnable();
        setRightIvResourse(getResources().getDrawable(R.mipmap.kefu_icon));
        setRightIvOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("客服");
            }
        });
        ActivityCollector.addActivity(this);
        commitBean = RegisterCommitBean.getInstance();

        service_mode_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == general_rb.getId()) {
                    //普通注册
                    commitBean.setServiceMode(0);
                    totalCost = 1000;
                } else if (i == urgent_rb.getId()) {
                    //加急注册
                    commitBean.setServiceMode(1);
                    totalCost = 2000;
                } else if (i == guarantee_rb.getId()) {
                    //退费担保
                    commitBean.setServiceMode(2);
                    totalCost = 3000;
                }
                total_cost_tv.setText("总费用: " + totalCost + "元");
            }
        });
        if (commitBean.getServiceMode() != -1) {
            switch (commitBean.getServiceMode()) {
                case 0:
                    service_mode_rg.check(general_rb.getId());
                    break;
                case 1:
                    service_mode_rg.check(urgent_rb.getId());
                    break;
                case 2:
                    service_mode_rg.check(guarantee_rb.getId());
                    break;
            }
        }
        finish_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_card_five_finish_tv:
                //确定
//                ToastUtils.getInstance().showToast("请确认都已填写完整");
//                AppBus.getInstance().post(toNextPageEvent());
                if (!commitBean.isNull()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("index", 2);
                    JumpUtils.startActivity(this, RegisterDataPreviewActivity.class, bundle);
                }
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.register_three_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}

