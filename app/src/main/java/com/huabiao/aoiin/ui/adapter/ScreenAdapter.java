package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.ScreenBean;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.DeviceUtils;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.example.zhaoshuang.rippledemo
 * @date 2017-08-09 11:46
 * @description 查询结果的筛选弹框Adapter
 */
public class ScreenAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<ScreenBean.ScreenlistBean> list;

    public ScreenAdapter(Context mContext, List<ScreenBean.ScreenlistBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getSlist().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getSlist().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_hint_popupwindow, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.iv_left = (ImageView) convertView.findViewById(R.id.screen_item_left_iv);
            groupHolder.tv_content = (TextView) convertView.findViewById(R.id.screen_item_content_tv);
            groupHolder.iv_right = (ImageView) convertView.findViewById(R.id.screen_item_right_iv);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.iv_left.setVisibility(View.GONE);
        groupHolder.iv_right.setVisibility(View.VISIBLE);
        if (isExpanded) {//展开
            groupHolder.iv_right.setImageResource(R.mipmap.shouqi);
        } else {
            groupHolder.iv_right.setImageResource(R.mipmap.xiala);
        }
        groupHolder.tv_content.setTextSize(DeviceUtils.px2sp(mContext, 32));
        groupHolder.tv_content.setText(list.get(groupPosition).getSname());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_hint_popupwindow, parent, false);
            groupHolder = new ViewHolderItem();
            groupHolder.iv_left = (ImageView) convertView.findViewById(R.id.screen_item_left_iv);
            groupHolder.tv_content = (TextView) convertView.findViewById(R.id.screen_item_content_tv);
            groupHolder.iv_right = (ImageView) convertView.findViewById(R.id.screen_item_right_iv);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (ViewHolderItem) convertView.getTag();
        }
        ClassificationBean bean = list.get(groupPosition).getSlist().get(childPosition);
        groupHolder.iv_left.setVisibility(View.VISIBLE);
        groupHolder.iv_right.setVisibility(View.INVISIBLE);
        BitmapLoader.ins().loadImage("", R.mipmap.shenghezhong, groupHolder.iv_left);
        groupHolder.tv_content.setTextSize(DeviceUtils.px2sp(mContext, 28));
        groupHolder.tv_content.setText(bean.getClassificationid() + " - " + bean.getClassificationname());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // child是否可以被点击，true为可以点，false为不可以点
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private static class ViewHolderGroup {
        private TextView tv_content;
        private ImageView iv_right;
        private ImageView iv_left;
    }

    private static class ViewHolderItem {
        private TextView tv_content;
        private ImageView iv_right;
        private ImageView iv_left;
    }
}
