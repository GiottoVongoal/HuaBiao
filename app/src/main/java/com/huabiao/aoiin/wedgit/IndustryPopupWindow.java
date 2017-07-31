package com.huabiao.aoiin.wedgit;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterOneIndustryBean.IndustrylistBean;
import com.huabiao.aoiin.ui.adapter.IndustryPopupWindowAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.OnItemClickListener;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.wedgit
 * @date 2017-07-12 10:27
 * @description Tab中的注册--选择行业的弹框
 */
public class IndustryPopupWindow extends PopupWindow {
    private TextView industry_dialog_title_tv;
    private RecyclerView industry_dialog_rv;
    private TextView industry_dialog_finish_tv;
    private View mMenuView;
    private LinearLayoutManager layoutManager;
    private IndustryPopupWindowAdapter adapter;

    public IndustryPopupWindow(Context context, String title, List<IndustrylistBean> list, int place,
                               OnItemClickListener onItemClickListener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.industry_dialog, null);
        industry_dialog_title_tv = (TextView) mMenuView.findViewById(R.id.industry_dialog_title_tv);
        industry_dialog_rv = (RecyclerView) mMenuView.findViewById(R.id.industry_dialog_rv);
        industry_dialog_finish_tv = (TextView) mMenuView.findViewById(R.id.industry_dialog_finish_tv);
        industry_dialog_title_tv.setText(title);
        if (list != null) {
            //创建线性布局,垂直方向
            layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            //给RecyclerView设置布局管理器
            industry_dialog_rv.setLayoutManager(layoutManager);
            adapter = new IndustryPopupWindowAdapter(context, place, list);
            industry_dialog_rv.setAdapter(adapter);
            adapter.setOnItemClickListener(onItemClickListener);

        }
        industry_dialog_finish_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.industry_dialog_ll).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        setContentView(mMenuView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setAnimationStyle(R.style.bottom_enter_anim);
        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.popup_main_background)));
        setFocusable(true);
    }

}
