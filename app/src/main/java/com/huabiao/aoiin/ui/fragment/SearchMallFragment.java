package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.ui.adapter.SearchHistoryAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ToSearchMallPageEvent;
import com.squareup.otto.Produce;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.ClickUtil;
import com.ywy.mylibs.utils.KeyboardUtils;
import com.ywy.mylibs.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-09-01 15:05
 * @description 首页点击查询跳转的页面
 */
public class SearchMallFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.search_mall_back_iv)
    ImageView back_iv;
    @Bind(R.id.search_mall_search_et)
    EditText search_et;
    @Bind(R.id.search_mall_search_tv)
    TextView search_tv;
    @Bind(R.id.search_mall_history_search_rv)
    RecyclerView history_rv;
    @Bind(R.id.search_mall_history_search_none_tv)
    TextView history_none_tv;
    @Bind(R.id.search_mall_history_search_clear_tv)
    TextView history_clear_tv;

    private List<String> historyList;
    private SearchHistoryAdapter historyAdapter;
    private String historyString;// 用于存储历史纪录的字符串
    private SPUtils sp;

    @Override
    public void bindView(Bundle savedInstanceState) {
        // 把软键盘上的回车键改为搜索,布局中android:imeOptions="actionSearch"
        search_et.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键【回车键改为了搜索键】
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    // 进行搜索操作
                    String etString = search_et.getText().toString().trim();
                    getSearch(etString);
                }
                return false;
            }
        });
        // 获取历史记录
        historyList = new ArrayList<>();
        sp = new SPUtils("searchtrademark");//搜索商标
        initHistory();
        history_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        historyAdapter = new SearchHistoryAdapter(getContext(), historyList);
        history_rv.setAdapter(historyAdapter);
        //监听点击事件
        setOnClick();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_mall_back_iv:
                ClickUtil.onBackClick();
                break;
            case R.id.search_mall_search_tv:
                String etString = search_et.getText().toString().trim();
                getSearch(etString);
                break;
            case R.id.search_mall_history_search_clear_tv:
                clearHistory();
                break;
        }
    }

    private void initHistory() {
        historyString = sp.getString("historylist", "");
        ALog.i("---本地搜索历史-->", historyString);
        if (historyString.length() != 0) {
            stringToList(historyString);
            history_clear_tv.setVisibility(View.VISIBLE);
            history_none_tv.setVisibility(View.GONE);
        } else {
            history_clear_tv.setVisibility(View.GONE);
            history_none_tv.setVisibility(View.VISIBLE);
        }
    }

    private void setOnClick() {
        back_iv.setOnClickListener(this);
        search_tv.setOnClickListener(this);
        history_clear_tv.setOnClickListener(this);
        historyAdapter.setItemClickListener(new InterfaceManager.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                getSearch(historyList.get(position));
            }
        });
    }

    /**
     * 产生事件
     *
     * @return
     */
    @Produce
    public ToSearchMallPageEvent toSearchMall(int index,String searchString) {
        return new ToSearchMallPageEvent(index,searchString);
    }

    /**
     * 进行查询并将查询内容写入历史记录中
     */
    private void getSearch(String searchString) {
        KeyboardUtils.hideSoftInput(getActivity());
        if (!TextUtils.isEmpty(searchString)) {
            putString(searchString);
            getActivity().finish();
            AppBus.getInstance().post(toSearchMall(1,searchString));
        } else {
            showToast("请输入搜索内容");
        }
    }

    /**
     * 存储新的搜索历史(去掉重复的)
     *
     * @param putString 插入的字符串
     */
    private void putString(String putString) {
        String string = sp.getString("historylist", "");
        String replaceString = putString + ",";
        if (string.indexOf(replaceString) != -1) {
            string = string.replaceAll(replaceString, "");
            putString = replaceString + string;
            sp.put("historylist", putString);
        } else {
            putString = putString + "," + sp.getString("historylist", "");
            sp.put("historylist", putString);
        }
    }

    /**
     * 弹出框提示是否清除历史搜索记录
     */
    private void clearHistory() {
//        OnClickListener off = new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        };
//        OnClickListener on = new OnClickListener() {
//            @Override
//            public void onClick(View v) {
        sp.put("historylist", "");
        historyList.clear();
        historyAdapter.notifyDataSetChanged();
        history_clear_tv.setVisibility(View.GONE);
        history_none_tv.setVisibility(View.VISIBLE);
        showToast("清除成功");
//            }
//        };
//        new NewViewTools(TradingHallSearchActivity.getContext()).dia_log(
//                new String[]{"确定清除历史搜索记录?"}, "提示", off, on, new String[]{
//                        "取消", "确定"});
    }

    private void stringToList(String string) {
        historyList.clear();
        String[] myStrings = string.split(",");
        for (int i = 0; i < myStrings.length; i++) {
            historyList.add(myStrings[i]);
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.search_mall_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
