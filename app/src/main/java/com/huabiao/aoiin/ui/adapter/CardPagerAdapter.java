package com.huabiao.aoiin.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;

import com.huabiao.aoiin.ui.interfaces.ICardAdapter;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements ICardAdapter {

    private List<CardView> mViews;
    private float mBaseElevation;

    public CardPagerAdapter() {
        mViews = new ArrayList<>();
    }

    public void setCardItem(List<CardView> item) {
        mViews = item;
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        if (mBaseElevation == 0) {
            mBaseElevation = mViews.get(position).getCardElevation();
        }
        mViews.get(position).setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

}
