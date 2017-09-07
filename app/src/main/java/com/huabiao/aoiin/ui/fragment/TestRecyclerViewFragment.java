package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MyOrdersBean;
import com.huabiao.aoiin.constant.FlagBase;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.MyOrdersAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.recycler.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static android.net.wifi.SupplicantState.COMPLETED;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-30 11:15
 * @description
 */
public class TestRecyclerViewFragment extends BaseFragment {
    private MyOrdersAdapter adapter;
    @Bind(R.id.myorders_recyclerview)
    XRecyclerView ordersrcyclerview;
    List<MyOrdersBean.MyorderslistBean> mList;

    private int mpage = 1;
    private int mLoadType = 0;

    @Override
    public void bindView(Bundle savedInstanceState) {
        ordersrcyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        ordersrcyclerview.setHasFixedSize(true);

        mList = new ArrayList<>();
        adapter = new MyOrdersAdapter(getContext(), mList);
        ordersrcyclerview.setAdapter(adapter);
        ordersrcyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                mpage = 1;
                mLoadType = 100;
                getData(mpage);
            }

            @Override
            public void onLoadMore() {
                //滚动加载
                mpage++;
                mLoadType = 200;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(1000);
                        Message msg = new Message();
                        msg.what = 123;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
        ordersrcyclerview.refresh();
//        //关闭下拉刷新
//        ordersrcyclerview.setPullRefreshEnabled(false);
//        //关闭上滑加载
//        ordersrcyclerview.setLoadingMoreEnabled(false);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 123) {
                getData(mpage);
            }
        }
    };

    private void getData(final int mpage) {
        SearchModel.getMyordersList(getContext(), 1, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    MyOrdersBean bean = (MyOrdersBean) mData;
                    List<MyOrdersBean.MyorderslistBean> list = bean.getMyorderslist();
                    if (mLoadType == 100) {
                        mList = list;
                        if (mList.size() > 0 && mList != null) {
                            if (adapter != null) {
                                adapter.update(mList);
                            }
                            ordersrcyclerview.refreshComplete();
                        }
                    } else if (mLoadType == 200) {
                        if (list.size() > 0 && list != null && mpage <= 5) {
                            mList.addAll(list);
                            if (adapter != null) {
                                adapter.update(mList);
                            }
                            ordersrcyclerview.loadMoreComplete();
                        } else {
                            ordersrcyclerview.setNoMore(true);
                        }
                    }
                }
            }
        });
    }


    @Override
    public int getContentLayout() {
        return R.layout.layout_myorders;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
