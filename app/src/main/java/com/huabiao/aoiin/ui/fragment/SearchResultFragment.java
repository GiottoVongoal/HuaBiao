package com.huabiao.aoiin.ui.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.LineChartBean;
import com.huabiao.aoiin.bean.SearchResultBean;
import com.huabiao.aoiin.bean.SearchResultBean.RecommendBean;
import com.huabiao.aoiin.bean.SearchResultBean.Classification.ClassficationsmalltypeBean;
import com.huabiao.aoiin.bean.SelectClassificationListBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.SearchResultBottomAdapter;
import com.huabiao.aoiin.ui.adapter.UpMenuAdapter;
import com.huabiao.aoiin.ui.adapter.SearchResultTopAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyGridLayoutManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-13 11:36
 * @description 查询结果已注册页面
 */
public class SearchResultFragment extends BaseFragment implements View.OnClickListener {
    //下拉筛选菜单
    @Bind(R.id.search_result_menu_ll)
    LinearLayout menu_ll;
    @Bind(R.id.search_result_menu_tv)
    TextView menu_tv;
    private PopupWindow popMenu;
    private RecyclerView popRecyclerView;
    private UpMenuAdapter menuAdapter;

    //展示数据
    @Bind(R.id.search_result_top_rv)
    RecyclerView top_rv;
    private SearchResultTopAdapter topAdapter;
    @Bind(R.id.search_result_bottom_rv)
    RecyclerView bottom_rv;
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
                    SelectClassificationListBean bean = (SelectClassificationListBean) mData;
                    List<ClassificationBean> list = bean.getClassification();
                    initPopMenu(list);
                    menu_ll.setOnClickListener(SearchResultFragment.this);
                }
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
                    List<ClassficationsmalltypeBean> detailedList = searchResult.getClassification().getClassficationsmalltype();
                    top_rv.setLayoutManager(new FullyGridLayoutManager(getContext(), 2));
                    topAdapter = new SearchResultTopAdapter(getContext(), detailedList);
                    top_rv.setAdapter(topAdapter);
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
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_result_menu_ll:
                menu_tv.setTextColor(getResources().getColor(R.color.black3));
                popRecyclerView.setAdapter(menuAdapter);
                popMenu.showAsDropDown(menu_ll, 0, 2);
                break;
        }
    }

    //下拉筛选菜单相关
    private void initPopMenu(final List<ClassificationBean> list) {
        View contentView = View.inflate(getContext(), R.layout.popwin_supplier_list, null);
        popMenu = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                menu_tv.setTextColor(getResources().getColor(R.color.black3));
            }
        });
        menu_tv.setText(list.get(0).getClassificationname());//默认显示第一个类别
        initData(list.get(0).getClassificationid());//根据类别获取已注册数据
        popRecyclerView = (RecyclerView) contentView
                .findViewById(R.id.popwin_supplier_list_rv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom)
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        popMenu.dismiss();
                    }
                });
        popRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<String> l = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            l.add(list.get(i).getClassificationname());
        }
        menuAdapter = new UpMenuAdapter(getContext(), l);

        menuAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                popMenu.dismiss();
                menu_tv.setText(list.get(position).getClassificationname());
                showToast(list.get(position).getClassificationname());
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.search_result_registered_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
