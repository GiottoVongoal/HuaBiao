package com.huabiao.aoiin.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.constant.FlagBase;
import com.huabiao.aoiin.model.MeRecyclerViewBean;
import com.huabiao.aoiin.ui.adapter.MeRecyclerViewAdapder;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.OnItemClickListener;
import com.huabiao.aoiin.wedgit.SpacesItemDecoration;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.StringUtil;
import com.ywy.mylibs.wedgit.wedgit.CircleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-06-05 18:48
 * @description 我的页面
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.me_recyclerview)
    RecyclerView me_recyclerview;
    private MeRecyclerViewAdapder adapder;
    private GridLayoutManager manager;

    private List<MeRecyclerViewBean> list;
    private String[] text = {"注册前查询", "商标注册", "LOGO版权", "商标购买", "国际商标注册", "查专利能否申请", "商标驳回复审", "商标侵权检测"};
    private int[] img = {R.mipmap.v2_shouye_zc, R.mipmap.v2_shouye_sbzc, R.mipmap.v2_shouye_bqdj, R.mipmap.v2_shouye_buy
            , R.mipmap.v2_shouye_gjzc, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        initList();
        showView();
        setOnClickListener();
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            MeRecyclerViewBean bean = new MeRecyclerViewBean();
            bean.setImg(img[i]);
            bean.setText(text[i]);
            bean.setFlag(i + 1);
            list.add(bean);
        }
    }

    private void showView() {
        //给RecyclerView设置布局管理器
        manager = new GridLayoutManager(getContext(), 3);
        me_recyclerview.setLayoutManager(manager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.x2);
        me_recyclerview.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        adapder = new MeRecyclerViewAdapder(getActivity(), list);
        me_recyclerview.setAdapter(adapder);
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.me_head_layout, me_recyclerview, false);
        //设置
        ImageView me_setting = (ImageView) header.findViewById(R.id.me_setting);
        me_setting.setOnClickListener(this);
        //用户头像
        CircleView me_user_photo = (CircleView) header.findViewById(R.id.me_user_photo);
        me_user_photo.setOnClickListener(this);
        //用户姓名
        TextView me_user_name = (TextView) header.findViewById(R.id.me_user_name);
        me_user_name.setText("我是用户名");
        //进度提醒
        LinearLayout me_progress_reminder = (LinearLayout) header.findViewById(R.id.me_progress_reminder);
        me_progress_reminder.setOnClickListener(this);
        //我的消息
        LinearLayout me_my_news = (LinearLayout) header.findViewById(R.id.me_my_news);
        me_my_news.setOnClickListener(this);
        adapder.setHeaderView(header);
        StringUtil.isEmpty("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_setting:
                showToast("设置");
                break;
            case R.id.me_user_photo:
                showToast("头像");
                break;
            case R.id.me_progress_reminder:
                showToast("进度提醒");
                break;
            case R.id.me_my_news:
                showToast("我的消息");
                break;
        }
    }

    private void setOnClickListener() {
        adapder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                switch (position) {
                    case FlagBase.ME_TRADEMARK_SEARCH:
                        // 注册前查询
                        showToast(position + "");
                        break;
                    case FlagBase.ME_TRADEMARK_REGISTRATION:
                        // 商标注册
                        showToast(position + "");
                        break;
                    case FlagBase.ME_LOGG_COPYRIGHT:
                        // LOGO版权
                        showToast(position + "");
                        break;
                    case FlagBase.ME_BUG_TRADEMARK:
                        // 商标购买
                        showToast(position + "");
                        break;
                    case FlagBase.ME_INTERMARK_REGISTRATION:
                        // 国际商标注册
                        showToast(position + "");
                        break;
                    case FlagBase.ME_IS_APPLY_FOR_A_PATENT:
                        // 查专利能否申请
                        showToast(position + "");
                        break;
                    case FlagBase.ME_TRADEMARK_REVIEW:
                        // 商标驳回复审
                        showToast(position + "");
                        break;
                    case FlagBase.ME_TRADEMARK_TORT:
                        // 商标侵权检测
                        showToast(position + "");
                        break;
                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.me_layout;
    }
}
