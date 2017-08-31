package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.BuyingInfoBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.BuyingAdapter1;
import com.huabiao.aoiin.ui.adapter.BuyingAdapter2;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.huabiao.aoiin.wedgit.MaxRecyclerView;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.BitmapLoader;

import butterknife.Bind;

/**
 * Created by Aoiin-9 on 2017/8/1.
 * 商城详情页
 */

public class BuyingFragment extends BaseFragment implements View.OnClickListener {
    private BuyingAdapter1 buyingAdapter1;
    private BuyingAdapter2 buyingAdapter2;
    //商标无效
    @Bind(R.id.buying_trademarkuseless)
    TextView trademarkuseless;
    //求购详情页面最下方的两个按钮
    @Bind(R.id.buying1_tv)
    TextView buying1_tv;
    @Bind(R.id.buying2_tv)
    TextView buying2_tv;
    //商标服务列表
    @Bind(R.id.buying_recyclerview1)
    MaxRecyclerView buyingRecylerView1;
    //公告和状态
    @Bind(R.id.buying_recyclerview2)
    MaxRecyclerView buying_recyclerview2;

    //商标详情页图片
    @Bind(R.id.buying_view_img)
    ImageView view_img;
    //商标详情页商标名
    @Bind(R.id.buying_view_trademarkname)
    TextView view_trademarkname;
    //商标详情页商标类型
    @Bind(R.id.buying_view_classfication)
    TextView view_classfication;
    //商标详情页商标状态
    @Bind(R.id.buying_view_trademarkstatus)
    TextView view_trademarkstatus;

    //商标基础信息申请人地址
    @Bind(R.id.buying_baseinfo_applicantaddress)
    TextView baseinfo_applicantaddress;
    //商标基础信息申请人名称
    @Bind(R.id.buying_baseinfo_applicantname)
    TextView baseinfo_applicantname;
    //商标基础信息商标名称
    @Bind(R.id.buying_baseinfo_trademarkname)
    TextView baseinfo_trademarkname;
    //商标基础信息商标注册号
    @Bind(R.id.buying_baseinfo_registrationnumber)
    TextView baseinfo_registrationnumber;
    //商标基础信息分类
    @Bind(R.id.buying_baseinfo_classification)
    TextView baseinfo_classification;
    //代理公司
    @Bind(R.id.buying_baseinfo_proxycompany)
    TextView baseinfo_proxycompany;

