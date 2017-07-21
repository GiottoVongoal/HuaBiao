package com.huabiao.aoiin.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.ALog;

import java.util.List;

/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.adslip
 * @date 2017-01-23 17:16
 * @description Banner图Adapter
 */
public class BannerAdapter extends PagerAdapter {
    //数据源
    private List<ImageView> mList;

    public BannerAdapter(List<ImageView> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        //取超大的数，实现无线循环效果
//        return Integer.MAX_VALUE;
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        try {
            if (mList.get(position).getParent() == null) {
                ((ViewPager) container).addView(mList.get(position), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mList.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理跳转逻辑
                ALog.i("position = " + position);
            }
        });
        return mList.get(position % mList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position % mList.size()));
    }
}
