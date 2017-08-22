package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.TradeFormListBean.TradeFormItemBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager.OnItemClickListener;
import com.ywy.mylibs.utils.BitmapLoader;

import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.adapter
 * @date 2017-08-09 15:36
 * @description 商标注册表单列表Adapter
 */
public class TradeFormListAdapter extends RecyclerView.Adapter<TradeFormListAdapter.ViewHolder> {
    private Context context;
    private List<TradeFormItemBean> mList;

    public TradeFormListAdapter(Context context, List<TradeFormItemBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trade_form_list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TradeFormItemBean bean = mList.get(position);
        holder.item_tv.setText(bean.getTradename());
        BitmapLoader.ins().loadImage(bean.getLogoimg(), R.mipmap.logobg, holder.item_iv);
        holder.item_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClickListener(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout item_rl;
        private ImageView item_iv;
        private TextView item_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            item_rl = (RelativeLayout) itemView.findViewById(R.id.trade_form_list_item_rl);
            item_iv = (ImageView) itemView.findViewById(R.id.trade_form_list_item_iv);
            item_tv = (TextView) itemView.findViewById(R.id.trade_form_list_item_tv);
        }
    }
}
