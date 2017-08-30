package com.huabiao.aoiin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
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
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.List;

import butterknife.Bind;

import static android.R.attr.phoneNumber;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-13 11:36
 * @description 查询结果已注册页面
 */
public class SearchResultActivity extends BaseActivity {
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

    @Bind(R.id.search_result_sv)
    ScrollView search_result_sv;

    private String tradename, goodsname;
    private int REQUEST_CODE = 0;

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getIntent().getExtras();
        tradename = bundle.getString("tradename");
        goodsname = bundle.getString("goodsname");
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        search_result_sv.smoothScrollTo(0, 20);
        setTitle(tradename + "-" + goodsname);
        setBackEnable();
        setRightIvResourse(getResources().getDrawable(R.mipmap.shaixuan));
        setRightIvOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(SearchResultActivity.this, SearchResultScreenActivity.class);
                // 开始一个新的 Activity等候返回结果
                startActivityForResult(newIntent, REQUEST_CODE);
            }
        });

        SearchModel.getSelectClassificationList(SearchResultActivity.this, new InterfaceManager.CallBackCommon() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    String id = extras.getString("id");
                    String name = extras.getString("name");
                    ALog.i("id = " + id + ",name = " + name);
                }
            }
        }
    }

    //下拉筛选菜单相关
    private void initPopMenu(final List<ScreenlistBean> list) {
        //默认获取第一类数据
        initData(list.get(0).getSlist().get(0).getClassificationid());
        screenPopupWindow = new ScreenPopupWindow(SearchResultActivity.this, list, new InterfaceManager.OnScreenItemClickListener() {
            @Override
            public void onItemClickListener(View view, ClassificationBean bean) {
                ALog.i("bean", bean.getClassificationid() + "-" + bean.getClassificationname());
                initData(bean.getClassificationid());
            }
        });
    }

    //获取展示数据
    private void initData(String classificationid) {
        SearchModel.getSearchResult(SearchResultActivity.this, classificationid, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    SearchResultBean searchResult = (SearchResultBean) mData;
                    //展示小分类
//                    List<ClassficationsmalltypeBean> detailedList = searchResult.getClassification().getClassficationsmalltype();
                    top_rv.setLayoutManager(new FullyLinearLayoutManager(SearchResultActivity.this));
                    topAdapter = new SearchResultTopAdapter(SearchResultActivity.this, searchResult.getClassification());
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
