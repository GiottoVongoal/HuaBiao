package com.huabiao.aoiin.ui.interfaces;


import android.widget.RelativeLayout;

public interface ICardAdapter {

    RelativeLayout getCardViewAt(int position);

    int getCount();
}
