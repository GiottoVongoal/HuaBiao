package com.huabiao.aoiin.ui.interfaces;

import android.view.View;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.interfaces
 * @date 2017-06-15 11:38
 * @description
 */
public class InterfaceManager {


    //创建RecyclerView的Item点击事件
    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }
}
