package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterOneIndustryBean;
import com.huabiao.aoiin.model.RegisterModel;
import com.huabiao.aoiin.ui.activity.RegisterActivity;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.OnItemClickListener;
import com.huabiao.aoiin.wedgit.RegisterOneFinishPopupWindow;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.KeyboardUtils;

import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-12 09:36
 * @description Tab中的注册输入页面
 */
public class RegisterOneFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.register_one_tradename_et)
    TextInputLayout tradename_et;//商标名
    @Bind(R.id.register_one_goodsname_et)
    TextInputLayout goodsname_et;//商品名
    @Bind(R.id.register_one_register_tv)
    FloatingActionButton register_tv;//注册按钮
    private String tradename = "", goodsname = "";

    //选择客服弹出框
    private RegisterOneFinishPopupWindow finishPopupWindow;

    @Bind(R.id.btn_left)
    TextView btn_left;
    @Bind(R.id.btn_right)
    TextView btn_right;
    private boolean isOpen = false;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        tradename_et.getEditText().setText("我是商标名(注册)");
        goodsname_et.getEditText().setText("我是商品名(注册)");
        register_tv.setOnClickListener(this);
        hide(btn_left);
        hide(btn_right);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.register_one_register_tv:
                //注册按钮
                KeyboardUtils.hideSoftInput(getActivity());
                tradename = tradename_et.getEditText().getText().toString();
                goodsname = goodsname_et.getEditText().getText().toString();
                if (TextUtils.isEmpty(tradename)) {
                    showToast("请输入商标名");
                    return;
                }
                if (TextUtils.isEmpty(goodsname)) {
                    showToast("请输入商品名");
                    return;
                }
                showFinishPopupWindow(view);
//                if (isOpen) {
//                    hide(btn_left);
//                    hide(btn_right);
//                    isOpen = false;
//                } else {
//                    show(btn_left, 2, -300);
//                    show(btn_right, 4, -300);
//                    isOpen = true;
//                }
                break;
        }
    }

    //选择客服弹出框
    private void showFinishPopupWindow(View view) {
        finishPopupWindow = new RegisterOneFinishPopupWindow(getContext(), new RegisterOneFinishPopupWindow.DialogClickListener() {
            @Override
            public void selectDefault() {
                showToast("默认注册\n" + tradename + "\n" + goodsname);
                JumpUtils.startActivity(getContext(), RegisterActivity.class);
                finishPopupWindow.dismiss();
            }

            @Override
            public void selectRecommand() {
                showToast("客服推荐\n" + tradename + "\n" + goodsname);
                JumpUtils.startFragmentByName(getContext(), CustomerServiceListFragment.class);
                finishPopupWindow.dismiss();
            }
        });
        finishPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private final void hide(final View child) {
        child.animate()
                .setDuration(400)
                .translationX(0)
                .translationY(0)
                .alpha(0)
                .start();
    }

    private final void show(final View child, final int position, final int radius) {
        float angleDeg = 180.f;
        int dist = radius;
        switch (position) {
            case 1:
                angleDeg += 0.f;
                break;
            case 2:
                angleDeg += 45.f;
                break;
            case 3:
                angleDeg += 90.f;
                break;
            case 4:
                angleDeg += 135.f;
                break;
            case 5:
                angleDeg += 180.f;
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                break;
        }

        final float angleRad = (float) (angleDeg * Math.PI / 180.f);

        final Float x = dist * (float) Math.cos(angleRad);
        final Float y = dist * (float) Math.sin(angleRad);
        child.animate()
                .setDuration(400)
                .translationX(x)
                .translationY(y)
                .alpha(100)
                .start();
    }

    @Override
    public int getContentLayout() {
        return R.layout.register_one_layout;
    }
}
