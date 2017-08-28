package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.tools.DataCleanManager;
import com.huabiao.aoiin.tools.ViewTools;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.CommonDialogUtils;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.wedgit.dialog.CommonLoadingDialog;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-21 09:49
 * @description 设置页面
 */
public class SettingFragment extends BaseFragment {
    @Bind(R.id.setting_log_out)
    TextView setting_log_out;

    @Bind(R.id.setting_ll)
    LinearLayout setting_ll;
    private ViewTools viewTools;

    private String[] text = {"账户安全", "地址管理", "建议反馈", "关于我们", "服务与隐私协议", "清除缓存"};
    TextView clearCacheTv;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("设置");
        setBackEnable();

        viewTools = new ViewTools(getContext());
        setting_ll.removeAllViews();//防止每次进入此页面都动态增加item
        viewTools.addeditview(setting_ll, null, text[0], new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 账户安全
                showToast("账户安全");
            }
        }, true);

        viewTools.addeditview(setting_ll, null, text[1], new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 地址管理
                JumpUtils.startFragmentByName(getContext(), UserAddressListFragment.class);
            }
        }, true);

        viewTools.addeditview(setting_ll, null, text[2], new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 建议反馈
                showToast("建议反馈");
            }
        }, true);

        viewTools.addeditview(setting_ll, null, text[3], new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 关于我们
                showToast("关于我们");
            }
        }, true);

        viewTools.addeditview(setting_ll, null, text[4], new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 服务与隐私协议
                showToast("服务与隐私协议");
            }
        }, true);

        String totalSize = "0(MB)";
        try {
            totalSize = DataCleanManager.getTotalCacheSize(getActivity());
        } catch (Exception e) {
        }
        clearCacheTv = viewTools.addeditview(setting_ll, null, text[5], totalSize, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 清除缓存
                CommonLoadingDialog mLoadingDialog = CommonDialogUtils.showLoadingDialog(getContext(), "正在清除");
                boolean isClear = DataCleanManager.clearAllCache(getActivity());
                if (isClear) {
                    CommonDialogUtils.dismissLoadingDialog(mLoadingDialog);
                    showToast("清理完成");
                    clearCacheTv.setText("0(MB)");
                }
            }
        }, true);

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
