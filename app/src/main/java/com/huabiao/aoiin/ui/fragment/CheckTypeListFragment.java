package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CheckTypeResult;
import com.huabiao.aoiin.bean.ClassificationItemBean;
import com.huabiao.aoiin.bean.ClassificationListBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.selecttool.AddressSelector;
import com.huabiao.aoiin.selecttool.DataProvider;
import com.huabiao.aoiin.selecttool.SelectedListener;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.utils.ClickUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.huabiao.aoiin.selecttool.AddressSelector.INDEX_INVALID;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-10 13:19
 * @description 查询结果未注册中选择分类页面
 */

public class CheckTypeListFragment extends BaseFragment {

    @Bind(R.id.check_type_list_tradename)
    TextView tradename;
    @Bind(R.id.check_type_list_classificationname)
    TextView classificationname;
    @Bind(R.id.check_type_list_confirm)
    TextView confirm;

    @Bind(R.id.check_type_list_fl)
    FrameLayout frameLayout;
    int deep = 2;
    AddressSelector selector;

    private int type;//测试数据变化使用

    private CheckTypeResult checkTypeResult;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("服务小项");
        setBackEnable();
        Bundle bundle = getActivity().getIntent().getExtras();
        tradename.setText(bundle.getString("tradename"));
        classificationname.setText(bundle.getString("classificationname"));
        checkTypeResult = CheckTypeResult.getInstance(deep);
        checkTypeResult.setChange(false);

        type = bundle.getInt("type");
        getJsonObj(type == 1 ? "classificationlist01.json" : "classificationlist02.json");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getResult();
            }
        });
    }

    private void getResult() {
        if (selector != null) {
            selector.callbackInternal(new SelectedListener() {
                @Override
                public void onAddressSelected(List<List<ClassificationItemBean>> selectAbles) {
                    if (selectAbles.get(deep - 1).size() > 0) {
                        ALog.i("selectAbles = " + selectAbles);
                        String string = "";
                        for (int i = 0; i < selectAbles.size(); i++) {
                            for (int j = 0; j < selectAbles.get(i).size(); j++) {
                                ClassificationItemBean resultBean = selectAbles.get(i).get(j);
                                string += resultBean.getClassificationname() + ",";
                            }
                        }
                        checkTypeResult.setSelectList(selectAbles);
                        checkTypeResult.setChange(true);
                        ALog.i("result = " + string);
                        ClickUtil.onBackClick();
                    } else {
                        showToast("请选择小项");
                    }
                }
            });
        }
    }

    private void getJsonObj(String string) {
        SearchModel.getClassificationResult(getContext(), string, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    ClassificationListBean bean = (ClassificationListBean) mData;
                    List<ClassificationItemBean> list = bean.getClassificationlist();
                    show(list);
                }
            }
        });
    }

    private void show(final List<ClassificationItemBean> list) {
        selector = new AddressSelector(getContext(), deep);
        selector.setDataProvider(list, new DataProvider() {
            @Override
            public void provideData(int currentDeep, DataReceiver receiver) {
                //根据tab的深度和前一项选择的id，获取下一级菜单项
                receiver.send();
            }
        });
        frameLayout.addView(selector.getView());
//        BottomDialog dialog = new BottomDialog(getContext());
//        dialog.init(getContext(), selector);
//        dialog.show();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.check_type_list_layout;
    }
}
