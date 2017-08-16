package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.UserProgressDateBean.UserprogressdatelistBean;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.wedgit.wedgit.CircleView;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-07-25 15:54
 * @description 日历进度Adapter
 */
public class UserProgressDateAdapter extends RecyclerView.Adapter<UserProgressDateAdapter.MyViewHolder> {
    private Context context;
    private List<UserprogressdatelistBean> mList;

    public UserProgressDateAdapter(Context context, List<UserprogressdatelistBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    /**
     * 更新列表数据
     *
     * @param list
     */
    public void updateListView(List<UserprogressdatelistBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public UserProgressDateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_progress_date_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserProgressDateAdapter.MyViewHolder holder, int position) {
        UserprogressdatelistBean bean = mList.get(position);
        holder.item_spot_iv.setVisibility(View.VISIBLE);
        holder.item_time_tv.setText(bean.getProgressdatetime());
        holder.item_content_tv.setText(bean.getProgressdatecontent());
        BitmapLoader.ins().loadImage(bean.getProgressdateimg(), R.mipmap.ic_launcher, holder.item_right_iv);
        if (position == mList.size() - 1) {
            holder.item_spot_iv.setBackground(context.getResources().getDrawable(R.mipmap.point_yellow));
        } else {
            holder.item_spot_iv.setBackground(context.getResources().getDrawable(R.mipmap.point_gray));
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_spot_iv;//进度条上的圆点
        private TextView item_time_tv;
        private TextView item_content_tv;
        private CircleView item_right_iv;


        public MyViewHolder(View itemView) {
            super(itemView);
            item_spot_iv = (ImageView) itemView.findViewById(R.id.user_progress_date_item_spot_iv);
            item_time_tv = (TextView) itemView.findViewById(R.id.user_progress_date_item_time_tv);
            item_content_tv = (TextView) itemView.findViewById(R.id.user_progress_date_item_content_tv);
            item_right_iv = (CircleView) itemView.findViewById(R.id.user_progress_date_item_right_iv);
        }
    }
}
