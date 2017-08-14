package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CheckTypeResult;
import com.huabiao.aoiin.bean.LineChartBean;
import com.huabiao.aoiin.bean.SearchResultUnRegisterCheckBean;
import com.huabiao.aoiin.bean.SearchResultUnregisteredAndCreatNameBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.SearchResultUnRegisteredAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.RegisterOneFinishPopupWindow;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

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

    private int Position;//点击的Position
    private int Type;//1相关分类;2其他分类
    private CheckTypeResult checkTypeResult;
    private String selectClassify;
    private int deep = 2;

    //选择客服弹出框
    private RegisterOneFinishPopupWindow finishPopupWindow;

    @Override
    public void bindView(Bundle savedInstanceState) {
        ALog.i("tradename = " + tradename + ";\tgoodsname=" + goodsname);
        relevantList = new ArrayList<>();
        otherList = new ArrayList<>();
        register_tv.setOnClickListener(this);
        collection_tv.setOnClickListener(this);
        checkTypeResult = CheckTypeResult.getInstance(deep);
        checkTypeResult.clearList();//先清除旧数据
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
                if (checkTypeResult.getSelectList().size() > 0) {
                    showFinishPopupWindow(view);
                } else {
                    showToast("请选择分类");
                }
                break;
            case R.id.search_result_unregistered_collection_tv:
                //收藏
                break;
        }
    }

    //选择客服弹出框
    private void showFinishPopupWindow(View view) {
        finishPopupWindow = new RegisterOneFinishPopupWindow(getContext(), new RegisterOneFinishPopupWindow.DialogClickListener() {
            @Override
            public void selectDefault() {
                //默认注册
                Bundle bundle = new Bundle();
                bundle.putString("tradename", tradename);
                bundle.putString("industry", "");//无行业
                bundle.putString("selectClassify", selectClassify);//选择的分类大类名称
                bundle.putInt("pageIndex", 1);
                JumpUtils.startFragmentByName(getContext(), RegisterFragment.class, bundle);
                finishPopupWindow.dismiss();
            }

            @Override
            public void selectRecommand() {
                //客服推荐
                Bundle bundle = new Bundle();
                bundle.putString("tradename", tradename);
                bundle.putString("industry", "");//无行业
                bundle.putString("selectClassify", selectClassify);//选择的分类大类名称
                bundle.putInt("pageIndex", 1);
                JumpUtils.startFragmentByName(getContext(), CustomerServiceListFragment.class, bundle);
                finishPopupWindow.dismiss();
            }
        });
        finishPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
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
