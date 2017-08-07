package com.huabiao.aoiin.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterBean;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.picview.BitmapUtil;
import com.huabiao.aoiin.tools.ActivityCollector;
import com.huabiao.aoiin.ui.activity.ShowLargeImageActivity;
import com.huabiao.aoiin.ui.adapter.RegisterDataPreviewAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyGridLayoutManager;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.ClickUtil;
import com.ywy.mylibs.utils.JumpUtils;

import butterknife.Bind;

import static android.R.attr.transitionName;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-04 14:37
 * @description 注册资料预览页面
 */
public class RegisterDataPreviewActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.register_data_preview_tradename_tv)
    TextView tradename_tv;//注册商标
    @Bind(R.id.register_data_preview_tradetype_tv)
    TextView tradetype_tv;//分类

    @Bind(R.id.register_data_preview_classification_rv)
    RecyclerView classification_rv;//注册类别
    private RegisterDataPreviewAdapter mAdapter;

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

    private RegisterCommitBean commitBean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("资料预览");
        setBackEnable();
        commitBean = RegisterCommitBean.getInstance();
        ActivityCollector.addActivity(this);

        //获取基本信息
        RegisterModel.getRegisterData(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    RegisterBean bean = (RegisterBean) mData;
                    tradename_tv.setText("注册商标:" + bean.getLinechart().getTradename());
                    tradetype_tv.setText("分类:" + bean.getLinechart().getTrademarkclassification());
                    linechart.setLineChartBean(bean.getLinechart());
                }
            }
        });

        mAdapter = new RegisterDataPreviewAdapter(this, commitBean.getClaList());
        classification_rv.setLayoutManager(new FullyGridLayoutManager(this, 2));
        classification_rv.setAdapter(mAdapter);

        username_tv.setText("客户姓名:" + commitBean.getUsername());
        userphone_tv.setText("联系电话:" + commitBean.getUserphone());
        contract_address_tv.setText("合同地址:" + commitBean.getContractAddress());
        code_tv.setText("邮政编码:" + commitBean.getCode());

        int person_type = commitBean.getPersonType();
        switch (person_type) {
            case 1:
                person_type_tv.setText("法人或其他组织");
                legal_or_id_tv.setText("法人姓名:" + commitBean.getLegalPersonName());
                break;
            case 2:
                person_type_tv.setText("个体工商户");
                legal_or_id_tv.setText("法人姓名:" + commitBean.getLegalPersonName());
                break;
            case 3:
                person_type_tv.setText("自然人");
                legal_or_id_tv.setText("身份证件文件号码:" + commitBean.getCertificatesID());
                break;
        }
        person_name_tv.setText("申请人姓名:" + commitBean.getPersonName());
        person_address_tv.setText("行政区划:" + commitBean.getCollectAddress());

        trade_logo_iv.setImageBitmap(BitmapUtil.createImageThumbnail(commitBean.getLogoImg()));
        proxy_iv.setImageBitmap(BitmapUtil.createImageThumbnail(commitBean.getProxyImg()));
        business_licence_iv.setImageBitmap(BitmapUtil.createImageThumbnail(commitBean.getBusinessLicenceImg()));
//        BitmapLoader.ins().loadLocalImage(commitBean.getLogoImg(), R.mipmap.ic_launcher, trade_logo_iv, 150, 100);
//        BitmapLoader.ins().loadLocalImage(commitBean.getProxyImg(), R.mipmap.ic_launcher, proxy_iv, 150, 100);
//        BitmapLoader.ins().loadLocalImage(commitBean.getBusinessLicenceImg(), R.mipmap.ic_launcher, business_licence_iv, 200, 100);

        int serviceMode = commitBean.getServiceMode();
        switch (serviceMode) {
            case 1:
                service_mode_tv.setText("普通注册");
                break;
            case 2:
                service_mode_tv.setText("加急注册");
                break;
            case 3:
                service_mode_tv.setText("注册 + 预审 + 退费担保");
                break;
        }

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
                showLargeImg(trade_logo_iv, commitBean.getLogoImg());
                break;
            case R.id.register_data_preview_proxy_iv:
                showLargeImg(proxy_iv, commitBean.getProxyImg());
                break;
            case R.id.register_data_preview_business_licence_iv:
                showLargeImg(business_licence_iv, commitBean.getBusinessLicenceImg());
                break;
            case R.id.register_data_preview_back_tv:
                ClickUtil.onBackClick();
                break;
            case R.id.register_data_preview_commit_tv:
                JumpUtils.startFragmentByName(this, PayInfoDetailFragment.class);
                ActivityCollector.finishAll();
                commitBean.emptyBean();
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

    @Override
    public int getContentLayout() {
        return R.layout.register_data_preview_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
