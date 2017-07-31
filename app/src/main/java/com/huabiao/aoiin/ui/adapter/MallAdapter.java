package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MallBean.ShoppingmalllistBean;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.StringUtil;
import com.ywy.mylibs.utils.ToastUtils;

import java.util.List;

public class MallAdapter extends BaseAdapter {
    private Context context;
    private List<ShoppingmalllistBean> data2;

    public MallAdapter(Context context, List<ShoppingmalllistBean> data2) {
        this.context = context;
        this.data2 = data2;
    }

    public class MallViewHolder {
        public ImageView imageView;
        public TextView mallTextview;
        public TextView show_TextView;
        public TextView register_TextView;
        public TextView applicant_TexView;
        public TextView classfication_TextView;
        public TextView mall_listitem_buy;
        public TextView mall_listitem_Cybersquatting;
        public TextView mall_listitem_yiyi;
    }

    public void addList(List<ShoppingmalllistBean> list) {
        data2.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data2.size();
    }

    @Override
    public Object getItem(int i) {
        return data2.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MallViewHolder mallViewHolder;
        if (view == null) {
            mallViewHolder = new MallViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.layout_mall_list_item, null);
            mallViewHolder.imageView = (ImageView) view.findViewById(R.id.mall_listitem_imageView);
            mallViewHolder.mallTextview = (TextView) view.findViewById(R.id.mall_contentTextView);
            mallViewHolder.show_TextView = (TextView) view.findViewById(R.id.show_TextView);
            mallViewHolder.register_TextView = (TextView) view.findViewById(R.id.mall_register_number);
            mallViewHolder.applicant_TexView = (TextView) view.findViewById(R.id.mall_applicantTextview);
            mallViewHolder.classfication_TextView = (TextView) view.findViewById(R.id.mall_classficationid_TextView);
            mallViewHolder.mall_listitem_yiyi = (TextView) view.findViewById(R.id.mall_listitem_yiyi);
            mallViewHolder.mall_listitem_Cybersquatting = (TextView) view.findViewById(R.id.mall_listitem_Cybersquatting);
            mallViewHolder.mall_listitem_buy = (TextView) view.findViewById(R.id.mall_listitem_buy);
            view.setTag(mallViewHolder);

        } else {
            mallViewHolder = (MallViewHolder) view.getTag();
        }
        //加载数据
        final ShoppingmalllistBean mb = data2.get(i);
        mallViewHolder.mallTextview.setText(mb.getGoodsname());
        mallViewHolder.show_TextView.setText(mb.getStatus());
        mallViewHolder.register_TextView.setText("注册号：" + mb.getRegnumber());
        mallViewHolder.applicant_TexView.setText("申请人：" + mb.getApplicantname());
        mallViewHolder.classfication_TextView.setText(mb.getClassificationid() + "类" + "-" + mb.getClassificationname());
        BitmapLoader.ins().loadImage(mb.getGoodsimg(), R.mipmap.perter_portrait, mallViewHolder.imageView);
        String statusString = mb.getStatus();
        if (!StringUtil.isEmpty(statusString)) {
            int status = Integer.parseInt(statusString);
            switch (status) {
                case 1:
                    mallViewHolder.mall_listitem_buy.setVisibility(View.VISIBLE);
                    mallViewHolder.mall_listitem_yiyi.setVisibility(View.INVISIBLE);
                    mallViewHolder.mall_listitem_Cybersquatting.setVisibility(View.GONE);
                    mallViewHolder.show_TextView.setText("注册成功");
                    break;
                case 2:
                    mallViewHolder.mall_listitem_buy.setVisibility(View.VISIBLE);
                    mallViewHolder.mall_listitem_yiyi.setVisibility(View.INVISIBLE);
                    mallViewHolder.mall_listitem_Cybersquatting.setVisibility(View.GONE);
                    mallViewHolder.show_TextView.setText("待审核中");
                    break;
                case 3:
                    mallViewHolder.mall_listitem_buy.setVisibility(View.GONE);
                    mallViewHolder.mall_listitem_yiyi.setVisibility(View.INVISIBLE);
                    mallViewHolder.mall_listitem_Cybersquatting.setVisibility(View.VISIBLE);
                    mallViewHolder.show_TextView.setText("商标无效");
                    break;
                case 4:
                    mallViewHolder.mall_listitem_buy.setVisibility(View.VISIBLE);
                    mallViewHolder.mall_listitem_yiyi.setVisibility(View.VISIBLE);
                    mallViewHolder.show_TextView.setText("初审公告");
                    break;
            }
        }
        mallViewHolder.mallTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getInstance().showToast(mb.getGoodsname());
            }
        });
        return view;
    }
}
