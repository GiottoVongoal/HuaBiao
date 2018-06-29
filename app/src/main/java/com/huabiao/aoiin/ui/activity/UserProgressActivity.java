package com.huabiao.aoiin.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
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
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.StatusBarUtils;
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
    @Bind(R.id.user_progress_tv)
    TextView progress_tv;
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
        //设置状态栏颜色
//        StatusBarUtils.setWindowStatusBarColor(this, R.color.yellow_fdd400);

        getTrademarkList();
    }

    //获取我的商标列表
    private void getTrademarkList() {
        HomeModel.getUserTrademarkProgressListBean(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    UserTrademarkProgressListBean bean = (UserTrademarkProgressListBean) mData;
                    List<TrademarkprogresslistBean> list = bean.getTrademarkprogresslist();
                    toolbar_title.setText(list.get(0).getTrademarkname());//默认显示第一个类别
                    initToolBar(list.get(0).getTrademarkname());
                    initPopMenu(list);
                    initData(list.get(0).getTrademarkid());//根据类别获取商标进度数据
                    //关闭点击事件
//                    toolbar_title.setOnClickListener(UserProgressActivity.this);
                }
            }
        });
    }

    private void initToolBar(String title) {
        //设置Toolbar
        toolbar.setTitle(title);
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
//        mCollapsingToolbarLayout.setTitleEnabled(false);
        mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.LEFT);//设置收缩后标题的位置
        mCollapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);////设置展开后标题的位置
//        mCollapsingToolbarLayout.setTitle("我是大标题");//设置标题的名字
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置展开后标题的颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);//设置收缩后标题的颜色
    }

    private void initData(String trademarkId) {
        //根据商标ID获取商标进度
        HomeModel.getUserProgressList(this, trademarkId, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    bean = (UserProgressListBean) mData;
                    progress_tv.setText(bean.getLatestprogress());
                    progress_tv.setOnClickListener(UserProgressActivity.this);
                    progress_tv.setVisibility(View.INVISIBLE);
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
                                bundle.putString("status", l.get(position).getProgressstatustext());
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
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popMenu.setBackgroundDrawable(dw);
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                toolbar_title.setTextColor(getResources().getColor(R.color.black3));
            }
        });
        popRecyclerView = (RecyclerView) contentView
                .findViewById(R.id.popwin_supplier_list_rv);
//        contentView.findViewById(R.id.popwin_supplier_list_bottom)
//                .setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View arg0) {
//                        popMenu.dismiss();
//                    }
//                });
        popRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> l = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            l.add(list.get(i).getTrademarkname());
        }
        menuAdapter = new UpMenuAdapter(this, l);
        popRecyclerView.setAdapter(menuAdapter);
        menuAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                popMenu.dismiss();
                toolbar_title.setText(list.get(position).getTrademarkname());
                showToast(list.get(position).getTrademarkname());
                initData(list.get(position).getTrademarkid());//根据类别获取商标进度数据
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_progress_toolbar_title:
                //下拉菜单
                getTrademarkList();
                toolbar_title.setTextColor(getResources().getColor(R.color.black3));
                popMenu.showAsDropDown(toolbar_title, 0, 3);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.7f;
                getWindow().setAttributes(lp);
                popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1f;
                        getWindow().setAttributes(lp);
                    }
                });

                break;
            case R.id.user_progress_tv:
                //最新进度
                Bundle bundle = new Bundle();
                bundle.putString("time", bean.getLatestime());
                bundle.putString("status", bean.getLatestprogress());
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
