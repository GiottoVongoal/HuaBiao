package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.ClickUtil;
import com.ywy.mylibs.utils.JumpUtils;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-06-07 18:44
 * @description Tab下的查询商标页面
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.search_tradename_tl)
    TextInputLayout search_tradename_tl;//商标名
    @Bind(R.id.search_goodsname_tl)
    TextInputLayout search_goodsname_tl;//商品名
    @Bind(R.id.search_tv)
    TextView search_tv;//查询按钮

    String tradename = "", goodsname = "";

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("查询");
        setBackEnable();

        search_tradename_tl.getEditText().setText("海飞丝");
        search_goodsname_tl.getEditText().setText("洗发水");
        search_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_tv:
                tradename = search_tradename_tl.getEditText().getText().toString();
                goodsname = search_goodsname_tl.getEditText().getText().toString();
                if (TextUtils.isEmpty(tradename)) {
                    showToast("请输入商标名");
                    return;
                }
                if (TextUtils.isEmpty(goodsname)) {
                    showToast("请输入商品名");
                    return;
                }
                startSearch();
                break;
        }
    }

    private void startSearch() {
        Bundle bundle = new Bundle();
        bundle.putString("tradename", tradename);
        bundle.putString("goodsname", goodsname);
        JumpUtils.startFragmentByName(getContext(), SearchResultFragment.class, bundle);
    }

    @Override
    public int getContentLayout() {
        return R.layout.search_layout;
    }

}
