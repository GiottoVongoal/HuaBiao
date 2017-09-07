package com.huabiao.aoiin.wedgit;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.ywy.mylibs.utils.DeviceUtils;

/**
 * 通用的对话框
 */
public class CommonSimpleDialog extends Dialog {
    private static Context mContext;
    private View view;
    public TextView common_content_center_tv;  //居中的显示内容
    public TextView common_dialog_left_tv;  //左按钮
    public TextView common_dialog_right_tv;  //右按钮
    public RelativeLayout common_dialog_title_rl;  //标题
    public LinearLayout common_dialog_title_right_ll;  //标题右部分
    public TextView common_dialog_title_tv;  //标题内容
    public TextView common_dialog_title_right_tv;  //右边标题内容
    public ImageView common_dialog_title_right_iv;  //右边标题icon
    public View common_dialog_vertical_line; //两个按钮间的分割线
    private View.OnClickListener leftButtonListener, rightButtonListener;
    private String titleStr;//标题
    private Object content;//内容
    private String leftStr, rightStr;//按钮文字
    private boolean isCancel = false; //是否点击取消
    private boolean isHasTitle = false; //默认有标题
    private boolean isHasTwo = true; //是否有两个按钮

    public CommonSimpleDialog(Context context) {
        super(context, R.style.ProgressHUD1);
        this.mContext = context;
        titleStr = "提示";
        this.leftStr = "取消";
        this.rightStr = "确定";
    }

    /**
     * 设置标题，默认无标题
     */
    public CommonSimpleDialog setTitle(String titleStr) {
        this.isHasTitle = true;
        this.titleStr = titleStr;
        return this;
    }

    /**
     * 设置内容显示
     */
    public CommonSimpleDialog setContent(Object content) {
        this.content = content;
        return this;
    }

    /**
     * 设置两个按钮的显示，默认一个按钮
     *
     * @param isHasTwo 是否有两个按钮
     * @param leftStr  左
     * @param rightStr 右
     */
    public CommonSimpleDialog setButton(boolean isHasTwo, String leftStr, String rightStr, View.OnClickListener leftButtonListener, View.OnClickListener rightButtonListener) {
        this.isHasTwo = isHasTwo;
        this.leftStr = leftStr;
        this.rightStr = rightStr;
        this.leftButtonListener = leftButtonListener;
        this.rightButtonListener = rightButtonListener;
        return this;
    }


    /**
     * 设置两个按钮的显示
     *
     * @param leftButtonListener
     * @param rightButtonListener
     * @return
     */
    public CommonSimpleDialog setButton(View.OnClickListener leftButtonListener, View.OnClickListener rightButtonListener) {
        this.isHasTwo = true;
        this.leftStr = "取消";
        this.rightStr = "确定";
        this.leftButtonListener = leftButtonListener;
        this.rightButtonListener = rightButtonListener;
        return this;
    }

    /**
     * 设置是否点击框外消失，默认不消失
     *
     * @param isCancel
     * @return
     */
    public CommonSimpleDialog setCancel(boolean isCancel) {
        this.isCancel = isCancel;
        return this;
    }

    private void initView() {
        view = LayoutInflater.from(mContext).inflate(R.layout.dialog_common_simple, null);
        common_content_center_tv = (TextView) view.findViewById(R.id.common_content_center_tv);
        common_dialog_left_tv = (TextView) view.findViewById(R.id.common_dialog_left_tv);
        common_dialog_right_tv = (TextView) view.findViewById(R.id.common_dialog_right_tv);
        common_dialog_title_rl = (RelativeLayout) view.findViewById(R.id.common_dialog_title_rl);
        common_dialog_title_right_ll = (LinearLayout) view.findViewById(R.id.common_dialog_title_right_ll);
        common_dialog_title_tv = (TextView) view.findViewById(R.id.common_dialog_title_tv);
        common_dialog_title_right_tv = (TextView) view.findViewById(R.id.common_dialog_title_right_tv);
        common_dialog_title_right_iv = (ImageView) view.findViewById(R.id.common_dialog_title_right_iv);
        common_dialog_vertical_line = (View) view.findViewById(R.id.common_dialog_vertical_line);
        this.setContentView(view);
        this.setCanceledOnTouchOutside(isCancel);// 设置点击屏幕Dialog不消失
    }

