package com.huabiao.aoiin.ui.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.RegisterBean;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.bean.ScreenclassficationBean;
import com.huabiao.aoiin.bean.ScreenclassficationBean.SlistBean;
import com.huabiao.aoiin.bean.SelectClassificationCheckBean;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.tools.ActivityCollector;
import com.huabiao.aoiin.ui.adapter.RegisterCardOneAdapter;
import com.huabiao.aoiin.ui.adapter.UpMenuAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
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

    @Bind(R.id.register_card_one_select_tradetype_tv)
    TextView select_tradetype_tv;//按类筛选
    private PopupWindow popMenu;
    private RecyclerView popRecyclerView;
    private UpMenuAdapter menuAdapter;

    @Bind(R.id.register_card_one_classification_rv)
    RecyclerView classification_rv;
    RegisterCardOneAdapter adapter;
    List<SelectClassificationCheckBean> list;

    @Bind(R.id.register_card_one_linechart)
    DrawLineChartView linechart;//折线图

    @Bind(R.id.register_card_one_next_tv)
    TextView next_tv;//确定

    private RegisterCommitBean commitBean;
    private int flag = 0;

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

        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        classification_rv.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        adapter = new RegisterCardOneAdapter(this, list);
        classification_rv.setAdapter(adapter);

        commitBean = RegisterCommitBean.getInstance();
        select_tradetype_tv.setOnClickListener(this);
        next_tv.setOnClickListener(this);

        getClassification();
    }

    /**
     * 获取可注册类别列表
     */
    private void getClassification() {
        SearchModel.getScreenclassfication(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    ScreenclassficationBean bean = (ScreenclassficationBean) mData;
                    List<SlistBean> list = bean.getSlist();
                    initPopMenu(list);
                    if (flag == 0) {
                        tradetype_tv.setText(list.get(0).getClassificationid() + "-" + list.get(0).getClassificationname());//默认显示第一个类别
                        getShowData(list.get(0).getClassificationid());//根据类别获取可注册小项
                        flag++;
                    }
                }
            }
        });
    }

    //根据注册类别获取注册小项

    private void getShowData(String classificationid) {
        RegisterModel.getRegisterData(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    RegisterBean bean = (RegisterBean) mData;
                    tradename_tv.setText(bean.getLinechart().getTradename());
//                    tradetype_tv.setText(bean.getLinechart().getClassificationid() + "-" + bean.getLinechart().getTrademarkclassification());
                    linechart.setLineChartBean(bean.getLinechart());

                    list = getList(bean.getClassification());
                    adapter.update(list);

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
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_card_one_select_tradetype_tv:
                //按类筛选
                getClassification();
                popMenu.showAsDropDown(view);
                break;
            case R.id.register_card_one_next_tv:
                //下一步
//                AppBus.getInstance().post(produceChangeIndex());
                save();
                break;
        }
    }

    //保存选择数据
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
        if (result.size() > 0) {
            commitBean.setClaList(result);
            JumpUtils.startActivity(this, RegisterTwoActivity.class);
        } else {
            showToast("请选择分类");
        }
    }

    //下拉筛选菜单相关
    private void initPopMenu(final List<SlistBean> list) {
        View contentView = View.inflate(this, R.layout.popwin_supplier_list, null);
        popMenu = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
//                tradetype_tv.setTextColor(getResources().getColor(R.color.black3));
            }
        });
        popRecyclerView = (RecyclerView) contentView
                .findViewById(R.id.popwin_supplier_list_rv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom)
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        popMenu.dismiss();
                    }
                });
        popRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> l = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            l.add(list.get(i).getClassificationid() + "-" + list.get(i).getClassificationname());
        }
        menuAdapter = new UpMenuAdapter(this, l);
        popRecyclerView.setAdapter(menuAdapter);
        menuAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                popMenu.dismiss();
                tradetype_tv.setText(list.get(position).getClassificationid() + "-" + list.get(position).getClassificationname());
                if (commitBean.getClaList().size() > 0) {
                    commitBean.getClaList().clear();
                }
                getShowData(list.get(position).getClassificationid());//根据类别获取可注册小项
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
