package com.huabiao.aoiin.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.ui.fragment.BottonNavigationViewFragment;
import com.huabiao.aoiin.ui.fragment.TestFragment;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.huabiao.aoiin.R.id.textView;

public class MainActivity extends BaseActivity {
    private MenuItem menuItem;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Bind(R.id.botton_navi_view)
    BottomNavigationView botton_navi_view;
    @Bind(R.id.botton_viewpager)
    ViewPager botton_viewpager;


    @Override
    public void bindView(Bundle savedInstanceState) {
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                JumpUtils.startFragmentByName(MainActivity.this, TestFragment.class);
//            }
//        });
//        BottomNavigationViewHelper.disableShiftMode(botton_navi_view);//点击效果和三个item时的效果相同
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
                    case R.id.menu_item_one:
                        botton_viewpager.setCurrentItem(0);
                        break;
                    case R.id.menu_item_two:
                        botton_viewpager.setCurrentItem(1);
                        break;
                    case R.id.menu_item_three:
                        botton_viewpager.setCurrentItem(2);
                        break;
                    case R.id.menu_item_four:
                        botton_viewpager.setCurrentItem(3);
                        break;
                    case R.id.menu_item_five:
                        botton_viewpager.setCurrentItem(4);
                        break;
                }
                return true;//返回 true 使点击有效
            }
        });
        botton_navi_view.getMenu().getItem(2).setChecked(true);

        botton_viewpager = (ViewPager) findViewById(R.id.botton_viewpager);
        botton_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    botton_navi_view.getMenu().getItem(0).setChecked(false);
                }
                botton_navi_view.getMenu().getItem(position).setChecked(true);
                menuItem = botton_navi_view.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // 如果想禁止滑动，可以把下面的代码取消注释
        botton_viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        adapter.addFragment(BottonNavigationViewFragment.newInstance("拨号"));
        adapter.addFragment(BottonNavigationViewFragment.newInstance("信息"));
        adapter.addFragment(BottonNavigationViewFragment.newInstance("联系人"));
        adapter.addFragment(BottonNavigationViewFragment.newInstance("查询"));
        adapter.addFragment(BottonNavigationViewFragment.newInstance("我的"));
        botton_viewpager.setAdapter(adapter);

    }

    class ViewpagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> list = new ArrayList<>();

        public ViewpagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        public void addFragment(Fragment fragment) {
            list.add(fragment);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }
}
