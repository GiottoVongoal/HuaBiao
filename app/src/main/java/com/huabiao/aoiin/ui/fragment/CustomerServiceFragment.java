package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CustomerServiceListBean;
import com.huabiao.aoiin.bean.CustomerServiceListBean.CustomerservicelistBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.CustomServiceCardAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.ui.view.CustomServiceItemView;
import com.huabiao.aoiin.wedgit.ShadowTransformer;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-31 16:59
 * @description 客服列表
 */
public class CustomerServiceFragment extends BaseFragment {
    @Bind(R.id.customer_service_vp)
    ViewPager customer_service_vp;
    CustomServiceCardAdapter mAdapter;
    ShadowTransformer mCardShadowTransformer;

    private List<RelativeLayout> vpList;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("客服列表");
        setBackEnable();

        SearchModel.getCustomerSerCustomerServiceList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    CustomerServiceListBean cb = (CustomerServiceListBean) mData;
                    List<CustomerservicelistBean> list = cb.getCustomerservicelist();
                    showData(list);
                }
            }
        });
    }

    private void showData(List<CustomerservicelistBean> list) {
        //vp相关初始化
        vpList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CustomServiceItemView view = new CustomServiceItemView(getContext());
            view.getShowData(list.get(i));
            vpList.add(view);
        }
        mAdapter = new CustomServiceCardAdapter();
        for (int i = 0; i < list.size(); i++) {
            mAdapter.addCardItem(list.get(i));
        }
//        mCardAdapter.setCardItem(vpList);
        mCardShadowTransformer = new ShadowTransformer(customer_service_vp, mAdapter);
        mCardShadowTransformer.enableScaling(true);
        customer_service_vp.setAdapter(mAdapter);
        customer_service_vp.setPageTransformer(false, mCardShadowTransformer);
        customer_service_vp.setOffscreenPageLimit(3);
        customer_service_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // arg0 :当前页面，及你点击滑动的页面；arg1:当前页面偏移的百分比；arg2:当前页面偏移的像素位置
            }

            @Override
            public void onPageSelected(int position) {
                //页面跳转完后被调用，arg0是你当前选中的页面的Position（位置编号）
                for (int i = 0; i < vpList.size(); i++) {
                    ALog.i(i + "==" + (vpList.get(i) == null ? "null" : "notNull"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做。
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.customer_service_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
