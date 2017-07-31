package com.huabiao.aoiin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.ui.adapter.SearchResultTabPagerAdapter;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.ClickUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-13 10:43
 * @description 查询结果的TabFragment
 */
public class SearchResultTabFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.search_result_tab_back_iv)
    ImageView tab_back_iv;
    @Bind(R.id.search_result_tablayout)
    TabLayout tab_tablayout;
    @Bind(R.id.search_result_tab_vp)
    ViewPager tab_vp;

    private List<Fragment> list;
    SearchResultTabPagerAdapter adapter;

    private String tradename = "", goodsname = "";
    SearchResultRegisteredFragment registeredFragment;
    SearchResultUnRegisteredFragment unRegisteredFragment;

    @Override
    public void bindView(Bundle savedInstanceState) {
        tab_back_iv.setOnClickListener(this);
        Bundle bundle = getActivity().getIntent().getExtras();
        tradename = bundle.getString("tradename");
        goodsname = bundle.getString("goodsname");

        list = new ArrayList<>();
        registeredFragment = new SearchResultRegisteredFragment();
        unRegisteredFragment = new SearchResultUnRegisteredFragment();
        unRegisteredFragment.getIntentString(tradename, goodsname);//传值
        list.add(registeredFragment);
        list.add(unRegisteredFragment);
        adapter = new SearchResultTabPagerAdapter(getActivity().getSupportFragmentManager(), list, getContext());
        tab_vp.setAdapter(adapter);
        tab_tablayout.setupWithViewPager(tab_vp);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_result_tab_back_iv:
                ClickUtil.onBackClick();
                break;
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.search_result_tab_layout;
    }

}
