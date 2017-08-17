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
 * @date 2017-08-04 15:26
 * @description
 */
public class RegisterDataPreviewAdapter extends RecyclerView.Adapter<RegisterDataPreviewAdapter.ViewHolder> {
    private Context context;
    private List<ClassificationBean> mList;

    public RegisterDataPreviewAdapter(Context context, List<ClassificationBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.register_data_preview_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_top_tv.setText(mList.get(position).getClassificationid());
        holder.item_tv.setText(mList.get(position).getClassificationname());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView item_top_tv, item_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            item_top_tv = (TextView) itemView.findViewById(R.id.register_data_preview_item_top_tv);
            item_tv = (TextView) itemView.findViewById(R.id.register_data_preview_item_tv);
        }
    }

}
