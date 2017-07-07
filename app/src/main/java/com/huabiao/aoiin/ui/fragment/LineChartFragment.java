package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.LineChartBean;
import com.huabiao.aoiin.bean.SearchResultRegisteredBean;
import com.huabiao.aoiin.bean.SearchResultUnregisteredBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-07 10:31
 * @description 测试画折线图
 */
public class LineChartFragment extends BaseFragment {

    @Bind(R.id.line_chart)
    DrawLineChartView line_chart;
    @Bind(R.id.line_chart2)
    DrawLineChartView line_chart2;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        SearchModel.getSearchResult(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    SearchResultRegisteredBean bean = (SearchResultRegisteredBean) mData;
                    ALog.i("SearchResultRegisteredBean", bean);
                    LineChartBean linechart = bean.getLinechart();
                    if (linechart != null) {
                        line_chart.setLineChartBean(linechart);
                    }
                }
            }
        });

        SearchModel.getSearchUnregisterResult(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    SearchResultUnregisteredBean bean2 = (SearchResultUnregisteredBean) mData;
                    LineChartBean lineChart2 = bean2.getLinechart();
                    if (lineChart2 != null) {
                        line_chart2.setLineChartBean(lineChart2);
                    }
                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.line_chart_fragment_layout;
    }
}
