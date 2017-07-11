package com.huabiao.aoiin.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.constant.FlagBase;
import com.huabiao.aoiin.ui.adapter.MeRecyclerViewAdapder;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.OnItemClickListener;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
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
    private LinearLayoutManager manager;

    private String[] text = {"我的收藏", "浏览记录", "地址管理", "反馈", "服务与隐私协议"};

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        showView();
        setOnClickListener();
    }

    private void showView() {
        //给RecyclerView设置布局管理器
        manager = new LinearLayoutManager(getContext());
        me_recyclerview.setLayoutManager(manager);
        adapder = new MeRecyclerViewAdapder(getActivity(), text);
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
        //商标注册表单
        CardView me_trademark_registered_form_cv = (CardView) header.findViewById(R.id.me_trademark_registered_form_cv);
        me_trademark_registered_form_cv.setOnClickListener(this);
        //进度提醒
        CardView me_remind_progress_cv = (CardView) header.findViewById(R.id.me_remind_progress_cv);
        me_remind_progress_cv.setOnClickListener(this);
        //我的订单
        CardView me_order_cv = (CardView) header.findViewById(R.id.me_order_cv);
        me_order_cv.setOnClickListener(this);
        adapder.setHeaderView(header);
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
            case R.id.me_trademark_registered_form_cv:
                showToast("商标注册表单");
                break;
            case R.id.me_remind_progress_cv:
                showToast("进度提醒");
                break;
            case R.id.me_order_cv:
                showToast("我的订单");
                break;
        }
    }

    private void setOnClickListener() {
        adapder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                switch (position) {
                    case FlagBase.ME_COLLECTION:
                        // 我的收藏
                        showToast(position + "我的收藏");
                        break;
                    case FlagBase.ME_BROWSE_RECORD:
                        // 浏览记录
                        showToast(position + "浏览记录");
                        break;
                    case FlagBase.ME_ADDRESS_MANAGEMENT:
                        // 地址管理
                        showToast(position + "地址管理");
                        break;
                    case FlagBase.ME_FEEDBACK:
                        // 反馈
                        showToast(position + "反馈");
                        break;
                    case FlagBase.ME_AGREEMENT:
                        // 服务与隐私协议
                        showToast(position + "服务与隐私协议");
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
