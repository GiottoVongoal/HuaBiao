package com.huabiao.aoiin.wedgit;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultBean.ClassificationBean.ClassficationsmalltypeBean;
import com.huabiao.aoiin.ui.adapter.ShowRegisteredTypePopupAdapter;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.wedgit
 * @date 2017-07-14 10:32
 * @description 查询--已注册--点击次级分类的小分类弹框
 */
public class ShowRegisteredTypePopupWindow extends PopupWindow {
    private Context context;
    private View contentView;
    // 用于保存PopupWindow的宽度
    private int width;
    // 用于保存PopupWindow的高度
    private int height;

    private RecyclerView popmenu_rv;

    public ShowRegisteredTypePopupWindow(Context context, List<ClassficationsmalltypeBean> list, int direction, int trademarkstatus) {
        super(context);
        this.context = context;
        this.initPopupWindow(list, direction, trademarkstatus);
    }

    /**
     * @param list
     * @param direction 设置动画的方向:1 左;2 右
     */
    private void initPopupWindow(List<ClassficationsmalltypeBean> list, int direction, int trademarkstatus) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contentView = inflater.inflate(R.layout.show_registered_type_popupwindow, null);
        popmenu_rv = (RecyclerView) contentView.findViewById(R.id.show_registered_type_popmenu_rv);
        this.setContentView(contentView);
        // 设置弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击
        this.setTouchable(true);
        this.setFocusable(true);
        // 设置点击是否消失
//        this.setOutsideTouchable(true);
        //设置弹出窗体动画效果
        this.setAnimationStyle(direction == 1 ? R.style.PopupleftAnimation : R.style.PopupRightAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable background = new ColorDrawable(context.getResources().getColor(R.color.transparent));
        //设置弹出窗体的背景
        this.setBackgroundDrawable(background);
        // 绘制
        this.mandatoryDraw();

        popmenu_rv.setLayoutManager(new LinearLayoutManager(context));
        ShowRegisteredTypePopupAdapter adapter = new ShowRegisteredTypePopupAdapter(context, list);
        popmenu_rv.setAdapter(adapter);
        //增加底布局
        View footer = LayoutInflater.from(context).inflate(R.layout.show_registered_type_popup_bottom_layout, popmenu_rv, false);
        TextView footerTv = (TextView) footer.findViewById(R.id.show_registered_type_popup_bottom_tv);
        String statusString = "";
        switch (trademarkstatus) {
            case 1:
                statusString = "已注册";
                break;
            case 2:
                statusString = "未注册";
                break;
            case 3:
                statusString = "待审核";
                break;
        }
        footerTv.setText("商品" + statusString);
        adapter.setFooterView(footer);

        this.contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int heightTop = contentView.findViewById(R.id.show_registered_type_popmenu_rv).getTop();
                int heightBottom = contentView.findViewById(R.id.show_registered_type_popmenu_rv).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < heightTop || y > heightBottom) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    /**
     * 强制绘制popupWindowView，并且初始化popupWindowView的尺寸
     */
    private void mandatoryDraw() {
        this.contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        /**
         * 强制刷新后拿到PopupWindow的宽高
         */
        this.width = this.contentView.getMeasuredWidth();
        this.height = this.contentView.getMeasuredHeight();
    }

    /**
     * 显示在控件的下右方
     *
     * @param parent parent
     */
    public void showAtDropDownRight(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0] + parent.getWidth() - this.width, location[1] + parent.getHeight());
        }
    }

    /**
     * 显示在控件的下左方
     *
     * @param parent parent
     */
    public void showAtDropDownLeft(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0], location[1] + parent.getHeight());
        }
    }

    /**
     * 显示在控件的下中方
     *
     * @param parent parent
     */
    public void showAtDropDownCenter(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0] / 2 + parent.getWidth() / 2 - this.width / 6, location[1] + parent.getHeight());
        }
    }
}
