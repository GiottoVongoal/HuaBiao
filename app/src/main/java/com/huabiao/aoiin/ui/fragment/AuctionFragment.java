package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.AuctionBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.AuctionAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * Created by Aoiin-9 on 2017/8/25.
 * 商标拍卖
 */

public class AuctionFragment extends BaseFragment {
    private AuctionAdapter auctionAdapter;
    @Bind(R.id.auction_recycleview)
    RecyclerView auction_recyclerview;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("商标拍卖");
        setBackEnable();
        setRightIvResourse(getResources().getDrawable(R.mipmap.fangdajing));
        setRightIvOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("放大镜");
            }
        });
        SearchModel.getAuctionList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    AuctionBean bean = (AuctionBean) mData;
                    auction_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                    auctionAdapter = new AuctionAdapter(getContext(), bean.getAuctionlist());
                    auction_recyclerview.setAdapter(auctionAdapter);

                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.auction_layout;
    }
}
