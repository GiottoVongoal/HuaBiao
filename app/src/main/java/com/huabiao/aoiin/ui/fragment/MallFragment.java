package com.huabiao.aoiin.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MallBean;
import com.huabiao.aoiin.bean.ScreenclassficationBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.MallAdapter;
import com.huabiao.aoiin.ui.adapter.UpMenuAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.recycler.XRecyclerView;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 商城列表
 */
public class MallFragment extends BaseFragment implements View.OnClickListener {
    private MallAdapter mallAapter;
    //将下划线放入一个数组
    View[] lines = new View[4];
    //当前页面的页数
    private int page = 1;
    //m是传的标志位，1234代表的是全部、可求购、可抢注、可异议的页面
    private int m = 1;
    @Bind(R.id.Mall_listView)
    XRecyclerView mall_listview;
    //可求购按钮and line
    @Bind(R.id.mall_canbuy_tv)
    TextView mall_canbuy_tv;
    @Bind(R.id.mall_canbuy_tv_line)
    View mall_canbuy_tv_line;
    //可异议按钮 and line
    @Bind(R.id.mall_canyiyi_tv)
    TextView mall_canyiyi_tv;
    @Bind(R.id.mall_canyiyi_tv_line)
    View mall_canyiyi_tv_line;
    //可抢注按钮 and line
    @Bind(R.id.mall_cancybersquatting_tv)
    TextView mall_cancybersquatting_tv;
    @Bind(R.id.mall_cancybersquatting_tv_line)
    View mall_cancybersquatting_tv_line;
    //全部 and line
    @Bind(R.id.mall_all_tv)
    TextView mall_all_tv;
    @Bind(R.id.mall_all_tv_line)
    View mall_all_tv_line;
    //输入框
    @Bind(R.id.mall_search_tv)
    TextView mall_search_tv;
    //筛选图片
    @Bind(R.id.mall_img)
    ImageView mall_img;
    //筛选下拉菜单
    private PopupWindow popupWindowshaixuan;
    //下拉筛选菜单的Adapter
    private UpMenuAdapter menuAdapter;
    private RecyclerView popRecyclerView;

    @Override
    public void bindView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getList(page, m);
        //五个按钮以及筛选图片按钮的的监听事件
        mall_cancybersquatting_tv.setOnClickListener(this);
        mall_canbuy_tv.setOnClickListener(this);
        mall_all_tv.setOnClickListener(this);
        mall_canyiyi_tv.setOnClickListener(this);
        mall_search_tv.setOnClickListener(this);
        mall_img.setOnClickListener(this);
        mall_listview.setLayoutManager(new LinearLayoutManager(getContext()));

        mall_listview.setPullRefreshEnabled(false);
        mall_listview.setLoadingMoreEnabled(false);
    }


    //下拉筛选
    private void initShaixuan(View view, final List<ScreenclassficationBean.SlistBean> list) {
        View contentView = view.inflate(getContext(), R.layout.layout_shaixuan, null);

        final PopupWindow popupWindowshaixuan = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindowshaixuan.setOutsideTouchable(true);
        popupWindowshaixuan.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.menu_bg));
        popupWindowshaixuan.setFocusable(true);
//        popupWindowshaixuan.setAnimationStyle(R.style.popwin_anim_style);
        popupWindowshaixuan.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
            }
        });

        popRecyclerView = (RecyclerView) contentView
                .findViewById(R.id.popwin_shaixuan_list_rv);
        contentView.findViewById(R.id.popwin_shaixuan_list_bottom)
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        popupWindowshaixuan.dismiss();
                    }
                });
        popRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<String> l = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            l.add(list.get(i).getClassificationname());
        }
        menuAdapter = new UpMenuAdapter(getContext(), l);
        menuAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                showToast(list.get(position).getClassificationname());
                popupWindowshaixuan.dismiss();
            }
        });
        //窗口显示方式
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindowshaixuan.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] - popupWindowshaixuan.getHeight());
        //在控件的下方弹出窗口
        popupWindowshaixuan.showAsDropDown(view);
    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mall_search_tv.setText(bundle.getString("search"));
        }
    }

    /**
     * 跳转到商城页面
     *
     * @param msg
     */
    public void SearchMallEvent(String msg) {
        if (mall_search_tv != null)
            mall_search_tv.setText(msg);
    }

    private void getList(final int page, int m) {
        SearchModel.getShoppingMallList(getContext(), m, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    MallBean mallBean = (MallBean) mData;
                    if (page == 1) {
                        mallAapter = new MallAdapter(getContext(), mallBean.getShoppingmalllist());
                        mall_listview.setAdapter(mallAapter);
                    } else {
                        mallAapter.addList(mallBean.getShoppingmalllist());
                    }
                }
            }
        });
        //设置线的显示隐藏
        lines[0] = mall_all_tv_line;
        lines[1] = mall_canbuy_tv_line;
        lines[2] = mall_cancybersquatting_tv_line;
        lines[3] = mall_canyiyi_tv_line;
        for (int i = 0; i < lines.length; i++) {
            lines[i].setVisibility(View.INVISIBLE);
            if (i == (m - 1)) {
                lines[i].setVisibility(View.VISIBLE);
            }
        }
    }

    //五个按钮的监听事件实例化，点击显示不同页面，解析不同的json.
    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.mall_canbuy_tv:
                m = 2;
                page = 1;
                getList(page, m);
                break;
            case R.id.mall_cancybersquatting_tv:
                m = 3;
                page = 1;
                getList(page, m);
                break;
            case R.id.mall_canyiyi_tv:
                m = 4;
                page = 1;
                getList(page, m);
                break;
            case R.id.mall_all_tv:
                m = 1;
                page = 1;
                getList(page, m);
                break;
            case R.id.mall_img:
                showToast("筛选");
//                SearchModel.getScreenclassfication(getContext(), new InterfaceManager.CallBackCommon() {
//                    @Override
//                    public void getCallBackCommon(Object mData) {
//                        if (mData != null) {
//                            ScreenclassficationBean bean = (ScreenclassficationBean) mData;
//                            initShaixuan(view, bean.getSlist());
//                        }
//                    }
//                });
//                popRecyclerView.setAdapter(menuAdapter);
                break;
            case R.id.mall_search_tv:
                //输入框
                JumpUtils.startFragmentByName(getContext(), SearchMallFragment.class);
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_mall;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
