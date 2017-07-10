package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-10 13:19
 * @description
 */

public class CheckTypeListFragment extends BaseFragment {
    @Bind(R.id.check_type_list_rv)
    RecyclerView check_type_list_rv;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {


        SearchModel.getJsonResult(getContext(), "classificationlist1.json", new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {

                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.check_type_list_layout;
    }
}
