package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.FinanceEvakuateReportBean;
import com.huabiao.aoiin.bean.FinanceEvakuateReportBean.SimilarproductBean;
import com.huabiao.aoiin.model.FinanceModel;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.ColorArcProgressBar;
import com.huabiao.aoiin.wedgit.XCRoundRectImageView;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.BitmapLoader;

import butterknife.Bind;


/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-08 12:12
 * @description 商标评估报告
 */
public class FinanceEvaluateReportFragment extends BaseFragment {
    @Bind(R.id.finance_evaluate_report_trade_iv)
    XCRoundRectImageView trade_iv;
    @Bind(R.id.finance_evaluate_report_tradename_tv)
    TextView tradename_tv;
    @Bind(R.id.finance_evaluate_report_tradetype_tv)
    TextView tradetype_tv;
    @Bind(R.id.finance_evaluate_report_tradestatus_tv)
    TextView tradestatus_tv;
    @Bind(R.id.finance_evaluate_report_regnumber_tv)
    TextView regnumber_tv;//注册号

    @Bind(R.id.finance_evaluate_report_circle_bar)
    ColorArcProgressBar circle_bar;

    @Bind(R.id.finance_evaluate_report_monthsales_tv)
    TextView monthsales_tv;//月销量
    @Bind(R.id.finance_evaluate_report_hotrank_tv)
    TextView hotrank_tv;//月品牌热门排行榜

    //相似产品数据
    @Bind(R.id.finance_evaluate_report_similarproduct_tradename_tv)
    TextView similarproduct_tradename_tv;
    @Bind(R.id.finance_evaluate_report_similarproduct_tradetype_tv)
    TextView similarproduct_tradetype_tv;
    @Bind(R.id.finance_evaluate_report_similarproduct_circle_bar)
    ColorArcProgressBar similarproduct_circle_bar;
    @Bind(R.id.finance_evaluate_report_similarproduct_monthsales_tv)
    TextView similarproduct_monthsales_tv;//月销量
    @Bind(R.id.finance_evaluate_report_similarproduct_hotrank_tv)
    TextView similarproduct_hotrank_tv;//月品牌热门排行榜

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
        BitmapLoader.ins().loadImage(bean.getTradeimg(), R.mipmap.logobg, trade_iv);
        tradename_tv.setText(bean.getTradename());
        tradetype_tv.setText(bean.getClassificationid() + " - " + bean.getTrademarkclassification());
        switch (bean.getTradestatus()) {
            case 1:
                tradestatus_tv.setText("已注册");
                break;
            case 2:
                tradestatus_tv.setText("商标无效");
                break;
            case 3:
                tradestatus_tv.setText("受理中");
                break;
            case 4:
                tradestatus_tv.setText("初审公告");
                break;
        }
        regnumber_tv.setText("注册号 : " + bean.getRegnumber());
        circle_bar.setCurrentValues(bean.getTradegrade());
        monthsales_tv.setText(bean.getMonthsales());
        hotrank_tv.setText(bean.getHotrank());

        //相似产品数据
        SimilarproductBean similarproductBean = bean.getSimilarproduct();
        similarproduct_tradename_tv.setText(similarproductBean.getTradename());
        similarproduct_tradetype_tv.setText(similarproductBean.getClassificationid() + " - " + similarproductBean.getTrademarkclassification());
        similarproduct_circle_bar.setCurrentValues(similarproductBean.getTradegrade());
        similarproduct_monthsales_tv.setText(similarproductBean.getMonthsales());
        similarproduct_hotrank_tv.setText(similarproductBean.getHotrank());
//        circle_bar.setContent(String.valueOf(bean.getTradegrade()));
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
