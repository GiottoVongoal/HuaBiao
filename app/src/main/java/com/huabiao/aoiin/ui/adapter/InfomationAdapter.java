package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.HomeBean;
import com.huabiao.aoiin.bean.HomeBean.HomeinfolistBean;
import com.huabiao.aoiin.wedgit.XCRoundRectImageView;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-08-16 12:05
 * @description 首页资讯Adapter
 */
public class InfomationAdapter extends RecyclerView.Adapter<InfomationAdapter.ViewHolder> {
    private Context context;
    private List<HomeinfolistBean> list;

    public InfomationAdapter(Context context, List<HomeinfolistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_infomation_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        BitmapLoader.ins().loadImage(list.get(position).getHomeinfoUrl(), R.mipmap.ic_launcher, holder.item_iv);
        holder.item_iv.setBackground(context.getResources().getDrawable(R.mipmap.zhixun_bg));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        XCRoundRectImageView item_iv;

        public ViewHolder(View itemView) {
            super(itemView);
            item_iv = (XCRoundRectImageView) itemView.findViewById(R.id.home_infomation_item_iv);
        }
    }
}
