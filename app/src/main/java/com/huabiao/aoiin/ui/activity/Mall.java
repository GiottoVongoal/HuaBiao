package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MallBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.MallAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import butterknife.Bind;


public class Mall extends BaseFragment implements View.OnClickListener {
    private boolean isLast;     //是否滑动到底部的标志位
    private MallAdapter mallAapter;
    private int page = 1;

    @Bind(R.id.Mall_listView)
    ListView mall_listview;
    //可求购按钮
    @Bind(R.id.mall_canbuy_tv)
    TextView mall_canbuy_tv;
    //可异议按钮
    @Bind(R.id.mall_canyiyi_tv)
    TextView mall_canyiyi_tv;
    //在售按钮
    @Bind(R.id.mall_forsale_tv)
    TextView mall_forsale_tv;
    //可抢注按钮
    @Bind(R.id.mall_cancybersquatting_tv)
    TextView mall_cancybersquatting_tv;
    //全部
    @Bind(R.id.mall_all_tv)
    TextView mall_all_tv;
    //筛选图片
    @Bind(R.id.mall_img)
    ImageView mall_img;
    @Override
    public void bindView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getList(1);
        //五个按钮以及筛选图片按钮的的监听事件
        mall_cancybersquatting_tv.setOnClickListener(this);
        mall_canbuy_tv.setOnClickListener(this);
        mall_all_tv.setOnClickListener(this);
        mall_canyiyi_tv.setOnClickListener(this);
        mall_forsale_tv.setOnClickListener(this);
        mall_img.setOnClickListener(this);
        //列表监听事件以及实例化
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

//五个按钮的监听事件实例化
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mall_canbuy_tv:
                showToast("可求购");
                break;
            case R.id.mall_cancybersquatting_tv:
                showToast("可抢注");
                break;
            case R.id.mall_canyiyi_tv:
                showToast("可异议");
                break;
            case R.id.mall_forsale_tv:
                showToast("在售");
                break;
            case R.id.mall_all_tv:
                showToast("全部");
                break;
            case R.id.mall_img:
                showToast("筛选");
                break;
        }
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
