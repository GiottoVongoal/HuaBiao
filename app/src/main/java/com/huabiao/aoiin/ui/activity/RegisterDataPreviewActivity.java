package com.huabiao.aoiin.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterBean;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.bean.TradeFormBean;
import com.huabiao.aoiin.bean.TradeFormBean.FormBean;
import com.huabiao.aoiin.bean.TradeFormBean.FormBean.ImgInfoBean;
import com.huabiao.aoiin.bean.TradeFormBean.FormBean.PersonTypeInfoBean;
import com.huabiao.aoiin.bean.TradeFormBean.FormBean.UserBasicInfoBean;
import com.huabiao.aoiin.model.MeModel;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.tools.ActivityCollector;
import com.huabiao.aoiin.ui.adapter.RegisterDataPreviewAdapter;
import com.huabiao.aoiin.ui.fragment.PayInfoDetailFragment;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.ClickUtil;
import com.ywy.mylibs.utils.JumpUtils;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-04 14:37
 * @description 注册资料预览页面/商标注册表单预览
 */
public class RegisterDataPreviewActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.register_data_preview_tradename_tv)
    TextView tradename_tv;//注册商标
    @Bind(R.id.register_data_preview_tradetype_tv)
    TextView tradetype_tv;//分类

    @Bind(R.id.register_data_preview_classification_rv)
    RecyclerView classification_rv;//注册类别
    private RegisterDataPreviewAdapter mAdapter;
    private FullyLinearLayoutManager layoutManager;

    @Bind(R.id.register_data_preview_linechart)
    DrawLineChartView linechart;//折线图

    @Bind(R.id.register_data_preview_username_tv)
    TextView username_tv;//客户姓名
    @Bind(R.id.register_data_preview_userphone_tv)
    TextView userphone_tv;//联系电话
    @Bind(R.id.register_data_preview_contract_address_tv)
    TextView contract_address_tv;//合同地址
    @Bind(R.id.register_data_preview_code_tv)
    TextView code_tv;//邮政编码

    @Bind(R.id.register_data_preview_person_type_tv)
    TextView person_type_tv;//申请人类型
    @Bind(R.id.register_data_preview_person_name_tv)
    TextView person_name_tv;//申请人姓名
    @Bind(R.id.register_data_preview_legal_or_id_tv)
    TextView legal_or_id_tv;//法人姓名/ID
    @Bind(R.id.register_data_preview_person_address_tv)
    TextView person_address_tv;//地址

    @Bind(R.id.register_data_preview_logo_ll)
    LinearLayout logo_ll;//图片展示Layout
    @Bind(R.id.register_data_preview_trade_logo_iv)
    ImageView trade_logo_iv;//商标图样
    @Bind(R.id.register_data_preview_proxy_iv)
    ImageView proxy_iv;//委托书
    @Bind(R.id.register_data_preview_business_licence_iv)
    ImageView business_licence_iv;//营业执照

    @Bind(R.id.register_data_preview_service_mode_tv)
    TextView service_mode_tv;//服务方式

    @Bind(R.id.register_data_preview_back_tv)
    TextView back_tv;
    @Bind(R.id.register_data_preview_commit_tv)
    TextView commit_tv;
    private TextView[] tvs = new TextView[2];

    private RegisterCommitBean commitBean;
    private FormBean formbean;

    private int index;//1 商标注册表单; 2 注册资料预览
    private String tradeid;// 商标注册表单的ID

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("index", 1);
        tradeid = bundle.getString("tradeid");
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        setBackEnable();
        setRightIvResourse(getResources().getDrawable(R.mipmap.kefu_icon));
        setRightIvOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("客服");
            }
        });

        switch (index) {
            case 1:
                //商标注册表单
                setTitle("商标注册表单预览");
                MeModel.getTradeFormBean(this, tradeid, new InterfaceManager.CallBackCommon() {
                    @Override
                    public void getCallBackCommon(Object mData) {
                        if (mData != null) {
                            TradeFormBean bean = (TradeFormBean) mData;
                            formbean = bean.getTraderegisterform();
                            showFormData(formbean);
                        }
                    }
                });
                break;
            case 2:
                //注册资料预览
                tvs[0] = back_tv;
                tvs[1] = commit_tv;
                setSelect(1);
                setTitle("预览");
                commitBean = RegisterCommitBean.getInstance();
                ActivityCollector.addActivity(this);
                showRegisterData(commitBean);

                break;
        }
        setOnclick();
    }

    /**
     * 商标注册表单预览
     */
    private void showFormData(FormBean bean) {
        tradename_tv.setText(bean.getLinechart().getTradename());
        tradetype_tv.setText(bean.getLinechart().getTrademarkclassification());
        linechart.setLineChartBean(bean.getLinechart());

        mAdapter = new RegisterDataPreviewAdapter(this, bean.getClassification());
        layoutManager = new FullyLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        classification_rv.setLayoutManager(layoutManager);
        classification_rv.setAdapter(mAdapter);

        UserBasicInfoBean userBean = bean.getUserbasicinfo();
        username_tv.setText("客户姓名:" + userBean.getUsername());
        userphone_tv.setText("联系电话:" + userBean.getUserphone());
        contract_address_tv.setText("合同地址:" + userBean.getContractaddress());
        code_tv.setText("邮政编码:" + userBean.getCode());

        PersonTypeInfoBean personBean = bean.getPersontypeinfo();
        int person_type = personBean.getPersontype();
        switch (person_type) {
            case 1:
                person_type_tv.setText("法人或其他组织");
                legal_or_id_tv.setText("法人姓名:" + personBean.getLegalname());
                break;
            case 2:
                person_type_tv.setText("个体工商户");
                legal_or_id_tv.setText("法人姓名:" + personBean.getLegalname());
                break;
            case 3:
                person_type_tv.setText("自然人");
                legal_or_id_tv.setText("身份证件文件号码:" + personBean.getCertificatesid());
                break;
        }
        person_name_tv.setText("申请人姓名:" + personBean.getPersonname());
        person_address_tv.setText("行政区划:" + personBean.getPersonaddress());

        ImgInfoBean imgbean = bean.getImginfo();
        BitmapLoader.ins().loadImage(imgbean.getLogoimg(), R.mipmap.logobg, trade_logo_iv);
        BitmapLoader.ins().loadImage(imgbean.getProxyimg(), R.mipmap.logobg, proxy_iv);
        BitmapLoader.ins().loadImage(imgbean.getBusinesslicenceimg(), R.mipmap.logobg, business_licence_iv);

        int serviceMode = bean.getServicemode();
        switch (serviceMode) {
            case 0:
                service_mode_tv.setText("普通注册");
                break;
            case 1:
                service_mode_tv.setText("加急注册");
                break;
            case 2:
                service_mode_tv.setText("注册 + 预审 + 退费担保");
                break;
        }
        back_tv.setVisibility(View.GONE);
        commit_tv.setText("下载表单");
    }

    /**
     * 注册内容数据预览
     *
     * @param bean
     */
    private void showRegisterData(RegisterCommitBean bean) {
        //获取基本信息
        RegisterModel.getRegisterData(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    RegisterBean bean = (RegisterBean) mData;
                    tradename_tv.setText(bean.getLinechart().getTradename());
                    tradetype_tv.setText(bean.getLinechart().getTrademarkclassification());
                    linechart.setLineChartBean(bean.getLinechart());
                }
            }
        });

        mAdapter = new RegisterDataPreviewAdapter(this, bean.getClaList());
        layoutManager = new FullyLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        classification_rv.setLayoutManager(layoutManager);
        classification_rv.setAdapter(mAdapter);

        username_tv.setText("客户姓名:" + bean.getUsername());
        userphone_tv.setText("联系电话:" + bean.getUserphone());
        contract_address_tv.setText("合同地址:" + bean.getContractAddress());//bean.getContractSelectAddress() +
        code_tv.setText("邮政编码:" + bean.getCode());

        int person_type = bean.getPersonType();
        switch (person_type) {
            case 0:
                person_type_tv.setText("自然人");
//                legal_or_id_tv.setText("法人姓名:" + bean.getLegalPersonName());
                break;
            case 1:
                person_type_tv.setText("个体工商户");
//                legal_or_id_tv.setText("法人姓名:" + bean.getLegalPersonName());
                break;
            case 2:
                person_type_tv.setText("公司或其他组织");
//                legal_or_id_tv.setText("身份证件文件号码:" + bean.getCertificatesID());
                break;
        }
