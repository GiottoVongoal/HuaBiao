package com.huabiao.aoiin.wedgit;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.huabiao.aoiin.R;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.wedgit
 * @date 2017-07-12 11:31
 * @description Tab中的注册--点击注册的弹框
 */
public class RegisterOneFinishPopupWindow extends PopupWindow {
    //默认注册
    private RelativeLayout dialog_default_rl;
    //客服推荐
    private RelativeLayout dialog_recommand_rl;

    private View mMenuView;

    public RegisterOneFinishPopupWindow(Context context, final DialogClickListener listener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.register_fragment_finish_dialog, null);
        dialog_default_rl = (RelativeLayout) mMenuView.findViewById(R.id.register_one_finish_dialog_default_rl);
        dialog_recommand_rl = (RelativeLayout) mMenuView.findViewById(R.id.register_one_finish_dialog_recommand_rl);
        dialog_default_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.selectDefault();
            }
        });
        dialog_recommand_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.selectRecommand();
            }
        });
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.register_one_finish_dialog_ll).getTop();
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

    public interface DialogClickListener {
        void selectDefault();

        void selectRecommand();
    }
}
