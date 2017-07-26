package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.HomeBannarBean;
import com.huabiao.aoiin.bean.HomeBannarBean.BannarlistBean;
import com.huabiao.aoiin.model.HomeModel;
import com.huabiao.aoiin.ui.activity.MainActivity;
import com.huabiao.aoiin.ui.adapter.BannerAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.JumpUtils;

import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.huabiao.aoiin.R.id.center_vertical;
import static com.huabiao.aoiin.R.id.view;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-20 09:56
 * @description 首页
 */
public class HomePageFragment extends BaseFragment implements View.OnClickListener {
    //Banner
    @Bind(R.id.home_bannar_vp)
    ViewPager home_bannar_vp;
    @Bind(R.id.home_bannar_title_tv)
    TextView home_bannar_title_tv;
    @Bind(R.id.home_bannar_points)
    LinearLayout home_bannar_points;
    private Handler handler;
    private BannerListener bannarListener;
    private int pointIndex = 0;// 圆圈标志位
    private boolean isStop = false;// 线程标志
    private List<BannarlistBean> bannarList;
    private BannerAdapter mAdapter;
    private List<ImageView> mlist;
    //输入框
    @Bind(R.id.home_search_et)
    EditText home_search_et;
    @Bind(R.id.home_search_do_tv)
    TextView home_search_do_tv;
    //按钮
    @Bind(R.id.home_search_cv)
    CardView home_search_tv;
    @Bind(R.id.home_register_cv)
    CardView home_register_tv;
    @Bind(R.id.home_creat_name_cv)
    CardView home_creat_name_tv;
    @Bind(R.id.home_progress_cv)
    CardView home_progress_tv;

    @Override
    public void bindView(Bundle savedInstanceState) {
        home_search_et.setOnClickListener(this);
        home_search_do_tv.setOnClickListener(this);

        home_search_tv.setOnClickListener(this);
        home_register_tv.setOnClickListener(this);
        home_creat_name_tv.setOnClickListener(this);
        home_progress_tv.setOnClickListener(this);

        HomeModel.getBannarList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    HomeBannarBean bean = (HomeBannarBean) mData;
                    bannarList = bean.getBannarlist();
                    initBannar();
                }
            }
        });
    }

    private void initBannar() {
        if (mlist != null)
            return;
        mlist = new ArrayList<>();
        View view;
        LayoutParams params;
        for (int i = 0; i < bannarList.size(); i++) {
            // 设置广告图
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            BitmapLoader.ins().loadImage(bannarList.get(i).getPageUrl(), R.mipmap.perter_portrait, imageView);
            mlist.add(imageView);
            // 设置圆圈点
            view = new View(getContext());
            params = new LayoutParams(15, 15);
            params.leftMargin = 10;
            view.setBackgroundResource(R.drawable.bannar_point_background);
            view.setLayoutParams(params);
            view.setEnabled(false);
            home_bannar_points.addView(view);
        }
        mAdapter = new BannerAdapter(mlist);
        home_bannar_vp.setAdapter(mAdapter);

        bannarListener = new BannerListener();
        home_bannar_vp.setOnPageChangeListener(bannarListener);
        //取中间数来作为起始位置
//        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % bannarList.size());
        //用来出发监听器
        home_bannar_vp.setCurrentItem(0);
        home_bannar_points.getChildAt(pointIndex).setEnabled(true);

        handler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                // super.handleMessage(msg);
                //更新当前的ViewPager 要显示的当前条目
                home_bannar_vp.setCurrentItem((home_bannar_vp.getCurrentItem() + 1) % 3);
            }
        };
        //实现自动切换功能
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (!isStop) {
                    SystemClock.sleep(2000);
                    //拿着我们创建的handler发消息
                    handler.sendEmptyMessage(0);
                }
            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_search_et:
            case R.id.home_search_do_tv:
                //输入框
                ((MainActivity) getContext()).setItem(1);
                break;
            case R.id.home_search_cv:
                //查询
                JumpUtils.startFragmentByName(getContext(), SearchFragment.class);
                break;
            case R.id.home_register_cv:
                //注册
                JumpUtils.startFragmentByName(getContext(), RegisterOneFragment.class);
                break;
            case R.id.home_creat_name_cv:
                //取名
                JumpUtils.startFragmentByName(getContext(), TestFragment.class);
                break;
            case R.id.home_progress_cv:
                //进度
                JumpUtils.startFragmentByName(getContext(), UserProgressFragment.class);
                break;
        }
    }

    //实现VierPager监听器接口
    class BannerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            int newPosition = position % bannarList.size();
//            home_bannar_title_tv.setText(bannarList.get(newPosition).getPagetitle());
            home_bannar_points.getChildAt(newPosition).setEnabled(true);
            home_bannar_points.getChildAt(pointIndex).setEnabled(false);
            // 更新标志位
            pointIndex = newPosition;
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.home_page_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
