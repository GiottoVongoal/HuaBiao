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
        TextView tv2 = new TextView(getContext());
        tv2.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        main_ll.addView(tv2, lp2);
        tv2.setText("点我去检查控件是否划出屏幕页面");
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.startActivity(getContext(), CheckViewIsShowActivity.class);
            }
        });

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }
}
