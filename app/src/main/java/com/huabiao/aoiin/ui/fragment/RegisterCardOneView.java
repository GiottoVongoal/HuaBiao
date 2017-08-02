package com.huabiao.aoiin.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.RegisterBean;
import com.huabiao.aoiin.bean.SelectClassificationCheckBean;
import com.huabiao.aoiin.bean.SelectClassificationListBean;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.RegisterCardOneAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ChangeRegisterIndexEvent;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyGridLayoutManager;
import com.squareup.otto.Produce;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-02 20:40
 * @description 自主注册第一张卡片(基本信息、注册类别、折线图)
 */
public class RegisterCardOneView extends LinearLayout implements View.OnClickListener {
    private TextView tradename_tv;//注册商标
    private TextView tradetype_tv;//分类

    //按类筛选
    private TextView classification_tv;
    private RecyclerView classification_rv;
    private RegisterCardOneAdapter adapter;
    private List<SelectClassificationCheckBean> list;

    private DrawLineChartView linechart;//折线图
    private TextView meaning_tv;//释义
    private TextView next_tv;//下一步

    private View view;
    private Context context;

    public RegisterCardOneView(Context context) {
        super(context);
        view = inflate(context, R.layout.register_card_one_layout, this);
        this.context = context;
        list = new ArrayList<>();
        tradename_tv = (TextView) view.findViewById(R.id.register_card_one_tradename_tv);
        tradetype_tv = (TextView) view.findViewById(R.id.register_card_one_tradetype_tv);
        classification_tv = (TextView) view.findViewById(R.id.register_card_one_classification_tv);
        classification_rv = (RecyclerView) view.findViewById(R.id.register_card_one_classification_rv);
        linechart = (DrawLineChartView) view.findViewById(R.id.register_card_one_linechart);
        meaning_tv = (TextView) view.findViewById(R.id.register_card_one_meaning_tv);
        next_tv = (TextView) view.findViewById(R.id.register_card_one_next_tv);

        RegisterModel.getRegisterData(context, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    RegisterBean bean = (RegisterBean) mData;
                    getShowData(bean);
                }
            }
        });
        classification_tv.setOnClickListener(this);
        next_tv.setOnClickListener(this);
    }

    private void getShowData(RegisterBean bean) {
        tradename_tv.setText("注册商标" + bean.getLinechart().getTradename());
        tradetype_tv.setText("分类" + bean.getLinechart().getClassificationid() + "-" + bean.getLinechart().getTrademarkclassification());
        linechart.setLineChartBean(bean.getLinechart());

        list = getList(bean.getClassification());
        classification_rv.setLayoutManager(new FullyGridLayoutManager(getContext(), 2));
        adapter = new RegisterCardOneAdapter(context, list);
        classification_rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                boolean ischeck = list.get(position).isCheck();
                if (ischeck) {
                    list.get(position).setCheck(false);
                } else {
                    list.get(position).setCheck(true);
                }
                adapter.notifyDataSetChanged();
            }
        });

        meaning_tv.setText(bean.getMeans());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_card_one_classification_tv:
                //按类筛选
                getClassification();
                break;
            case R.id.register_card_one_next_tv:
                //下一步
                AppBus.getInstance().post(produceChangeIndex());
                break;
        }
    }

    /**
     * 产生事件
     *
     * @return
     */
    @Produce
    public ChangeRegisterIndexEvent produceChangeIndex() {
        return new ChangeRegisterIndexEvent(1);
    }

    /**
     * 获取注册类别列表
     */
    private void getClassification() {
        SearchModel.getSelectClassificationList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    SelectClassificationListBean bean = (SelectClassificationListBean) mData;
                    List<ClassificationBean> list = bean.getClassification();
                    //选择注册类别,更新数据
                }
            }
        });
    }

    private List<SelectClassificationCheckBean> getList(List<ClassificationBean> list) {
        List<SelectClassificationCheckBean> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SelectClassificationCheckBean bean = new SelectClassificationCheckBean();
            bean.setClassificationid(list.get(i).getClassificationid());
            bean.setClassificationname(list.get(i).getClassificationname());
            bean.setCheck(false);
            resultList.add(bean);
        }
        return resultList;
    }
}
