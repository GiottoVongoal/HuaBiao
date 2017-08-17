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
    //将下划线放入一个数组
    View[] lines = new View[4];
    //当前页面的页数，1是首页
    private int page = 1;
    //m是传的标志位，1234代表的是全部、可求购、可抢注、可异议的页面
    private int m = 1;
    @Bind(R.id.Mall_listView)
    ListView mall_listview;
    //可求购按钮and line
    @Bind(R.id.mall_canbuy_tv)
    TextView mall_canbuy_tv;
    @Bind(R.id.mall_canbuy_tv_line)
    View mall_canbuy_tv_line;
    //可异议按钮 and line
    @Bind(R.id.mall_canyiyi_tv)
    TextView mall_canyiyi_tv;
    @Bind(R.id.mall_canyiyi_tv_line)
    View mall_canyiyi_tv_line;
    //可抢注按钮 and line
    @Bind(R.id.mall_cancybersquatting_tv)
    TextView mall_cancybersquatting_tv;
    @Bind(R.id.mall_cancybersquatting_tv_line)
    View mall_cancybersquatting_tv_line;
    //全部 and line
    @Bind(R.id.mall_all_tv)
    TextView mall_all_tv;
    @Bind(R.id.mall_all_tv_line)
    View mall_all_tv_line;
    //筛选图片
    @Bind(R.id.mall_img)
    ImageView mall_img;

    @Override
    public void bindView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getList(page, m);
        //五个按钮以及筛选图片按钮的的监听事件
        mall_cancybersquatting_tv.setOnClickListener(this);
        mall_canbuy_tv.setOnClickListener(this);
        mall_all_tv.setOnClickListener(this);
        mall_canyiyi_tv.setOnClickListener(this);
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
                    page++;
                    getList(page, m);
                }
            }
        });
    }

    private void getList(final int page, int m) {
        SearchModel.getShoppingMallList(getContext(), m, new InterfaceManager.CallBackCommon() {
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
        //设置线的显示隐藏
        lines[0] = mall_all_tv_line;
        lines[1] = mall_canbuy_tv_line;
        lines[2] = mall_cancybersquatting_tv_line;
        lines[3] = mall_canyiyi_tv_line;
        for (int i = 0; i < lines.length; i++) {
            lines[i].setVisibility(View.INVISIBLE);
            if (i == (m - 1)) {
                lines[i].setVisibility(View.VISIBLE);
            }
        }

    }

    //五个按钮的监听事件实例化，点击显示不同页面，解析不同的json.
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mall_canbuy_tv:
                m = 2;
                page = 1;
                getList(page, m);
                break;
            case R.id.mall_cancybersquatting_tv:
                m = 3;
                page = 1;
                getList(page, m);
                break;
            case R.id.mall_canyiyi_tv:
                m = 4;
                page = 1;
                getList(page, m);
                break;
            case R.id.mall_all_tv:
                m = 1;
                page = 1;
                getList(page, m);
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
