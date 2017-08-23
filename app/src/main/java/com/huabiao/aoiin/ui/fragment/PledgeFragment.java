package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.PledgeBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.PledgeAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-23 14:58
 * @description 商标质押页面
 */
public class PledgeFragment extends BaseFragment {
    private PledgeAdapter pledgeAdapter;
    @Bind(R.id.pledge_recycleview)
    RecyclerView pledge_recycleview;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("商标质押");
        setBackEnable();
        SearchModel.getPledgeList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    PledgeBean bean = (PledgeBean) mData;
                    pledge_recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
                    pledgeAdapter=new PledgeAdapter(getContext(),bean.getPledgelist());
                    pledge_recycleview.setAdapter(pledgeAdapter);
                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.pledge_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
