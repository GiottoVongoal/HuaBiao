package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.tools.AnimatorUtils;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ToSearchMallPageEvent;
import com.huabiao.aoiin.wedgit.MultipleTextViewGroup;
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
public class SearchMallFragment extends BaseFragment implements View.OnClickListener, MultipleTextViewGroup.OnMultipleTVItemClickListener {
    @Bind(R.id.search_mall_search_et)
    EditText search_et;
    @Bind(R.id.search_mall_search_delete_iv)
    ImageView search_delete_iv;
    @Bind(R.id.search_mall_search_tv)
    TextView search_tv;
    @Bind(R.id.search_mall_history_search_tv_group)
    MultipleTextViewGroup history_tv_group;
    @Bind(R.id.search_mall_history_search_clear_tv)
    TextView history_clear_tv;
    @Bind(R.id.search_mall_history_search_nothing_tv)
    TextView history_nothing_tv;

    private List<String> historyList;
    private String historyString;// 用于存储历史纪录的字符串
    private SPUtils sp;

    private AnimatorUtils animatorUtils;

    @Override
    public void bindView(Bundle savedInstanceState) {
        // 把软键盘上的回车键改为搜索,布局中android:imeOptions="actionSearch"
        search_et.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键【回车键改为了搜索键】
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    // 进行搜索操作
                    String etString = search_et.getText().toString().trim();
                    gotoSearch(etString);
                }
                return false;
            }
        });

        search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!TextUtils.isEmpty(s)) {
                    search_tv.setText("搜索");
                    search_delete_iv.setVisibility(View.VISIBLE);
                } else {
                    search_tv.setText("取消");
                    search_delete_iv.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (search_et.getText().toString().trim().length() > 10) {
                    String search = search_et.getText().toString().trim();
                    search_et.setText(search.substring(0, 10));
                    search_et.setSelection(10);
                    showToast("最多只能10个字哦!");
                }
            }
        });
        search_delete_iv.setOnClickListener(this);

        // 获取历史记录
        historyList = new ArrayList<>();
        sp = new SPUtils("searchtrademark");//搜索商标
        initHistory();
        animatorUtils = new AnimatorUtils();

        history_tv_group.setTextViews(historyList);
        history_tv_group.setOnMultipleTVItemClickListener(this);

        //监听点击事件
        setOnClick();
    }

    /**
     * 进行查询并将查询内容写入历史记录中
     */
    private void gotoSearch(String searchString) {
        KeyboardUtils.hideSoftInput(getActivity());
        if (!TextUtils.isEmpty(searchString)) {
            if (searchString.length() <= 1) {
                showToast("最少也要2个字哦!");
                return;
            }
            if (searchString.length() > 10) {
                return;
            }
            putString(searchString);
            getActivity().finish();
            AppBus.getInstance().post(toSearchMall(1, searchString));
        } else {
            showToast("请输入搜索内容");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_mall_search_tv:
                String etString = search_et.getText().toString().trim();
                if (!TextUtils.isEmpty(etString)) {
                    gotoSearch(etString);
                } else {
                    KeyboardUtils.hideSoftInput(getActivity());
                    getActivity().finish();
                }
                break;
            case R.id.search_mall_history_search_clear_tv:
                clearHistory();
                break;
            case R.id.search_mall_search_delete_iv:
                search_et.setText("");
                break;
        }
    }

    @Override
    public void onMultipleTVItemClick(View view, int position) {
        gotoSearch(historyList.get(position));
    }

    private void setOnClick() {
        search_tv.setOnClickListener(this);
        history_clear_tv.setOnClickListener(this);
    }

    @Produce
    public ToSearchMallPageEvent toSearchMall(int index, String searchString) {
        return new ToSearchMallPageEvent(index, searchString);
    }

    private void initHistory() {
        historyString = sp.getString("historylist", "");
        ALog.i("---本地搜索历史-->", historyString);
        if (historyString.length() != 0) {
            stringToList(historyString);
            history_clear_tv.setVisibility(View.VISIBLE);
            history_nothing_tv.setVisibility(View.GONE);
        } else {
            history_clear_tv.setVisibility(View.GONE);
            history_nothing_tv.setVisibility(View.VISIBLE);
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
     * 清除历史搜索记录
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
//        sp.put("historylist", "");
        historyList.clear();
        history_clear_tv.setVisibility(View.GONE);
        history_nothing_tv.setVisibility(View.VISIBLE);
        animatorUtils.closeHiddenView(history_tv_group);// 关闭布局
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
