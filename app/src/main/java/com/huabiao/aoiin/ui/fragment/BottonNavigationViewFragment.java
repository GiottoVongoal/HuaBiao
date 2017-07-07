package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.materialdesign
 * @date 2017-07-05 13:20
 * @description
 */
public class BottonNavigationViewFragment extends BaseFragment {

    @Bind(R.id.tv)
    TextView tvInfo;

    public static BottonNavigationViewFragment newInstance(String info) {
        Bundle args = new Bundle();
        BottonNavigationViewFragment fragment = new BottonNavigationViewFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        tvInfo.setText(getArguments().getString("info"));
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "hello", Snackbar.LENGTH_SHORT).show();
                JumpUtils.startFragmentByName(getContext(), TestFragment.class);
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.item_list;
    }
}