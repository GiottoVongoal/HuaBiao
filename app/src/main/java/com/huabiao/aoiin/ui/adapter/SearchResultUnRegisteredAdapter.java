package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultUnRegisterCheckBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-07-14 15:06
 * @description 查询结果未注册Adapter/注册第一步跳转过来的Type展示Adapter(有点击事件)
 */
public class SearchResultUnRegisteredAdapter extends RecyclerView.Adapter<SearchResultUnRegisteredAdapter.UnRegisteredViewHolder> {
    private Context context;
    private List<SearchResultUnRegisterCheckBean> list;

    public SearchResultUnRegisteredAdapter(Context context, List<SearchResultUnRegisterCheckBean> list) {
        this.context = context;
        this.list = list;
    }

    public InterfaceManager.OnItemClickListener itemClickListener;

    public void setOnItemClickListener(InterfaceManager.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 更新列表数据
     *
     * @param list
     */
    public void updateListView(List<SearchResultUnRegisterCheckBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public UnRegisteredViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result_unregistered_item_layout, parent, false);
        return new UnRegisteredViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UnRegisteredViewHolder holder, final int position) {
        SearchResultUnRegisterCheckBean bean = list.get(position);
        holder.item_tv.setText(bean.getClassificationid() + " - " + bean.getClassificationname());
        boolean ischeck = bean.isCheck();
        if (ischeck) {
            holder.item_iv.setImageResource(R.mipmap.star_pre);
        } else {
            holder.item_iv.setImageResource(R.mipmap.star_nor);
        }
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

    class UnRegisteredViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item_ll;
        ImageView item_iv;
        TextView item_tv;

        public UnRegisteredViewHolder(View itemView) {
            super(itemView);
            item_ll = (LinearLayout) itemView.findViewById(R.id.search_result_unregistered_item);
            item_iv = (ImageView) itemView.findViewById(R.id.search_result_unregistered_item_iv);
            item_tv = (TextView) itemView.findViewById(R.id.search_result_unregistered_item_tv);
        }
    }
}
