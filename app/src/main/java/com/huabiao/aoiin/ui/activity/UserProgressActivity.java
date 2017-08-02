package com.huabiao.aoiin.ui.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.UserProgressListBean;
import com.huabiao.aoiin.bean.UserProgressListBean.ProgresslistBean;
import com.huabiao.aoiin.bean.UserTrademarkProgressListBean;
import com.huabiao.aoiin.bean.UserTrademarkProgressListBean.TrademarkprogresslistBean;
import com.huabiao.aoiin.model.HomeModel;
import com.huabiao.aoiin.ui.adapter.UpMenuAdapter;
import com.huabiao.aoiin.ui.adapter.UserProgressAdapter;
import com.huabiao.aoiin.ui.fragment.UserProgressDateFragment;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.ColorArcProgressBar;
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-26 11:13
 * @description 进度页面
 */
public class UserProgressActivity extends BaseActivity implements View.OnClickListener {
    //下拉筛选菜单
//    @Bind(R.id.user_progress_menu_ll)
//    LinearLayout user_progress_menu_ll;
//    @Bind(R.id.user_progress_menu_tv)
//    TextView user_progress_menu_tv;
    private PopupWindow popMenu;
    private RecyclerView popRecyclerView;
    private UpMenuAdapter menuAdapter;

    //收缩布局
    @Bind(R.id.user_progress_toolbar)
    Toolbar toolbar;
    @Bind(R.id.user_progress_toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.user_progress_collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    //展示数据
    @Bind(R.id.user_progress_circle_bar)
    ColorArcProgressBar circle_bar;
    @Bind(R.id.user_progress_rv)
    RecyclerView user_progress_rv;
    private UserProgressAdapter mAdapter;
    private UserProgressListBean bean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //设置Toolbar
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//决定左上角的图标是否可以点击
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//决定左上角图标的右侧是否有向左的小箭头
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //设置标题
        mCollapsingToolbarLayout.setTitleEnabled(false);

        //获取我的商标列表
        HomeModel.getUserTrademarkProgressListBean(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    UserTrademarkProgressListBean bean = (UserTrademarkProgressListBean) mData;
                    initPopMenu(bean.getTrademarkprogresslist());
                    toolbar_title.setOnClickListener(UserProgressActivity.this);
                }
            }
        });
    }

    private void initData(String trademarkId) {
        //根据商标ID获取商标进度
        HomeModel.getUserProgressList(this, trademarkId, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    bean = (UserProgressListBean) mData;
                    circle_bar.setCurrentValues(100);
                    circle_bar.setContent(bean.getLatestprogress());
                    circle_bar.setOnClickListener(UserProgressActivity.this);
                    user_progress_rv.setLayoutManager(new FullyLinearLayoutManager(UserProgressActivity.this));
                    final List<ProgresslistBean> l = bean.getUserprogresslist();
                    mAdapter = new UserProgressAdapter(UserProgressActivity.this, l);
                    user_progress_rv.setAdapter(mAdapter);
                    mAdapter.setItemClickListener(new InterfaceManager.OnItemClickListener() {
                        @Override
                        public void onItemClickListener(View view, int position) {
                            if (!StringUtil.isEmpty(l.get(position).getProgresstime())) {
                                Bundle bundle = new Bundle();
                                bundle.putString("time", l.get(position).getProgresstime());
                                JumpUtils.startFragmentByName(UserProgressActivity.this, UserProgressDateFragment.class, bundle);
                            }
                        }
                    });
                }
            }
        });
    }

    //下拉筛选菜单相关
    private void initPopMenu(final List<TrademarkprogresslistBean> list) {
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
                toolbar_title.setTextColor(getResources().getColor(R.color.black3));
            }
        });
        toolbar_title.setText(list.get(0).getTrademarkname());//默认显示第一个类别
        initData(list.get(0).getTrademarkid());//根据类别获取商标进度数据
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
            l.add(list.get(i).getTrademarkname());
        }
        menuAdapter = new UpMenuAdapter(this, l);
        menuAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                popMenu.dismiss();
                toolbar_title.setText(list.get(position).getTrademarkname());
                showToast(list.get(position).getTrademarkname());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_progress_toolbar_title:
            //下拉菜单
                toolbar_title.setTextColor(getResources().getColor(R.color.black3));
                popRecyclerView.setAdapter(menuAdapter);
                popMenu.showAsDropDown(toolbar_title, 0, 2);
                break;
            case R.id.user_progress_circle_bar:
                //最新进度
                Bundle bundle = new Bundle();
                bundle.putString("time", bean.getLatestime());
                JumpUtils.startFragmentByName(this, UserProgressDateFragment.class, bundle);
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.user_progress_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}