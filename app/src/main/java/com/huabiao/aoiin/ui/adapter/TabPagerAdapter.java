package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-07-13 10:55
 * @description
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
    private List<String> titles = new ArrayList<>();
    private Context context;
    private List<Fragment> list;


    public TabPagerAdapter(FragmentManager fm, List<Fragment> list, Context context, List<String> titles) {
        super(fm);
        this.list = list;
        this.context = context;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
