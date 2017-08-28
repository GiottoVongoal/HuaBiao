package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MyOrdersBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.MyOrdersAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

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
    RecyclerView ordersrcyclerview;
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
        getMyordersList(mpage,n);
    }

    private void getMyordersList(final int mpage, int n) {
        SearchModel.getMyordersList(getContext(), n, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    MyOrdersBean bean = (MyOrdersBean) mData;
                    List<MyOrdersBean.MyorderslistBean> list = bean.getMyorderslist();
                    if (mpage == 1) {
                        ordersrcyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                        ordersAdapter = new MyOrdersAdapter(getContext(), list);
                        ordersrcyclerview.setAdapter(ordersAdapter);
                    } else {
                        ordersAdapter.update(list);
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
                n = 1;
                mpage = 1;
                getMyordersList(mpage,n);
                break;
            case R.id.myorders_yetpay_tv:
                 n = 2;
                 mpage = 1;
                 getMyordersList(mpage,n);
                 break;
            case R.id.myorders_finish_tv:
                n = 3;
                mpage = 1;
                getMyordersList(mpage,n);
                break;
            case R.id.myorders_cancel_tv:
                n = 4;
                mpage = 1;
                getMyordersList(mpage,n);
                break;
        }
    }
}
