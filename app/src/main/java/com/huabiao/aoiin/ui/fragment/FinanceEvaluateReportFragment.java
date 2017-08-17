package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.FinanceEvakuateReportBean;
import com.huabiao.aoiin.model.FinanceModel;
import com.huabiao.aoiin.ui.adapter.ServiceAreaAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.CircleTextView;
import com.huabiao.aoiin.wedgit.ColorArcProgressBar;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.BitmapLoader;

import butterknife.Bind;

import static com.umeng.analytics.a.p;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-08 12:12
 * @description 商标评估报告
 */
public class FinanceEvaluateReportFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.finance_evaluate_report_trade_iv)
    ImageView trade_iv;
    @Bind(R.id.finance_evaluate_report_tradename_tv)
    TextView tradename_tv;
    @Bind(R.id.finance_evaluate_report_tradetype_tv)
    TextView tradetype_tv;
    @Bind(R.id.finance_evaluate_report_tradestatus_tv)
    TextView tradestatus_tv;

    @Bind(R.id.finance_evaluate_report_circle_bar)
    ColorArcProgressBar circle_bar;

    @Bind(R.id.finance_evaluate_report_linechart)
    DrawLineChartView linechart;

    @Bind(R.id.finance_evaluate_report_time_limit_tv)
    TextView time_limit_tv;

    @Bind(R.id.finance_evaluate_report_service_area_rv)
    RecyclerView service_area_rv;
    private ServiceAreaAdapter serviceAdapter;

    @Bind(R.id.finance_evaluate_report_trends_info_tv)
    TextView trends_info_tv;
    @Bind(R.id.finance_evaluate_report_trends_more_tv)
    TextView trends_more_tv;

    @Bind(R.id.finance_evaluate_report_to_next_tv)
    TextView to_next_tv;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("商标评估报告");
        setBackEnable();
        FinanceModel.getFinanceEvaluateReport(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    FinanceEvakuateReportBean bean = (FinanceEvakuateReportBean) mData;
                    showData(bean);
                }
            }
        });

    }

    private void showData(FinanceEvakuateReportBean bean) {
        BitmapLoader.ins().loadImage(bean.getTradeimg(), R.mipmap.ic_launcher, trade_iv);
        tradename_tv.setText(bean.getTradename());
        tradetype_tv.setText(bean.getClassificationid() + "类 - " + bean.getTrademarkclassification());
        switch (bean.getTradestatus()) {
            case 1:
                tradestatus_tv.setText("商标已注册");
                break;
            case 2:
                tradestatus_tv.setText("商标无效");
                break;
            case 3:
                tradestatus_tv.setText("商标注册申请等待受理中");
                break;
            case 4:
                tradestatus_tv.setText("初审公告");
                break;
        }
        circle_bar.setCurrentValues(bean.getTradegrade());
//        circle_bar.setContent(String.valueOf(bean.getTradegrade()));
        linechart.setLineChartBean(bean.getLinechart());
        time_limit_tv.setText("专用期限：" + bean.getTimelimit());
        serviceAdapter = new ServiceAreaAdapter(getContext(), bean.getServicearealist());
        service_area_rv.setLayoutManager(new FullyLinearLayoutManager(getContext()));
        service_area_rv.setAdapter(serviceAdapter);
        trends_info_tv.setText(bean.getTrendsinfo());

        trends_more_tv.setOnClickListener(this);
        to_next_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finance_evaluate_report_trends_more_tv:
                //相关动态更多
                showToast("更多");
                break;
            case R.id.finance_evaluate_report_to_next_tv:
                //资讯专业人员获取精准评估结果
                showToast("资讯专业人员获取精准评估结果");
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.finance_evaluate_report_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
