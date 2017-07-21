package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.constant.FlagBase;
import com.huabiao.aoiin.ui.adapter.SettingRecyclerViewAdapder;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-21 09:49
 * @description 设置页面
 */
public class SettingFragment extends BaseFragment {
    @Bind(R.id.setting_rv)
    RecyclerView setting_rv;
    @Bind(R.id.setting_log_out)
    TextView setting_log_out;

    private SettingRecyclerViewAdapder adapder;
    private String[] text = {"账户安全", "地址管理", "建议反馈", "关于我们", "服务与隐私协议", "清除缓存"};

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("设置");
        setBackEnable();
        setting_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapder = new SettingRecyclerViewAdapder(getActivity(), text);
        setting_rv.setAdapter(adapder);
        setOnClickListener();
    }

    private void setOnClickListener() {
        adapder.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                switch (position) {
                    case FlagBase.SETTING_ACCOUNT_SAFE:
                        // 账户安全
                        showToast(position + "账户安全");
                        break;
                    case FlagBase.SETTING_ADDRESS:
                        // 地址管理
                        showToast(position + "地址管理");
                        break;
                    case FlagBase.SETTING_FEEDBACK:
                        // 建议反馈
                        showToast(position + "建议反馈");
                        break;
                    case FlagBase.SETTING_ABOUT_US:
                        // 关于我们
                        showToast(position + "关于我们");
                        break;
                    case FlagBase.SETTING__AGREEMENT:
                        // 服务与隐私协议
                        showToast(position + "服务与隐私协议");
                        break;
                    case FlagBase.SETTING_CLEAR_CACHE:
                        // 清除缓存
                        showToast(position + "清除缓存");
                        break;
                }
            }
        });
        setting_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("退出登录");
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.setting_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
