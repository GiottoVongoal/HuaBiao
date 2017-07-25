package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.HotSearchWordsBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.HotSearchAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

public class HotSearchWord extends BaseFragment {
    private HotSearchAdapter mAdapter;

    @Bind(R.id.my_recyclerview)
    RecyclerView mRecylerView;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        //初始化
        SearchModel.getHotWords(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    HotSearchWordsBean bean = (HotSearchWordsBean) mData;
                    mRecylerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                    mRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    mRecylerView.setLayoutManager(new HotSearchWordLayoutManager());
                    SnapHelper snapHelper=new LinearSnapHelper();
                    snapHelper.attachToRecyclerView(mRecylerView);
                    mAdapter = new HotSearchAdapter(bean.getHotwords());
                    mRecylerView.setAdapter(mAdapter);
                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_hot_search_word;
    }

}
