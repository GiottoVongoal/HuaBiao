package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CheckTypeResult;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.LineChartBean;
import com.huabiao.aoiin.bean.SearchResultUnRegisterCheckBean;
import com.huabiao.aoiin.bean.SearchResultUnregisteredAndCreatNameBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.SearchResultUnRegisteredAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyGridLayoutManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-14 09:17
 * @description 查询结果未注册页面
 */
public class SearchResultUnRegisteredFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.search_result_unregistered_register_tv)
    TextView register_tv;//注册
    @Bind(R.id.search_result_unregistered_collection_tv)
    TextView collection_tv;//收藏

    @Bind(R.id.search_result_unregistered_relevant_rv)
    RecyclerView relevant_rv;
    @Bind(R.id.search_result_unregistered_other_rv)
    RecyclerView other_rv;
    @Bind(R.id.search_result_unregistered_line_chart)
    DrawLineChartView line_chart;

    private String tradename = "", goodsname = "";

    private SearchResultUnRegisteredAdapter relevantAdapter, otherAdapter;
    private List<SearchResultUnRegisterCheckBean> relevantList, otherList;

    private int Position, Type;
    private CheckTypeResult checkTypeResult;
    private int deep = 2;

    @Override
    public void bindView(Bundle savedInstanceState) {
        ALog.i("tradename = " + tradename + ";\tgoodsname=" + goodsname);
        relevantList = new ArrayList<>();
        otherList = new ArrayList<>();
        register_tv.setOnClickListener(this);
        collection_tv.setOnClickListener(this);
        checkTypeResult = CheckTypeResult.getInstance(deep);
        SearchModel.getSearchUnregisterResult(getContext(), tradename, goodsname, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    SearchResultUnregisteredAndCreatNameBean bean = (SearchResultUnregisteredAndCreatNameBean) mData;
                    showData(bean);
                }
            }
        });
        new CheckTypeListFragment();
    }

    private void showData(SearchResultUnregisteredAndCreatNameBean bean) {
        //相关分类
        relevantList = getList(bean.getClassification());
        relevant_rv.setLayoutManager(new FullyGridLayoutManager(getContext(), 2));
        relevantAdapter = new SearchResultUnRegisteredAdapter(getContext(), relevantList);
        relevant_rv.setAdapter(relevantAdapter);
        relevantAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, final int position) {
                Bundle bundle = new Bundle();
                bundle.putString("tradename", tradename);
                bundle.putString("classificationname", relevantList.get(position).getClassificationid() + " - " + relevantList.get(position).getClassificationname());
                bundle.putInt("type", 1);//测试数据变化使用
                JumpUtils.startFragmentByName(getContext(), CheckTypeListFragment.class, bundle);
                Position = position;
                Type = 1;
            }
        });
        //其他分类
        otherList = getList(bean.getOtherclassification());
        other_rv.setLayoutManager(new FullyGridLayoutManager(getContext(), 2));
        otherAdapter = new SearchResultUnRegisteredAdapter(getContext(), otherList);
        other_rv.setAdapter(otherAdapter);
        otherAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("tradename", tradename);
                bundle.putString("classificationname", otherList.get(position).getClassificationid() + " - " + otherList.get(position).getClassificationname());
                bundle.putInt("type", 2);//测试数据变化使用
                JumpUtils.startFragmentByName(getContext(), CheckTypeListFragment.class, bundle);
                Position = position;
                Type = 2;
            }
        });
        //折线图
        LineChartBean linechart = bean.getLinechart();
        if (linechart != null) {
            line_chart.setLineChartBean(linechart);
        }
    }

    public void getIntentString(String tradename, String goodsname) {
        this.tradename = tradename;
        this.goodsname = goodsname;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_result_unregistered_register_tv:
                //注册
                break;
            case R.id.search_result_unregistered_collection_tv:
                //收藏
                break;
        }
    }

    private List<SearchResultUnRegisterCheckBean> getList(List<ClassificationBean> list) {
        List<SearchResultUnRegisterCheckBean> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SearchResultUnRegisterCheckBean bean = new SearchResultUnRegisterCheckBean();
            bean.setClassificationid(list.get(i).getClassificationid());
            bean.setClassificationname(list.get(i).getClassificationname());
            bean.setCheck(false);
            resultList.add(bean);
        }
        return resultList;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkTypeResult.isChange()) {
            switch (Type) {
                case 1:
                    //选择了相关推荐item,把其他分类的都取消选中
                    for (int i = 0; i < relevantList.size(); i++) {
                        if (i == Position)
                            relevantList.get(i).setCheck(true);
                        else
                            relevantList.get(i).setCheck(false);
                    }
                    relevantAdapter.updateListView(relevantList);
                    for (int i = 0; i < otherList.size(); i++) {
                        otherList.get(i).setCheck(false);
                    }
                    otherAdapter.updateListView(otherList);
                    break;
                case 2:
                    //选择了其他分类item,把相关推荐的都取消选中
                    for (int i = 0; i < otherList.size(); i++) {
                        if (i == Position)
                            otherList.get(i).setCheck(true);
                        else
                            otherList.get(i).setCheck(false);
                    }
                    otherAdapter.updateListView(otherList);
                    for (int i = 0; i < relevantList.size(); i++) {
                        relevantList.get(i).setCheck(false);
                    }
                    relevantAdapter.updateListView(relevantList);
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        checkTypeResult.clearList();
    }

    @Override
    public int getContentLayout() {
        return R.layout.search_result_unregistered_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}