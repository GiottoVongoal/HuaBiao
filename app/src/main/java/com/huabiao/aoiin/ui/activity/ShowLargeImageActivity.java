package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.picview.BitmapUtil;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.BitmapLoader;
import com.ywy.mylibs.utils.ClickUtil;
import com.ywy.mylibs.utils.DeviceUtils;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.activity
 * @date 2017-08-04 18:18
 * @description
 */
public class ShowLargeImageActivity extends BaseActivity {
    @Bind(R.id.show_large_image_iv)
    ImageView large_iv;

    @Override
    public void bindView(Bundle savedInstanceState) {
        String path = getIntent().getStringExtra("path");
        int width = DeviceUtils.getScreenWidth(this);
        int height = DeviceUtils.getScreenHeight(this);

        large_iv.setImageBitmap(BitmapUtil.createImageThumbnail(path));

//        BitmapLoader.ins().loadLocalImage(path, R.mipmap.ic_launcher, large_iv, width, height);
        large_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickUtil.onBackClick();
            }
        });
    }


    @Override
    public int getContentLayout() {
        return R.layout.show_large_image_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
