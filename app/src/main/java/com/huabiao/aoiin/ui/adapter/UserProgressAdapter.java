package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.UserProgressListBean.ProgresslistBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.StringUtil;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-07-26 13:11
 * @description
 */
public class UserProgressAdapter extends RecyclerView.Adapter<UserProgressAdapter.UserProgressViewHolder> {
    private Context context;
    private List<ProgresslistBean> list;

    public UserProgressAdapter(Context context, List<ProgresslistBean> list) {
        this.context = context;
        this.list = list;
    }

    public InterfaceManager.OnItemClickListener itemClickListener;

    public void setItemClickListener(InterfaceManager.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public UserProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_progress_item_layout, parent, false);
        return new UserProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserProgressViewHolder holder, final int position) {
        ProgresslistBean bean = list.get(position);
        BitmapLoader.ins().loadImage(bean.getProgressimg(), R.mipmap.ic_launcher, holder.item_iv);
        holder.item_content_tv.setText(bean.getProgresscontent());
        holder.item_status_tv.setText(StringUtil.isEmpty(bean.getProgressstatustext()) ? "" : bean.getProgressstatustext());
        holder.item_time_tv.setText(StringUtil.isEmpty(bean.getProgresstime()) ? "" : bean.getProgresstime());
        holder.item_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickListener(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class UserProgressViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout item_ll;
        ImageView item_iv;
        TextView item_content_tv, item_status_tv, item_time_tv;

        public UserProgressViewHolder(View itemView) {
            super(itemView);
            item_ll = (RelativeLayout) itemView.findViewById(R.id.user_progress_item_ll);
            item_iv = (ImageView) itemView.findViewById(R.id.user_progress_item_iv);
            item_content_tv = (TextView) itemView.findViewById(R.id.user_progress_item_content_tv);
            item_status_tv = (TextView) itemView.findViewById(R.id.user_progress_item_status_tv);
            item_time_tv = (TextView) itemView.findViewById(R.id.user_progress_item_time_tv);
        }
    }

}