//        person_name_tv.setText("申请人姓名:" + bean.getPersonName());
//        person_address_tv.setText("行政区划:" + bean.getCollectAddress());
        legal_or_id_tv.setVisibility(View.GONE);
        person_name_tv.setVisibility(View.GONE);
        person_address_tv.setVisibility(View.GONE);

//        trade_logo_iv.setImageBitmap(BitmapUtil.createImageThumbnail(bean.getLogoImg()));
//        proxy_iv.setImageBitmap(BitmapUtil.createImageThumbnail(bean.getProxyImg()));
//        business_licence_iv.setImageBitmap(BitmapUtil.createImageThumbnail(bean.getBusinessLicenceImg()));
        logo_ll.setVisibility(View.GONE);

        int serviceMode = bean.getServiceMode();
        switch (serviceMode) {
            case 0:
                service_mode_tv.setText("普通注册");
                break;
            case 1:
                service_mode_tv.setText("加急注册");
                break;
            case 2:
                service_mode_tv.setText("注册 + 预审 + 退费担保");
                break;
        }
        back_tv.setVisibility(View.VISIBLE);
        commit_tv.setText("立即申请");
    }

    private void setOnclick() {
        trade_logo_iv.setOnClickListener(this);
        proxy_iv.setOnClickListener(this);
        business_licence_iv.setOnClickListener(this);

        back_tv.setOnClickListener(this);
        commit_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_data_preview_trade_logo_iv:
                showLargeImg(trade_logo_iv, index == 1 ? formbean.getImginfo().getLogoimg() : "");//commitBean.getLogoImg()
                break;
            case R.id.register_data_preview_proxy_iv:
                showLargeImg(proxy_iv, index == 1 ? formbean.getImginfo().getProxyimg() : "");//commitBean.getProxyImg()
                break;
            case R.id.register_data_preview_business_licence_iv:
                showLargeImg(business_licence_iv, index == 1 ? formbean.getImginfo().getBusinesslicenceimg() : "");//commitBean.getBusinessLicenceImg()
                break;
            case R.id.register_data_preview_back_tv:
                setSelect(0);
                ClickUtil.onBackClick();
                break;
            case R.id.register_data_preview_commit_tv:
                if (index == 1) {
                    showToast("下载表单");
                } else {
                    setSelect(1);
                    JumpUtils.startFragmentByName(this, PayInfoDetailFragment.class);
                    ActivityCollector.finishAll();
                    commitBean.emptyBean();
                }
                break;
        }
    }


    //显示大图
    private void showLargeImg(ImageView iv, String path) {
        Intent i = new Intent(this, ShowLargeImageActivity.class);
        i.putExtra("path", path);
        String transitionName = "square_blue";
        ActivityOptions transitionActivityOptions =
                ActivityOptions.makeSceneTransitionAnimation(this, iv, transitionName);
        startActivity(i, transitionActivityOptions.toBundle());
    }

    //设置最下边按钮点击颜色
    public void setSelect(int position) {
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setBackgroundColor(getResources().getColor(R.color.white));
            if (i == position) {
                tvs[i].setBackgroundColor(getResources().getColor(R.color.yellow_fdd400));
            }
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.register_data_preview_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
