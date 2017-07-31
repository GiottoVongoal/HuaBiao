package com.huabiao.aoiin.ui.activity;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CreatNameBean;
import com.huabiao.aoiin.bean.RegisterOneIndustryBean;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.ui.view.DenominateRotatePanLayout;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.IndustryPopupWindow;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class DenominateFragment extends BaseFragment implements DenominateRotatePanLayout.AnimationEndListener, View.OnClickListener {
    @Bind(R.id.rp_layout)
    DenominateRotatePanLayout rp;

    @Bind(R.id.go)
    ImageView goBtn;

    @Bind(R.id.tv_name)
    TextView name;

    @Bind(R.id.tv_trademarkclassification)
    TextView trademarkclassification;

    @Bind(R.id.tv_means)
    TextView means;

    @Bind(R.id.creat_name_line_chart)
    DrawLineChartView creat_name_line_chart;

    @Bind(R.id.denominate_trade_name_et)
    EditText denominate_trade_name_et;

    @Bind(R.id.denominate_industry_btn)
    Button denominate_industry_btn;//选择行业

    private List<CreatNameBean.RecommendnamelistBean> list;
    private IndustryPopupWindow industryWindow;
    private int place = 0;
    private String industry = "", tradename = "";

    public void endAnimation(int position) {
        goBtn.setEnabled(true);
        setCreatNameData(position);
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        refreshView(false);
        denominate_industry_btn.setOnClickListener(this);
    }

    private void refreshView(final boolean isFirst) {
        SearchModel.getCreatName(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    CreatNameBean bean = (CreatNameBean) mData;
                    list = bean.getRecommendnamelist();
                    if (list.size() > 0) {
                        setData();
                    }
                    rp.startRotate(-1);
                    if (isFirst) {
                        goBtn.setEnabled(false);
                    }
                }
            }
        });
    }

    private void setData() {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String name=list.get(i).getLinechart().getTradename();
            ALog.i("list.get(i) "+list.get(i)+"   "+name);
            nameList.add(name);
        }
        ALog.i(nameList.toString());
        rp.setStr(nameList);
        rp.setAnimationEndListener(this);
        goBtn.setOnClickListener(this);
    }

    private void setCreatNameData(int position) {
        name.setText(list.get(position).getLinechart().getTradename());
        means.setText(list.get(position).getMeans());
        String classificationString = list.get(position).getLinechart().getClassificationid() + " - " + list.get(position).getLinechart().getTrademarkclassification();
        trademarkclassification.setText(classificationString);
        creat_name_line_chart.setLineChartBean(list.get(position).getLinechart());

    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.go:
                refreshView(true);
                break;
            case R.id.denominate_industry_btn:
                RegisterModel.getIndustryList(getContext(), new InterfaceManager.CallBackCommon() {
                    @Override
                    public void getCallBackCommon(Object mData) {
                        if (mData != null) {
                            RegisterOneIndustryBean bean = (RegisterOneIndustryBean) mData;
                            showIndustryWindow(view, bean.getRegisteroneindustrylist());
                        }
                    }
                });
                break;
        }
    }

    private void showIndustryWindow(View view, final List<RegisterOneIndustryBean.IndustrylistBean> industryList) {
        industryWindow = new IndustryPopupWindow(getContext(), "标题", industryList, place, new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                industry = industryList.get(position).getIndustryname();
                denominate_industry_btn.setText(industry);
                place = position;
                industryWindow.dismiss();
            }
        });
        //显示窗口
        industryWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_denominate;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}