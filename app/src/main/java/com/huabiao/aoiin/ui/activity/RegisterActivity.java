package com.huabiao.aoiin.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.constant.FlagBase;
import com.huabiao.aoiin.ui.adapter.CardPagerAdapter;
import com.huabiao.aoiin.ui.fragment.RegisterCardOneView;
import com.huabiao.aoiin.ui.fragment.RegisterCardThreeView;
import com.huabiao.aoiin.ui.fragment.RegisterCardTwoView;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ChangeRegisterIndexEvent;
import com.huabiao.aoiin.wedgit.ShadowTransformer;
import com.squareup.otto.Subscribe;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-21 15:57
 * @description 默认注册页面
 */
public class RegisterActivity extends BaseActivity {

    private List<CardView> vpList;

    @Bind(R.id.register_card_vp)
    ViewPager register_card_vp;
    CardPagerAdapter mCardAdapter;
    ShadowTransformer mCardShadowTransformer;

    private RegisterCardOneView cardOne;
    private RegisterCardTwoView cardTwo;
    private RegisterCardThreeView cardThree;

//    private String tradename, industry;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("注册");
        setBackEnable();

        AppBus.getInstance().register(this);//注册

        //卡片初始化
        cardOne = new RegisterCardOneView(this);
        cardTwo = new RegisterCardTwoView(this);
        cardThree = new RegisterCardThreeView(this);
        //vp相关初始化
        vpList = new ArrayList<>();
        vpList.add(cardOne);
        vpList.add(cardTwo);
        vpList.add(cardThree);

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.setCardItem(vpList);
        mCardShadowTransformer = new ShadowTransformer(register_card_vp, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);
        register_card_vp.setAdapter(mCardAdapter);
        register_card_vp.setPageTransformer(false, mCardShadowTransformer);
        register_card_vp.setOffscreenPageLimit(3);
    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getIntent().getExtras();
//        tradename = bundle.getString("tradename");
//        industry = bundle.getString("industry");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FlagBase.MEDIA_PHOTO:// 拍照
//                String filePath = Path + folderName + File.separator + "trademark_logo.jpg";
//                ALog.i("filePath = " + filePath);
//                File file = new File(filePath);
//                if (file.exists()) {
//                    Bitmap mBitmap = BitmapUtil.createImageThumbnail(filePath);
//                    int degree = mMediaView.loadImageDegree(filePath);
//                    Bitmap bitmap = mMediaView.rotateBitmap(mBitmap, degree);
//                    mMediaView.saveBitmap(bitmap, filePath);
//                    trademark_logo_iv.setImageBitmap(bitmap);
//                }
                break;
            case FlagBase.MEDIA_SPHOTO:// 选照
//                if (data != null && data.getData() != null) {
//                    Bitmap bitmap = mMediaView.selectPhotoSave(data, folderName, "trademark_logo");
//                    trademark_logo_iv.setImageBitmap(bitmap);
//                }
                break;
        }
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
