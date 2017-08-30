package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.LineChartBean;
import com.huabiao.aoiin.bean.ScreenBean;
import com.huabiao.aoiin.bean.ScreenBean.ScreenlistBean;
import com.huabiao.aoiin.bean.SearchResultBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.SearchResultTopAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.huabiao.aoiin.wedgit.MaxRecyclerView;
import com.huabiao.aoiin.wedgit.ScreenPopupWindow;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-13 11:36
 * @description 查询结果已注册页面
 */
public class SearchResultFragment extends BaseFragment {
    //筛选
    private ScreenPopupWindow screenPopupWindow;
    @Bind(R.id.search_result_num_tv)
    TextView search_result_num_tv;

    //展示数据
    @Bind(R.id.search_result_top_rv)
    MaxRecyclerView top_rv;
    private SearchResultTopAdapter topAdapter;
    @Bind(R.id.search_result_line_chart)
    DrawLineChartView line_chart;

    private String tradename, goodsname;

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getActivity().getIntent().getExtras();
        tradename = bundle.getString("tradename");
        goodsname = bundle.getString("goodsname");
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle(tradename + "-" + goodsname);
        setBackEnable();
        setRightIvResourse(getContext().getResources().getDrawable(R.mipmap.shaixuan));
        setRightIvOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.startFragmentByName(getContext(), SearchResultScreenFragment.class);
            }
        });

        SearchModel.getSelectClassificationList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    ScreenBean bean = (ScreenBean) mData;
                    List<ScreenlistBean> list = bean.getScreenlist();
                    initPopMenu(list);
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
                    int num = searchResult.getClassification().getClassficationsmalltype().size();
                    search_result_num_tv.setText("为您找到" + num + "个未注册相关结果");
                    top_rv.setAdapter(topAdapter);
                    top_rv.setNestedScrollingEnabled(false);
                    //展示折线图
                    LineChartBean linechart = searchResult.getLinechart();
                    if (linechart != null) {
                        line_chart.setLineChartBean(linechart);
                    }
                }
            }
        });
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
