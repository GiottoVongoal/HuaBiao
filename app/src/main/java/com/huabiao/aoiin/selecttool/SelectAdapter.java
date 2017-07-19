package com.huabiao.aoiin.selecttool;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationItemBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectAdapter extends BaseAdapter {
    List<ClassificationItemBean> datas;
    int selectedIndex = AddressSelector.INDEX_INVALID;
    Activity activity;

    public SelectAdapter(Activity activity, List<ClassificationItemBean> datas) {
        this.activity = activity;
        this.datas = datas;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item_area, parent, false);

            holder = new Holder();
            holder.select_type_rl = (RelativeLayout) convertView.findViewById(R.id.select_type_rl);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.imageViewCheckMark = (ImageView) convertView.findViewById(R.id.imageViewCheckMark);
            holder.select_item_cb = (CheckBox) convertView.findViewById(R.id.select_item_cb);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final ClassificationItemBean item = datas.get(position);
        holder.textView.setText(item.getClassificationname() + item.getClassificationid());
        if (item.isNextlevel()) {
            //单选
            boolean checked = selectedIndex != AddressSelector.INDEX_INVALID && datas.get(selectedIndex).getClassificationid().equals(item.getClassificationid());
            holder.textView.setEnabled(!checked);
            holder.imageViewCheckMark.setVisibility(checked ? View.VISIBLE : View.GONE);
            holder.select_item_cb.setVisibility(View.GONE);
        } else {
            holder.imageViewCheckMark.setVisibility(View.GONE);
            holder.select_item_cb.setVisibility(View.VISIBLE);
            holder.select_item_cb.setChecked(item.isChecked());
        }
        return convertView;
    }

    class Holder {
        RelativeLayout select_type_rl;
        TextView textView;
        ImageView imageViewCheckMark;
        CheckBox select_item_cb;
    }
}
