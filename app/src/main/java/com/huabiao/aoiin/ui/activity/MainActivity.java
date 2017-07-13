package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.ui.fragment.BottonNavigationViewFragment;
import com.huabiao.aoiin.ui.fragment.MeFragment;
import com.huabiao.aoiin.ui.fragment.RegisterOneFragment;
import com.huabiao.aoiin.ui.fragment.SearchFragment;
import com.huabiao.aoiin.wedgit.BottomNavigationViewHelper;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.botton_navi_view)
    BottomNavigationView botton_navi_view;
    @Bind(R.id.botton_fl)
    FrameLayout botton_fl;

    Fragment RegisterOneFragment, SearchFragment, MeFragment;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        BottomNavigationViewHelper.disableShiftMode(botton_navi_view);//点击效果和三个item时的效果相同

        MeFragment = new MeFragment();
        RegisterOneFragment = new RegisterOneFragment();
        SearchFragment = new SearchFragment();

        File newFile = new File(Environment.getExternalStorageDirectory().getPath() + "/music/", "5816.mp3");
        if (newFile.exists()) {
            ALog.i("newFile.exists()");
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(newFile);
            } catch (FileNotFoundException e) {
                ALog.i("FileNotFoundException e : " + e.toString());
            }
            if (fis == null) {
                ALog.i("fis==null");
            } else {
                ALog.i("fis!=null");
            }
        } else {
            ALog.i("!newFile.exists()");
        }
        botton_navi_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_creat_name:
                        addFragment(BottonNavigationViewFragment.newInstance("拨号"));
                        break;
                    case R.id.menu_item_registered:
                        addFragment(RegisterOneFragment);
                        break;
                    case R.id.menu_item_search:
                        addFragment(SearchFragment);
                        break;
                    case R.id.menu_item_hot_world:
                        addFragment(BottonNavigationViewFragment.newInstance("热搜词"));
                        break;
                    case R.id.menu_item_my:
                        addFragment(MeFragment);
                        break;
                }
                return true;//返回 true 使点击有效
            }
        });
        //默认进来选中第三个
        addFragment(SearchFragment);
        botton_navi_view.getMenu().getItem(2).setChecked(true);
    }

    private void addFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tran = manager.beginTransaction();
        tran.replace(R.id.botton_fl, fragment);
        tran.commit();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }
}
