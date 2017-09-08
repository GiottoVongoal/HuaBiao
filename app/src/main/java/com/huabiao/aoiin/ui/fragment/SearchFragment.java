package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.tools.ViewTools;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.JumpUtils;
import com.ywy.mylibs.utils.KeyboardUtils;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-06-07 18:44
 * @description 查询商标输入页面
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener {
    //商品名
    @Bind(R.id.search_goodsname_rl)
    RelativeLayout search_goodsname_rl;
    @Bind(R.id.search_goodsname_tl)
    TextInputLayout search_goodsname_tl;
    //商标名
    @Bind(R.id.search_tradename_rl)
    RelativeLayout search_tradename_rl;
    @Bind(R.id.search_tradename_tl)
    TextInputLayout search_tradename_tl;
    @Bind(R.id.search_tradename_delete_iv)
    ImageView search_tradename_delete_iv;

    @Bind(R.id.search_goodsname_delete_iv)
    ImageView search_goodsname_delete_iv;
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

//        search_tradename_tl.getEditText().setText("海飞丝");
//        search_goodsname_tl.getEditText().setText("洗发水");
        search_tv.setOnClickListener(this);

        ViewTools.setEdittext(search_tradename_tl.getEditText(), search_tradename_delete_iv
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        search_tradename_tl.getEditText().setText("");
                    }
                });
        ViewTools.setEdittext(search_goodsname_tl.getEditText(), search_goodsname_delete_iv
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        search_goodsname_tl.getEditText().setText("");
                    }
                });

        search_goodsname_rl.setOnClickListener(this);
        search_tradename_rl.setOnClickListener(this);
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
            case R.id.search_goodsname_rl:
                KeyboardUtils.showSoftInput(getActivity(),search_goodsname_tl.getEditText());
                break;
            case R.id.search_tradename_rl:
                KeyboardUtils.showSoftInput(getActivity(),search_tradename_tl.getEditText());
                break;
        }
    }

    private void startSearch() {
        Bundle bundle = new Bundle();
        bundle.putString("tradename", tradename);
        bundle.putString("goodsname", goodsname);
        JumpUtils.startActivity(getContext(), SearchResultActivity.class, bundle);
    }

    @Override
    public int getContentLayout() {
        return R.layout.search_layout;
    }

}
