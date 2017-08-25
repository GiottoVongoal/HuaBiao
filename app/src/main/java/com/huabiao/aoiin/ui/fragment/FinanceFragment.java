package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.KeyboardUtils;
import com.ywy.mylibs.utils.StringUtil;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-08 11:24
 * @description 金融页面
 */
public class FinanceFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.finance_tradename_tl)
    TextInputLayout tradename_tl;//商标名
    @Bind(R.id.finance_tradeid_tl)
    TextInputLayout tradeid_tl;//商标编号

    @Bind(R.id.finance_evaluate_tv)
    TextView evaluate_circle_tv;//评估

    @Bind(R.id.finance_trade_pledge_ll)
    LinearLayout trade_pledge_ll;//商标质押
    @Bind(R.id.finance_trade_sale_ll)
    LinearLayout trade_sale_ll;//商标拍卖

    @Override
    public void bindView(Bundle savedInstanceState) {
        tradename_tl.getEditText().setText("商标名");
        tradeid_tl.getEditText().setText("商标编号");

        evaluate_circle_tv.setOnClickListener(this);
        trade_pledge_ll.setOnClickListener(this);
        trade_sale_ll.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finance_evaluate_tv:
                //评估
                if (StringUtil.isEmpty(tradename_tl.getEditText().getText().toString().toString())) {
                    showToast("请输入商标名");
                    return;
                }
                if (StringUtil.isEmpty(tradeid_tl.getEditText().getText().toString().toString())) {
                    showToast("请输入商标编号");
                    return;
                }
                KeyboardUtils.hideSoftInput(getActivity());
                JumpUtils.startFragmentByName(getContext(), FinanceEvaluateReportFragment.class);
                break;
            case R.id.finance_trade_pledge_ll:
                //商标质押
                JumpUtils.startFragmentByName(getContext(),PledgeFragment.class);
                break;
            case R.id.finance_trade_sale_ll:
                //商标拍卖
             JumpUtils.startFragmentByName(getContext(),AuctionFragment.class);
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.finance_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
