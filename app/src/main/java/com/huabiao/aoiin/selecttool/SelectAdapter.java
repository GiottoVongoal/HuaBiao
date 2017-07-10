package com.huabiao.aoiin.selecttool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationItemBean;

import java.util.List;

/**
 * Created by dun on 17/2/9.
 */

public class SelectAdapter extends BaseAdapter {
    List<ClassificationItemBean> datas;
    int selectedIndex = AddressSelector.INDEX_INVALID;

    public SelectAdapter(List<ClassificationItemBean> datas) {
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
//        return datas.get(position).getClassificationid();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area, parent, false);

            holder = new Holder();
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.imageViewCheckMark = (ImageView) convertView.findViewById(R.id.imageViewCheckMark);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        ClassificationItemBean item = (ClassificationItemBean) getItem(position);
        holder.textView.setText(item.getClassificationname() + item.getClassificationid());

        boolean checked = selectedIndex != AddressSelector.INDEX_INVALID && datas.get(selectedIndex).getClassificationid().equals(item.getClassificationid());
        holder.textView.setEnabled(!checked);
        holder.imageViewCheckMark.setVisibility(checked ? View.VISIBLE : View.GONE);

        return convertView;
    }

    class Holder {
        TextView textView;
        ImageView imageViewCheckMark;
    }
}
