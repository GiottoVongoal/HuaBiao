package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MyOrdersBean;
import com.huabiao.aoiin.constant.FlagBase;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.activity.PayInfoDetailActivity;
import com.huabiao.aoiin.ui.adapter.MyOrdersAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.recycler.XRecyclerView;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Aoiin-9 on 2017/8/23.
 */

public class MyOrdersFragment extends BaseFragment implements View.OnClickListener {
    private MyOrdersAdapter ordersAdapter;
    private boolean isLast;     //是否滑动到底部的标志位
    //全部按钮
    @Bind(R.id.myorders_all_tv)
    TextView all;
    //取消按钮
    @Bind(R.id.myorders_cancel_tv)
    TextView cancel;
    //已完成按钮
    @Bind(R.id.myorders_finish_tv)
    TextView finish;
    //待支付按钮
    @Bind(R.id.myorders_yetpay_tv)
    TextView yetpay;
    @Bind(R.id.myorders_recyclerview)
    XRecyclerView ordersrcyclerview;
    //待付款下标线
    @Bind(R.id.myorders_yetpaylines)
    View myorders_yetpaylines;
    //已完成下标线
    @Bind(R.id.myorders_finishlines)
    View myorders_finishlines;
    //已取消下标线
    @Bind(R.id.myorders_cancellines)
    View myorders_cancellines;
    //全部下标线
    @Bind(R.id.myorders_alllines)
    View myorders_alllines;
    //选中时下标线
    View[] mlines = new View[4];
    //当前页面的页数
    private int mpage = 1;
    //n是标志位，1234代表的是全部、待支付、已完成、已取消的页面
    private int n = 1;
    //定义mloadtype的默认值是下拉刷新
    private int mLoadType = FlagBase.PULL_TO_REFRESH;
    //刷新list
    private List<MyOrdersBean.MyorderslistBean> list_refresh;
    //将四个页面装进一个数组
    private int[] pagercv;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的订单");
        setBackEnable();
        finish.setOnClickListener(this);
        all.setOnClickListener(this);
        cancel.setOnClickListener(this);
        yetpay.setOnClickListener(this);
        getMyordersList(mpage, n);
        list_refresh = new ArrayList<>();
        ordersrcyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        ordersAdapter = new MyOrdersAdapter(getContext(), list_refresh);
        ordersrcyclerview.setAdapter(ordersAdapter);
        ordersrcyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mpage = 1;
                mLoadType = FlagBase.PULL_TO_REFRESH;
                getMyordersList(mpage, n);
            }

            @Override
            public void onLoadMore() {
                //加载更多
                mpage++;
                mLoadType = 200;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        Message msg = new Message();
                        msg.what = 123;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
        ordersrcyclerview.refresh();
        ordersrcyclerview.setLoadingMoreEnabled(false);
        // 设置Item增加、移除动画
        ordersrcyclerview.setItemAnimator(new DefaultItemAnimator());

        ordersAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("status", list_refresh.get(position).getStatus() == 1 ? 1 : 0);
                JumpUtils.startActivity(getContext(), PayInfoDetailActivity.class, bundle);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 123) {
                mLoadType = FlagBase.SCROLL_LOAD_MORE;
                getMyordersList(mpage, n);
            }
        }
    };

    private void getMyordersList(final int mpage, int n) {
        SearchModel.getMyordersList(getContext(), n, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    MyOrdersBean bean = (MyOrdersBean) mData;
                    List<MyOrdersBean.MyorderslistBean> list = bean.getMyorderslist();
                    if (mLoadType == FlagBase.PULL_TO_REFRESH) {
                        list_refresh = list;
                        if (list_refresh.size() > 0 && list_refresh != null) {
                            if (ordersAdapter != null) {
                                ordersAdapter.update(list_refresh);
                            }
                            ordersrcyclerview.refreshComplete();
                        }
                    } else if (mLoadType == FlagBase.SCROLL_LOAD_MORE) {
                        if (list.size() > 0 && list != null) {
                            list_refresh.addAll(list);
                            if (ordersAdapter != null) {
                                ordersAdapter.update(list_refresh);
                            }
                            ordersrcyclerview.loadMoreComplete();
                        } else {
                            ordersrcyclerview.setNoMore(true);
                        }
                    }
                }
            }
        });


//        置线的显示隐藏
        mlines[0] = myorders_alllines;
        mlines[1] = myorders_yetpaylines;
        mlines[2] = myorders_finishlines;
        mlines[3] = myorders_cancellines;
        for (int i = 0; i < mlines.length; i++) {
            mlines[i].setVisibility(View.INVISIBLE);
            if (i == (n - 1)) {
                mlines[i].setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.layout_myorders;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myorders_all_tv:
                //全部
                n = 1;
                mpage = 1;
                getMyordersList(mpage, n);
                break;
            case R.id.myorders_yetpay_tv:
                //2待支付
                n = 2;
                mpage = 1;
                getMyordersList(mpage, n);
                break;
            case R.id.myorders_finish_tv:
                //3已完成
                n = 3;
                mpage = 1;
                getMyordersList(mpage, n);
                break;
            case R.id.myorders_cancel_tv:
                //4已取消
                n = 4;
                mpage = 1;
                getMyordersList(mpage, n);
                break;
        }
    }
}
