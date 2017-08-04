package com.huabiao.aoiin.ui.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.ywy.mylibs.base.BaseFragment;
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
public class UserProgressFragment extends BaseFragment implements View.OnClickListener {
    //下拉筛选菜单
    @Bind(R.id.user_progress_menu_ll)
    LinearLayout user_progress_menu_ll;
    @Bind(R.id.user_progress_menu_tv)
    TextView user_progress_menu_tv;
    private PopupWindow popMenu;
    private RecyclerView popRecyclerView;
    private UpMenuAdapter menuAdapter;

    //展示数据
    @Bind(R.id.user_progress_tv)
    TextView user_progress_tv;
    @Bind(R.id.user_progress_rv)
    RecyclerView user_progress_rv;
    private UserProgressAdapter mAdapter;
    private UserProgressListBean bean;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("进度");
        setBackEnable();
        //获取我的商标列表
        HomeModel.getUserTrademarkProgressListBean(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    UserTrademarkProgressListBean bean = (UserTrademarkProgressListBean) mData;
                    initPopMenu(bean.getTrademarkprogresslist());
                    user_progress_menu_ll.setOnClickListener(UserProgressFragment.this);
                }
            }
        });
    }

    private void initData(String trademarkId) {
        //根据商标ID获取商标进度
        HomeModel.getUserProgressList(getContext(), trademarkId, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    bean = (UserProgressListBean) mData;
                    user_progress_tv.setText(bean.getLatestprogress());
                    user_progress_tv.setOnClickListener(UserProgressFragment.this);
                    user_progress_rv.setLayoutManager(new FullyLinearLayoutManager(getContext()));
                    final List<ProgresslistBean> l = bean.getUserprogresslist();
                    mAdapter = new UserProgressAdapter(getContext(), l);
                    user_progress_rv.setAdapter(mAdapter);
                    mAdapter.setItemClickListener(new InterfaceManager.OnItemClickListener() {
                        @Override
                        public void onItemClickListener(View view, int position) {
                            if (!StringUtil.isEmpty(l.get(position).getProgresstime())) {
                                Bundle bundle = new Bundle();
                                bundle.putString("time", l.get(position).getProgresstime());
                                JumpUtils.startFragmentByName(getContext(), UserProgressDateFragment.class, bundle);
                            }
                        }
                    });
                }
            }
        });
    }

    //下拉筛选菜单相关
    private void initPopMenu(final List<TrademarkprogresslistBean> list) {
        View contentView = View.inflate(getContext(), R.layout.popwin_supplier_list, null);
        popMenu = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                user_progress_menu_tv.setTextColor(getResources().getColor(R.color.black3));
            }
        });
        user_progress_menu_tv.setText(list.get(0).getTrademarkname());//默认显示第一个类别
        initData(list.get(0).getTrademarkid());//根据类别获取商标进度数据
        popRecyclerView = (RecyclerView) contentView
                .findViewById(R.id.popwin_supplier_list_rv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom)
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        popMenu.dismiss();
                    }
                });
        popRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<String> l = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            l.add(list.get(i).getTrademarkname());
        }
        menuAdapter = new UpMenuAdapter(getContext(), l);
        menuAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                popMenu.dismiss();
                user_progress_menu_tv.setText(list.get(position).getTrademarkname());
                showToast(list.get(position).getTrademarkname());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_progress_menu_ll:
                //下拉菜单
                user_progress_menu_tv.setTextColor(getResources().getColor(R.color.black3));
                popRecyclerView.setAdapter(menuAdapter);
                popMenu.showAsDropDown(user_progress_menu_ll, 0, 2);
                break;
            case R.id.user_progress_tv:
                //最新进度
                Bundle bundle = new Bundle();
                bundle.putString("time", bean.getLatestime());
                JumpUtils.startFragmentByName(getContext(), UserProgressDateFragment.class, bundle);
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
