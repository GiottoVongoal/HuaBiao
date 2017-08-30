package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.HomeBean;
import com.huabiao.aoiin.bean.HomeBean.BannarlistBean;
import com.huabiao.aoiin.bean.HomeBean.HomeinfolistBean;
import com.huabiao.aoiin.bean.HomeBean.HotwordslistBean;
import com.huabiao.aoiin.bean.TestBean;
import com.huabiao.aoiin.model.HomeModel;
import com.huabiao.aoiin.retrofit.RetrofitClinetImpl;
import com.huabiao.aoiin.ui.activity.MainActivity;
import com.huabiao.aoiin.ui.activity.UserProgressActivity;
import com.huabiao.aoiin.ui.adapter.BannerAdapter;
import com.huabiao.aoiin.ui.adapter.HotWordsAdapter;
import com.huabiao.aoiin.ui.adapter.InfomationAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.retrofit.RetrofitClient;
import com.ywy.mylibs.utils.DeviceUtils;
import com.ywy.mylibs.utils.JumpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.ResponseBody;

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
    //按钮
    @Bind(R.id.home_search_ll)
    LinearLayout home_search_ll;
    @Bind(R.id.home_creat_name_ll)
    LinearLayout home_creat_name_ll;
    @Bind(R.id.home_register_ll)
    LinearLayout home_register_ll;
    @Bind(R.id.home_progress_ll)
    LinearLayout home_progress_ll;
    //资讯
    @Bind(R.id.home_information_rv)
    RecyclerView home_information_rv;
    private InfomationAdapter infomationAdapter;
    //热搜词
    @Bind(R.id.home_hot_words_rv)
    RecyclerView home_hot_words_rv;
    private HotWordsAdapter hotWordsAdapter;

    @Override
    public void bindView(Bundle savedInstanceState) {
        home_search_et.setOnClickListener(this);

        home_search_ll.setOnClickListener(this);
        home_creat_name_ll.setOnClickListener(this);
        home_register_ll.setOnClickListener(this);
        home_progress_ll.setOnClickListener(this);

        HomeModel.getHomeData(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    HomeBean bean = (HomeBean) mData;
                    bannarList = bean.getBannarlist();
                    initBannar();
                    showHotWords(bean);
                    showInfo(bean);
                }
            }
        });
    }

    //获取资讯
    private void showInfo(HomeBean bean) {
        List<HomeinfolistBean> infoList = bean.getHomeinfolist();
        infomationAdapter = new InfomationAdapter(getContext(), infoList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        home_information_rv.setLayoutManager(linearLayoutManager);
        home_information_rv.setAdapter(infomationAdapter);
    }

    //获取热搜词
    private void showHotWords(HomeBean bean) {
        List<HotwordslistBean> howWordList = bean.getHotwordslist();
        hotWordsAdapter = new HotWordsAdapter(getContext(), howWordList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        home_hot_words_rv.setLayoutManager(linearLayoutManager);
        home_hot_words_rv.setAdapter(hotWordsAdapter);
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
//            BitmapLoader.ins().loadImage(bannarList.get(i).getPageUrl(), R.mipmap.touxiang, imageView);
            imageView.setBackground(getContext().getResources().getDrawable(R.mipmap.banner));
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

        Map<String, String> source = new HashMap<>();
        source.put("name", "aaa");
        RetrofitClinetImpl.getInstance(getContext())
                .newRetrofitClient()
                .postL("api/v2/wallet"
                        , source
                        , new RetrofitClient.ResponseCallBack<ResponseBody>() {
                            @Override
                            public void onSucceess(ResponseBody response) {
                                try {
                                    String string = new String(response.bytes());
                                    ALog.i("RetrofitClinetImpl---string--->" + string);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Throwable throwable) {
                                if (throwable != null) {
                                    ALog.i("RetrofitClinetImpl---throwable--->" + throwable.getMessage());
                                }
                            }
                        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_search_et:
                //输入框
                ((MainActivity) getContext()).setItem(1);
                break;
            case R.id.home_search_ll:
                //查询
                JumpUtils.startFragmentByName(getContext(), SearchFragment.class);
                break;
            case R.id.home_creat_name_ll:
                //取名
                JumpUtils.startFragmentByName(getContext(), DenominateFragment.class);
//                JumpUtils.startFragmentByName(getContext(), TestRecyclerViewFragment.class);
                break;
            case R.id.home_register_ll:
                //注册
                JumpUtils.startFragmentByName(getContext(), RegisterFragment.class);
                break;
            case R.id.home_progress_ll:
                //进度
                JumpUtils.startActivity(getContext(), UserProgressActivity.class);
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
