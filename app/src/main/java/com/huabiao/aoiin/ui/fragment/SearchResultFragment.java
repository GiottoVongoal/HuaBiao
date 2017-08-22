package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.LineChartBean;
import com.huabiao.aoiin.bean.ScreenBean;
import com.huabiao.aoiin.bean.ScreenBean.ScreenlistBean;
import com.huabiao.aoiin.bean.SearchResultBean;
import com.huabiao.aoiin.bean.SearchResultBean.RecommendBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.SearchResultBottomAdapter;
import com.huabiao.aoiin.ui.adapter.SearchResultTopAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyGridLayoutManager;
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.huabiao.aoiin.wedgit.MaxRecyclerView;
import com.huabiao.aoiin.wedgit.ScreenPopupWindow;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-13 11:36
 * @description 查询结果已注册页面
 */
public class SearchResultFragment extends BaseFragment implements View.OnClickListener {
    //筛选
    @Bind(R.id.search_result_screen_tv)
    TextView screen_tv;
    private ScreenPopupWindow screenPopupWindow;

    //展示数据
    @Bind(R.id.search_result_top_rv)
    MaxRecyclerView top_rv;
    private SearchResultTopAdapter topAdapter;
    @Bind(R.id.search_result_bottom_rv)
    MaxRecyclerView bottom_rv;
    private SearchResultBottomAdapter bottomAdapter;
    @Bind(R.id.search_result_line_chart)
    DrawLineChartView line_chart;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("查询结果");
        setBackEnable();

        SearchModel.getSelectClassificationList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    ScreenBean bean = (ScreenBean) mData;
                    List<ScreenlistBean> list = bean.getScreenlist();
                    initPopMenu(list);
                    screen_tv.setOnClickListener(SearchResultFragment.this);
                }
            }
        });
    }

    //下拉筛选菜单相关
    private void initPopMenu(final List<ScreenlistBean> list) {
        //默认获取第一类数据
        initData(list.get(0).getSlist().get(0).getClassificationid());
        screenPopupWindow = new ScreenPopupWindow(getActivity(), list, new InterfaceManager.OnScreenItemClickListener() {
            @Override
            public void onItemClickListener(View view, ClassificationBean bean) {
                ALog.i("bean", bean.getClassificationid() + "-" + bean.getClassificationname());
                initData(bean.getClassificationid());
            }
        });
    }

    //获取展示数据
    private void initData(String classificationid) {
        SearchModel.getSearchResult(getContext(), classificationid, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    SearchResultBean searchResult = (SearchResultBean) mData;
                    //展示小分类
//                    List<ClassficationsmalltypeBean> detailedList = searchResult.getClassification().getClassficationsmalltype();
                    top_rv.setLayoutManager(new FullyLinearLayoutManager(getContext()));
                    topAdapter = new SearchResultTopAdapter(getContext(), searchResult.getClassification());
                    top_rv.setAdapter(topAdapter);
                    top_rv.setNestedScrollingEnabled(false);
                    //展示折线图
                    LineChartBean linechart = searchResult.getLinechart();
                    if (linechart != null) {
                        line_chart.setLineChartBean(linechart);
                    }
                    //展示推荐
                    List<RecommendBean> recommendList = searchResult.getRecommend();
                    bottom_rv.setLayoutManager(new FullyGridLayoutManager(getContext(), 2));
                    bottomAdapter = new SearchResultBottomAdapter(getContext(), recommendList);
                    bottom_rv.setAdapter(bottomAdapter);
                    bottom_rv.setNestedScrollingEnabled(false);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_result_screen_tv:
                screenPopupWindow.showPopupWindow(view);
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.search_result_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
