package com.huabiao.aoiin.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.ui.adapter.SearchHistoryAdapter;
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
 * @date 2017-06-07 18:44
 * @description 查询商标页面
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener {
    //titlebar
    @Bind(R.id.search_back_iv)
    ImageView search_back_iv;

    @Bind(R.id.search_et)
    EditText search_et;
    @Bind(R.id.search_do_tv)
    TextView search_do_tv;//查询按钮
    @Bind(R.id.history_search_none_tv)
    TextView history_search_none_tv;//暂无
    @Bind(R.id.history_search_clear_tv)
    TextView history_search_clear_tv;//清空

    @Bind(R.id.history_search_lv)
    ListView history_search_lv;
    private List<String> historyList;
    private SearchHistoryAdapter historyAdapter;
    private String historyString;// 用于存储历史纪录的字符串
    private SPUtils sp;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        // 把软键盘上的回车键改为搜索,布局中android:imeOptions="actionSearch"
        search_et.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键【回车键改为了搜索键】
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    getActivity().getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    // 进行搜索操作
                    getSearch();
                }
                return false;
            }
        });
        // 获取历史记录
        historyList = new ArrayList<>();
        sp = new SPUtils("searchtrademark");//搜索商标
        initHistory();
        historyAdapter = new SearchHistoryAdapter(getContext(), historyList);
        history_search_lv.setAdapter(historyAdapter);
        //监听点击事件
        setOnClick();
    }

    private void initHistory() {
        historyString = sp.getString("historylist", "");
        ALog.i("---本地搜索历史-->", historyString);
        if (historyString.length() != 0) {
            stringToList(historyString);
            history_search_clear_tv.setVisibility(View.VISIBLE);
            history_search_none_tv.setVisibility(View.GONE);
        } else {
            history_search_clear_tv.setVisibility(View.GONE);
            history_search_none_tv.setVisibility(View.VISIBLE);
        }
    }

    private void setOnClick() {
        search_do_tv.setOnClickListener(this);
        history_search_clear_tv.setOnClickListener(this);
        search_back_iv.setOnClickListener(this);
        history_search_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                putString(historyList.get(position));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initHistory();
        if (historyAdapter != null) {
            historyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_back_iv:
                ClickUtil.onBackClick();
                break;
            case R.id.search_do_tv:
                getSearch();
                break;
            case R.id.history_search_clear_tv:
                clearHistory();
                break;
        }
    }

    /**
     * 进行查询并将查询内容写入历史记录中
     */
    private void getSearch() {
        KeyboardUtils.hideSoftInput(getActivity());
        String etString = search_et.getText().toString();
        if (!TextUtils.isEmpty(etString)) {
            putString(etString);
            showToast("查询" + etString);
            ClickUtil.onBackClick();
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
        history_search_clear_tv.setVisibility(View.GONE);
        history_search_none_tv.setVisibility(View.VISIBLE);
        showToast("清除成功");
//            }
//        };
//        new NewViewTools(TradingHallSearchActivity.this).dia_log(
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
        return R.layout.search_layout;
    }

}
