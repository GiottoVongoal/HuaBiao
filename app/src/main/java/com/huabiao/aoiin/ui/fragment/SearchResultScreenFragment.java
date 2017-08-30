package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ScreenBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.adapter.ScreenAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Aoiin-9 on 2017/8/30.
 * 查询结果筛选数据
 */

public class SearchResultScreenFragment extends BaseFragment {
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

        SearchModel.getSelectClassificationList(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    ScreenBean bean = (ScreenBean) mData;
                    List<ScreenBean.ScreenlistBean> list = bean.getScreenlist();
                    adapter = new ScreenAdapter(getContext(), list);
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
                }
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.item_root_hintpopupwindow;
    }
}
