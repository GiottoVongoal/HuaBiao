package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultUnregisteredAndCreatNameBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.DenominateDetailsAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * Created by Aoiin-9 on 2017/8/11.
 */

public class DenominateDetailsFragment extends BaseFragment implements View.OnClickListener {
    private DenominateDetailsAdapter detailsAdapter;

    //可注册按钮
    @Bind(R.id.details_tv1)
    TextView details_tv1;
    //筛选按钮
    @Bind(R.id.details_tv2)
    TextView details_tv2;
    //分类的两个框
    @Bind(R.id.denominate_details_rcv)
    RecyclerView denominate_details_rcv;
    //折线图
    @Bind(R.id.details_linechart)
    DrawLineChartView details_linechart;
    //释义
    @Bind(R.id.details_content)
    TextView details_content;
    //注册按钮
    @Bind(R.id.details_register_tv)
    TextView details_register_tv;
    //用来传递标题的nameString
    private String nameString;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getActivity().getIntent().getExtras();
        nameString = bundle.getString("nameString");
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        //三个按钮的监听
        details_tv1.setOnClickListener(this);
        details_tv2.setOnClickListener(this);
        details_register_tv.setOnClickListener(this);
        //标题的右边图片以及监听事件
        setTitle(nameString);
        setRightIvResourse(getResources().getDrawable(R.mipmap.ic_launcher));
        setRightIvOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("收藏");
            }
        });
        SearchModel.getSearchUnregisterResult(getContext(), "", "", new InterfaceManager.CallBackCommon() {

            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    SearchResultUnregisteredAndCreatNameBean bean = (SearchResultUnregisteredAndCreatNameBean) mData;
                    denominate_details_rcv.setLayoutManager(new FullyLinearLayoutManager(getContext()));
                    detailsAdapter = new DenominateDetailsAdapter(bean.getClassification());
                    denominate_details_rcv.setAdapter(detailsAdapter);
                    details_content.setText(bean.getMeans());
                    details_linechart.setLineChartBean(bean.getLinechart());
                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.layout_denominate_details;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.details_register_tv:
                showToast("注册按钮");
                break;
            case R.id.details_tv1:
                showToast("可注册按钮");
                break;
            case R.id.details_tv2:
                showToast("筛选按钮");
                break;
        }
    }
}
