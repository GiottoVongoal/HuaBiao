package com.huabiao.aoiin.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.MallBean.ShoppingmalllistBean;
import com.huabiao.aoiin.ui.fragment.BuyingFragment;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.StringUtil;

import java.util.List;

import static com.huabiao.aoiin.R.id.mall_listitem_Cybersquatting;

public class MallAdapter extends BaseAdapter implements View.OnClickListener {
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
        public TextView mall_listitem_buy_tv;
        public TextView mall_listitem_Cybersquatting_tv;
        public TextView mall_listitem_yiyi_tv;
        public ImageView mall_status_img;
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
            view = LayoutInflater.from(context).inflate(R.layout.layout_mall_list_item, viewGroup, false);
            //商标图片
            mallViewHolder.imageView = (ImageView) view.findViewById(R.id.mall_listitem_imageView);
            //商标名
            mallViewHolder.mallTextview = (TextView) view.findViewById(R.id.mall_contentTextView);
            //状态
            mallViewHolder.show_TextView = (TextView) view.findViewById(R.id.show_TextView);
            // 注册号
            mallViewHolder.register_TextView = (TextView) view.findViewById(R.id.mall_register_number);
            //申请人
            mallViewHolder.applicant_TexView = (TextView) view.findViewById(R.id.mall_applicantTextview);
            //分类id
            mallViewHolder.classfication_TextView = (TextView) view.findViewById(R.id.mall_classficationid_TextView);
            //异议按钮
            mallViewHolder.mall_listitem_yiyi_tv = (TextView) view.findViewById(R.id.mall_listitem_yiyi);
            //状态图片
            mallViewHolder.mall_status_img=(ImageView)view.findViewById( R.id.mall_status_img);
            //抢注按钮
            mallViewHolder.mall_listitem_Cybersquatting_tv = (TextView) view.findViewById(R.id.mall_listitem_Cybersquatting);
            //求购按钮
            mallViewHolder.mall_listitem_buy_tv = (TextView) view.findViewById(R.id.mall_listitem_buy);
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
        mallViewHolder.classfication_TextView.setText(mb.getClassificationid() + " - " + mb.getClassificationname());
        BitmapLoader.ins().loadImage(mb.getGoodsimg(), R.mipmap.logobg, mallViewHolder.imageView);
        //根据json中的status设置文字显示状态。1-注册成功，2-待审核中，3-商标无效，4-初审公告
        String statusString = mb.getStatus();
        if (!StringUtil.isEmpty(statusString)) {
            int status = Integer.parseInt(statusString);
            switch (status) {
                case 1:
                    mallViewHolder.mall_listitem_buy_tv.setVisibility(View.VISIBLE);
                    mallViewHolder.mall_listitem_yiyi_tv.setVisibility(View.INVISIBLE);
                    mallViewHolder.mall_listitem_Cybersquatting_tv.setVisibility(View.GONE);
                    mallViewHolder.show_TextView.setText("注册成功");
                    mallViewHolder.mall_status_img.setImageResource(R.mipmap.chenggong);
                    break;
                case 2:
                    mallViewHolder.mall_listitem_buy_tv.setVisibility(View.VISIBLE);
                    mallViewHolder.mall_listitem_yiyi_tv.setVisibility(View.INVISIBLE);
                    mallViewHolder.mall_listitem_Cybersquatting_tv.setVisibility(View.GONE);
                    mallViewHolder.show_TextView.setText("待审核中");
                    mallViewHolder.mall_status_img.setImageResource(R.mipmap.shenghezhong);
                    break;
                case 3:
                    mallViewHolder.mall_listitem_buy_tv.setVisibility(View.GONE);
                    mallViewHolder.mall_listitem_yiyi_tv.setVisibility(View.INVISIBLE);
                    mallViewHolder.mall_listitem_Cybersquatting_tv.setVisibility(View.VISIBLE);
                    mallViewHolder.show_TextView.setText("商标无效");
                    break;
                case 4:
                    mallViewHolder.mall_listitem_Cybersquatting_tv.setVisibility(View.GONE);
                    mallViewHolder.mall_listitem_buy_tv.setVisibility(View.VISIBLE);
                    mallViewHolder.mall_listitem_yiyi_tv.setVisibility(View.VISIBLE);
                    mallViewHolder.show_TextView.setText("初审公告");
                    break;
            }
        }
        //抢注、求购、异议按钮监听事件
        mallViewHolder.mall_listitem_Cybersquatting_tv.setOnClickListener(this);
        mallViewHolder.mall_listitem_buy_tv.setOnClickListener(this);
        mallViewHolder.mall_listitem_yiyi_tv.setOnClickListener(this);
        return view;
    }

    //设置pageid 1--求购详情页，2--异议详情页，3--抢注详情页，传到下一页面根据id设置值
    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.mall_listitem_buy:
                bundle.putInt("pageid", 1);
                break;
            case R.id.mall_listitem_yiyi:
                bundle.putInt("pageid", 2);
                break;
            case mall_listitem_Cybersquatting:
                bundle.putInt("pageid", 3);
                break;
        }
        JumpUtils.startFragmentByName(context,  BuyingFragment.class, bundle);
    }
}
