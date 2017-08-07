package com.huabiao.aoiin.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.tools.ActivityCollector;
import com.huabiao.aoiin.ui.adapter.CardPagerAdapter;
import com.huabiao.aoiin.ui.fragment.RegisterDataPreviewActivity;
import com.huabiao.aoiin.ui.ottobus.ToNextPageEvent;
import com.huabiao.aoiin.ui.view.RegisterCardBaseView;
import com.huabiao.aoiin.ui.view.RegisterCardFiveView;
import com.huabiao.aoiin.ui.view.RegisterCardFourView;
import com.huabiao.aoiin.ui.view.RegisterCardOneView;
import com.huabiao.aoiin.ui.view.RegisterCardThreeView;
import com.huabiao.aoiin.ui.view.RegisterCardTwoView;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ChangeRegisterIndexEvent;
import com.huabiao.aoiin.wedgit.ShadowTransformer;
import com.squareup.otto.Subscribe;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.huabiao.aoiin.R.id.register_card_vp_points;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-21 15:57
 * @description 默认注册页面
 */
public class RegisterActivity extends BaseActivity {

    private List<RegisterCardBaseView> vpList;

    @Bind(R.id.register_card_vp)
    ViewPager register_card_vp;
    CardPagerAdapter mCardAdapter;
    ShadowTransformer mCardShadowTransformer;

    private int pointIndex = 0;// 圆圈标志位
    @Bind(R.id.register_card_vp_points)
    LinearLayout vp_points;

    private RegisterCommitBean commitBean;//提交注册的所有数据

    private RegisterCardOneView cardOne;
    private RegisterCardTwoView cardTwo;
    private RegisterCardThreeView cardThree;
    private RegisterCardFourView cardFour;
    private RegisterCardFiveView cardFive;

//    private String tradename, industry;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("注册");
        setBackEnable();

        commitBean = RegisterCommitBean.getInstance();
        ActivityCollector.addActivity(this);

        AppBus.getInstance().register(this);//注册

        //卡片初始化
        cardOne = new RegisterCardOneView(this);
        cardTwo = new RegisterCardTwoView(this);
        cardThree = new RegisterCardThreeView(this);
        cardFour = new RegisterCardFourView(this);
        cardFive = new RegisterCardFiveView(this);
        //vp相关初始化
        vpList = new ArrayList<>();
        vpList.add(cardOne);
        vpList.add(cardTwo);
        vpList.add(cardThree);
        vpList.add(cardFour);
        vpList.add(cardFive);

        // 设置圆圈点
        for (int i = 0; i < vpList.size(); i++) {
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
            params.leftMargin = 15;
            view.setBackgroundResource(R.drawable.register_point_background);
            view.setLayoutParams(params);
            view.setEnabled(false);
            vp_points.addView(view);
        }

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.setCardItem(vpList);
        mCardShadowTransformer = new ShadowTransformer(register_card_vp, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);
        register_card_vp.setAdapter(mCardAdapter);
        register_card_vp.setPageTransformer(false, mCardShadowTransformer);
        register_card_vp.setOffscreenPageLimit(3);
        vp_points.getChildAt(pointIndex).setEnabled(true);
        register_card_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // arg0 :当前页面，及你点击滑动的页面；arg1:当前页面偏移的百分比；arg2:当前页面偏移的像素位置
            }

            @Override
            public void onPageSelected(int position) {
                //页面跳转完后被调用，arg0是你当前选中的页面的Position（位置编号）
                if (position > 0) {
                    vpList.get(position - 1).save();
                }
                for (int i = 0; i < vpList.size(); i++) {
                    ALog.i(i + "==" + (vpList.get(i) == null ? "null" : "notNull"));
                }
                int newPosition = position % vpList.size();
                vp_points.getChildAt(newPosition).setEnabled(true);
                vp_points.getChildAt(pointIndex).setEnabled(false);
                // 更新标志位
                pointIndex = newPosition;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做。
            }
        });
    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getIntent().getExtras();
//        tradename = bundle.getString("tradename");
//        industry = bundle.getString("industry");
    }

    @Override
    public void onResume() {
        super.onResume();
        cardThree.setPersonTypeSelect(commitBean.getPersonType() - 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cardFour.onActivityResult(requestCode, data);
    }

    /**
     * 实现点击卡片中下一步按钮跳转下个Item
     *
     * @param event
     */
    @Subscribe
    public void setIndex(ChangeRegisterIndexEvent event) {
        register_card_vp.setCurrentItem(event.getIndex());
    }

    @Subscribe
    public void toNextPage(ToNextPageEvent event) {
        if (!commitBean.isNull()) {
            JumpUtils.startActivity(this, RegisterDataPreviewActivity.class);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.register_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppBus.getInstance().unregister(this);
    }
}
