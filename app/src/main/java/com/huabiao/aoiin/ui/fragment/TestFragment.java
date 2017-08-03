package com.huabiao.aoiin.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.CameraUtils;
import com.ywy.mylibs.utils.JumpUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by PC on 2017/5/24.
 */

public class TestFragment extends BaseFragment {

    @Bind(R.id.textView)
    TextView textView;

    @Bind(R.id.main_ll)
    LinearLayout main_ll;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Environment.getExternalStorageDirectory().getPath();
                Uri filePath = Uri.parse(url);
                startActivity(CameraUtils.getCameraIntent(filePath));
            }
        });
        TextView tv1 = new TextView(getContext());
        tv1.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        main_ll.addView(tv1, lp1);
        tv1.setText("点我去输入验证码页面");
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.startFragmentByName(getContext(), VerificationCodeFragment.class);
            }
        });
        TextView tv3 = new TextView(getContext());
        tv3.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        main_ll.addView(tv3, lp3);
        tv3.setText("点我去我的页面");
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.startFragmentByName(getContext(), MeFragment.class);
            }
        });
        TextView tv4 = new TextView(getContext());
        tv4.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        main_ll.addView(tv4, lp4);
        tv4.setText("点我去搜索页面");
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.startFragmentByName(getContext(), SearchFragment.class);
            }
        });
        TextView tv5 = new TextView(getContext());
        tv5.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        main_ll.addView(tv5, lp5);
        tv5.setText("点我去折线图页面");
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.startFragmentByName(getContext(), LineChartFragment.class);
            }
        });
        TextView tv6 = new TextView(getContext());
        tv6.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp6 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        main_ll.addView(tv6, lp6);
        tv6.setText("点我去多级选择页面");
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.startFragmentByName(getContext(), CheckTypeListFragment.class);
            }
        });

    }

    @Override
    public int getContentLayout() {
        return R.layout.test_fragment_layout;
    }
}
