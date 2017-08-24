package com.huabiao.aoiin.ui.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.tools.ViewTools;
import com.huabiao.aoiin.ui.activity.LoginActivity;
import com.huabiao.aoiin.ui.activity.UserProgressActivity;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.wedgit.wedgit.CircleView;

import butterknife.Bind;

import static com.huabiao.aoiin.R.id.me_trademark_registered_form_cv;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-06-05 18:48
 * @description 我的页面
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.me_user_info_ll)
    LinearLayout me_user_info_ll;
    @Bind(R.id.me_user_photo)
    CircleView me_user_photo;//用户头像
    @Bind(R.id.me_user_name)
    TextView me_user_name;//用户姓名
    @Bind(R.id.me_trademark_registered_form_cv)
    LinearLayout registered_form_cv;//商标注册表单
    @Bind(R.id.me_remind_progress_cv)
    LinearLayout me_remind_progress_cv;//进度提醒
    @Bind(R.id.me_order_cv)
    LinearLayout me_order_cv;//我的订单

    @Bind(R.id.me_add_item_ll)
    LinearLayout me_add_item_ll;
    private ViewTools viewTools;

    private String[] text = {"我的收藏", "我的足记", "我的消息", "设置"};

    @Override
    public void bindView(Bundle savedInstanceState) {
        showView();
    }

    private void showView() {
//        BitmapLoader.ins().loadImage("https://b-ssl.duitang.com/uploads/blog/201509/29/20150929164702_KMUBn.thumb.700_0.jpeg", R.mipmap.touxiang, me_user_photo);
        me_user_name.setText("登录/注册");

        viewTools = new ViewTools(getContext());
        me_add_item_ll.removeAllViews();//防止每次进入此页面都动态增加item
        viewTools.addeditview(me_add_item_ll, null, text[0], new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("我的收藏");
            }
        }, true);
        viewTools.addeditview(me_add_item_ll, null, text[1], new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("我的足记");
            }
        }, true);
        viewTools.addeditview(me_add_item_ll, null, text[2], new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("我的消息");
            }
        }, 30, false);
        viewTools.addeditview(me_add_item_ll, null, text[3], new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.startFragmentByName(getContext(), SettingFragment.class);
            }
        }, false);

        me_user_info_ll.setOnClickListener(this);
        me_user_photo.setOnClickListener(this);
        registered_form_cv.setOnClickListener(this);
        me_remind_progress_cv.setOnClickListener(this);
        me_order_cv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_user_info_ll:
                JumpUtils.startActivity(getContext(), LoginActivity.class);
                break;
            case R.id.me_user_photo:
                showToast("头像");
                break;
            case me_trademark_registered_form_cv:
                JumpUtils.startFragmentByName(getContext(), TradeFormListFragment.class);
                break;
            case R.id.me_remind_progress_cv:
                //进度提醒
                JumpUtils.startActivity(getContext(), UserProgressActivity.class);
                break;
            case R.id.me_order_cv:
                JumpUtils.startFragmentByName(getContext(),MyOrdersFragment.class);
                break;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.me_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
