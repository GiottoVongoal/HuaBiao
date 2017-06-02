package com.huabiao.aoiin.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.CameraUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by PC on 2017/5/24.
 */

public class TestFragment extends BaseFragment {

    @Bind(R.id.textView)
    TextView textView;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Environment.getExternalStorageDirectory().getPath();
                Uri filePath = Uri.parse(url);
                startActivity(CameraUtils.getCameraIntent(filePath));
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }
}
