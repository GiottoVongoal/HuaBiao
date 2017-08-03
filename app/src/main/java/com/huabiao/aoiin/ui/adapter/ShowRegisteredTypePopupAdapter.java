package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResultBean.Classification.ClassficationsmalltypeBean.DetailedBean;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-07-14 10:52
 * @description 查询结果--点击次级分类的小分类弹框Adapter
 */
public class ShowRegisteredTypePopupAdapter extends RecyclerView.Adapter<ShowRegisteredTypePopupAdapter.PopupViewHolder> {
    private Context context;
    private List<DetailedBean> list;

    private View mFooterView;//底布局
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    public ShowRegisteredTypePopupAdapter(Context context, List<DetailedBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public PopupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new PopupViewHolder(mFooterView);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.show_registered_type_popup_layout, parent, false);
        return new PopupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopupViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            return;
        }
        DetailedBean bean = list.get(position);
        String showText = "[" + bean.getId() + "]\t" + bean.getTypename();
        holder.popwind_tv.setText(showText);

    }

    @Override
    public int getItemViewType(int position) {
        if (mFooterView == null)
            return TYPE_NORMAL;
        if (position == getItemCount() - 1)
            return TYPE_FOOTER;//最后一个,应该加载Footer
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return mFooterView == null ? list.size() : list.size() + 1;
    }

    class PopupViewHolder extends RecyclerView.ViewHolder {
        TextView popwind_tv;

        public PopupViewHolder(View itemView) {
            super(itemView);
            popwind_tv = (TextView) itemView.findViewById(R.id.show_registered_type_popup_tv);
        }
    }
}
