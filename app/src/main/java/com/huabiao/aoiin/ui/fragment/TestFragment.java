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
        TextView tv7 = new TextView(getContext());
        tv7.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp7 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        main_ll.addView(tv7, lp7);
        tv7.setText("点我去测试XRecyclerView分页页面");
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpUtils.startFragmentByName(getContext(), TestRecyclerViewFragment.class);
            }
        });

    }

    @Override
    public int getContentLayout() {
        return R.layout.test_fragment_layout;
    }
}
