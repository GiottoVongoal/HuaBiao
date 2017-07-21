package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CheckTypeResult;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.RegisterBean;
import com.huabiao.aoiin.bean.SearchResultUnRegisterCheckBean;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.ui.adapter.SearchResultUnRegisteredAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.CheckEdittextTextWatcher;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyGridLayoutManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-21 15:57
 * @description 默认注册页面
 */
public class RegisterFragment extends BaseFragment {
    @Bind(R.id.register_trade_name_tv)
    TextView trade_name_tv;//注册商标:我是商标名
    @Bind(R.id.register_industry_tv)
    TextView industry_tv;//行业

    @Bind(R.id.register_classification_rv)
    RecyclerView classification_rv;//可注册类别
    private SearchResultUnRegisteredAdapter mAdapter;
    private List<SearchResultUnRegisterCheckBean> list;
    private int Position;
    private CheckTypeResult checkTypeResult;
    private int deep = 2;

    @Bind(R.id.register_line_chart)
    DrawLineChartView register_line_chart;//折线图

    @Bind(R.id.register_trademark_logo_iv)
    ImageView trademark_logo_iv;//商标图样

    //客户联系方式
    @Bind(R.id.register_username_et)
    TextInputLayout username_et;
    @Bind(R.id.register_userphone_et)
    TextInputLayout userphone_et;

    //申请人类型
    @Bind(R.id.register_applicant_type_rg)
    RadioGroup applicant_type_rg;
    @Bind(R.id.register_legal_person_rb)
    RadioButton legal_person_rb;//法人或其他组织
    @Bind(R.id.register_individual_person_rb)
    RadioButton individual_person_rb;//个体工商户
    @Bind(R.id.register_average_person_rb)
    RadioButton average_person_rb;//自然人

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
    private RegisterBean bean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("注册");
        setBackEnable();
        trade_name_tv.setText("注册商标:" + tradename);
        industry_tv.setText("分类:" + industry);
        checkTypeResult = CheckTypeResult.getInstance(deep);
        RegisterModel.getRegisterData(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    bean = (RegisterBean) mData;
                    showData();
                }
            }
        });
        init();

    }

    private void init() {
        userphone_et.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(userphone_et, "请输入正确的手机号!", 1));
        //开启计数(输入框后边有0/11的字数统计)
        userphone_et.setCounterEnabled(true);
        userphone_et.setCounterMaxLength(11);//最大输入限制数(输入框后边有0/11的字数统计)
        //申请人类型
        applicant_type_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                Bundle bundle = new Bundle();
                switch (i) {
                    case R.id.register_legal_person_rb:
                        //法人或其他组织
                        bundle.putInt("type", 1);
                        bundle.putString("title", "法人或其他组织");
                        break;
                    case R.id.register_individual_person_rb:
                        //个体工商户
                        bundle.putInt("type", 2);
                        bundle.putString("title", "个体工商户");
                        break;
                    case R.id.register_average_person_rb:
                        //自然人
                        bundle.putInt("type", 3);
                        bundle.putString("title", "自然人");
                        break;
                }
                JumpUtils.startFragmentByName(getContext(), RegisterApplicantPersonFragment.class, bundle);
            }
        });
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

    }

    private void showData() {
        list = getList(bean.getClassification());
        register_line_chart.setLineChartBean(bean.getLinechart());
        classification_rv.setLayoutManager(new FullyGridLayoutManager(getContext(), 2));
        mAdapter = new SearchResultUnRegisteredAdapter(getContext(), list);
        classification_rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, final int position) {
                Bundle bundle = new Bundle();
                bundle.putString("tradename", tradename);
                bundle.putString("classificationname", list.get(position).getClassificationid() + " - " + list.get(position).getClassificationname());
                bundle.putInt("type", 1);//测试数据变化使用
                JumpUtils.startFragmentByName(getContext(), CheckTypeListFragment.class, bundle);
                Position = position;
            }
        });
    }


    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getActivity().getIntent().getExtras();
        tradename = bundle.getString("tradename");
        industry = bundle.getString("industry");
    }

    @Override
    public void onResume() {
        super.onResume();
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
