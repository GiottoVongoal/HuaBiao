package com.huabiao.aoiin.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Bind(R.id.textView)
    TextView textView;

    @Override
    public void bindView(Bundle savedInstanceState) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }
}
