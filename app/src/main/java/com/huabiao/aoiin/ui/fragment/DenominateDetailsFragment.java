package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.SearchResult;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.DenominateDetailsAdapter;
import com.huabiao.aoiin.ui.adapter.UpMenuAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.huabiao.aoiin.wedgit.FullyLinearLayoutManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

import static com.umeng.analytics.pro.x.R;


/**
 * Created by Aoiin-9 on 2017/8/11.
 */

public class DenominateDetailsFragment extends BaseFragment implements View.OnClickListener {
    private DenominateDetailsAdapter detailsAdapter;

    //可注册按钮
    @Bind(R.id.details_tv1)
    TextView details_tv1;
    //筛选按钮
    @Bind(R.id.details_tv2)
    TextView details_tv2;
    //分类的两个框
    @Bind(R.id.denominate_details_rcv)
    RecyclerView denominate_details_rcv;
    //折线图
    @Bind(R.id.details_linechart)
    DrawLineChartView details_linechart;
    //释义
    @Bind(R.id.details_content)
    TextView details_content;
    //注册按钮
    @Bind(R.id.details_register_tv)
    TextView details_register_tv;
    //用来传递标题的nameString
    private String nameString;
    //筛选下拉菜单
    private PopupWindow shaixuan;
    //下拉展示菜单的Adapter
    private UpMenuAdapter menuAdapter;
    //未收藏
    private boolean flag = false;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getActivity().getIntent().getExtras();
        nameString = bundle.getString("nameString");
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        //三个按钮的监听
        details_tv1.setOnClickListener(this);
        details_tv2.setOnClickListener(this);
        details_register_tv.setOnClickListener(this);
        //标题的右边图片以及监听事件
        setTitle(nameString);
        setBackEnable();
        //收藏图片
        final ImageView shoucang = setRightIvResourse(R.mipmap.shoucang2);
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    setRightIvResourse(getResources().getDrawable(R.mipmap.shoucang2));
                    flag = false;
                    showToast("取消");
                } else {
                    setRightIvResourse(getResources().getDrawable(R.mipmap.shoucang1));
                    flag = true;
                    showToast("收藏");
                }
            }
        });
        SearchModel.getCreatName(getContext(), "", "", new InterfaceManager.CallBackCommon() {

            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    SearchResult bean = (SearchResult) mData;
                    denominate_details_rcv.setLayoutManager(new FullyLinearLayoutManager(getContext()));
                    detailsAdapter = new DenominateDetailsAdapter(bean.getClassification());
                    denominate_details_rcv.setAdapter(detailsAdapter);
                    details_content.setText(bean.getMeans());
                    details_linechart.setLineChartBean(bean.getLinechart());
                }
            }
        });
    }

    private void initData(String trademarkId) {
    }
//    private void initShaixuan(final List<ScreenclassficationBean.SlistBean>list) {//下拉筛选菜单相关
//            View contentView = View.inflate(getContext(), R.layout.popwin_supplier_list, null);
//        shaixuan = new PopupWindow(contentView,
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.MATCH_PARENT);
//        shaixuan.setOutsideTouchable(true);
//        shaixuan.setBackgroundDrawable(new BitmapDrawable());
//        shaixuan.setFocusable(true);
//        shaixuan.setAnimationStyle(R.style.popwin_anim_style);
//        shaixuan.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                public void onDismiss() {
//                    details_tv2.setTextColor(getResources().getColor(R.color.black3));
//                }
//            });
//        details_tv2.setText(list.get(0).getClassificationname());//默认显示第一个类别
//            initData(list.get(0).getClassificationid());//根据类别获取商标进度数据
//            popRecyclerView = (RecyclerView) contentView
//                    .findViewById(R.id.popwin_supplier_list_rv);
//            contentView.findViewById(R.id.popwin_supplier_list_bottom)
//                    .setOnClickListener(new View.OnClickListener() {
//                        public void onClick(View arg0) {
//                            shaixuan.dismiss();
//                        }
//                    });
//            popRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            List<String> l = new ArrayList<>();
//            for (int i = 0; i < list.size(); i++) {
//                l.add(list.get(i).getClassificationname());
//            }
//            menuAdapter = new UpMenuAdapter(getContext(), l);
//            menuAdapter.setOnItemClickListener(new InterfaceManager.OnItemClickListener() {
//                @Override
//                public void onItemClickListener(View view, int position) {
//                    shaixuan.dismiss();
//                    details_tv2.setText(getContext().get(position).getTrademarkname());
//                    showToast(list.get(position).getTrademarkname());
//                }
//            });
//    }

    @Override
    public int getContentLayout() {
        return R.layout.layout_denominate_details;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.details_register_tv:
                showToast("注册按钮");
                break;
            case R.id.details_tv1:
                showToast("可注册按钮");
                break;
            case R.id.details_tv2:
                showToast("筛选按钮");
                break;
        }
    }
}
