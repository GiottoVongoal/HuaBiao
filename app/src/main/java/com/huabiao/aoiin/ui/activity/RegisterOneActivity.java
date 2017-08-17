package com.huabiao.aoiin.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.RegisterBean;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.bean.ScreenBean;
import com.huabiao.aoiin.bean.SelectClassificationCheckBean;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.tools.ActivityCollector;
import com.huabiao.aoiin.ui.adapter.RegisterCardOneAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyGridLayoutManager;
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.huabiao.aoiin.wedgit.ScreenPopupWindow;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.activity
 * @date 2017-08-15 14:23
 * @description 自主注册第一步(基本信息、注册类别、折线图)
 */
public class RegisterOneActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.register_card_one_tradename_tv)
    TextView tradename_tv;//注册商标
    @Bind(R.id.register_card_one_tradetype_tv)
    TextView tradetype_tv;//分类


    //    @Bind(R.id.register_card_one_classification_tv)
//    TextView classification_tv;//按类筛选
    ScreenPopupWindow screenPopupWindow;
    @Bind(R.id.register_card_one_classification_rv)
    RecyclerView classification_rv;
    RegisterCardOneAdapter adapter;
    List<SelectClassificationCheckBean> list;

    @Bind(R.id.register_card_one_linechart)
    DrawLineChartView linechart;//折线图

    @Bind(R.id.register_card_one_next_tv)
    TextView next_tv;//确定

    private RegisterCommitBean commitBean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("注册信息");
        setBackEnable();
        setRightIvResourse(getResources().getDrawable(R.mipmap.kefu_icon));
        setRightIvOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("客服");
            }
        });
        ActivityCollector.addActivity(this);

        commitBean = RegisterCommitBean.getInstance();
        RegisterModel.getRegisterData(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    RegisterBean bean = (RegisterBean) mData;
                    getShowData(bean);
                }
            }
        });
//        classification_tv.setOnClickListener(this);
        next_tv.setOnClickListener(this);
    }

    private void getShowData(RegisterBean bean) {
        tradename_tv.setText(bean.getLinechart().getTradename());
        tradetype_tv.setText(bean.getLinechart().getClassificationid() + "-" + bean.getLinechart().getTrademarkclassification());
        linechart.setLineChartBean(bean.getLinechart());

        list = getList(bean.getClassification());
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        classification_rv.setLayoutManager(layoutManager);
        adapter = new RegisterCardOneAdapter(this, list);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.register_card_one_classification_tv:
//                //按类筛选
//                getClassification(view);
//                break;
            case R.id.register_card_one_next_tv:
                //下一步
//                AppBus.getInstance().post(produceChangeIndex());
                save();
                JumpUtils.startActivity(this, RegisterTwoActivity.class);
                break;
        }
    }

    private void save() {
        List<ClassificationBean> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                ClassificationBean bean = new ClassificationBean();
                bean.setClassificationname(list.get(i).getClassificationname());
                bean.setClassificationid(list.get(i).getClassificationid());
                result.add(bean);
            }
        }
        commitBean.setClaList(result);
    }

    /**
     * 获取注册类别列表
     */
    private void getClassification(final View view) {
        SearchModel.getSelectClassificationList(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    ScreenBean bean = (ScreenBean) mData;
                    List<ScreenBean.ScreenlistBean> list = bean.getScreenlist();
                    //选择注册类别,更新数据
                    screenPopupWindow = new ScreenPopupWindow(RegisterOneActivity.this, list, new InterfaceManager.OnScreenItemClickListener() {
                        @Override
                        public void onItemClickListener(View view, ClassificationBean bean) {
                            ALog.i("bean", bean.getClassificationid() + "-" + bean.getClassificationname());
                        }
                    });
                    screenPopupWindow.showPopupWindow(view);
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

    @Override
    public int getContentLayout() {
        return R.layout.register_one_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


}