    //商标注册信息申请日期
    @Bind(R.id.buying_registerinfo_applicationdate)
    TextView registerinfo_applicationdate;
    //商标注册信息注册日期
    @Bind(R.id.buying_registerinfo_registerdate)
    TextView registerinfo_registerdate;
    //商标注册信息初审公告日期
    @Bind(R.id.buying_registerinfo_preliminarynoticedate)
    TextView registerinfo_preliminarynoticedate;
    int n;
    //下面的两个按钮
    private TextView[] tvlist = new TextView[2];

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        //bundle传递数据，最下方按钮的文字变化，case123是表示页面id
        Bundle bundle = getActivity().getIntent().getExtras();
        n = bundle.getInt("pageid", 1);
        setTitle("商标详情页");
        setRightIvResourse(getResources().getDrawable(R.mipmap.ic_launcher));
        setBackEnable();
        switch (n) {
            case 1:
                buying1_tv.setText("咨询客服");
                buying2_tv.setText("求购此标");
                break;
            case 2:
                buying1_tv.setText("阻止申请");
                buying2_tv.setText("求购此标");
                break;
            case 3:
                buying1_tv.setText("咨询客服");
                buying2_tv.setText("抢注此标");
                break;
        }
        tvlist[0] = buying1_tv;
        tvlist[1] = buying2_tv;
        setSelect(1);
        buying1_tv.setOnClickListener(this);
        buying2_tv.setOnClickListener(this);
        SearchModel.getBuyingInfo(getContext(), n, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    //获取整个模型类的数据
                    BuyingInfoBean buyingInfoBean = (BuyingInfoBean) mData;
                    BitmapLoader.ins().loadImage(buyingInfoBean.getImg(), R.mipmap.touxiang, view_img);
                    view_trademarkname.setText(buyingInfoBean.getName());
                    view_classfication.setText(buyingInfoBean.getClassificationid() + "类" + "-" + buyingInfoBean.getClassifictiontype());
                    view_trademarkstatus.setText(buyingInfoBean.getStatus());
                    //根据json的状态位来显示不同的状态
                    int i = Integer.parseInt(buyingInfoBean.getStatus());
                    switch (i) {
                        case 0:
                            view_trademarkstatus.setText("商标无效");
                            break;
                        case 1:
                            view_trademarkstatus.setText("初审公告");
                            break;
                        case 2:
                            view_trademarkstatus.setText("注册成功");
                            break;
                        case 3:
                            view_trademarkstatus.setText("待审核中");
                            break;
                    }
                    //商标基础信息获取数据
                    BuyingInfoBean.BaseinfoBean baseinfoBean = buyingInfoBean.getBaseinfo();
                    baseinfo_trademarkname.setText("商标名称：" + baseinfoBean.getTrademarkname());
                    baseinfo_registrationnumber.setText("注册号：" + baseinfoBean.getRegistrationnumber());
                    baseinfo_classification.setText("分类：" + buyingInfoBean.getClassificationid() + buyingInfoBean.getClassifictiontype());
                    baseinfo_applicantname.setText("申请人：" + baseinfoBean.getApplicantname());
                    baseinfo_applicantaddress.setText("申请人地址：" + baseinfoBean.getApplicantaddress());
                    baseinfo_proxycompany.setText("代理公司：" + baseinfoBean.getProxycompany());
                    //商标注册信息获取数据
                    BuyingInfoBean.RegisterinfoBean registerinfoBean = buyingInfoBean.getRegisterinfo();
                    registerinfo_applicationdate.setText("申请日期：" + registerinfoBean.getApplicationdate());
                    registerinfo_registerdate.setText("注册日期：" + registerinfoBean.getRegisterdate());
                    registerinfo_preliminarynoticedate.setText("初审公告日期：" + registerinfoBean.getPreliminarynoticedate());
                    //商标服务列表RecyclerView1,adpter数据获取
                    buyingRecylerView1.setLayoutManager(new FullyLinearLayoutManager(getContext()));
                    buyingAdapter1 = new BuyingAdapter1(buyingInfoBean.getServicelist());
                    buyingRecylerView1.setAdapter(buyingAdapter1);
                    /*商标公告与状态
                     商标服务列表RecyclerView1,adpter数据获取
                     "商标无效"是否可见，有数据时不可见，无数据时可见*/
                    buying_recyclerview2.setLayoutManager(new FullyLinearLayoutManager(getContext()));
                    buyingAdapter2 = new BuyingAdapter2(getContext(), buyingInfoBean.getNotice());
                    buying_recyclerview2.setAdapter(buyingAdapter2);
                    if (buyingInfoBean.getNotice() != null && buyingInfoBean.getNotice().size() > 0) {
                        buying_recyclerview2.setAdapter(buyingAdapter2);
                        trademarkuseless.setVisibility(View.GONE);
                    } else {
                        trademarkuseless.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void setSelect(int position) {
        for (int i = 0; i < tvlist.length; i++) {
            tvlist[i].setBackgroundColor(getResources().getColor(R.color.yellow_fdd400));
            if (i == position) {
                tvlist[i].setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.layout_buying;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buying1_tv:
                switch (n) {
                    case 1:
                        showToast("求购详情页的咨询按钮");
                        setSelect(1);
                        break;
                    case 2:
                        showToast("异议详情页的阻止按钮");
                        setSelect(1);
                        break;
                    case 3:
                        showToast("抢注详情页的咨询按钮");
                        setSelect(1);
                        break;
                }
                break;
            case R.id.buying2_tv:
                switch (n) {
                    case 1:
                        showToast("求购详情页的求购按钮");
                        setSelect(0);
                        break;
                    case 2:
                        showToast("异议详情页的求购按钮");
                        setSelect(0);
                        break;
                    case 3:
                        showToast("抢注详情页的抢注按钮");
                        setSelect(0);
                        break;
                }
                break;
        }
    }
}
