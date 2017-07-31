package com.huabiao.aoiin.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CheckTypeResult;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.ClassificationItemBean;
import com.huabiao.aoiin.bean.RegisterApplicantPersonBean;
import com.huabiao.aoiin.bean.RegisterBean;
import com.huabiao.aoiin.bean.SearchResultUnRegisterCheckBean;
import com.huabiao.aoiin.constant.FlagBase;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.picview.BitmapUtil;
import com.huabiao.aoiin.picview.MediaView;
import com.huabiao.aoiin.ui.adapter.SearchResultUnRegisteredAdapter;
import com.huabiao.aoiin.ui.fragment.CheckTypeListFragment;
import com.huabiao.aoiin.ui.fragment.RegisterApplicantPersonFragment;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.CheckEdittextTextWatcher;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyGridLayoutManager;
import com.huabiao.aoiin.wedgit.SelectPicPopupWindow;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-21 15:57
 * @description 默认注册页面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.register_trade_name_tv)
    TextView trade_name_tv;//注册商标:我是商标名
    @Bind(R.id.register_industry_tv)
    TextView industry_tv;//行业

    @Bind(R.id.register_classification_rv)
    RecyclerView classification_rv;//可注册类别
    //注册第一步跳转过来的
    private SearchResultUnRegisteredAdapter mAdapter;
    private List<SearchResultUnRegisterCheckBean> list;
    private int Position;
    //查询跳转过来的
    @Bind(R.id.register_classification_tv)
    TextView register_classification_tv;//隐藏类别的RecyclerView,显示一个TextView
    @Bind(R.id.register_classification_card)
    CardView register_classification_card;
    private CheckTypeResult checkTypeResult;
    private String selectClassify;//在查询页面选择的分类大类名称
    private List<List<ClassificationItemBean>> typeList;
    private int deep = 2;

    @Bind(R.id.register_line_chart)
    DrawLineChartView register_line_chart;//折线图

    @Bind(R.id.register_trademark_logo_iv)
    ImageView trademark_logo_iv;//商标图样
    //图片相关
    private SelectPicPopupWindow picWindow;
    private String Path = FlagBase.SDCARD_FJ_PATH;// 附件存放的路径
    private MediaView mMediaView;// 多媒体处理类
    private String folderName;// 文件夹名

    //客户联系方式
    @Bind(R.id.register_username_et)
    TextInputLayout username_et;
    @Bind(R.id.register_userphone_et)
    TextInputLayout userphone_et;

    //申请人类型
    @Bind(R.id.register_legal_person_tv)
    TextView legal_person_tv;//法人或其他组织
    @Bind(R.id.register_individual_person_tv)
    TextView individual_person_tv;//个体工商户
    @Bind(R.id.register_average_person_tv)
    TextView average_person_tv;//自然人
    private RegisterApplicantPersonBean personBean;
    private TextView[] AppPersonTV = new TextView[3];

    //服务方式
    @Bind(R.id.register_service_mode_rg)
    RadioGroup service_mode_rg;
    @Bind(R.id.register_general_register_rb)
    RadioButton general_register_rb;//普通注册
    @Bind(R.id.register_urgent_register_rb)
    RadioButton urgent_register_rb;//加急注册
    @Bind(R.id.register_rate100_register_rb)
    RadioButton rate100_register_rb;//成功率100%

    @Bind(R.id.register_commit_tv)
    TextView commit_tv;//提交注册

    private String tradename, industry;
    private int pageIndex;//1查询;2注册
    private RegisterBean bean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("注册");
        setBackEnable();
        personBean = RegisterApplicantPersonBean.getInstance();
        AppPersonTV[0] = legal_person_tv;
        AppPersonTV[1] = individual_person_tv;
        AppPersonTV[2] = average_person_tv;
        trade_name_tv.setText("注册商标:" + tradename);
        industry_tv.setText("分类:" + (StringUtil.isEmpty(industry) ? "无" : industry));
        checkTypeResult = CheckTypeResult.getInstance(deep);
        mMediaView = MediaView.getInstance(this);
        folderName = mMediaView.getFolderName();
        if (pageIndex == 1) {
            //查询跳转过来的(获取查询页面选择的结果)
            typeList = new ArrayList<>();
            for (int i = 0; i < deep; i++) {
                typeList.add(new ArrayList<ClassificationItemBean>());
            }
            typeList = checkTypeResult.getSelectList();
        }
        ALog.i("typeList-->" + typeList);
        RegisterModel.getRegisterData(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    bean = (RegisterBean) mData;
                    showData();
                }
            }
        });
        initEdit();
    }

    private void initEdit() {
        userphone_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(userphone_et, "请输入正确的手机号!", 1));
        userphone_et.setCounterEnabled(true);
        userphone_et.setCounterMaxLength(11);//最大输入限制数(输入框后边有0/11的字数统计)
        //商标图标
        trademark_logo_iv.setOnClickListener(this);
        //申请人类型
        legal_person_tv.setOnClickListener(RegisterActivity.this);
        individual_person_tv.setOnClickListener(RegisterActivity.this);
        average_person_tv.setOnClickListener(this);
        //服务方式
        service_mode_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.register_general_register_rb:
                        //普通注册
                        showToast("普通注册");
                        break;
                    case R.id.register_urgent_register_rb:
                        //加急注册
                        showToast("加急注册");
                        break;
                    case R.id.register_rate100_register_rb:
                        //成功率100%
                        showToast("成功率100%");
                        break;
                }
            }
        });
        //提交
        commit_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.register_trademark_logo_iv:
                //商标图标
                selectPic(view);
                break;
            case R.id.register_legal_person_tv:
                //法人或其他组织
                bundle.putInt("type", 1);
                bundle.putString("title", "法人或其他组织");
                JumpUtils.startFragmentByName(this, RegisterApplicantPersonFragment.class, bundle);
                break;
            case R.id.register_individual_person_tv:
                //个体工商户
                bundle.putInt("type", 2);
                bundle.putString("title", "个体工商户");
                JumpUtils.startFragmentByName(this, RegisterApplicantPersonFragment.class, bundle);
                break;
            case R.id.register_average_person_tv:
                //自然人
                bundle.putInt("type", 3);
                bundle.putString("title", "自然人");
                JumpUtils.startFragmentByName(this, RegisterApplicantPersonFragment.class, bundle);
                break;
            case R.id.register_commit_tv:
                //提交
                commit();
                break;
        }
    }

    private void commit() {
        //上传商标logo图片
        File file = new File(Path + "trademark_logo.jpg");
        if (!file.exists()) {
            //不存在這個文件
            return;
        }
        String tagName = "";
        String tagType = "";
        Map<String, File> files = new HashMap<String, File>();
        files.put(file.getName(), file);
    }

    private void selectPic(View view) {
        picWindow = new SelectPicPopupWindow(this, itemsOnClick);
        picWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    // 为弹出窗口实现监听类
    private final View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            picWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_take_photo:
                    //申请动态权限
                    //第二个参数是需要申请的权限
                    if (ContextCompat.checkSelfPermission(RegisterActivity.this
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        //权限还没有授予，需要在这里写申请权限的代码
                        ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 7);
                    } else {
                        //权限已经被授予，在这里直接写要执行的相应方法即可
                        mMediaView.takePhoto(folderName, "trademark_logo", FlagBase.MEDIA_PHOTO);
                    }
                    break;
                case R.id.btn_pick_photo:
                    mMediaView.selectPhoto(FlagBase.MEDIA_SPHOTO);
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FlagBase.MEDIA_PHOTO:// 拍照
                String filePath = Path + folderName + File.separator + "trademark_logo.jpg";
                ALog.i("filePath = " + filePath);
                File file = new File(filePath);
                if (file.exists()) {
                    Bitmap mBitmap = BitmapUtil.createImageThumbnail(filePath);
                    int degree = mMediaView.loadImageDegree(filePath);
                    Bitmap bitmap = mMediaView.rotateBitmap(mBitmap, degree);
                    mMediaView.saveBitmap(bitmap, filePath);
                    trademark_logo_iv.setImageBitmap(bitmap);
                }
                break;
            case FlagBase.MEDIA_SPHOTO:// 选照
                if (data != null && data.getData() != null) {
                    Bitmap bitmap = mMediaView.selectPhotoSave(data, folderName, "trademark_logo");
                    trademark_logo_iv.setImageBitmap(bitmap);
                }
                break;
        }
    }

    private void showData() {
        classification_rv.setLayoutManager(new FullyGridLayoutManager(this, 2));
        if (pageIndex == 2) {
            //注册第一步跳转过来的
            list = getList(bean.getClassification());
            mAdapter = new SearchResultUnRegisteredAdapter(this, list);
            classification_rv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
                @Override
                public void onItemClickListener(View view, final int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("tradename", tradename);
                    bundle.putString("classificationname", list.get(position).getClassificationid() + " - " + list.get(position).getClassificationname());
                    bundle.putInt("type", 1);//测试数据变化使用
                    JumpUtils.startFragmentByName(RegisterActivity.this, CheckTypeListFragment.class, bundle);
                    Position = position;
                }
            });
        } else {
            //查询跳转过来的
            classification_rv.setVisibility(View.GONE);
            register_classification_card.setVisibility(View.VISIBLE);
            register_classification_tv.setText(selectClassify);
        }
        //折线图
        register_line_chart.setLineChartBean(bean.getLinechart());
    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getIntent().getExtras();
        tradename = bundle.getString("tradename");
        industry = bundle.getString("industry");
        pageIndex = bundle.getInt("pageIndex", 2);//1查询;2注册第一步
        selectClassify = bundle.getString("selectClassify");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (pageIndex == 2) {
            if (checkTypeResult.isChange()) {
                //判断是否有选择注册分类
                for (int i = 0; i < list.size(); i++) {
                    if (i == Position)
                        list.get(i).setCheck(true);
                    else
                        list.get(i).setCheck(false);
                }
                mAdapter.updateListView(list);
            }
        }
        setAppPersonSelect(personBean.getChangeType() - 1);
        ALog.i("personBean-->" + personBean);
    }

    private void setAppPersonSelect(int position) {
        for (int i = 0; i < AppPersonTV.length; i++) {
            AppPersonTV[i].setBackgroundResource(R.drawable.g_white_gray);
            if (i == position) {
                AppPersonTV[i].setBackground(getResources().getDrawable(R.drawable.g_white_white));
            }
        }
    }

    private List<SearchResultUnRegisterCheckBean> getList(List<ClassificationBean> list) {
        List<SearchResultUnRegisterCheckBean> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SearchResultUnRegisterCheckBean bean = new SearchResultUnRegisterCheckBean();
            bean.setClassificationid(list.get(i).getClassificationid());
            bean.setClassificationname(list.get(i).getClassificationname());
            bean.setCheck(false);
            resultList.add(bean);
        }
        return resultList;
    }


    @Override
    public int getContentLayout() {
        return R.layout.register_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
