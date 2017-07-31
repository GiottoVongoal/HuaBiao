package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MallBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.MallAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;


public class Mall extends BaseFragment {
    private boolean isLast;     //是否滑动到底部的标志位
    private MallAdapter mallAapter;
    private int page = 1;

    @Bind(R.id.Mall_listView)
    ListView mall_listview;


    @Override
    public void bindView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getList(1);
        //设置列表滚动监听
        mall_listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isLast = (totalItemCount == firstVisibleItem + visibleItemCount);     //判断是否滑倒底部

            }


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //当滑动到底部 且 手指离开屏幕时 确定为需要加载分页
                if (isLast && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    getList(page++);
                }
            }
        });
        mall_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                mall_listview.setAdapter(mallAapter);

            }
        });
    }

    private void getList(final int page) {
        SearchModel.getShoppingMallList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    MallBean mallBean = (MallBean) mData;
                    if (page == 1) {
                        mallAapter = new MallAdapter(getContext(), mallBean.getShoppingmalllist());
                        mall_listview.setAdapter(mallAapter);
                    } else {
                        mallAapter.addList(mallBean.getShoppingmalllist());
                    }
                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_mall;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
