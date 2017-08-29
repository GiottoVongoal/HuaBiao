package com.huabiao.aoiin.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;

import static com.huabiao.aoiin.R.id.tv;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.tools
 * @date 2017-08-07 17:19
 * @description
 */
public class ViewTools {
    private Context context;

    public ViewTools(Context context) {
        this.context = context;
    }

    /**
     * 编辑更多页面布局 ture 为显示 一条灰线
     **/
    public ImageView addeditview(LinearLayout other_addlayout, Integer img,
                                 String textleft, final View.OnClickListener onClickListener, boolean bool) {

        View child = LayoutInflater.from(context).inflate(
                R.layout.view_me_item, null);

        ImageView imageview_geren_icon = (ImageView) child
                .findViewById(R.id.view_me_left_iv);
        if (img != null) {
            imageview_geren_icon.setImageResource(img);
        }
        TextView left = (TextView) child.findViewById(R.id.view_me_tv);
        left.setText(textleft);
        View view_other = (View) child.findViewById(R.id.view_geren);

        RelativeLayout rl = (RelativeLayout) child.findViewById(R.id.table_geren);
        rl.setOnClickListener(onClickListener);

        if (bool) {
            view_other.setVisibility(View.VISIBLE);
        } else {
            view_other.setVisibility(View.GONE);
        }
        other_addlayout.addView(child);
        return imageview_geren_icon;
    }


    /**
     * 编辑更多页面布局 ture 为显示 一条灰线 并且隐藏空白距离 false 隐藏灰线
     **/
    public void addeditview(LinearLayout other_addlayout, Integer img,
                            String textleft, final View.OnClickListener onClickListener, int h,
                            boolean bool) {

        View child = LayoutInflater.from(context).inflate(
                R.layout.view_me_item, null);

        ImageView imageview_geren_icon = (ImageView) child
                .findViewById(R.id.view_me_left_iv);
        if (img != null) {
            imageview_geren_icon.setImageResource(img);
        }

        TextView left = (TextView) child.findViewById(R.id.view_me_tv);
        left.setText(textleft);
        View view_other = (View) child.findViewById(R.id.view_geren);

        RelativeLayout rl = (RelativeLayout) child.findViewById(R.id.table_geren);
        rl.setOnClickListener(onClickListener);
        TextView view_height = (TextView) child
                .findViewById(R.id.view_bottom_height);

        if (bool) {
            view_other.setVisibility(View.VISIBLE);
            view_height.setVisibility(View.GONE);
        } else {
            view_height.setVisibility(View.VISIBLE);
            view_other.setVisibility(View.GONE);
        }

        ViewGroup.LayoutParams params_1 = view_height.getLayoutParams();
        params_1.height = h;
        view_height.setLayoutParams(params_1);
        other_addlayout.addView(child);

    }

    /**
     * 编辑更多页面布局 ture 为显示 一条灰线
     * 带右边文字
     **/
    public TextView addeditview(LinearLayout other_addlayout, Integer img,
                                String textleft, String textRight, final View.OnClickListener onClickListener, boolean bool) {

        View child = LayoutInflater.from(context).inflate(
                R.layout.view_me_item, null);

        ImageView imageview_geren_icon = (ImageView) child
                .findViewById(R.id.view_me_left_iv);
        if (img != null) {
            imageview_geren_icon.setImageResource(img);
        }
        TextView left = (TextView) child.findViewById(R.id.view_me_tv);
        left.setText(textleft);
        TextView right = (TextView) child.findViewById(R.id.view_me_right_tv);
        right.setText(textRight);
        View view_other = (View) child.findViewById(R.id.view_geren);

        RelativeLayout rl = (RelativeLayout) child.findViewById(R.id.table_geren);
        rl.setOnClickListener(onClickListener);

        if (bool) {
            view_other.setVisibility(View.VISIBLE);
        } else {
            view_other.setVisibility(View.GONE);
        }
        other_addlayout.addView(child);
        return right;
    }


    /**
     * 编辑更多页面布局 ture 为显示 一条灰线 并且隐藏空白距离 false 隐藏灰线
     * 带右边文字
     **/
    public void addeditview(LinearLayout other_addlayout, Integer img,
                            String textleft, String textRight, final View.OnClickListener onClickListener, int h,
                            boolean bool) {

        View child = LayoutInflater.from(context).inflate(
                R.layout.view_me_item, null);

        ImageView imageview_geren_icon = (ImageView) child
                .findViewById(R.id.view_me_left_iv);
        if (img != null) {
            imageview_geren_icon.setImageResource(img);
        }

        TextView left = (TextView) child.findViewById(R.id.view_me_tv);
        left.setText(textleft);
        TextView right = (TextView) child.findViewById(R.id.view_me_right_tv);
        right.setText(textRight);
        View view_other = (View) child.findViewById(R.id.view_geren);

        RelativeLayout rl = (RelativeLayout) child.findViewById(R.id.table_geren);
        rl.setOnClickListener(onClickListener);
        TextView view_height = (TextView) child
                .findViewById(R.id.view_bottom_height);

        if (bool) {
            view_other.setVisibility(View.VISIBLE);
            view_height.setVisibility(View.GONE);
        } else {
            view_height.setVisibility(View.VISIBLE);
            view_other.setVisibility(View.GONE);
        }

        ViewGroup.LayoutParams params_1 = view_height.getLayoutParams();
        params_1.height = h;
        view_height.setLayoutParams(params_1);
        other_addlayout.addView(child);

    }
}
