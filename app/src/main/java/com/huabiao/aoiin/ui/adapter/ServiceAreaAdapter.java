package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationBean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-08-08 13:36
 * @description 商标评估报告中服务范围Adapter
 */
public class ServiceAreaAdapter extends RecyclerView.Adapter<ServiceAreaAdapter.ViewHolder> {
    private Context context;
    private List<ClassificationBean> mList;

    public ServiceAreaAdapter(Context context, List<ClassificationBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_area_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassificationBean bean = mList.get(position);
        holder.service_area_item_tv.setText("【" + bean.getClassificationid() + "——" + bean.getClassificationname() + "】");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView service_area_item_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            service_area_item_tv = (TextView) itemView.findViewById(R.id.service_area_item_tv);
        }
    }

}
