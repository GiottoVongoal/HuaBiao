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
import com.huabiao.aoiin.ui.fragment.HomePageFragment;
import com.huabiao.aoiin.ui.fragment.MeFragment;
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

    Fragment HomePageFragment, Mall, MeFragment, Customerservicelist;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        BottomNavigationViewHelper.disableShiftMode(botton_navi_view);//点击效果和三个item时的效果相同
        HomePageFragment = new HomePageFragment();
        Mall = new Mall();
        Customerservicelist = new Customerservicelist();
        MeFragment = new MeFragment();

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
//            ALog.i("!newFile.exists()");
        }
        botton_navi_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_one:
                        setItem(0);
                        break;
                    case R.id.menu_item_two:
                        setItem(1);
                        break;
                    case R.id.menu_item_three:
                        setItem(2);
                        break;
                    case R.id.menu_item_four:
                        setItem(3);
                        break;
                }
                return true;//返回 true 使点击有效
            }
        });
        setItem(0);
    }

    public void setItem(int index) {
        switch (index) {
            case 0:
                addFragment(HomePageFragment);
                break;
            case 1:
                addFragment(Mall);
                break;
            case 2:
                addFragment(Customerservicelist);
                break;
            case 3:
                addFragment(MeFragment);
                break;
        }
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
