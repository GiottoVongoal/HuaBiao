package com.ywy.mylibs.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.blankj.ALog;
import com.ywy.mylibs.R;

/**
 * Created by yangweiyi on 16/6/7.
 */
public class BindFragmentAct extends BaseActivity {

    private String className;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment fragment;
    private Bundle bundle;


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Intent intent = getIntent();
        className = intent.getStringExtra("className");
        if (intent.getExtras() != null) {
//            resId = intent.getExtras().getInt("resId");
            bundle = intent.getExtras();
        }


    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        fragment = transformClass(className);
        if (bundle != null)
            fragment.setArguments(bundle);
        ft.replace(R.id.fl_bind_fragment, fragment);
        ft.commit();
    }


    private Fragment transformClass(String className) {
        Fragment fragment = null;
        try {
            Class fragmentClass = Class.forName(className);
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (fragment == null) {
                fragment = new Fragment();
            }
        }
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ALog.i("BindFragmentAct onResume ");
        fragment.setUserVisibleHint(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        ALog.i("BindFragmentAct", "BindFragmentAct onPause ");
        fragment.setUserVisibleHint(false);
    }

    @Override
    public int getContentLayout() {
        return R.layout.act_bind_fragment;
    }


}
