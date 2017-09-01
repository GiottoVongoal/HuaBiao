package com.huabiao.aoiin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationBean;
import com.huabiao.aoiin.bean.ScreenBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.ScreenAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Aoiin-9 on 2017/8/30.
 * 查询结果筛选数据显示
 */

public class SearchResultScreenActivity extends BaseActivity {
    private ScreenAdapter adapter;
    @Bind(R.id.exlv)
    ExpandableListView exlv;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("筛选");
        setBackEnable();

        SearchModel.getSelectClassificationList(this, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    ScreenBean bean = (ScreenBean) mData;
                    final List<ScreenBean.ScreenlistBean> list = bean.getScreenlist();
                    adapter = new ScreenAdapter(SearchResultScreenActivity.this, list);
                    exlv.setAdapter(adapter);
                    exlv.expandGroup(0);//默认展开第一项
                    exlv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                        @Override
                        public void onGroupExpand(int groupPosition) {
                            for (int i = 0, count = adapter.getGroupCount(); i < count; i++) {
                                if (groupPosition != i) {// 关闭其他分组
                                    exlv.collapseGroup(i);
                                }
                            }
                        }
                    });
                    exlv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                        @Override
                        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                            resultData(list.get(groupPosition).getSlist().get(childPosition));
                            return true;
                        }
                    });
                }
            }
        });
    }

    private void resultData(ClassificationBean bean) {
        // 设置返回数据
        Bundle bundle = new Bundle();
        bundle.putString("id", bean.getClassificationid());
        bundle.putString("name", bean.getClassificationname());
        Intent intent = new Intent();
        intent.putExtras(bundle);
        // 返回intent
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public int getContentLayout() {
        return R.layout.item_root_hintpopupwindow;
    }
}
