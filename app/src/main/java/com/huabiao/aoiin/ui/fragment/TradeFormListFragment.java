package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.TradeFormListBean;
import com.huabiao.aoiin.bean.TradeFormListBean.TradeFormItemBean;
import com.huabiao.aoiin.model.MeModel;
import com.huabiao.aoiin.ui.adapter.TradeFormListAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-09 15:32
 * @description 商标注册表单列表
 */
public class TradeFormListFragment extends BaseFragment {
    @Bind(R.id.trade_form_list_rv)
    RecyclerView trade_form_list_rv;
    private TradeFormListAdapter adapter;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("商标注册表单");
        setBackEnable();

        MeModel.getTradeFormList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    TradeFormListBean bean = (TradeFormListBean) mData;
                    showData(bean);
                }
            }
        });
    }

    private void showData(TradeFormListBean bean) {
        final List<TradeFormItemBean> list = bean.getTradeformlist();
        adapter = new TradeFormListAdapter(getContext(), list);
        trade_form_list_rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        trade_form_list_rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", 1);
                bundle.putString("tradeid", list.get(position).getClassificationid());
                JumpUtils.startActivity(getContext(), RegisterDataPreviewActivity.class, bundle);
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.trade_form_list_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
