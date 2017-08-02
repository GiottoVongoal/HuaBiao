package com.huabiao.aoiin.ui.activity;


import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.constant.FlagBase;
import com.huabiao.aoiin.ui.fragment.RegisterCardOneView;
import com.huabiao.aoiin.ui.fragment.RegisterCardTwoView;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ChangeRegisterIndexEvent;
import com.huabiao.aoiin.wedgit.JazzyViewPager;
import com.huabiao.aoiin.wedgit.OutlineContainer;
import com.squareup.otto.Bus;
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
    @Bind(R.id.register_jazzy_vp)
    JazzyViewPager register_jazzy_vp;

    private List<View> vpList;

    private RegisterCardOneView cardOne;
    private RegisterCardTwoView cardTwo;

    private String tradename, industry;
    private int pageIndex;//1查询;2注册
    private String selectClassify;//在查询页面选择的分类大类名称

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("注册");
        setBackEnable();

        AppBus.getInstance().register(this);//注册

        //卡片初始化
        cardOne = new RegisterCardOneView(this);
        cardTwo = new RegisterCardTwoView(this);
        //vp相关初始化
        vpList = new ArrayList<>();
        vpList.add(cardOne);
        vpList.add(cardTwo);

        register_jazzy_vp.setTransitionEffect(JazzyViewPager.TransitionEffect.ZoomIn);
        register_jazzy_vp.setFadeEnabled(true);
        register_jazzy_vp.setPageMargin(0);
        register_jazzy_vp.setAdapter(new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View view, Object object) {
                if (view instanceof OutlineContainer) {
                    return ((OutlineContainer) view).getChildAt(0) == object;
                } else {
                    return view == object;
                }
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(register_jazzy_vp.findViewFromObject(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(vpList.get(position));
                register_jazzy_vp.setObjectForPosition(vpList.get(position),
                        position);
                return vpList.get(position);
            }

            @Override
            public int getCount() {
                return vpList.size();
            }
        });

    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getIntent().getExtras();
        tradename = bundle.getString("tradename");
        industry = bundle.getString("industry");
        pageIndex = bundle.getInt("pageIndex", 2);//1查询;2注册第一步
        selectClassify = bundle.getString("selectClassify");
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

    @Subscribe
    public void setIndex(ChangeRegisterIndexEvent event) {
        register_jazzy_vp.setCurrentItem(event.getIndex());
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
