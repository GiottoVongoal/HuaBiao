package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MallBean.ShoppingmalllistBean;
import com.huabiao.aoiin.ui.fragment.BuyingFragment;
import com.huabiao.aoiin.wedgit.XCRoundRectImageView;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.StringUtil;

import java.util.List;

public class MallAdapter extends RecyclerView.Adapter<MallAdapter.ViewHolder> {
    private Context context;
    private List<ShoppingmalllistBean> data2;

    public MallAdapter(Context context, List<ShoppingmalllistBean> data2) {
        this.context = context;
        this.data2 = data2;
    }

    public void addList(List<ShoppingmalllistBean> list) {
        data2.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_mall_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //加载数据
        final ShoppingmalllistBean mb = data2.get(position);
        holder.mallTextview.setText(mb.getGoodsname());
        holder.show_TextView.setText(mb.getStatus());
        holder.register_TextView.setText("注册号：" + mb.getRegnumber());
        holder.applicant_TexView.setText("申请人：" + mb.getApplicantname());
        holder.classfication_TextView.setText(mb.getClassificationid() + " - " + mb.getClassificationname());
        BitmapLoader.ins().loadImage(mb.getGoodsimg(), R.mipmap.logobg, holder.imageView);
        //根据json中的status设置文字显示状态。1-注册成功，2-待审核中，3-商标无效，4-初审公告
        String statusString = mb.getStatus();
        if (!StringUtil.isEmpty(statusString)) {
            final int status = Integer.parseInt(statusString);
            switch (status) {
                case 1://可求购
                    holder.mall_listitem_buy_tv.setVisibility(View.VISIBLE);
                    holder.mall_listitem_yiyi_tv.setVisibility(View.GONE);
                    holder.mall_listitem_Cybersquatting_tv.setVisibility(View.GONE);
                    holder.show_TextView.setText("注册成功");
                    holder.mall_status_img.setImageResource(R.mipmap.chenggong);
                    break;
                case 2://可异议
                    holder.mall_listitem_buy_tv.setVisibility(View.GONE);
                    holder.mall_listitem_yiyi_tv.setVisibility(View.VISIBLE);
                    holder.mall_listitem_Cybersquatting_tv.setVisibility(View.GONE);
                    holder.show_TextView.setText("待审核中");
                    holder.mall_status_img.setImageResource(R.mipmap.shenghezhong);
                    break;
                case 3://可抢注
                    holder.mall_listitem_buy_tv.setVisibility(View.GONE);
                    holder.mall_listitem_yiyi_tv.setVisibility(View.GONE);
                    holder.mall_listitem_Cybersquatting_tv.setVisibility(View.VISIBLE);
                    holder.show_TextView.setText("商标无效");
                    holder.mall_status_img.setImageResource(R.mipmap.useless);
                    break;
                case 4://可异议
                    holder.mall_listitem_Cybersquatting_tv.setVisibility(View.GONE);
                    holder.mall_listitem_buy_tv.setVisibility(View.GONE);
                    holder.mall_listitem_yiyi_tv.setVisibility(View.VISIBLE);
                    holder.show_TextView.setText("初审公告");
                    holder.mall_status_img.setImageResource(R.mipmap.notice);
                    break;
            }
            //抢注、求购、异议按钮监听事件
            holder.mall_listitem_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("pageid", status);
                    JumpUtils.startFragmentByName(context, BuyingFragment.class, bundle);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data2.size();
    }

    //设置pageid 1--求购详情页，2--异议详情页，3--抢注详情页，传到下一页面根据id设置值

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mall_listitem_ll;
        public XCRoundRectImageView imageView;
        public TextView mallTextview;
        public TextView show_TextView;
        public TextView register_TextView;
        public TextView applicant_TexView;
        public TextView classfication_TextView;
        public TextView mall_listitem_buy_tv;
        public TextView mall_listitem_Cybersquatting_tv;
        public TextView mall_listitem_yiyi_tv;
        public ImageView mall_status_img;

        public ViewHolder(View itemView) {
            super(itemView);
            //商城item布局
            mall_listitem_ll = (LinearLayout) itemView.findViewById(R.id.mall_listitem_ll);
            //商标图片
            imageView = (XCRoundRectImageView) itemView.findViewById(R.id.mall_listitem_imageView);
            //商标名
            mallTextview = (TextView) itemView.findViewById(R.id.mall_contentTextView);
            //状态
            show_TextView = (TextView) itemView.findViewById(R.id.show_TextView);
            // 注册号
            register_TextView = (TextView) itemView.findViewById(R.id.mall_register_number);
            //申请人
            applicant_TexView = (TextView) itemView.findViewById(R.id.mall_applicantTextview);
            //分类id
            classfication_TextView = (TextView) itemView.findViewById(R.id.mall_classficationid_TextView);
            //异议按钮
            mall_listitem_yiyi_tv = (TextView) itemView.findViewById(R.id.mall_listitem_yiyi);
            //状态图片
            mall_status_img = (ImageView) itemView.findViewById(R.id.mall_status_img);
            //抢注按钮
            mall_listitem_Cybersquatting_tv = (TextView) itemView.findViewById(R.id.mall_listitem_Cybersquatting);
            //求购按钮
            mall_listitem_buy_tv = (TextView) itemView.findViewById(R.id.mall_listitem_buy);
        }
    }
}
