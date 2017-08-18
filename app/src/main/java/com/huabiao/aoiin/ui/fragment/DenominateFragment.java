package com.huabiao.aoiin.ui.fragment;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CreatNameBean;
import com.huabiao.aoiin.bean.RegisterOneIndustryBean;
import com.huabiao.aoiin.model.AnalysisJson;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.ui.view.DenominateRotatePanLayout;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.IndustryPopupWindow;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 取名
 */
public class DenominateFragment extends BaseFragment implements DenominateRotatePanLayout.AnimationEndListener, View.OnClickListener {
    //转盘
    @Bind(R.id.rp_layout)
    DenominateRotatePanLayout rp;
    //中心的点击图片
    @Bind(R.id.go)
    ImageView goBtnIV;
    //商品名
    @Bind(R.id.tv_name)
    TextView nameTv;
    //商品类型
    @Bind(R.id.tv_trademarkclassification)
    TextView trademarkclassificationTv;
    //释义的内容
    @Bind(R.id.tv_means)
    TextView meansTV;
    //折线图
    @Bind(R.id.creat_name_line_chart)
    DrawLineChartView creat_name_line_chart;
    //输入商品名
    @Bind(R.id.denominate_trade_name_et)
    EditText denominate_trade_name_et;
    //选择行业
    @Bind(R.id.denominate_industry_btn)
    Button denominate_industry_btn;
    //跳转到详情页面的layout id
    @Bind(R.id.denominate_layout)
    LinearLayout denominate_layout;

    //用于传值的nameString
    private String nameString;
    private List<CreatNameBean.RecommendnamelistBean> list;
    private IndustryPopupWindow industryWindow;
    private int place = 0;
    private String industry = "";
    public void endAnimation(int position) {
        goBtnIV.setEnabled(true);
        setCreatNameData(position);
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        refreshView(false);
        denominate_industry_btn.setOnClickListener(this);
        denominate_layout.setOnClickListener(this);
        setTitle("商标取名");
        setBackEnable();
    }

    private void refreshView(final boolean isFirst) {
        AnalysisJson.getDenominateName(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    CreatNameBean bean = (CreatNameBean) mData;
                    ALog.i("CreatNameBean-->" + bean.toString());
                    list = bean.getRecommendnamelist();
                    if (list.size() > 0) {
                        setData();
                    }
                    rp.startRotate(-1);
                    if (isFirst) {
                        goBtnIV.setEnabled(false);
                    }
                }
            }
        });
    }

    private void setData() {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getLinechart().getTradename();
            ALog.i("list.get(i) " + list.get(i) + "   " + name);
            nameList.add(name);
        }
        ALog.i(nameList.toString());
        rp.setStr(nameList);
        rp.setAnimationEndListener(this);
        goBtnIV.setOnClickListener(this);
    }

    private void setCreatNameData(int position) {
        nameString = list.get(position).getLinechart().getTradename();
        nameTv.setText(nameString);
        meansTV.setText(list.get(position).getMeans());
        String classificationString = list.get(position).getLinechart().getClassificationid() + " - " + list.get(position).getLinechart().getTrademarkclassification();
        trademarkclassificationTv.setText(classificationString);
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
            case R.id.denominate_layout:
                Bundle bundle = new Bundle();
                bundle.putString("nameString",nameString);
                JumpUtils.startFragmentByName(getContext(), DenominateDetailsFragment.class,bundle);
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