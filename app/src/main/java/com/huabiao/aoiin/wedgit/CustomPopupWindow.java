package com.huabiao.aoiin.wedgit;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CustomerServiceListBean.CustomerservicelistBean;
import com.ywy.mylibs.utils.BitmapLoader;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.wedgit
 * @date 2017-07-17 16:55
 * @description 客服推荐列表弹框
 */
public class CustomPopupWindow extends PopupWindow {
    private View mMenuView;
    private TextView name_tv, company_tv, successrate_tv, usedtime_tv, service_tv, next_tv;
    private ImageView circle_photo_iv, rate_iv;

    public CustomPopupWindow(Context context, CustomerservicelistBean bean, final CustomDialogClickListener listener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.custom_popup_dialog, null);
        name_tv = (TextView) mMenuView.findViewById(R.id.custom_popup_name_tv);
        company_tv = (TextView) mMenuView.findViewById(R.id.custom_popup_company_tv);
        successrate_tv = (TextView) mMenuView.findViewById(R.id.custom_popup_successrate_tv);
        usedtime_tv = (TextView) mMenuView.findViewById(R.id.custom_popup_usedtime_tv);
        service_tv = (TextView) mMenuView.findViewById(R.id.custom_popup_service_tv);
        next_tv = (TextView) mMenuView.findViewById(R.id.custom_popup_next_tv);
        circle_photo_iv = (ImageView) mMenuView.findViewById(R.id.custom_popup_circle_photo_iv);
        rate_iv = (ImageView) mMenuView.findViewById(R.id.custom_popup_rate_iv);

        if (bean != null) {
            name_tv.setText(bean.getCustomerservicename());
            company_tv.setText(bean.getCustomerservicecompany());
            successrate_tv.setText(bean.getSuccessrate());
            usedtime_tv.setText(bean.getUsedtime());
            service_tv.setText(bean.getService());

            BitmapLoader.ins().loadImage(bean.getCustomerserviceimg(), R.mipmap.perter_portrait, circle_photo_iv);
            next_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.selectNext();
                }
            });
            rate_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.selectRateCustom();
                }
            });
        }
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.custom_popup_dialog_rl).getTop();
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
        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.popup_bg_b0000000)));
        setFocusable(true);

    }

    public interface CustomDialogClickListener {
        void selectNext();

        void selectRateCustom();
    }
}
