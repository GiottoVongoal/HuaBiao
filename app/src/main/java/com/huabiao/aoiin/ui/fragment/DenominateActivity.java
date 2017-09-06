package com.huabiao.aoiin.ui.fragment;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CreatNameBean;
import com.huabiao.aoiin.bean.RegisterOneIndustryBean;
import com.huabiao.aoiin.model.AnalysisJson;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.ui.adapter.UpMenuAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.ui.view.DenominateRotatePanLayout;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.KeyboardUtils;
import com.ywy.mylibs.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 取名
 */
public class DenominateActivity extends BaseActivity implements DenominateRotatePanLayout.AnimationEndListener, View.OnClickListener {
    //转盘
    @Bind(R.id.rp_layout)
    DenominateRotatePanLayout rp;
    //中心的点击图片
    @Bind(R.id.zhizhen)
    ImageView goBtnIV;
    //商品名
//    @Bind(R.id.tv_name)
//    TextView nameTv;
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
    //选择行业(以前是btn现在已经改为了textview)
    @Bind(R.id.denominate_industry_btn)
    TextView denominate_industry_btn;
    //跳转到详情页面的layout id
    @Bind(R.id.denominate_layout)
    LinearLayout denominate_layout;
    //行业下拉框的adapter和recycler view
    private UpMenuAdapter menuAdapter;
    private RecyclerView popRecyclerView;
    //用于传值的nameString
    private String nameString;
    private List<CreatNameBean.RecommendnamelistBean> list;
    //行业弹框
    private PopupWindow industryWindow;
    private String industry = "";
    private String str = "";

    public void endAnimation(int position) {
        goBtnIV.setEnabled(true);
        setCreatNameData(position);
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("商标取名");
        setBackEnable();
        refreshView(true);//flase不可点击，true可点击
        denominate_industry_btn.setOnClickListener(this);
        denominate_layout.setOnClickListener(this);
        goBtnIV.setOnClickListener(this);
    }

    private void refreshView(final boolean isFirst) {
        AnalysisJson.getDenominateName(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    CreatNameBean bean = (CreatNameBean) mData;
                    list = bean.getRecommendnamelist();
                    if (list.size() > 0) {
                        setData();
                    }
                    if (!isFirst) {
                        rp.startRotate(-1);
                        goBtnIV.setEnabled(false);
                    } else {
                        //如果是第一次进入设置数据为list[0]
                        rp.initAngle();
                        meansTV.setText(list.get(0).getMeans());
                        trademarkclassificationTv.setText(list.get(0).getLinechart().getClassificationid() + " - " + list.get(0).getLinechart().getTrademarkclassification());
                        creat_name_line_chart.setLineChartBean(list.get(0).getLinechart());
                        //将第一次的默认数据赋给nameString并传入下一个页面
                        String s1=list.get(0).getLinechart().getTradename();
                        nameString=s1;
                    }


                }
            }
        });
    }

    //将商品名取出来set到转盘上
    private void setData() {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getLinechart().getTradename();
            nameList.add(name);
        }
        ALog.i(nameList.toString());
        rp.setStr(nameList);
        rp.setAnimationEndListener(this);
    }

    //写入数据
    private void setCreatNameData(int position) {
            nameString = list.get(position).getLinechart().getTradename();
//        nameTv.setText(nameString);
        meansTV.setText(list.get(position).getMeans());
        String classificationString = list.get(position).getLinechart().getClassificationid() + " - " + list.get(position).getLinechart().getTrademarkclassification();
        trademarkclassificationTv.setText(classificationString);
        //折线图
        creat_name_line_chart.setLineChartBean(list.get(position).getLinechart());
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            //判断editview与行业是否有值，有值的话点击go有效,没有值则输出弹框提示
            case R.id.zhizhen:
                str = denominate_trade_name_et.getText().toString();
                if (StringUtil.isEmpty(str)) {
                    showToast("请输入商品名");
                    return;
                }
                if (StringUtil.isEmpty(industry)) {
                    showToast("请选择行业");
                    return;
                }
                refreshView(false);
                break;
            //点击行业按钮弹出选择弹窗
            case R.id.denominate_industry_btn:
                KeyboardUtils.hideSoftInput(this);
                RegisterModel.getIndustryList(this, new InterfaceManager.CallBackCommon() {
                    @Override
                    public void getCallBackCommon(Object mData) {
                        if (mData != null) {
                            RegisterOneIndustryBean bean = (RegisterOneIndustryBean) mData;
                            showIndustryWindow(view, bean.getRegisteroneindustrylist());
                        }
                    }
                });
                popRecyclerView.setAdapter(menuAdapter);
                break;
            //点击跳到详情页，并传了一个nameString的bundle过去
            case R.id.denominate_layout:
                Bundle bundle = new Bundle();
                bundle.putString("nameString", nameString);
                JumpUtils.startFragmentByName(this, DenominateDetailsFragment.class, bundle);
                break;
        }
    }

    //行业选择弹框
    private void showIndustryWindow(View view, final List<RegisterOneIndustryBean.IndustrylistBean> industryList) {
        View industryview = view.inflate(this, R.layout.layout_industry, null);
        industryWindow = new PopupWindow(industryview, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        industryWindow.setOutsideTouchable(true);
        industryWindow.setBackgroundDrawable(new BitmapDrawable());
        industryWindow.setFocusable(true);
        industryWindow.setAnimationStyle(R.style.popwin_anim_style);
        industryWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                denominate_industry_btn.setTextColor(getResources().getColor(R.color.black3));
            }
        });
//        denominate_industry_btn.setText(industryList.get(1).getIndustryname());//默认显示列表的第二个
        popRecyclerView = (RecyclerView) industryview.findViewById(R.id.popwin_industry_list_rv);
        industryview.findViewById(R.id.popwin_industry_list_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                industryWindow.dismiss();
            }
        });
        popRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < industryList.size(); i++) {
            list.add(industryList.get(i).getIndustryname());
        }
        menuAdapter = new UpMenuAdapter(this, list);
        menuAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                industry = industryList.get(position).getIndustryname();
                denominate_industry_btn.setText(industry);
                showToast(industryList.get(position).getIndustryname());
                industryWindow.dismiss();
            }
        });
        //显示窗口方式
        industryWindow.showAsDropDown(denominate_industry_btn);

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