    /**
     * 设置标题，默认无标题
     */
    private void setDialogTitle() {
        if (isHasTitle) {
            common_dialog_title_rl.setVisibility(View.VISIBLE);
            common_dialog_title_tv.setText(titleStr);
        } else {
            common_dialog_title_rl.setVisibility(View.GONE);
        }
    }

    /**
     * 设置内容显示，必须设置的
     */
    private void setDialogContent() {
        String str = content.toString();
        // 计算TextView宽度：xml中定义的宽度300dp，转换成px
        float textViewWidth = DeviceUtils.convertDpToPixel(mContext, 270);
        float dotWidth = DeviceUtils.getCharWidth(common_content_center_tv, '.');
        ALog.d("TextViewWidth", "TextView width " + textViewWidth);
        int sumWidth = 0;
        sumWidth = DeviceUtils.getStringWidth(common_content_center_tv, str, sumWidth);
        if (sumWidth + dotWidth * 3 >= textViewWidth) {
            common_content_center_tv.setGravity(Gravity.CENTER | Gravity.LEFT);
        } else {
            common_content_center_tv.setGravity(Gravity.CENTER);
        }

        if (content instanceof Spanned) {
            common_content_center_tv.setText((Spanned) content);
        } else {
            common_content_center_tv.setText((String) content);
        }
    }

    /**
     * 设置两个按钮的显示
     */
    private void setDialogButton() {
        if (isHasTwo) {
            common_dialog_left_tv.setVisibility(View.VISIBLE);
            common_dialog_right_tv.setVisibility(View.VISIBLE);
            common_dialog_vertical_line.setVisibility(View.VISIBLE);
            common_dialog_left_tv.setText(leftStr);
            common_dialog_right_tv.setText(rightStr);
        } else {
            common_dialog_left_tv.setVisibility(View.GONE);
            common_dialog_vertical_line.setVisibility(View.GONE);
            common_dialog_right_tv.setVisibility(View.VISIBLE);
            common_dialog_right_tv.setText(rightStr);
        }
        common_dialog_left_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftButtonListener != null) {
                    leftButtonListener.onClick(v);
                }
                dismiss();
            }
        });
        common_dialog_right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightButtonListener != null) {
                    rightButtonListener.onClick(v);
                }
                dismiss();
            }
        });
    }

    public CommonSimpleDialog build() {
        initView();
        setDialogTitle();
        setDialogContent();
        setDialogButton();
        return this;
    }

    /**
     * 标题栏右边有控件的
     *
     * @param isHasRightTitle 是否有右边标题内容
     * @param isHasTv         是否有右边文字标题
     * @param isHasIcon       是否有右边图标标题
     * @param rightTitleStr   右边标题内容
     * @param rightDrawable   右边图标
     */
    public void setRightTitle(boolean isHasRightTitle, boolean isHasTv, boolean isHasIcon, String rightTitleStr, Drawable rightDrawable) {
        if (isHasRightTitle) {
            common_dialog_title_right_ll.setVisibility(View.VISIBLE);
            if (isHasTv) {
                common_dialog_title_right_tv.setVisibility(View.VISIBLE);
                if (rightTitleStr != null) {
                    common_dialog_title_right_tv.setText(rightTitleStr);
                }
            } else {
                common_dialog_title_right_tv.setVisibility(View.GONE);
            }

            if (isHasIcon) {
                common_dialog_title_right_iv.setVisibility(View.VISIBLE);
                if (rightDrawable != null) {
                    common_dialog_title_right_iv.setImageDrawable(rightDrawable);
                }
            } else {
                common_dialog_title_right_iv.setVisibility(View.GONE);
            }
        } else {
            common_dialog_title_right_ll.setVisibility(View.GONE);
        }
    }
}
