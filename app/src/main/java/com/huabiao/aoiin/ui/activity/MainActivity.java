package com.huabiao.aoiin.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.ui.fragment.FinanceFragment;
import com.huabiao.aoiin.ui.fragment.HomePageFragment;
import com.huabiao.aoiin.ui.fragment.MallFragment;
import com.huabiao.aoiin.ui.fragment.MeFragment;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ToSearchMallPageEvent;
import com.huabiao.aoiin.wedgit.BottomNavigationViewHelper;
import com.squareup.otto.Subscribe;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

//    @Bind(R.id.home_rg)
//    RadioGroup home_rg;

//    @Bind(R.id.home_shouye_rb)
//    RadioButton home_shouye_rb;
//    @Bind(R.id.home_mall_rb)
//    RadioButton home_mall_rb;
//    @Bind(R.id.home_finance_rb)
//    RadioButton home_finance_rb;
//    @Bind(R.id.home_me_rb)
//    RadioButton home_me_rb;

    @Bind(R.id.botton_navi_view)
    BottomNavigationView botton_navi_view;
    @Bind(R.id.botton_fl)
    FrameLayout botton_fl;

    Fragment homePageFragment, mallFragment, financeFragment, meFragment;

    @Override
    public void bindView(Bundle savedInstanceState) {
        BottomNavigationViewHelper.disableShiftMode(botton_navi_view);//点击效果和三个item时的效果相同
        homePageFragment = new HomePageFragment();
        mallFragment = new MallFragment();
        financeFragment = new FinanceFragment();
        meFragment = new MeFragment();

        AppBus.getInstance().register(this);//注册事件

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

//        home_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//                if (i == home_shouye_rb.getId()) {
//                    //首页
//                    setItem(0);
//                } else if (i == home_mall_rb.getId()) {
//                    //商城
//                    setItem(1);
//                } else if (i == home_finance_rb.getId()) {
//                    //金融
//                    setItem(2);
//                } else if (i == home_me_rb.getId()) {
//                    //我的
//                    setItem(3);
//                }
//            }
//        });
//        home_rg.check(home_shouye_rb.getId());
        setItem(0);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //进行授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void setItem(int index) {
        switch (index) {
            case 0:
//                home_rg.check(home_shouye_rb.getId());
                addFragment(homePageFragment);
                break;
            case 1:
//                home_rg.check(home_mall_rb.getId());
                addFragment(mallFragment);
                break;
            case 2:
//                home_rg.check(home_finance_rb.getId());
                addFragment(financeFragment);
                break;
            case 3:
//                home_rg.check(home_me_rb.getId());
                addFragment(meFragment);
                break;
        }
    }

    private void addFragment(Fragment fragment) {
//        Slide slideTransition = new Slide(Gravity.LEFT);
//        slideTransition.setDuration(300);
//        ChangeBounds changeBoundsTransition = new ChangeBounds();
//        changeBoundsTransition.setDuration(300);
        // Create fragment and define some of it transitions
//        fragment.setReenterTransition(slideTransition);
//        fragment.setExitTransition(slideTransition);
//        fragment.setSharedElementEnterTransition(changeBoundsTransition);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tran = manager.beginTransaction();
        tran.replace(R.id.botton_fl, fragment);
        tran.commitAllowingStateLoss();
    }

    /**
     * 跳转到商城页面
     *
     * @param event
     */
    @Subscribe
    public void ToSearcmMallPageEvent(ToSearchMallPageEvent event) {
        botton_navi_view.setSelectedItemId(botton_navi_view.getMenu().getItem(event.getIndex()).getItemId());//设置选中的item效果
        setItem(event.getIndex());
        ((MallFragment) mallFragment).SearchMallEvent(event.getSearchStr());
        if (mallFragment.isVisible())
            return;
        Bundle bundle = new Bundle();
        bundle.putString("search", event.getSearchStr());
        mallFragment.setArguments(bundle);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    private static Boolean isExit = false;// 退出

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // finish();
            // System.exit(0);
            Timer tExit = null;
            if (isExit == false) {
                isExit = true;
                Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                tExit = new Timer();
                tExit.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false; // 取消退出
                    }
                }, 2000);
            } else {
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppBus.getInstance().unregister(this);//解除注册事件
    }
}
